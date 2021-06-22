package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat


/**
 * Set Button Background Color
 * */

fun Button.setBackgroundColor(context: Context, color: Int) {
    this.setBackgroundColor(ContextCompat.getColor(context, color))
}