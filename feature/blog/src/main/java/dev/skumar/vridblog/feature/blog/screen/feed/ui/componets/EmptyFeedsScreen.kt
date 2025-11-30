package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import dev.skumar.vridblog.core.presentation.theme.buttonText
import dev.skumar.vridblog.core.presentation.theme.l3
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState


@Composable
fun EmptyFeedsScreen(
    uiState: FeedUiState,
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    when(uiState.isDownloadingPosts) {

        true -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LoadingPostsIndicator()
            }

        }

        false -> {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.9f)
                ) {
                    Text(
                        text = "There are no blog posts in database.\n\nClick the Download Posts button to load posts.",
                        style = MaterialTheme.typography.l3,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                ) {
                    Button(
                        onClick = {
                            processEvent(FeedEvent.DownloadBlogPost(1))
                        },
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(
                            text = "Download Posts",
                            style = MaterialTheme.typography.buttonText
                        )
                    }
                }

            }

        }

    }
}