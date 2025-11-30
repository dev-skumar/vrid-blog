package dev.skumar.vridblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.skumar.vridblog.base.BaseAppViewModel
import dev.skumar.vridblog.base.BaseAppViewPort
import dev.skumar.vridblog.core.presentation.theme.VridBlogTheme
import dev.skumar.vridblog.navigation.AppNavigation
import org.koin.compose.viewmodel.koinViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VridBlogTheme {

                val baseAppViewModel = koinViewModel<BaseAppViewModel>()
                val errorDialog by baseAppViewModel.errorDialog.collectAsStateWithLifecycle()

                BaseAppViewPort(
                    appNavigation = {
                        AppNavigation(
                            backStack = baseAppViewModel.backStack,
                            performNavigation = baseAppViewModel::performNavigation
                        )
                    },
                    errorDialog = errorDialog
                )

            }
        }
    }

}