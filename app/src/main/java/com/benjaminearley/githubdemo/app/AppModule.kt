package com.benjaminearley.githubdemo.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun providesApp(): App {
        return app
    }

    @Provides
    @Singleton
    fun providesAppContext(): Context {
        return app.applicationContext
    }
}
