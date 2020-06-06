package com.test.githubapi_mvvm.viewMode

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.R

class GithubListBottom(private val v:View):RecyclerView.ViewHolder(v) {
    val progress = v.findViewById<ProgressBar>(R.id.loading_bar)
    val txt_no_data = v.findViewById<TextView>(R.id.txt_no_data)

}