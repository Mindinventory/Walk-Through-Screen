package com.mi.walkthroughandroid.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mi.walkthroughandroid.R
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType


object ContentAnimUtils {

    /**
     * Provide Animation based on type
     * @param context
     * @param contentAnimationType
     * */
    fun getAnimation(context: Context, contentAnimationType: ContentAnimationType): Animation {
        return when (contentAnimationType) {
            ContentAnimationType.SLIDER -> AnimationUtils.loadAnimation(context, R.anim.left_in)
            ContentAnimationType.FADE -> AnimationUtils.loadAnimation(context, R.anim.fade)
            ContentAnimationType.NONE -> AnimationUtils.loadAnimation(context, R.anim.none)
            ContentAnimationType.SCALE -> AnimationUtils.loadAnimation(context, R.anim.scale)
            ContentAnimationType.TOP_IN -> AnimationUtils.loadAnimation(context, R.anim.top_in)
            ContentAnimationType.BOTTOM_IN -> AnimationUtils.loadAnimation(context, R.anim.bottom_in)
            ContentAnimationType.BOUNCE -> AnimationUtils.loadAnimation(context, R.anim.bounce)
        }
    }
}