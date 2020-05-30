package com.test.githubapi_mvvm.api.services

import com.test.githubapi_mvvm.mode.GithubUserInfoMode
import com.test.githubapi_mvvm.mode.GithubUserMode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    fun getAllUserList(@Query("since") since:Int,@Query("per_page") per_page:Int):Call<List<GithubUserMode>>

    @GET("users/{login}")
    fun getUserInfo(@Path("login") login:String):Call<GithubUserInfoMode>
}