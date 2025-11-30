package dev.skumar.vridblog.feature.blog.screen.article.ui

import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.viewinterop.AndroidView
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.feature.blog.screen.article.ArticleEvent
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiData
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiState
import dev.skumar.vridblog.feature.blog.screen.article.ui.components.ArticleScreenTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    postId: Long,
    uiState: ArticleUiState,
    uiData: ArticleUiData,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (ArticleEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        processEvent(ArticleEvent.LoadBlogPostById(id = postId))
    }

    LaunchedEffect(uiState.shouldExit) {
        if (uiState.shouldExit) {
            performNavigation(NavigationAction.NavigateBackToPreviousScreen)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {

                ArticleScreenTopAppBar(
                    performNavigation = performNavigation
                )

            }

        ) { innerPadding ->

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                when(uiState.isLoading && uiData.post == null) {

                    true -> {
                        CircularProgressIndicator()
                    }

                    false -> {

                        val post = uiData.post!!

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            AndroidView(
                                factory = { context: Context ->

                                    WebView(context).apply {

                                        settings.javaScriptEnabled = true

                                        webViewClient = object : WebViewClient() {

                                            override fun onPageStarted(
                                                view: WebView?,
                                                url: String?,
                                                favicon: Bitmap?
                                            ) {
                                                super.onPageStarted(view, url, favicon)
                                                processEvent(ArticleEvent.ToggleArticleLoadingState(true))
                                            }

                                            override fun onPageFinished(view: WebView?, url: String?) {
                                                super.onPageFinished(view, url)
                                                processEvent(ArticleEvent.ToggleArticleLoadingState(false))
                                            }
                                        }

                                        loadUrl(post.onlineLink)

                                    }
                                },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(if (uiState.isArticleLoading) 0f else 1f)
                            )


                            if (uiState.isArticleLoading) {
                                CircularProgressIndicator()
                            }

                        }

                    }

                }
            }
        }
    }
}