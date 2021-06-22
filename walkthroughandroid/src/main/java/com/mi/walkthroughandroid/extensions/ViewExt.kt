package com.mi.walkthroughandroid.extensions

import android.view.View

/**
 * Set Visibility = VISIBLE
 * */
fun View.show() {
    if (this.visibility == View.INVISIBLE) {
        this.visibility = View.VISIBLE
    }
}

/**
 * Set Visibility = INVISIBLE
 * */
fun View.hide() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.INVISIBLE
    }
}