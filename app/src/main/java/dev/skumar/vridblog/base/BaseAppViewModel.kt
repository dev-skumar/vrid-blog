package dev.skumar.vridblog.base

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import dev.skumar.vridblog.core.domain.error.ErrorController
import dev.skumar.vridblog.core.domain.error.ErrorDialog
import dev.skumar.vridblog.core.presentation.navigation.Screen
import dev.skumar.vridblog.navigation.NavigationAction
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class BaseAppViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val errorController: ErrorController
): ViewModel() {


    val errorDialog: StateFlow<ErrorDialog?> = errorController.errorDialog


    //
    // ===========================[ Navigation ]=============================

    private val _backStack = mutableStateListOf<NavKey>()
        .apply {
            this.add(Screen.Feed)
        }

    val backStack: List<NavKey>
        get() = _backStack


    fun performNavigation(action: NavigationAction) {
        viewModelScope.launch {
            when(action) {

                is NavigationAction.NavigateTo -> {
                    _backStack.add(action.key)
                }

                NavigationAction.NavigateBackToPreviousScreen -> {
                    _backStack.removeLastOrNull()
                }

                NavigationAction.ClearStackAndNavigateToHomeScreen -> {
                    _backStack.clear()
                    _backStack.add(Screen.Feed)
                }

            }
        }
    }

    // ======================================================================
}