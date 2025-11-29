package dev.skumar.vridblog.feature.blog.usecases


data class BlogPostUseCases(
    val getAllBlogPosts: GetAllBlogPosts,
    val getBlogPostById: GetBlogPostById,
    val loadBlogPostsForPage: LoadBlogPostsForPage,
    val resyncPosts: ResyncPosts
)