package com.adarsh.gitclosedpullrequest

import android.app.Application
import com.adarsh.gitclosedpullrequest.api.ClosedPullApi
import com.adarsh.gitclosedpullrequest.repo.ClosedPullListRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitClosedPullApplication : Application() {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val api: ClosedPullApi = retrofit.create(ClosedPullApi::class.java)

    val closedPullListRepo: ClosedPullListRepo by lazy {
        ClosedPullListRepo(api)
    }
}