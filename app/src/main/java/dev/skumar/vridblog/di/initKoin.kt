package dev.skumar.vridblog.di

import dev.skumar.vridblog.service.networking.di.networkingServiceModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dispatchersModule,
            appLevelModule,
            networkingServiceModule
        )
    }
}