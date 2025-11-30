package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.skumar.vridblog.core.presentation.R
import dev.skumar.vridblog.core.presentation.theme.h3
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreenTopAppBar(
    posts: List<BlogPost>?,
    uiState: FeedUiState,
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        title = {
            Text(
                text = "Feed",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {

            if (uiState.isDownloadingPosts) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(0.6f)
                )

            } else {

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home_24px),
                        contentDescription = "Home icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                    )
                }

            }

        },
        actions = {

            when(posts == null) {

                true -> {
                    // Don't display vertical more icon when posts is null
                }

                false -> {

                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        val isExpanded = uiState.isTopBarMenuExpanded

                        IconButton(
                            onClick = {
                                processEvent(FeedEvent.ToggleTopMenuBar(true))
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.more_vert_24px),
                                contentDescription = "More Options",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = {
                                processEvent(FeedEvent.ToggleTopMenuBar(false))
                            },
                            containerColor = MaterialTheme.colorScheme.surface
                        ) {

                            if (posts.isEmpty()) {

                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "Download Posts"
                                        )
                                    },
                                    onClick = {
                                        processEvent(FeedEvent.ToggleTopMenuBar(false))
                                        processEvent(FeedEvent.DownloadBlogPost(1))
                                    }
                                )

                            } else {

                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "Resync Blog Posts"
                                        )
                                    },
                                    onClick = {
                                        processEvent(FeedEvent.ToggleTopMenuBar(false))
                                        processEvent(FeedEvent.ResyncBlogPosts)
                                    }
                                )

                            }

                        }
                    }

                }

            }

        }
    )

}