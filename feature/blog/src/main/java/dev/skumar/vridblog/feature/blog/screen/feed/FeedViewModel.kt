package dev.skumar.vridblog.feature.blog.screen.feed


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.vridblog.core.domain.error.ErrorController
import dev.skumar.vridblog.core.domain.result.Result
import dev.skumar.vridblog.feature.blog.usecases.BlogPostUseCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch


class FeedViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val errorController: ErrorController,
    private val useCases: BlogPostUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()


    private val _uiData = MutableStateFlow(FeedUiData())
    val uiData: StateFlow<FeedUiData> = _uiData
        .onStart {
            loadData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            FeedUiData()
        )


    private fun loadData() {
        viewModelScope.launch {

            val job1 = launch {
                delay(1500)
                getAllBlogPosts()
            }

            joinAll(job1)

            if (_uiData.value.posts != null) {
                _uiState.update { it.copy(isLoading = false) }
            }

        }
    }


    private fun reloadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            errorController.closeError()
            loadData()
        }
    }


    fun processEvent(event: FeedEvent) {
        viewModelScope.launch {
            when (event) {

                is FeedEvent.ToggleTopMenuBar -> {
                    _uiState.update { it.copy(isTopBarMenuExpanded = event.newValue) }
                }

                is FeedEvent.DownloadBlogPost -> {
                    loadBlogPostsForPage(event.pageNumber)
                }

                FeedEvent.ResyncBlogPosts -> {
                    resyncPosts()
                }

            }
        }
    }



    private suspend fun getAllBlogPosts() {
        useCases.getAllBlogPosts().onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Result.Success -> {
                    _uiData.update { it.copy(posts = result.data) }
                }

                is Result.Error -> {

                    val errorDialog = result.info.getErrorDialog(
                        onOkay = {
                            viewModelScope.launch {
                                errorController.closeError()
                                _uiState.update { it.copy(isLoading = false) }
                                reloadData()
                            }
                        }
                    )

                    errorController.displayError(errorDialog)
                }
            }
        }.collect()
    }



    private suspend fun loadBlogPostsForPage(pageNumber: Int) {
        useCases.loadBlogPostsForPage(pageNumber).onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiState.update { it.copy(isDownloadingPosts = true) }
                }

                is Result.Success -> {
                    _uiState.update { it.copy(isDownloadingPosts = false) }
                    _uiData.update {
                        it.copy(
                            posts = _uiData.value.posts?.plus(result.data)
                        )
                    }
                }

                is Result.Error -> {

                    val errorDialog = result.info.getErrorDialog(
                        enableRetry = true,
                        onRetry = {
                            viewModelScope.launch {
                                errorController.closeError()
                                loadBlogPostsForPage(pageNumber)
                            }
                        },
                        enableOkay = true,
                        onOkay = {
                            viewModelScope.launch {
                                _uiState.update { it.copy(isDownloadingPosts = false) }
                                errorController.closeError()
                            }
                        }
                    )

                    errorController.displayError(errorDialog)

                }

            }
        }.collect()
    }



    private suspend fun resyncPosts() {
        useCases.resyncPosts().onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiData.update { it.copy(posts = null) }
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Result.Success -> {
                    _uiData.update { it.copy(posts = result.data) }
                    _uiState.update { it.copy(isLoading = false) }
                }

                is Result.Error -> {

                    val errorDialog = result.info.getErrorDialog(
                        extraInfo = "",
                        enableRetry = true,
                        onRetry = {
                            viewModelScope.launch {
                                errorController.closeError()
                                resyncPosts()
                            }
                        },
                        enableOkay = true,
                        onOkay = {
                            viewModelScope.launch {
                                errorController.closeError()
                                _uiState.update { it.copy(isLoading = false) }
                            }
                        }
                    )

                    errorController.displayError(errorDialog)
                }

            }
        }.collect()
    }

}