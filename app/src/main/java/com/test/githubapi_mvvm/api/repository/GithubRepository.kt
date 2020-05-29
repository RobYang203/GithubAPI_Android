package com.test.githubapi_mvvm.api.repository

import android.util.Log
import com.test.githubapi_mvvm.api.services.GithubService
import com.test.githubapi_mvvm.mode.GithubUserInfoMode
import com.test.githubapi_mvvm.mode.GithubUserMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface IGithubRepository{
    fun getAllUserList(since:Int ,per_page:Int , onResultCallBack:IGithubRepository.ResponseCallBack)
    fun getUserInfo(login:String , onUserInfoResult:IGithubRepository.ResponseCallBack)
    interface ResponseCallBack{
        fun onResult(result:List<GithubUserMode>)
        fun onUserInfoResult(result:GithubUserInfoMode)
    }
}

class GithubRepository(service:GithubService):IGithubRepository {
    var service = service
    override fun getAllUserList(since: Int, per_page: Int , onResultCallBack:IGithubRepository.ResponseCallBack) {
        service.getAllUserList(since , per_page).enqueue(object :Callback<List<GithubUserMode>>{
            override fun onResponse(
                call: Call<List<GithubUserMode>>?,
                response: Response<List<GithubUserMode>>?
            ) {

                onResultCallBack.onResult(response!!.body())
            }

            override fun onFailure(call: Call<List<GithubUserMode>>?, t: Throwable?) {
                Log.e("GitHubAPI Error" , t.toString())
            }
        })
    }

    override fun getUserInfo(login: String, onUserInfoResult: IGithubRepository.ResponseCallBack) {
        service.getUserInfo(login).enqueue(object :Callback<GithubUserInfoMode>{
            override fun onResponse(
                call: Call<GithubUserInfoMode>?,
                response: Response<GithubUserInfoMode>?
            ) {
                onUserInfoResult.onUserInfoResult(response!!.body())
            }

            override fun onFailure(call: Call<GithubUserInfoMode>?, t: Throwable?) {
                Log.e("API getUserInfo Error" , t.toString())
            }
        })
    }
}