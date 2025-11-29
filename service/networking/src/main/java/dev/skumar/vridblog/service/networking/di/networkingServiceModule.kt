package dev.skumar.vridblog.service.networking.di

import dev.skumar.vridblog.core.domain.constants.NetworkConfig
import dev.skumar.vridblog.feature.blog.repository.remote.BlogPostApiService
import dev.skumar.vridblog.service.networking.api.BlogPostApiServiceImpl
import dev.skumar.vridblog.service.networking.factory.HttpClientFactory
import dev.skumar.vridblog.service.networking.mappers.BlogPostDtoMapper
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module


val networkingServiceModule = module {

    single<HttpClient> {
        HttpClientFactory.createHttpClient(
            baseUrl = NetworkConfig.BASE_URL,
            coroutineDispatcher = get(named("IoDispatcher"))
        )
    }


    single<BlogPostDtoMapper> {
        BlogPostDtoMapper()
    }


    single<BlogPostApiService> {
        BlogPostApiServiceImpl(get(), get())
    }


}