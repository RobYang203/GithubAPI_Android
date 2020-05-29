package com.test.githubapi_mvvm.viewMode

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.mode.GithubUserMode

class GithubListAdapter(context: Context , data:List<GithubUserMode>):RecyclerView.Adapter<GithubUserListItem>() {
    val context = context
    val data = data
    var itemEvent:itemEvent? = null

    fun setItemClick(clickEvent:itemEvent){
        itemEvent = clickEvent
    }
    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: GithubUserListItem, position: Int) {
        val itemData = data[position]

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if(itemEvent == null)
                    return
                itemEvent!!.onItemClick(itemData)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserListItem {
        return GithubUserListItem()
    }
}