package com.test.githubapi_mvvm.viewMode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.R
import com.test.githubapi_mvvm.databinding.VhUseritemBinding
import com.test.githubapi_mvvm.mode.GithubUserMode

class GithubListAdapter(context: Context , data:List<GithubUserMode>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_BOTTOM = 1
    val VIEW_DATA=0
    val context = context
    val data = data
    var itemEvent:itemEvent? = null
    var isLoading:Boolean = true
    fun setOnItemClick(clickEvent:itemEvent){
        itemEvent = clickEvent
    }

    override fun getItemCount(): Int {
        return  data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            VIEW_BOTTOM->{
                val h =(holder as GithubListBottom)
                if(isLoading){
                    h.txtNoData.visibility = View.GONE
                }else{
                    h.progress.visibility = View.GONE
                }
            }
            VIEW_DATA->{
                val itemData = data[position]
                (holder as GithubUserListItem).bind(itemData)
                holder.itemView.setOnClickListener(object :View.OnClickListener{
                    override fun onClick(v: View?) {
                        if(itemEvent == null)
                            return
                        itemEvent!!.onItemClick(itemData)
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
        val isLastItem = position > data.size -1
        val viewType =
            if(isLastItem)  VIEW_BOTTOM
            else VIEW_DATA;
        return viewType
    }

    fun setNoDataLoading(isLoading:Boolean){
        this.isLoading = isLoading
    }
}