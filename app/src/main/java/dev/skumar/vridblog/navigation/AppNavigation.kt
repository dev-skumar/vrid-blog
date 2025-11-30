package dev.skumar.vridblog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dev.skumar.vridblog.core.presentation.navigation.NavigationAction
import dev.skumar.vridblog.core.presentation.navigation.Screen
import dev.skumar.vridblog.feature.blog.screen.feed.FeedViewModel
import dev.skumar.vridblog.feature.blog.screen.feed.ui.FeedScreen
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppNavigation(
    backStack: List<NavKey>,
    performNavigation: (NavigationAction) -> Unit,
    modifier: Modifier = Modifier
) {

    NavDisplay(
        backStack = backStack,
        onBack = {  },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

            entry<Screen.Feed> {

                val feedVM = koinViewModel<FeedViewModel>()
                val uiState by feedVM.uiState.collectAsStateWithLifecycle()
                val uiData by feedVM.uiData.collectAsStateWithLifecycle()

                FeedScreen(
                    uiState = uiState,
                    uiData = uiData,
                    performNavigation = performNavigation,
                    processEvent = feedVM::processEvent
                )

            }

            entry<Screen.Article> {



            }

        }
    )

}