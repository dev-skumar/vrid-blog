package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skumar.vridblog.core.presentation.theme.l2


@Composable
fun LoadingPostsIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {

        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondary,
            strokeWidth = 5.dp
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Text(
            text = "Loading Posts ...",
            style = MaterialTheme.typography.l2,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}