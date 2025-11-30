package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter
import dev.skumar.vridblog.core.presentation.R


@Composable
fun LoadImageFromUrl(
    url: String,
    modifier: Modifier = Modifier
) {

    val painter = rememberAsyncImagePainter(
        model = url,
        placeholder = painterResource(R.drawable.loading_image),
        error = painterResource(R.drawable.not_available)
    )

    Image(
        painter = painter,
        contentDescription = "Feature Image",
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )

}