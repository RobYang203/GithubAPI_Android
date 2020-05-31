package com.test.githubapi_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.api.repository.IGithubRepository
import com.test.githubapi_mvvm.mode.GithubUserInfoMode
import com.test.githubapi_mvvm.mode.GithubUserMode
import com.test.githubapi_mvvm.viewMode.GithubListAdapter
import com.test.githubapi_mvvm.viewMode.itemEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    lateinit var repo:GithubRepository
    val TOTAL_PAGES =5;
    val PREPAGE = 20;
    var nowPage = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setInitData()
        setListener()
    }

    private fun setInitData(){
        val apiMg = APIManager.getAPIManagerInstance()
        val service = apiMg.getService()
        val since = nowPage * PREPAGE - PREPAGE
        repo = GithubRepository(service)
        repo.getAllUserList(since , PREPAGE ,object :IGithubRepository.ResponseCallBack{
            override fun onResult(result: List<GithubUserMode>) {
                val lm = LinearLayoutManager(baseContext)
                lm.orientation = LinearLayoutManager.VERTICAL

                vw_UserList.layoutManager = lm
                vw_UserList.addItemDecoration(DividerItemDecoration(baseContext , DividerItemDecoration.VERTICAL))

                val viewAdapter = GithubListAdapter(baseContext , result as MutableList<GithubUserMode>)
                viewAdapter.setOnItemClick(object :itemEvent{
                    override fun onItemClick(data: GithubUserMode) {
                        val login = data.login
                        val i = Intent(baseContext , UserDetailActivity::class.java)
                        i.putExtra("login" , login)
                        startActivityForResult(i ,1)
                    }
                })
                vw_UserList.adapter = viewAdapter
            }

        })
    }

    private fun setListener(){
        vw_UserList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager
                val adapter = recyclerView.adapter as GithubListAdapter

                if(manager == null){
                    throw RuntimeException("layoutManager error")
                }
                if(manager !is LinearLayoutManager){
                    return
                }
                val lastVisibleItemPos = manager.findLastCompletelyVisibleItemPosition()
               // Log.d("list lastVisibleItemPos" , lastVisibleItemPos.toString())
                if(nowPage + 1 > TOTAL_PAGES){
                    adapter.isLoading = false

                    return
                }

                if(lastVisibleItemPos >= adapter.itemCount - 1){
                    adapter.isLoading = true
                    nowPage++
                    val since =  nowPage * PREPAGE - PREPAGE

                    repo.getAllUserList(since , PREPAGE ,object:IGithubRepository.ResponseCallBack{
                        override fun onResult(result: List<GithubUserMode>) {
                            adapter.addDataList(result)
                        }
                    })

                    Toast.makeText(baseContext , lastVisibleItemPos.toString() , Toast.LENGTH_LONG).show()
                }



            }
        })
    }
}
