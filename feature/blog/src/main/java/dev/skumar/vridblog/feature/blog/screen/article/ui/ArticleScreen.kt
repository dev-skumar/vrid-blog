package dev.skumar.vridblog.feature.blog.screen.article.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.feature.blog.screen.article.ArticleEvent
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiData
import dev.skumar.vridblog.feature.blog.screen.article.ArticleUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    postId: Long,
    uiState: ArticleUiState,
    uiData: ArticleUiData,
    performNavigation: (NavigationAction) -> Unit,
    processEvent: (ArticleEvent) -> Unit,
    modifier: Modifier = Modifier
) {

}