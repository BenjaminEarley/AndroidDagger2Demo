package com.benjaminearley.githubdemo.base

import com.benjaminearley.githubdemo.app.AppComponent
import com.benjaminearley.githubdemo.data.PrefModule
import com.benjaminearley.githubdemo.mixin.MixinModule
import com.benjaminearley.githubdemo.network.GitHubModule
import com.benjaminearley.githubdemo.ui.home.HomeActivity
import dagger.Component

@BaseActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(GitHubModule::class, PrefModule::class, MixinModule::class))
interface BaseActivityComponent {
    fun inject(activity: HomeActivity)
}
