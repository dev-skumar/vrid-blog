package dev.skumar.vridblog.service.persistence.repository

import dev.skumar.vridblog.core.domain.exceptions.DatabaseException
import dev.skumar.vridblog.feature.blog.model.BlogPost
import dev.skumar.vridblog.feature.blog.repository.local.BlogPostRepository
import dev.skumar.vridblog.service.persistence.db.VridBlogDatabase
import dev.skumar.vridblog.service.persistence.mapper.BlogPostEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class BlogPostRepositoryImpl(
    private val db: VridBlogDatabase,
    private val mapper: BlogPostEntityMapper,
    private val dispatcher: CoroutineDispatcher
): BlogPostRepository {

    private val queries = db.blogPostEntityQueries


    override suspend fun insertBlogPost(post: BlogPost) {

        val entity = mapper.mapToEntity(post)

        withContext(dispatcher) {
            queries.transaction {
                queries.insertBlogPostEntity(
                    id = entity.id,
                    featureMediaUrl = entity.featureMediaUrl,
                    title = entity.title,
                    shortDescription = entity.shortDescription,
                    isShortDescriptionProtected = entity.isShortDescriptionProtected,
                    publishedOn = entity.publishedOn,
                    onlineLink = entity.onlineLink,
                    content = entity.content,
                    isContentProtected = entity.isContentProtected
                )
            }
        }
    }


    override suspend fun getBlogPostById(id: Long): BlogPost {
        return withContext(dispatcher) {
            queries.transactionWithResult {

                val entity = queries
                    .getBlogPostEntityById(id)
                    .executeAsOneOrNull()
                    ?: throw DatabaseException.EntityNotFound(
                        msg = "No Blog Post found with id: $id"
                    )

                mapper.mapFromEntity(entity)

            }
        }
    }


    override suspend fun getAllBlogPosts(): List<BlogPost> {
        return withContext(dispatcher) {
            queries.transactionWithResult {
                queries
                    .getAllBlogPostEntities()
                    .executeAsList()
                    .map {
                        mapper.mapFromEntity(it)
                    }
            }
        }
    }


    override suspend fun deleteAllBlogPosts() {
        withContext(dispatcher) {
            queries.transaction {
                queries.deleteAllBlogPostEntities()
            }
        }
    }


}