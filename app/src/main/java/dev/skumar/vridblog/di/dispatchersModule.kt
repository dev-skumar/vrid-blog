package dev.skumar.vridblog.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module


val dispatchersModule = module {

    single(named("IoDispatcher")) {
        Dispatchers.IO
    }

    single(named("MainDispatcher")) {
        Dispatchers.Main
    }

    single(named("DefaultDispatcher")) {
        Dispatchers.Default
    }

    single(named("UnconfinedDispatcher")) {
        Dispatchers.Unconfined
    }

}