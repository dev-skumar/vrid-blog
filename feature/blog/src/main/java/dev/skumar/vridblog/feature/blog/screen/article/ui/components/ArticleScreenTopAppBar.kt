package dev.skumar.vridblog.feature.blog.screen.article.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.skumar.vridblog.core.presentation.R
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.core.presentation.theme.h3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreenTopAppBar(
    performNavigation: (NavigationAction) -> Unit,
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = "Article",
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {

            IconButton(
                onClick = {
                    performNavigation(NavigationAction.NavigateBackToPreviousScreen)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_24px),
                    contentDescription = "Home icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                )
            }

        }
    )

}