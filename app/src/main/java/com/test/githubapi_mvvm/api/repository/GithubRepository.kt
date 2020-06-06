package com.test.githubapi_mvvm.api.repository

import com.test.githubapi_mvvm.api.services.GithubService
import com.test.githubapi_mvvm.mode.GithubUserInfoMode
import com.test.githubapi_mvvm.mode.GithubUserMode
import io.reactivex.Single
import retrofit2.Response

interface IGithubRepository{
    fun getAllUserList(since:Int ,per_page:Int):Single<Response<List<GithubUserMode>>>
    fun getUserInfo(login:String):Single<Response<GithubUserInfoMode>>
}

class GithubRepository(service:GithubService):IGithubRepository {
    var service = service
    override fun getAllUserList(since: Int, per_page: Int):Single<Response<List<GithubUserMode>>> {
        return service.getAllUserList(since , per_page)
    }

    override fun getUserInfo(login: String):Single<Response<GithubUserInfoMode>> {
        return service.getUserInfo(login)

    }
}