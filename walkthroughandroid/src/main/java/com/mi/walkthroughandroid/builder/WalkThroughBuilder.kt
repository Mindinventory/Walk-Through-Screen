package com.mi.walkthroughandroid.builder

import android.content.Context
import android.content.Intent
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.mi.walkthroughandroid.R
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType
import com.mi.walkthroughandroid.data.WalkThroughModel
import com.mi.walkthroughandroid.data.WalkThroughScreenModel
import com.mi.walkthroughandroid.ui.activity.WalkThroughActivity
import com.mi.walkthroughandroid.ui.enums.IndicatorStyle
import com.mi.walkthroughandroid.utils.Constants

class WalkThroughBuilder {

    var context: Context? = null

    var walkThroughScreenModels: ArrayList<WalkThroughScreenModel> = arrayListOf()

    @ColorRes
    var titleColor: Int = R.color.black

    @ColorRes
    var descriptionColor: Int = R.color.black

    @FontRes
    var titleFontFamily: Int = R.font.robotobold

    @FontRes
    var descriptionFontFamily: Int = R.font.robotolight

    var titleFontSize: Float = 8.0F

    var descriptionFontSize: Float = 4.0F

    @ColorRes
    var backgroundColor: Int = R.color.white

    @ColorRes
    var activeIndicatorColor: Int = R.color.dark_orange

    @ColorRes
    var inactiveIndicatorColor: Int = R.color.light_orange

    var indicatorStyle: IndicatorStyle = IndicatorStyle.DEFAULT

    @DimenRes
    var indicatorGap: Int = R.dimen.dp_3

    @DrawableRes
    var activeVectorDrawableRes: Int = R.drawable.shape_active_indicator

    @DrawableRes
    var inactiveVectorDrawableRes: Int = R.drawable.shape_inactive_indicator

    @DrawableRes
    var activeBitmapDrawableRes: Int = R.drawable.walkthrough

    @DrawableRes
    var inactiveBitmapDrawableRes: Int = R.drawable.walkthrough

    var activeBitmapDrawableSize: Int = Constants.ACTIVE_BITMAP_SIZE

    var inactiveBitmapDrawableSize: Int = Constants.INACTIVE_BITMAP_SIZE

    var activeVectorDrawableSize: Int = Constants.ACTIVE_BITMAP_SIZE

    var inactiveVectorDrawableSize: Int = Constants.INACTIVE_BITMAP_SIZE

    @DimenRes
    var activeIndicatorWidth: Int = R.dimen.dp_10

    @DimenRes
    var activeIndicatorHeight: Int = R.dimen.dp_10

    @DimenRes
    var inactiveIndicatorWidth: Int = R.dimen.dp_10

    @DimenRes
    var inactiveIndicatorHeight: Int = R.dimen.dp_10

    var indicatorAnimationType: IndicatorAnimationType = IndicatorAnimationType.NONE

    var contentAnimationType: ContentAnimationType = ContentAnimationType.NONE

    @ColorRes
    var buttonColor: Int = R.color.black

    @ColorRes
    var buttonTextColor: Int = R.color.white

    @FontRes
    var skipButtonFontFamily: Int = 0

    @ColorRes
    var skipButtonColor: Int = 0

    var skipButtonFontSize: Float = 4.0F

    /**
     * Set Context
     * */
    inline fun with(context: () -> Context) {
        this.context = context()
    }

    /**
     * Set WalkThroughScreens
     * */
    inline fun walkThroughScreens(walkThroughScreensModel: () -> ArrayList<WalkThroughScreenModel>) {
        this.walkThroughScreenModels = walkThroughScreensModel()
    }

    /**
     * Set TitleColor
     * */
    inline fun titleColor(titleColor: () -> Int) {
        this.titleColor = titleColor()
    }

    /**
     * Set DescriptionColor
     * */
    inline fun descriptionColor(descriptionColor: () -> Int) {
        this.descriptionColor = descriptionColor()
    }

    /**
     * Set Title Font Family
     **/
    inline fun titleFontFamily(titleFontFamily: () -> Int) {
        this.titleFontFamily = titleFontFamily()
    }

    /**
     * Set Description Font Family
     **/
    inline fun descriptionFontFamily(descriptionFontFamily: () -> Int) {
        this.descriptionFontFamily = descriptionFontFamily()
    }

