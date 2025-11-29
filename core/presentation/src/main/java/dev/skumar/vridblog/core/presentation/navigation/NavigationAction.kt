package dev.skumar.vridblog.core.presentation.navigation

import androidx.navigation3.runtime.NavKey

sealed class NavigationAction {

    data class NavigateTo(val key: NavKey): NavigationAction()

    data object NavigateBackToPreviousScreen: NavigationAction()

    data object ClearStackAndNavigateToHomeScreen: NavigationAction()

}