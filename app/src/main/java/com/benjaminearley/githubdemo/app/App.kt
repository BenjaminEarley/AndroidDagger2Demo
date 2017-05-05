package com.benjaminearley.githubdemo.app

import android.app.Application
import com.benjaminearley.githubdemo.BuildConfig
import com.benjaminearley.githubdemo.log.ProductionTree
import com.benjaminearley.githubdemo.network.NetModule
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject

class App : Application() {

    companion object {
        lateinit var instance: App
            @Synchronized get
            private set
        lateinit var appComponent: AppComponent
            private set
    }

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()

        instance = this

        Stetho.initializeWithDefaults(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(BuildConfig.github_base_url))
            .build()

        if (BuildConfig.FLAVOR == "release") {
            Timber.plant(ProductionTree())
        } else {
            Timber.plant(Timber.DebugTree())
        }

        appComponent.inject(this)

        Glide.get(this).register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
    }
}
