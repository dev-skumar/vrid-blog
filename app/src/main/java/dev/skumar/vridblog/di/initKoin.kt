package dev.skumar.vridblog.di

import dev.skumar.vridblog.feature.blog.di.blogFeatureModule
import dev.skumar.vridblog.service.networking.di.networkingServiceModule
import dev.skumar.vridblog.service.persistence.di.persistenceServiceModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dispatchersModule,
            appLevelModule,
            networkingServiceModule,
            persistenceServiceModule,
            blogFeatureModule
        )
    }
}