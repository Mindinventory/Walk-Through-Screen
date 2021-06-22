package com.mi.walkthroughandroid.extensions

import android.content.Context
import android.util.TypedValue

/**
 * Converts SP values to PX
 * @param context
 * @return converted value in px
 */

fun Float.spToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )
}



