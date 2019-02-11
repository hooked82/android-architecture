package com.hookedroid.androidarchitecture.adapter

import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageFromUrl(image: AppCompatImageView, imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        return
    }

    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}