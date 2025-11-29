package dev.skumar.vridblog.service.networking.dto.blog

import kotlinx.serialization.Serializable

@Serializable
data class Excerpt(
    val rendered: String,
    val protected: Boolean
)