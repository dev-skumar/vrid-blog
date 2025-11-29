package dev.skumar.vridblog.service.networking.dto.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogPostDto(
    val id: Long,
    @SerialName("jetpack_featured_media_url")
    val featuredMediaUrl: String,
    val title: Title,
    val excerpt: Excerpt,
    val date: String,
    val link: String,
    val content: Content
)