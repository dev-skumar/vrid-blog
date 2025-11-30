package dev.skumar.vridblog.feature.blog.screen.previews

import androidx.compose.runtime.Composable
import dev.skumar.vridblog.core.presentation.annotations.PreviewTheme
import dev.skumar.vridblog.core.presentation.theme.VridBlogTheme
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiData
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState
import dev.skumar.vridblog.feature.blog.screen.feed.ui.FeedScreen


@PreviewTheme
@Composable
fun LoadingFeedScreenPreview() {
    VridBlogTheme {
        FeedScreen(
            uiState = FeedUiState(
                isLoading = true
            ),
            uiData = FeedUiData(),
            performNavigation = { },
            processEvent = { }
        )
    }
}



@PreviewTheme
@Composable
fun EmptyBlogPostsFeedScreenPreview() {
    VridBlogTheme {
        FeedScreen(
            uiState = FeedUiState(
                isLoading = false
            ),
            uiData = FeedUiData(
                posts = emptyList()
            ),
            performNavigation = { },
            processEvent = { }
        )
    }
}




@PreviewTheme
@Composable
fun DownloadingPostsOnEmptyScreenPreview() {
    VridBlogTheme {
        FeedScreen(
            uiState = FeedUiState(
                isLoading = false,
                isDownloadingPosts = true
            ),
            uiData = FeedUiData(
                posts = emptyList()
            ),
            performNavigation = { },
            processEvent = { }
        )
    }
}



@PreviewTheme
@Composable
fun LoadedBlogPostsFeedScreenPreview() {
    VridBlogTheme {
        FeedScreen(
            uiState = FeedUiState(
                isLoading = false
            ),
            uiData = FeedUiData(
                posts = testPosts
            ),
            performNavigation = { },
            processEvent = { }
        )
    }
}




