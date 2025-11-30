package dev.skumar.vridblog.di

import androidx.lifecycle.SavedStateHandle
import dev.skumar.vridblog.base.BaseAppViewModel
import dev.skumar.vridblog.core.domain.error.ErrorController
import dev.skumar.vridblog.core.domain.utils.DeviceUtility
import dev.skumar.vridblog.core.presentation.util.DeviceUtilityImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appLevelModule = module {


    single<DeviceUtility> {
        DeviceUtilityImpl(get())
    }


    single<ErrorController> {
        ErrorController()
    }


    viewModel<BaseAppViewModel> { (handle: SavedStateHandle) ->
        BaseAppViewModel(handle, get())
    }


}