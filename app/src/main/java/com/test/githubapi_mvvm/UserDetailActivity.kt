package com.test.githubapi_mvvm

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.databinding.ActivityUserdetailBinding
import com.test.githubapi_mvvm.viewMode.GithubUserInfoFactory
import com.test.githubapi_mvvm.viewMode.GithubUserInfoViewMode
import kotlinx.android.synthetic.main.activity_userdetail.*

class UserDetailActivity : AppCompatActivity() {
    private lateinit var github_user_info_VM:GithubUserInfoViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        val login = intent.getStringExtra("login")

        val data_binding = DataBindingUtil.setContentView<ActivityUserdetailBinding>(this,R.layout.activity_userdetail)
        val api = APIManager.getAPIManagerInstance()
        val service = api.getService()

        github_user_info_VM = ViewModelProvider(this ,GithubUserInfoFactory(service))
            .get(GithubUserInfoViewMode::class.java)
        data_binding.userInfo = github_user_info_VM
        data_binding.lifecycleOwner = this

        github_user_info_VM.getUserInfo(login)

        //toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp)
        supportActionBar!!.title = "${login} Information"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }
        })
    }
}
