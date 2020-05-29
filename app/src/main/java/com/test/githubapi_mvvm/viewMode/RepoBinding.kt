package com.test.githubapi_mvvm.viewMode

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class RepoBinding {
}

@BindingAdapter("showText")
fun showText(view:TextView,isShow:Boolean){
    if(isShow){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun bindImage(view:ImageView,url:String){
    val context = view.context
    Glide.with(context)
        .load(url)
        .into(view)
}