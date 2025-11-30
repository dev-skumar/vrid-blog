package dev.skumar.vridblog.feature.blog.screen.feed


sealed class FeedEvent {

    data class ToggleTopMenuBar(val newValue: Boolean): FeedEvent()

    data object ResyncBlogPosts: FeedEvent()

    data class DownloadBlogPost(val pageNumber: Int): FeedEvent()

}