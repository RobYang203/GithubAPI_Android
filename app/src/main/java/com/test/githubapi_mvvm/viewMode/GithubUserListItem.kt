package com.test.githubapi_mvvm.viewMode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.databinding.VhUseritemBinding
import com.test.githubapi_mvvm.mode.GithubUserMode

interface itemEvent{
    fun onItemClick(data:GithubUserMode)
}
class GithubUserListItem(v:VhUseritemBinding):RecyclerView.ViewHolder(v.root) {
    val v = v
    fun bind(item:GithubUserMode){
        v.item = item
        v.executePendingBindings()
    }
}