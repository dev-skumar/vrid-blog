package dev.skumar.vridblog.feature.blog.repository.remote

import dev.skumar.vridblog.feature.blog.model.BlogPost

interface BlogPostApiService {

    suspend fun loadBlogPosts(page: Int): List<BlogPost>

}