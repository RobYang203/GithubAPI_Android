package com.test.githubapi_mvvm.api

import com.test.githubapi_mvvm.api.services.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager {
    companion object{
        var mInstance = APIManager()
        fun getAPIManagerInstance():APIManager{
            return mInstance
        }
    }

    private var service:GithubService;

    constructor(){
        //尾端必須要有斜線否則會報錯
        var baseUrl = "https://api.github.com/"
        var serviceBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = serviceBuilder.create(GithubService::class.java)
    }

    public fun getService():GithubService{
        return service
    }

}