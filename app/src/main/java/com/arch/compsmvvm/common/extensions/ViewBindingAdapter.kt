package com.arch.compsmvvm.common.extensions

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

private val requestOption = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))

@BindingAdapter("url_roundedCorner")
fun loadImageUrlRoundedCorner(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .apply(requestOption)
        .into(imageView)
}

@BindingAdapter("url")
fun loadImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}