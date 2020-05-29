package com.test.githubapi_mvvm.api.repository

import android.util.Log
import com.test.githubapi_mvvm.api.services.GithubService
import com.test.githubapi_mvvm.mode.GithubUserMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface IGithubRepository{
    fun getAllUserList(since:Int ,per_page:Int , onResultCallBack:IGithubRepository.ResponseCallBack)
    interface ResponseCallBack{
        fun onResult(result:List<GithubUserMode>)
    }
}

class GithubRepository(service:GithubService):IGithubRepository {
    var service = service
    override fun getAllUserList(since: Int, per_page: Int , onResultCallBack:IGithubRepository.ResponseCallBack) {
        service.getAllUserList(since , per_page).enqueue(object :Callback<GithubUserMode>{
            override fun onResponse(
                call: Call<GithubUserMode>?,
                response: Response<GithubUserMode>?
            ) {
                onResultCallBack.onResult()
            }

            override fun onFailure(call: Call<GithubUserMode>?, t: Throwable?) {
                Log.e("GitHubAPI Error" , t.toString())
            }
        })
    }
}