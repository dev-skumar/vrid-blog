package dev.skumar.vridblog.app

import android.app.Application
import dev.skumar.vridblog.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class VridBlogApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@VridBlogApp)
        }
    }

}