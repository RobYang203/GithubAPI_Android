package com.test.githubapi_mvvm.viewMode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.githubapi_mvvm.api.repository.IGithubRepository
import com.test.githubapi_mvvm.mode.GithubUserInfoMode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

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
        repo.getUserInfo(loginAccount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : DisposableSingleObserver<Response<GithubUserInfoMode>?>(){
                override fun onSuccess(response: Response<GithubUserInfoMode>) {
                    val code = response.code()
                    if(code != 200)
                        return
                    val result = response.body() as GithubUserInfoMode
                    login.value = result.login
                    id.value = result.id
                    avatar_url.value = result.avatar_url
                    blog.value = result.blog
                    name.value = result.name
                    location.value = result.location
                    bio.value = result.bio
                    site_admin.value = result.site_admin
                }
                override fun onError(e: Throwable) {
                    Log.e("Get User Info error" , e.toString())
                }
            })
    }
}