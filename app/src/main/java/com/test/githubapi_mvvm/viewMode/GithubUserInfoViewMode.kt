package com.test.githubapi_mvvm.viewMode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.githubapi_mvvm.api.repository.IGithubRepository
import com.test.githubapi_mvvm.mode.GithubUserInfoMode

class GithubUserInfoViewMode(private val repo:IGithubRepository):ViewModel() {

    val login:MutableLiveData<String> = MutableLiveData()
    val id:MutableLiveData<Int> = MutableLiveData()
    val avatar_url:MutableLiveData<String> = MutableLiveData()
    val blog:MutableLiveData<String> = MutableLiveData()
    val name:MutableLiveData<String> = MutableLiveData()
    val location:MutableLiveData<String> = MutableLiveData()
    val bio:MutableLiveData<String> = MutableLiveData()
    val site_admin:MutableLiveData<Boolean> = MutableLiveData()

    fun getUserInfo(loginAccount:String){
        repo.getUserInfo(loginAccount , object : IGithubRepository.ResponseUserInfoCallBack{
            override fun onResult(result: GithubUserInfoMode) {
                login.value = result.login
                id.value = result.id
                avatar_url.value = result.avatar_url
                blog.value = result.blog
                name.value = result.name
                location.value = result.location
                bio.value = result.bio
                site_admin.value = result.site_admin
            }
        })
    }
}