package com.example.movieapp.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R


fun ImageView.gorselIndir(url:String ?, placeholder: CircularProgressDrawable){
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load("https://image.tmdb.org/t/p/w500${url}").centerCrop().fitCenter().into(this)
}

fun placeholderYap(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}


@BindingAdapter("android:downloadImage")
fun downloadImage(view:ImageView,url:String?){
    view.gorselIndir(url, placeholderYap(view.context))
}

