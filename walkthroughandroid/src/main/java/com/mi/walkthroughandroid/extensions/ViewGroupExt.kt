package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat

/**
 * Set Background Color to Layout
 * */

fun ViewGroup?.setBackgroundColor(context: Context, color: Int) {
    this?.setBackgroundColor(ContextCompat.getColor(context, color))
}