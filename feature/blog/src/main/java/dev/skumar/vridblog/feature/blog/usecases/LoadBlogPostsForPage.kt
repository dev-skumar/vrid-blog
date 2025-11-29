package dev.skumar.vridblog.feature.blog.usecases

import dev.skumar.vridblog.core.domain.constants.ErrorMessage
import dev.skumar.vridblog.core.domain.constants.ErrorTitle
import dev.skumar.vridblog.core.domain.exceptions.NetworkException
import dev.skumar.vridblog.core.domain.result.ErrorInfo
import dev.skumar.vridblog.core.domain.result.Result
import dev.skumar.vridblog.core.domain.utils.DeviceUtility
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.repository.local.BlogPostRepository
import dev.skumar.vridblog.feature.blog.repository.remote.BlogPostApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class LoadBlogPostsForPage(
    private val apiService: BlogPostApiService,
    private val repository: BlogPostRepository,
    private val deviceUtility: DeviceUtility
) {
    operator fun invoke(pageNumber: Int): Flow<Result<List<BlogPost>>> = flow {
        try {
            emit(Result.Loading)

            if (!deviceUtility.isInternetAvailable())
                throw NetworkException.NoInternetException(ErrorMessage.NO_INTERNET)

            val newPosts = apiService.loadBlogPosts(pageNumber)
            newPosts.forEach { repository.insertBlogPost(it) }
            emit(Result.Success(newPosts))

        } catch (e: NetworkException.NoInternetException) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.NO_INTERNET,
                message = e.msg
            )))

        } catch (e: NetworkException.InternalServerException) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.INTERNAL_SERVER_ERROR,
                message = e.msg
            )))

        } catch (e: NetworkException.UnknownNetworkException) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.NETWORK_ERROR,
                message = e.msg
            )))

        } catch (e: Exception) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.APPLICATION_ERROR,
                message = e.message ?: ErrorMessage.SOMETHING_WENT_WRONG
            )))
            e.printStackTrace()

        }
    }
}