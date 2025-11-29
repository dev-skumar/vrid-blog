package dev.skumar.vridblog.service.networking.api

import dev.skumar.vridblog.core.domain.exceptions.NetworkException
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.repository.remote.BlogPostApiService
import dev.skumar.vridblog.service.networking.dto.blog.BlogPostDto
import dev.skumar.vridblog.service.networking.mappers.BlogPostDtoMapper
import dev.skumar.vridblog.service.networking.routes.RouteV2
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode


class BlogPostApiServiceImpl(
    private val mapper: BlogPostDtoMapper,
    private val client: HttpClient
): BlogPostApiService {


    override suspend fun loadBlogPosts(page: Int): List<BlogPost> {
        return try {

            val response = client.get(RouteV2.LoadPosts(page = page))

            val posts =  when(response.status) {
                HttpStatusCode.OK -> response.body<List<BlogPostDto>>()
                HttpStatusCode.InternalServerError -> throw NetworkException.InternalServerException(response.bodyAsText())
                else -> throw NetworkException.UnknownNetworkException(statusCode = response.status.value, msg = response.bodyAsText())
            }

            posts.map { mapper.mapFromDto(it) }

        } catch (e: Exception) {
            throw e
        }
    }


}