package dev.skumar.vridblog.feature.blog.screen.previews

import androidx.compose.runtime.Composable
import dev.skumar.vridblog.core.presentation.annotations.PreviewTheme
import dev.skumar.vridblog.core.presentation.theme.VridBlogTheme
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiData
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiState
import dev.skumar.vridblog.feature.blog.screen.article.ui.ArticleScreen


@PreviewTheme
@Composable
fun LoadingArticleScreenPreview() {
    VridBlogTheme {
        ArticleScreen(
            postId = 1001,
            uiState = ArticleUiState(),
            uiData = ArticleUiData(),
            performNavigation = { },
            processEvent = { }
        )
    }
}
