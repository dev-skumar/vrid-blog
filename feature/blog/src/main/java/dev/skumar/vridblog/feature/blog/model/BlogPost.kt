package dev.skumar.vridblog.feature.blog.model


data class BlogPost(
    val id: Long,
    val featuredMediaUrl: String,
    val title: String,
    val shortDescription: String,
    val isShortDescriptionProtected: Boolean,
    val publishedOn: String,
    val onlineLink: String,
    val content: String,
    val isContentProtected: Boolean
)