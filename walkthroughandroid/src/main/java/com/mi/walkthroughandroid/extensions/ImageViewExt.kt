package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.mi.walkthroughandroid.R

/**
 * Load Image
 * */
fun ImageView.setImage(@DrawableRes image: Int, context: Context, imageUrl: String = "") {
    if (imageUrl.isNotEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .error(R.drawable.placeholder)
            .into(this)
    } else {
        Glide.with(context)
            .load(image)
            .error(R.drawable.placeholder)
            .into(this)
    }
}