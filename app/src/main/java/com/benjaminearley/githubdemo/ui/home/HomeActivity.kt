package com.benjaminearley.githubdemo.ui.home

import android.os.Bundle
import com.benjaminearley.githubdemo.R
import com.benjaminearley.githubdemo.base.BaseActivity
import com.benjaminearley.githubdemo.mixin.ToolbarMixin
import com.benjaminearley.githubdemo.network.GitHubModule.GitHubApiInterface
import com.benjaminearley.githubdemo.util.pipe
import com.bumptech.glide.Glide
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var githubApiInterface: GitHubApiInterface
    @Inject
    lateinit var rxPreferences: RxSharedPreferences
    @Inject
    lateinit var toolbarMixin: ToolbarMixin

    val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        baseActivityComponent.inject(this)

        toolbarMixin.onCreate()

        Timber.tag("Debug")

        rxPreferences.getString(HELLO_WORLD_KEY).set("Hello World")
        val pref = rxPreferences.getString(HELLO_WORLD_KEY).asObservable()

        fab
            .clicks()
            .switchMap {
                githubApiInterface
                    .getUser("BenjaminEarley")
                    .subscribeOn(Schedulers.io())
                    .toObservable()
            }
            .flatMap { deals ->
                pref.map { value ->
                    Pair(value, deals)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (value, deals) ->
                Timber.d("Success: $value $deals")
                Glide.with(this).load("http://i.imgur.com/1ALnB2s.gif").asGif().fitCenter().into(imageView)
            }, { error ->
                Timber.d(error, "Error: " + error)
            }) pipe { disposables.add(it) }


    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    companion object {
        const val HELLO_WORLD_KEY = "HelloWorld"
    }
}