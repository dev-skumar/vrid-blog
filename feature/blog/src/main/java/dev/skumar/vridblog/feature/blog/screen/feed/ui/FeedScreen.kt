package dev.skumar.vridblog.feature.blog.screen.feed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiData
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.EmptyFeedsScreen
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.FeedLoadingScreen
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.FeedScreenTopAppBar
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.LoadedFeedScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    uiState: FeedUiState,
    uiData: FeedUiData,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
) {

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
                .navigationBarsPadding()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {

                FeedScreenTopAppBar(
                    posts = uiData.posts,
                    uiState = uiState,
                    processEvent = processEvent
                )

            }

        ) { innerPadding ->

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                when(uiState.isLoading && uiData.posts == null) {

                    true -> {
                        FeedLoadingScreen()
                    }

                    false -> {

                        val posts = uiData.posts!!

                        if (posts.isEmpty()) {

                            EmptyFeedsScreen(
                                processEvent = processEvent
                            )

                        } else {

                            LoadedFeedScreen(
                                uiState = uiState,
                                posts = posts,
                                processEvent = processEvent
                            )

                        }

                    }

                }

            }

        }
    }
}