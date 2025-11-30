package dev.skumar.vridblog.core.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed interface Screen : NavKey {


    @Serializable
    data object Feed: Screen


    @Serializable
    data class Article(
        val id: Long
    ): Screen


}