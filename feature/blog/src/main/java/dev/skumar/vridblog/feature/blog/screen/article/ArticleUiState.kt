package dev.skumar.vridblog.feature.blog.screen.article


data class ArticleUiState(
    val isLoading: Boolean = true,
    val isArticleLoading: Boolean = false,
    val shouldExit: Boolean = false
)