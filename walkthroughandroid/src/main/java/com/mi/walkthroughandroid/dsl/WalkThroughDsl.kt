package com.mi.walkthroughandroid.dsl

import android.content.Intent
import com.mi.walkthroughandroid.builder.WalkThroughBuilder

fun walkThrough(lambda: WalkThroughBuilder.() -> Unit): Intent {
    return WalkThroughBuilder().apply(lambda).build()
}