package com.test.githubapi_mvvm.viewMode

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.githubapi_mvvm.R

class GithubListBottom(private val v:View):RecyclerView.ViewHolder(v) {
    val progress = v.findViewById<ProgressBar>(R.id.loadingBar)
    val txtNoData = v.findViewById<TextView>(R.id.txtNoData)

}