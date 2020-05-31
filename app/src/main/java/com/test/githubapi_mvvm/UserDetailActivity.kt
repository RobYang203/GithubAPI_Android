package com.test.githubapi_mvvm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.databinding.ActivityUserdetailBinding
import com.test.githubapi_mvvm.viewMode.GithubUserInfoFactory
import com.test.githubapi_mvvm.viewMode.GithubUserInfoViewMode
import kotlinx.android.synthetic.main.activity_userdetail.*

class UserDetailActivity : AppCompatActivity() {
    private lateinit var githubUserInfoVM:GithubUserInfoViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        val login = intent.getStringExtra("login")

        val dataBinding = DataBindingUtil.setContentView<ActivityUserdetailBinding>(this,R.layout.activity_userdetail)
        val api = APIManager.getAPIManagerInstance()
        val service = api.getService()
        val repo = GithubRepository(service)

        githubUserInfoVM = ViewModelProvider(this ,GithubUserInfoFactory(repo))
            .get(GithubUserInfoViewMode::class.java)
        dataBinding.userInfo = githubUserInfoVM
        dataBinding.lifecycleOwner = this

        githubUserInfoVM.getUserInfo(login)

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