    /**
     * Set Title Font Size In SP
     **/
    inline fun titleFontSize(titleFontSize: () -> Float) {
        this.titleFontSize = titleFontSize()
    }

    /**
     * Set Description Font Size In SP
     **/
    inline fun descriptionFontSize(descriptionFontSize: () -> Float) {
        this.descriptionFontSize = descriptionFontSize()
    }

    /**
     * Set DescriptionColor
     * */
    inline fun backgroundColor(backgroundColor: () -> Int) {
        this.backgroundColor = backgroundColor()
    }

    /**
     * Set Active Indicator Color
     * */
    inline fun activeIndicatorColor(activeIndicatorColor: () -> Int) {
        this.activeIndicatorColor = activeIndicatorColor()
    }

    /**
     * Set Inactive Indicator Color
     * */
    inline fun inactiveIndicatorColor(inactiveIndicatorColor: () -> Int) {
        this.inactiveIndicatorColor = inactiveIndicatorColor()
    }

    /**
     * Set Indicator Style  DEFAULT, CIRCLE, RECTANGLE, ROUNDED_RECTANGLE
     * */
    inline fun indicatorStyle(indicatorStyle: () -> IndicatorStyle) {
        /* If IndicatorStyle is Rectangle or RoundRectangle and
            the Width and Height is not given then increase the width
            of active and inactive drawable to set as rectangle*/
        // Height will remain default
        when (indicatorStyle().name) {
            IndicatorStyle.ROUNDED_RECTANGLE.name,
            IndicatorStyle.RECTANGLE.name -> {
                activeIndicatorWidth = R.dimen.dp_30
                inactiveIndicatorWidth = R.dimen.dp_20
            }
        }
        this.indicatorStyle = indicatorStyle()
    }

    /**
     * Set Gap Between Indicator
     * */
    inline fun indicatorGap(indicatorGap: () -> Int) {
        this.indicatorGap = indicatorGap()
    }

    /**
     * Set Active Vector Drawable Resource
     * */
    inline fun activeVectorDrawableRes(activeVectorDrawableRes: () -> Int) {
        this.activeVectorDrawableRes = activeVectorDrawableRes()
    }

    /**
     * Set Inactive Vector Drawable Resource
     * */
    inline fun inactiveVectorDrawableRes(inactiveVectorDrawableRes: () -> Int) {
        this.inactiveVectorDrawableRes = inactiveVectorDrawableRes()
    }

    /**
     * Set Active Bitmap Drawable Resource
     * */
    inline fun activeBitmapDrawableRes(activeBitmapDrawableRes: () -> Int) {
        this.activeBitmapDrawableRes = activeBitmapDrawableRes()
    }

    /**
     * Set Inactive Bitmap Drawable Resource
     * */
    inline fun inactiveBitmapDrawableRes(inactiveBitmapDrawableRes: () -> Int) {
        this.inactiveBitmapDrawableRes = inactiveBitmapDrawableRes()
    }

    /**
     * Set Active Bitmap Drawable Size (W x H)
     * */
    inline fun activeBitmapDrawableSize(activeBitmapDrawableSize: () -> Int) {
        this.activeBitmapDrawableSize = activeBitmapDrawableSize()
    }

    /**
     * Set Inactive Bitmap Drawable Size (W x H)
     * */
    inline fun inactiveBitmapDrawableSize(inactiveBitmapDrawableSize: () -> Int) {
        this.inactiveBitmapDrawableSize = inactiveBitmapDrawableSize()
    }

    /**
     * Set Active Vector Drawable Size (W x H)
     * */
    inline fun activeVectorDrawableSize(activeVectorDrawableSize: () -> Int) {
        this.activeVectorDrawableSize = activeVectorDrawableSize()
    }

    /**
     * Set Inactive Bitmap Drawable Size (W x H)
     * */
    inline fun inactiveVectorDrawableSize(inactiveVectorDrawableSize: () -> Int) {
        this.inactiveVectorDrawableSize = inactiveVectorDrawableSize()
    }

    /**
     * Set Active Indicator Width
     * */
    inline fun activeIndicatorWidth(activeIndicatorWidth: () -> Int) {
        this.activeIndicatorWidth = activeIndicatorWidth()
    }

