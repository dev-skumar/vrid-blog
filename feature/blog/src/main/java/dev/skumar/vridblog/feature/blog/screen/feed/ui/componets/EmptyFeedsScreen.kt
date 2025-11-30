package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent


@Composable
fun EmptyFeedsScreen(
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Text(
        text = "This is temporary empty feed screen.",
        modifier = Modifier
            .fillMaxWidth()
    )

}