package com.test.githubapi_mvvm.api

import com.test.githubapi_mvvm.api.services.GithubService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIManager {
    companion object{
        var m_instance = APIManager()
        fun getAPIManagerInstance():APIManager{
            return m_instance
        }
    }

    private var service:GithubService;

    constructor(){
        //尾端必須要有斜線否則會報錯
        var base_url = "https://api.github.com/"
        var service_builder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = service_builder.create(GithubService::class.java)
    }

    public fun getService():GithubService{
        return service
    }

}