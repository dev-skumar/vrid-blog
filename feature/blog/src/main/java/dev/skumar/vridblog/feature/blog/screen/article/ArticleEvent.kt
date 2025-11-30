package dev.skumar.vridblog.feature.blog.screen.article


sealed class ArticleEvent {

    data class LoadBlogPostById(val id: Long): ArticleEvent()

    data class ToggleArticleLoadingState(val newValue: Boolean): ArticleEvent()

}