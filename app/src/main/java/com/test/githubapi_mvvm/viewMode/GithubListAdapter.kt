package com.test.githubapi_mvvm.viewMode

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.R
import com.test.githubapi_mvvm.api.repository.GithubRepository
import com.test.githubapi_mvvm.api.services.GithubService
import com.test.githubapi_mvvm.databinding.VhUseritemBinding
import com.test.githubapi_mvvm.mode.GithubUserMode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class GithubListAdapter(context: Context , service: GithubService):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_BOTTOM = 1
    val VIEW_DATA=0

    val context = context
    val repo = GithubRepository(service)

    val data:MutableList<GithubUserMode>  = mutableListOf()
    var item_event:itemEvent? = null
    var is_loading:Boolean = true
    fun setOnItemClick(click_event:itemEvent){
        item_event = click_event
    }

    override fun getItemCount(): Int {
        return  data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            VIEW_BOTTOM->{
                val h =(holder as GithubListBottom)
                if(is_loading){
                    h.txt_no_data.visibility = View.GONE
                    h.progress.visibility = View.VISIBLE
                }else{
                    h.progress.visibility = View.GONE
                    h.txt_no_data.visibility = View.VISIBLE
                }
            }
            VIEW_DATA->{
                val item_data = data[position]
                (holder as GithubUserListItem).bind(item_data)
                holder.itemView.setOnClickListener(object :View.OnClickListener{
                    override fun onClick(v: View?) {
                        if(item_event == null)
                            return
                        item_event!!.onItemClick(item_data)
                    }
                })
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val lInflater = LayoutInflater.from(context)
        val v1 = VhUseritemBinding.inflate(lInflater ,parent , false)

        val holder = when(viewType){
            VIEW_DATA->{

                GithubUserListItem(v1)
            }
            VIEW_BOTTOM->{
                val v2 = lInflater.inflate(R.layout.vh_bottom , parent , false)
                GithubListBottom(v2)
            }
            else -> {
                GithubUserListItem(v1)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        val is_last_item = position > data.size -1
        val view_type =
            if(is_last_item)  VIEW_BOTTOM
            else VIEW_DATA;
        return view_type
    }

    fun setNoDataLoading(is_loading:Boolean){
        this.is_loading = is_loading
    }

    fun addDataList(more_list:List<GithubUserMode>?){
        if(more_list == null)
            return
        data.addAll(more_list!!)

        notifyDataSetChanged()
    }

    fun getUserLisByRx(since:Int , page_count:Int) {
        repo.getAllUserList(since , page_count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object: DisposableSingleObserver<Response<List<GithubUserMode>>?>(){

                override fun onSuccess(response: Response<List<GithubUserMode>>) {
                    val code = response.code()
                    if(code != 200)
                        return
                    val result = response.body() as List<GithubUserMode>
                    addDataList(result)
                }

                override fun onError(e: Throwable) {
                    Log.e("Get User List error" , e.toString())
                }
            })
    }
}