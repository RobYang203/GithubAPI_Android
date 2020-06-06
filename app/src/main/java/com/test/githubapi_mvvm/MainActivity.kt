package com.test.githubapi_mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.api.APIManager
import com.test.githubapi_mvvm.mode.GithubUserMode
import com.test.githubapi_mvvm.viewMode.GithubListAdapter
import com.test.githubapi_mvvm.viewMode.itemEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TOTAL_PAGES =5;
    val PREPAGE = 20;
    var now_page = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitData()
        setListener()
    }

    private fun setInitData(){
        val api = APIManager.getAPIManagerInstance()
        val service = api.getService()
        val since = now_page * PREPAGE - PREPAGE


        val lm = LinearLayoutManager(baseContext)
        lm.orientation = LinearLayoutManager.VERTICAL

        vw_user_list.layoutManager = lm
        vw_user_list.addItemDecoration(DividerItemDecoration(baseContext , DividerItemDecoration.VERTICAL))

        val view_adapter = GithubListAdapter(baseContext ,service)
        view_adapter.setOnItemClick(object :itemEvent{
            override fun onItemClick(data: GithubUserMode) {
                val login = data.login
                val i = Intent(baseContext , UserDetailActivity::class.java)
                i.putExtra("login" , login)
                startActivityForResult(i ,1)
            }
        })


        vw_user_list.adapter = view_adapter
        //view_adapter.getUserLisByRx(since,PREPAGE)
    }

    private fun setListener(){
        vw_user_list.addOnScrollListener(object :RecyclerView.OnScrollListener(){
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
                Log.d("list lastVisibleItemPos" , lastVisibleItemPos.toString())
                if(now_page + 1 > TOTAL_PAGES){
                    adapter.is_loading = false
                    return
                }

                if(lastVisibleItemPos >= adapter.itemCount - 1){
                    adapter.is_loading = true
                    now_page++
                    val since =  now_page * PREPAGE - PREPAGE
                    adapter.getUserLisByRx(since,PREPAGE)


                }
            }
        })
    }

    fun checkView(isNoData:Boolean):Boolean{
        if(isNoData){
            txt_not_found.visibility = View.VISIBLE
            vw_user_list.visibility = View.GONE
            return false
        }

        return true
    }
}
