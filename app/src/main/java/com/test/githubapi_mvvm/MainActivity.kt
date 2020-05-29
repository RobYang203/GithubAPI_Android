package com.test.githubapi_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.api.repository.IGithubRepository
import com.test.githubapi_mvvm.mode.GithubUserMode
import com.test.githubapi_mvvm.viewMode.GithubListAdapter
import com.test.githubapi_mvvm.viewMode.itemEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiMg = APIManager.getAPIManagerInstance()
        val service = apiMg.getService()

        val repo = GithubRepository(service)
        repo.getAllUserList(0 , 20 ,object :IGithubRepository.ResponseCallBack{
            override fun onResult(result: List<GithubUserMode>) {
                val lm = LinearLayoutManager(baseContext)
                lm.orientation = LinearLayoutManager.VERTICAL

                vw_UserList.layoutManager = lm
                vw_UserList.addItemDecoration(DividerItemDecoration(baseContext , DividerItemDecoration.VERTICAL))

                val viewAdapter = GithubListAdapter(baseContext , result)
                viewAdapter.setOnItemClick(object :itemEvent{
                    override fun onItemClick(data: GithubUserMode) {
                        Toast.makeText(baseContext , "d" , Toast.LENGTH_LONG).show()
                    }
                })
                vw_UserList.adapter = viewAdapter
            }
        })
    }
}
