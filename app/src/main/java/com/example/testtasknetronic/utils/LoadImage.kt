package com.example.testtasknetronic.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.testtasknetronic.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)

        .load(url)
        .placeholder(R.drawable.load_icon)
        .into(this)
}