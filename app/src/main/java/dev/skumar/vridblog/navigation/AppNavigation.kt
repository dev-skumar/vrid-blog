package dev.skumar.vridblog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dev.skumar.vridblog.core.presentation.navigation.Screen


@Composable
fun AppNavigation(
    backStack: List<NavKey>,
    performNavigation: (NavigationAction) -> Unit,
    modifier: Modifier
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


            }

            entry<Screen.Article> {



            }

        }
    )

}