    /**
     * Set Active Indicator Height
     * */
    inline fun activeIndicatorHeight(activeIndicatorHeight: () -> Int) {
        this.activeIndicatorHeight = activeIndicatorHeight()
    }

    /**
     * Set Inactive Indicator Width
     * */
    inline fun inactiveIndicatorWidth(inactiveIndicatorWidth: () -> Int) {
        this.inactiveIndicatorWidth = inactiveIndicatorWidth()
    }

    /**
     * Set Inactive Indicator Height
     * */
    inline fun inactiveIndicatorHeight(inactiveIndicatorHeight: () -> Int) {
        this.inactiveIndicatorHeight = inactiveIndicatorHeight()
    }

    /**
     * Set Indicator Animation Type ( NONE, SCALE, SMOOTH_SCALE, LEFT_IN, RIGHT_IN, FLIP)
     * */
    inline fun indicatorAnimationType(indicatorAnimationType: () -> IndicatorAnimationType) {
        this.indicatorAnimationType = indicatorAnimationType()
    }

    /**
     * Set Content Animation Type ( LEFT_IN, RIGHT_IN, NONE)
     * */
    inline fun contentAnimationType(contentAnimationType: () -> ContentAnimationType) {
        this.contentAnimationType = contentAnimationType()
    }

    /**
     * Set Button Color
     * */
    inline fun buttonColor(buttonColor: () -> Int) {
        this.buttonColor = buttonColor()
    }

    /**
     * Set Button Text Color
     * */
    inline fun buttonTextColor(buttonTextColor: () -> Int) {
        this.buttonTextColor = buttonTextColor()
    }

    /**
     * Set Skip Button Family
     **/
    inline fun skipButtonFontFamily(skipButtonFontFamily: () -> Int) {
        this.skipButtonFontFamily = skipButtonFontFamily()
    }

    /**
     * Set Button Color
     * */
    inline fun skipButtonColor(skipButtonColor: () -> Int) {
        this.skipButtonColor = skipButtonColor()
    }

    /**
     * Set Skip Button Font Size In SP
     **/
    inline fun skipButtonFontSize(skipButtonFontSize: () -> Float) {
        this.skipButtonFontSize = skipButtonFontSize()
    }

    /**
     * To Build the Object of WalkThrough
     * */
    fun build(): Intent {
        val walkThroughModel = WalkThroughModel(
            walkThroughScreenModels = walkThroughScreenModels,
            titleColor = titleColor,
            descriptionColor = descriptionColor,
            titleFontFamily = titleFontFamily,
            titleFontSize = titleFontSize,
            descriptionFontSize = descriptionFontSize,
            descriptionFontFamily = descriptionFontFamily,
            backgroundColor = backgroundColor,
            activeIndicatorColor = activeIndicatorColor,
            inactiveIndicatorColor = inactiveIndicatorColor,
            indicatorStyle = indicatorStyle,
            indicatorGap = indicatorGap,
            activeVectorDrawableRes = activeVectorDrawableRes,
            inactiveVectorDrawableRes = inactiveVectorDrawableRes,
            activeBitmapDrawableRes = activeBitmapDrawableRes,
            inactiveBitmapDrawableRes = inactiveBitmapDrawableRes,
            activeBitmapDrawableSize = activeBitmapDrawableSize,
            inactiveBitmapDrawableSize = inactiveBitmapDrawableSize,
            activeVectorDrawableSize = activeVectorDrawableSize,
            inactiveVectorDrawableSize = inactiveVectorDrawableSize,
            activeIndicatorWidth = activeIndicatorWidth,
            activeIndicatorHeight = activeIndicatorHeight,
            inactiveIndicatorWidth = inactiveIndicatorWidth,
            inactiveIndicatorHeight = inactiveIndicatorHeight,
            indicatorAnimationType = indicatorAnimationType,
            contentAnimationType = contentAnimationType,
            buttonColor = buttonColor,
            buttonTextColor = buttonTextColor,
            skipButtonFontFamily = skipButtonFontFamily,
            skipButtonColor = skipButtonColor,
            skipButtonFontSize = skipButtonFontSize
        )
        if (context != null) {
            return Intent(context, WalkThroughActivity::class.java).apply {
                putExtra(WalkThroughActivity.WALK_THROUGH_KEY, walkThroughModel)
            }
        } else {
            throw NullPointerException("Please provide context.")
        }
    }
}