package com.benjaminearley.githubdemo.network

import android.os.Parcel
import android.os.Parcelable
import com.benjaminearley.githubdemo.base.BaseActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

@Module
class GitHubModule {

    interface GitHubApiInterface {
        @GET("/users/{login}")
        fun getUser(
            @Path("login") login: String): Single<User>
    }

    @Provides
    @BaseActivityScope
    fun providesGitHubInterface(retrofit: Retrofit): GitHubApiInterface {
        return retrofit.create<GitHubApiInterface>(GitHubApiInterface::class.java)
    }
}

class RetrofitList<out T>(val items: List<T> = listOf()) : List<T> by items {

    override fun toString(): String =
        items.joinToString(",")
}

fun <T> retrofitListOf(vararg items: T): RetrofitList<T> = RetrofitList(items.toList())

data class User(var login: String?, var blog: String?, var publicRepos: Int?) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readInt())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(login)
        dest?.writeString(blog)
        dest?.writeInt(publicRepos ?: 0)
    }
}
