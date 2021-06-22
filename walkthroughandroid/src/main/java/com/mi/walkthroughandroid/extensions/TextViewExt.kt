package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * SetFontFamily to the TextView
 * */
fun TextView.setFontFamily(@FontRes font: Int) {
    this.typeface = ResourcesCompat.getFont(this.context, font)
}

/**
 * SetFontSize to TextView
 * */
fun TextView.setFontSize(fontSize: Float, context: Context) {
    this.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.spToPx(context))
}

/**
 * SetTextColor to TextView
 * */
fun TextView.setTextColor(context: Context, color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}