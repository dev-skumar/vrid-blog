package dev.skumar.vridblog.service.networking.factory

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json


class HttpClientFactory {

    companion object {

        private const val CONNECTION_TIME_OUT = 10000
        private const val SOCKET_TIME_OUT = 15000

        fun createHttpClient(baseUrl: String, coroutineDispatcher: CoroutineDispatcher): HttpClient {

            return HttpClient(Android) {

                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                        }
                    )
                }


                install(Resources)


                defaultRequest {
                    url(baseUrl)
                }


                engine {

                    connectTimeout = CONNECTION_TIME_OUT
                    socketTimeout = SOCKET_TIME_OUT

                    dispatcher = coroutineDispatcher

                }

            }

        }

    }
}