package com.benjaminearley.githubdemo.data

import android.content.SharedPreferences
import com.benjaminearley.githubdemo.base.BaseActivityScope
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PrefModule {

    @Provides
    @BaseActivityScope
    fun provideRxPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences =
        RxSharedPreferences.create(sharedPreferences)
}
