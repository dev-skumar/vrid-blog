package dev.skumar.vridblog.feature.blog.di

import androidx.lifecycle.SavedStateHandle
import dev.skumar.vridblog.feature.blog.screen.feed.FeedViewModel
import dev.skumar.vridblog.feature.blog.usecases.BlogPostUseCases
import dev.skumar.vridblog.feature.blog.usecases.GetAllBlogPosts
import dev.skumar.vridblog.feature.blog.usecases.GetBlogPostById
import dev.skumar.vridblog.feature.blog.usecases.LoadBlogPostsForPage
import dev.skumar.vridblog.feature.blog.usecases.ResyncPosts
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val blogFeatureModule = module {


    single<BlogPostUseCases> {
        BlogPostUseCases(
            getAllBlogPosts = GetAllBlogPosts(get()),
            getBlogPostById = GetBlogPostById(get()),
            loadBlogPostsForPage = LoadBlogPostsForPage(get(), get(), get()),
            resyncPosts = ResyncPosts(get(), get(), get())
        )
    }


    viewModel<FeedViewModel> { (handle: SavedStateHandle) ->
        FeedViewModel(handle, get(), get())
    }


}