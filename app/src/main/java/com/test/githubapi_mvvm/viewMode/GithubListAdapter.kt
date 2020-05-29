package com.test.githubapi_mvvm.viewMode

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.mode.GithubUserMode

class GithubListAdapter(context: Context , data:List<GithubUserMode>):RecyclerView.Adapter<GithubUserListItem>() {
    val context = context
    val data = data

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: GithubUserListItem, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserListItem {

    }
}