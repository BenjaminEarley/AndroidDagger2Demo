package com.benjaminearley.githubdemo.mixin

import com.benjaminearley.githubdemo.base.ActivityScope
import com.benjaminearley.githubdemo.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class MixinModule(private val activity: BaseActivity) {

    @Provides
    @ActivityScope
    fun providesToolbarMixin(): ToolbarMixin {
        return ToolbarMixin(activity)
    }
}
