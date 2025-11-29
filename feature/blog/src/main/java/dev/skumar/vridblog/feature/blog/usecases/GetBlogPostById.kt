package dev.skumar.vridblog.feature.blog.usecases

import dev.skumar.vridblog.core.domain.constants.ErrorMessage
import dev.skumar.vridblog.core.domain.constants.ErrorTitle
import dev.skumar.vridblog.core.domain.exceptions.DatabaseException
import dev.skumar.vridblog.core.domain.result.ErrorInfo
import dev.skumar.vridblog.core.domain.result.Result
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.repository.local.BlogPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetBlogPostById(
    private val repository: BlogPostRepository
) {
    operator fun invoke(id: Long): Flow<Result<BlogPost>> = flow {
        try {
            emit(Result.Loading)

            val post = repository.getBlogPostById(id)
            emit(Result.Success(post))

        } catch (e: DatabaseException.EntityNotFound) {

            emit(Result.Error(ErrorInfo(
                title = ErrorTitle.NOT_FOUND,
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