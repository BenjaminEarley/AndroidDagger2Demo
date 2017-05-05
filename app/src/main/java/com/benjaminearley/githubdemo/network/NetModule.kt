package com.benjaminearley.githubdemo.network

import com.benjaminearley.githubdemo.app.App
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideStethoInterceptor(app: App): Interceptor {
        Stetho.initializeWithDefaults(app)
        return StethoInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(app: App): Cache {
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(app.cacheDir, cacheSize)
        return cache
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(stethoInterceptor: Interceptor, cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(stethoInterceptor)
        .cache(cache)
        .build()

    @Provides
    @Singleton
    fun provideDateAdapter(): DateAdapter = DateAdapter()

    @Provides
    @Singleton
    fun provideMoshi(dateAdapter: DateAdapter): Moshi = Moshi.Builder()
        .add(dateAdapter)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
        return retrofit
    }
}
