package com.benjaminearley.githubdemo.app

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
}
