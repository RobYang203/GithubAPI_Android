package com.test.githubapi_mvvm.viewMode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.api.services.GithubService

class GithubUserInfoFactory(private val service:GithubService):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(GithubUserInfoViewMode::class.java)){

                return  GithubUserInfoViewMode(GithubRepository(service)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}