package com.mi.walkthroughandroid.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mi.walkthroughandroid.R
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType

object AnimUtils {

    /**
     * Provide Animation base on type
     * @param context
     * @param indicatorAnimationType
     * */
    fun getAnimation(context: Context, indicatorAnimationType: IndicatorAnimationType): Animation {
        return when (indicatorAnimationType) {
            IndicatorAnimationType.SCALE -> AnimationUtils.loadAnimation(context, R.anim.scale)
            IndicatorAnimationType.LEFT_IN -> AnimationUtils.loadAnimation(context, R.anim.left_in)
            IndicatorAnimationType.RIGHT_IN -> AnimationUtils.loadAnimation(context, R.anim.right_in)
            IndicatorAnimationType.FLIP -> AnimationUtils.loadAnimation(context, R.anim.flip)
            IndicatorAnimationType.NONE -> AnimationUtils.loadAnimation(context, R.anim.none)
            IndicatorAnimationType.SMOOTH_SCALE -> AnimationUtils.loadAnimation(context, R.anim.smooth_scale)
        }
    }
}