package com.benjaminearley.githubdemo.base

import com.benjaminearley.githubdemo.MainActivity
import com.benjaminearley.githubdemo.app.AppComponent
import com.benjaminearley.githubdemo.data.PrefModule
import com.benjaminearley.githubdemo.mixin.MixinModule
import com.benjaminearley.githubdemo.network.GitHubModule
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(GitHubModule::class, PrefModule::class, MixinModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
