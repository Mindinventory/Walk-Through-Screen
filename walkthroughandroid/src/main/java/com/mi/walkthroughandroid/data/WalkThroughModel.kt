package com.mi.walkthroughandroid.data

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType
import com.mi.walkthroughandroid.ui.enums.IndicatorStyle
import kotlinx.parcelize.Parcelize


@Parcelize
data class WalkThroughModel(
    val walkThroughScreenModels: ArrayList<WalkThroughScreenModel> = arrayListOf(),
    @ColorRes val titleColor: Int,
    @ColorRes val descriptionColor: Int,
    @FontRes val titleFontFamily: Int,
    @FontRes val descriptionFontFamily: Int,
    val titleFontSize: Float,
    val descriptionFontSize: Float,
    @ColorRes val backgroundColor: Int,
    @ColorRes val activeIndicatorColor: Int,
    @ColorRes val inactiveIndicatorColor: Int,
    val indicatorStyle: IndicatorStyle,
    @DimenRes val indicatorGap: Int,
    @DrawableRes val activeVectorDrawableRes: Int,
    @DrawableRes val inactiveVectorDrawableRes: Int,
    @DrawableRes val activeBitmapDrawableRes: Int,
    @DrawableRes val inactiveBitmapDrawableRes: Int,
    val activeBitmapDrawableSize: Int,
    val inactiveBitmapDrawableSize: Int,
    val activeVectorDrawableSize: Int,
    val inactiveVectorDrawableSize: Int,
    @DimenRes val activeIndicatorWidth: Int,
    @DimenRes val activeIndicatorHeight: Int,
    @DimenRes val inactiveIndicatorWidth: Int,
    @DimenRes val inactiveIndicatorHeight: Int,
    val indicatorAnimationType: IndicatorAnimationType,
    val contentAnimationType: ContentAnimationType,
    @ColorRes val buttonColor: Int,
    @ColorRes val buttonTextColor: Int,
    @FontRes val skipButtonFontFamily: Int,
    @ColorRes val skipButtonColor: Int,
    val skipButtonFontSize: Float
) : Parcelable