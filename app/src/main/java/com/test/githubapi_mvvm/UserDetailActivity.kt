package com.test.githubapi_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.databinding.ActivityUserdetailBinding
import com.test.githubapi_mvvm.viewMode.GithubUserInfoViewMode

class UserDetailActivity : AppCompatActivity() {
    private lateinit var gitHubUserInfoVM:GithubUserInfoViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)
        val login = intent.getStringExtra("login")

        val dataBinding = DataBindingUtil.setContentView<ActivityUserdetailBinding>(this,R.layout.activity_userdetail)
        val api = APIManager.getAPIManagerInstance()
        val service = api.getService()
        val repo = GithubRepository(service)

        gitHubUserInfoVM = ViewModelProvider.

    }
}
