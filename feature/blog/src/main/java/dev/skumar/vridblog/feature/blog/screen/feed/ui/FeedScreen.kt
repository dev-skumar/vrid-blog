package dev.skumar.vridblog.feature.blog.screen.feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiData
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.EmptyFeedsScreen
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.FeedLoadingScreen
import dev.skumar.vridblog.feature.blog.screen.feed.ui.componets.LoadedFeedScreen


@Composable
fun FeedScreen(
    uiState: FeedUiState,
    uiData: FeedUiData,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
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