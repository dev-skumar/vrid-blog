package dev.skumar.vridblog.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import dev.skumar.vridblog.core.domain.error.ErrorDialog
import dev.skumar.vridblog.navigation.AppNavigation
import dev.skumar.vridblog.navigation.NavigationAction


@Composable
fun BaseAppViewPort(
    errorDialog: ErrorDialog?,
    backStack: List<NavKey>,
    performNavigation: (NavigationAction) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            AppNavigation(
                backStack = backStack,
                performNavigation = performNavigation,
                modifier = Modifier
            )

        }



        if (errorDialog != null) {

            // TODO("Display Error Dialog")

        }

    }

}