package com.benjaminearley.githubdemo.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.benjaminearley.githubdemo.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}
