package dev.skumar.vridblog.feature.blog.screen.article


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.skumar.vridblog.core.domain.error.ErrorController
import dev.skumar.vridblog.core.domain.result.Result
import dev.skumar.vridblog.feature.blog.usecases.BlogPostUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ArticleViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val errorController: ErrorController,
    private val useCases: BlogPostUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()


    private val _uiData = MutableStateFlow(ArticleUiData())
    val uiData: StateFlow<ArticleUiData> = _uiData
        .onStart {
            // no initial data loading
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ArticleUiData()
        )


    fun processEvent(event: ArticleEvent) {
        viewModelScope.launch {
            when (event) {
                is ArticleEvent.LoadBlogPostById -> {
                    loadBlogPostById(event.id)
                }
            }
        }
    }



    private suspend fun loadBlogPostById(id: Long) {
        useCases.getBlogPostById(id).onEach { result ->
            when(result) {

                Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Result.Success -> {
                    _uiData.update { it.copy(post = result.data) }
                    _uiState.update { it.copy(isLoading = false) }
                }

                is Result.Error -> {

                    val errorDialog = result.info.getErrorDialog {
                        viewModelScope.launch {
                            errorController.closeError()
                            _uiState.update { it.copy(shouldExit = true) }
                        }
                    }

                    errorController.displayError(errorDialog)

                }

            }
        }.collect()
    }

}