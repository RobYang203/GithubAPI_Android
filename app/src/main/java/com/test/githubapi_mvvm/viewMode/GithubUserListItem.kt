package com.test.githubapi_mvvm.viewMode

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.mode.GithubUserMode

interface itemEvent{
    fun onItemClick(data:GithubUserMode)
}
class GithubUserListItem(v:View):RecyclerView.ViewHolder(v) {

}