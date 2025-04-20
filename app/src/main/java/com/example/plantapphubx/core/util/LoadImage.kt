package com.example.plantapphubx.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.plantapphubx.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.place_holder)
        .error(R.drawable.error)
        .centerCrop()
        .into(view)
}