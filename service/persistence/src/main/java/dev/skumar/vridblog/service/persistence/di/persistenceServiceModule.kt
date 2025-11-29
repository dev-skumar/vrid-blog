package dev.skumar.vridblog.service.persistence.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.skumar.vridblog.feature.blog.repository.local.BlogPostRepository
import dev.skumar.vridblog.service.persistence.db.VridBlogDatabase
import dev.skumar.vridblog.service.persistence.mapper.BlogPostEntityMapper
import dev.skumar.vridblog.service.persistence.repository.BlogPostRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module


val persistenceServiceModule = module {


    single<SqlDriver> {

        val context: Context = get()

        AndroidSqliteDriver(
            schema = VridBlogDatabase.Schema,
            context = context,
            name = "vridblog.db"
        )

    }



    single<VridBlogDatabase> {

        val driver: SqlDriver = get()

        VridBlogDatabase(driver)

    }



    single<BlogPostEntityMapper> {
        BlogPostEntityMapper()
    }



    single<BlogPostRepository> {
        BlogPostRepositoryImpl(get(), get(), get(named("IoDispatcher")))
    }


}