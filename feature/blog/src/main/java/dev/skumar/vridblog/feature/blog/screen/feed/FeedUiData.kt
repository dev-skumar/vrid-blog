package dev.skumar.vridblog.feature.blog.screen.feed

import dev.skumar.vridblog.feature.blog.model.BlogPost


data class FeedUiData(
    val posts: List<BlogPost>? = null
)