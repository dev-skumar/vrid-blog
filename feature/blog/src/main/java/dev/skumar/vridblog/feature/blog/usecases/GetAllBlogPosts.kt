package dev.skumar.vridblog.feature.blog.usecases

import dev.skumar.vridblog.core.domain.constants.ErrorMessage
import dev.skumar.vridblog.core.domain.constants.ErrorTitle
import dev.skumar.vridblog.core.domain.result.ErrorInfo
import dev.skumar.vridblog.core.domain.result.Result
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.repository.local.BlogPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetAllBlogPosts(
    private val repository: BlogPostRepository
) {
    operator fun invoke(): Flow<Result<List<BlogPost>>> = flow {
        try {
            emit(Result.Loading)

            val posts = repository.getAllBlogPosts()
            emit(Result.Success(posts))

        } catch (e: Exception) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.APPLICATION_ERROR,
                message = e.message ?: ErrorMessage.SOMETHING_WENT_WRONG
            )))
            e.printStackTrace()

        }
    }
}