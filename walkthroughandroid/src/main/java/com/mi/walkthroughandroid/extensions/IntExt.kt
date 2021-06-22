package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.util.TypedValue

/**
 * Converts DP values to PX
 * @param context
 * @return converted value in px
 */
fun Int.dpToPx(context: Context): Int {
    var px: Int
    try {
        px = context.resources.getDimensionPixelOffset(this)
    } catch (e: Exception) {
        throw java.lang.IllegalArgumentException("Pleas provide width/height in DP")
    }
    return px
}



