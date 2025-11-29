package dev.skumar.vridblog.feature.blog.repository.local

import dev.skumar.vridblog.feature.blog.model.BlogPost


interface BlogPostRepository {

    suspend fun insertBlogPost(post: BlogPost)

    suspend fun getBlogPostById(id: Long): BlogPost

    suspend fun getAllBlogPosts(): List<BlogPost>

    suspend fun resyncBlogPosts(newPosts: List<BlogPost>): List<BlogPost>

}