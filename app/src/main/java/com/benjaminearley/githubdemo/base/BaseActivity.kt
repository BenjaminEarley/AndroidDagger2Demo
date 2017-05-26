package com.benjaminearley.githubdemo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.benjaminearley.githubdemo.app.App
import com.benjaminearley.githubdemo.data.PrefModule
import com.benjaminearley.githubdemo.mixin.MixinModule
import com.benjaminearley.githubdemo.network.GitHubModule

open class BaseActivity : AppCompatActivity() {

    lateinit var baseActivityComponent: BaseActivityComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivityComponent = DaggerBaseActivityComponent.builder()
            .appComponent(App.appComponent)
            .gitHubModule(GitHubModule())
            .prefModule(PrefModule())
            .mixinModule(MixinModule(this))
            .build()

    }
}