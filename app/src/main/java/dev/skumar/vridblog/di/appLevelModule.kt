package dev.skumar.vridblog.di

import androidx.lifecycle.SavedStateHandle
import dev.skumar.vridblog.base.BaseAppViewModel
import dev.skumar.vridblog.core.domain.error.ErrorController
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appLevelModule = module {

    single<ErrorController> {
        ErrorController()
    }

    viewModel { (handle: SavedStateHandle) ->
        BaseAppViewModel(handle, get())
    }

}