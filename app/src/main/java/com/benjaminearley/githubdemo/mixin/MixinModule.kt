package com.benjaminearley.githubdemo.mixin

import com.benjaminearley.githubdemo.base.BaseActivity
import com.benjaminearley.githubdemo.base.BaseActivityScope
import dagger.Module
import dagger.Provides

@Module
class MixinModule(private val activity: BaseActivity) {

    @Provides
    @BaseActivityScope
    fun providesToolbarMixin(): ToolbarMixin {
        return ToolbarMixin(activity)
    }
}
