package com.benjaminearley.githubdemo.app

import android.content.SharedPreferences
import com.benjaminearley.githubdemo.data.DataModule
import com.benjaminearley.githubdemo.network.NetModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, DataModule::class))
interface AppComponent {
    fun retrofit(): Retrofit
    fun sharedPreferences(): SharedPreferences
    fun inject(app: App)
}
