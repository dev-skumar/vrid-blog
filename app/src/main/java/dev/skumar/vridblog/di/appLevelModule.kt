package dev.skumar.vridblog.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import dev.skumar.vridblog.base.BaseAppViewModel
import dev.skumar.vridblog.core.domain.error.ErrorController
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appLevelModule = module {


    single<Context> {
        androidContext()
    }


    single<ErrorController> {
        ErrorController()
    }


    viewModel<BaseAppViewModel> { (handle: SavedStateHandle) ->
        BaseAppViewModel(handle, get())
    }


}