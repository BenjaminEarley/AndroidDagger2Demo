package com.benjaminearley.githubdemo.mixin

import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import com.benjaminearley.githubdemo.R
import com.benjaminearley.githubdemo.base.BaseActivity

class ToolbarMixin(val activity: BaseActivity) {
    lateinit var toolbar: Toolbar
    fun onCreate(block: ActionBar.() -> Unit = {}) {
        with(activity) {
            toolbar = findViewById(R.id.toolbar) as Toolbar
            setSupportActionBar(toolbar)
        }
        activity.supportActionBar?.block()
    }
}