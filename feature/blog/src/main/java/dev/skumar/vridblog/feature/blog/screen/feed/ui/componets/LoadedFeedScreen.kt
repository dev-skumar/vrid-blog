package dev.skumar.vridblog.feature.blog.screen.feed.ui.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skumar.vridblog.core.presentation.theme.bodyNormal
import dev.skumar.vridblog.core.presentation.theme.buttonText
import dev.skumar.vridblog.core.presentation.theme.l1
import dev.skumar.vridblog.core.presentation.util.parseDate
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.screen.feed.FeedEvent
import dev.skumar.vridblog.feature.blog.screen.feed.FeedUiState


@Composable
fun LoadedFeedScreen(
    uiState: FeedUiState,
    posts: List<BlogPost>,
    processEvent: (FeedEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        items(posts) { blogPost: BlogPost ->

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors().copy(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {

                            LoadImageFromUrl(
                                url = blogPost.featuredMediaUrl
                            )

                        }

                        Spacer(
                            modifier = Modifier
                                .height(24.dp)
                        )

                        Text(
                            text = AnnotatedString.fromHtml(blogPost.title),
                            textAlign = TextAlign.Left,
                            style = MaterialTheme.typography.l1,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(
                            modifier = Modifier
                                .height(16.dp)
                        )

                        Text(
                            text = AnnotatedString.fromHtml(blogPost.shortDescription),
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.bodyNormal,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = parseDate(blogPost.publishedOn),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                fontStyle = FontStyle.Italic
                            )

                            Button(
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer),
                                onClick = {
                                    // TODO(read post)
                                }
                            ) {
                               Text(
                                   text = "Read",
                                   style = MaterialTheme.typography.buttonText
                               )
                            }

                        }

                    }
                }
            }
        }
    }

}