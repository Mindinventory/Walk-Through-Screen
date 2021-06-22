
package com.mi.walkthroughandroid.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.drawable.*
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.mi.walkthroughandroid.ui.enums.IndicatorStyle
import com.mi.walkthroughandroid.utils.Constants.ROUNDED_RECTANGLE_OUTER_RADIUS

object ShapeUtils {

    private const val TAG = "ShapeUtils"

    /**
     * Get Shape Drawable As per Requirement
     *  @param context   Context
     *  @param shape  Indicator Style (DEFAULT,CIRCLE,RECTANGLE,SQUARE,ROUNDED_RECTANGLE)
     *  @param activeColor   Active Color
     *  @param inactiveColor Inactive Color
     *  @param activeVectorDrawable For Vector Type Indicator
     *  @param inactiveVectorDrawable For Vector Type Indicator
     *  @param activeBitmapDrawable For Bitmap Type Indicator
     *  @param inactiveBitmapDrawable For Bitmap Type Indicator
     *  @param activeBitmapDrawableSize Size for Active Bitmap Drawable Size
     *  @param inactiveBitmapDrawableSize Size for Inactive Bitmap Drawable Size
     *  @param activeVectorDrawableSize Size for Active Vector Drawable Size
     *  @param inactiveVectorDrawableSize Size for Inactive Vector Drawable Size
     *  @param activeIndicatorWidthInPx Width in pixels for Active Indicator
     *  @param activeIndicatorHeightInPx Height in pixels for Active Indicator
     *  @param inactiveIndicatorWidthInPx Width in pixels for Inactive Indicator
     *  @param inactiveIndicatorHeightInPx Width in pixels for Inactive Indicator
     * */
    fun getShapeDrawable(
        context: Context,
        shape: IndicatorStyle,
        @ColorRes activeColor: Int,
        @ColorRes inactiveColor: Int,
        @DrawableRes activeVectorDrawable: Int,
        @DrawableRes inactiveVectorDrawable: Int,
        @DrawableRes activeBitmapDrawable: Int,
        @DrawableRes inactiveBitmapDrawable: Int,
        activeBitmapDrawableSize: Int,
        inactiveBitmapDrawableSize: Int,
        activeVectorDrawableSize: Int,
        inactiveVectorDrawableSize: Int,
        activeIndicatorWidthInPx: Int,
        activeIndicatorHeightInPx: Int,
        inactiveIndicatorWidthInPx: Int,
        inactiveIndicatorHeightInPx: Int
    ): Drawable {
        return when (shape) {
            IndicatorStyle.CIRCLE -> createCircleShape(
                context = context,
                activeColor = activeColor,
                inactiveColor = inactiveColor,
                activeIndicatorWidthInPx = activeIndicatorWidthInPx,
                inactiveIndicatorWidthInPx = inactiveIndicatorWidthInPx
            )
            IndicatorStyle.SQUARE -> createSquareShape(
                context = context,
                activeColor = activeColor,
                inactiveColor = inactiveColor,
                activeIndicatorWidthInPx = activeIndicatorWidthInPx,
                inactiveIndicatorWidthInPx = inactiveIndicatorWidthInPx
            )
            IndicatorStyle.RECTANGLE -> createRectangleShape(
                context = context,
                activeColor = activeColor,
                inactiveColor = inactiveColor,
                activeIndicatorWidthInPx = activeIndicatorWidthInPx,
                activeIndicatorHeightInPx = activeIndicatorHeightInPx,
                inactiveIndicatorWidthInPx = inactiveIndicatorWidthInPx,
                inactiveIndicatorHeightInPx = inactiveIndicatorHeightInPx
            )
            IndicatorStyle.ROUNDED_RECTANGLE -> createRoundedRectangleShape(
                context = context,
                activeColor = activeColor,
                inactiveColor = inactiveColor,
                activeIndicatorWidthInPx = activeIndicatorWidthInPx,
                activeIndicatorHeightInPx = activeIndicatorHeightInPx,
                inactiveIndicatorWidthInPx = inactiveIndicatorWidthInPx,
                inactiveIndicatorHeightInPx = inactiveIndicatorHeightInPx
            )
            IndicatorStyle.VECTOR -> createVectorShape(
                context = context,
                activeVectorDrawableRes = activeVectorDrawable,
                inactiveVectorDrawableRes = inactiveVectorDrawable,
                activeVectorDrawableSize = activeVectorDrawableSize,
                inactiveVectorDrawableSize = inactiveVectorDrawableSize
            )
            IndicatorStyle.BITMAP -> createBitmapShape(
                context = context,
                activeBitmapDrawable = activeBitmapDrawable,
                inactiveBitmapDrawable = inactiveBitmapDrawable,
                activeBitmapDrawableSize = activeBitmapDrawableSize,
                inactiveBitmapDrawableSize = inactiveBitmapDrawableSize
            )
            IndicatorStyle.DEFAULT -> createCircleShape(
                context = context,
                activeColor = activeColor,
                inactiveColor = inactiveColor,
                activeIndicatorWidthInPx = activeIndicatorWidthInPx,
                inactiveIndicatorWidthInPx = inactiveIndicatorWidthInPx
            )
        }
    }

    /**
     * Create Circular Shape Drawable
     * @param context   Context
     * @param activeColor   Active Color
     * @param inactiveColor Inactive Color
     * @param activeIndicatorWidthInPx
     * @param inactiveIndicatorWidthInPx
     * */
    private fun createCircleShape(
        context: Context,
        @ColorRes activeColor: Int,
        @ColorRes inactiveColor: Int,
        activeIndicatorWidthInPx: Int,
        inactiveIndicatorWidthInPx: Int
    ): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        Log.d(TAG, "Shape Creating.......")

        // Width and Height both are same for Active Circle Shape
        val activeShape = ShapeDrawable(OvalShape())
        activeShape.apply {
            paint.color = ContextCompat.getColor(context, activeColor)
            intrinsicHeight = activeIndicatorWidthInPx
            intrinsicWidth = activeIndicatorWidthInPx
        }

        // Width and Height both are same for Inactive Circle Shape
        val inactiveShape = ShapeDrawable(OvalShape())
        inactiveShape.apply {
            paint.color = ContextCompat.getColor(context, inactiveColor)
            intrinsicHeight = inactiveIndicatorWidthInPx
            intrinsicWidth = inactiveIndicatorWidthInPx
        }

        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), activeShape)
        stateListDrawable.addState(intArrayOf(), inactiveShape)

        return stateListDrawable
    }

    /**
     * Create Square Shape Drawable
     * @param context   Context
     * @param activeColor   Active Color
     * @param inactiveColor Inactive Color
     * @param activeIndicatorWidthInPx
     * @param inactiveIndicatorWidthInPx
     * */
    private fun createSquareShape(
        context: Context,
        @ColorRes activeColor: Int,
        @ColorRes inactiveColor: Int,
        activeIndicatorWidthInPx: Int,
        inactiveIndicatorWidthInPx: Int
    ): StateListDrawable {

        val stateListDrawable = StateListDrawable()
        val roundRectShape = RoundRectShape(null, RectF(), null)

        // Width and Height both are same for Active Square Shape
        val activeShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = activeIndicatorWidthInPx
            intrinsicWidth = activeIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, activeColor)
        }

        // Width and Height both are same for Inactive Square Shape
        val inactiveShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = inactiveIndicatorWidthInPx
            intrinsicWidth = inactiveIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, inactiveColor)
        }

        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), activeShape)
        stateListDrawable.addState(intArrayOf(), inactiveShape)
        return stateListDrawable
    }

    /**
     * Create Rectangle Shape Drawable
     * @param context   Context
     * @param activeColor   Active Color
     * @param inactiveColor Inactive Color
     * @param activeIndicatorWidthInPx
     * @param activeIndicatorHeightInPx
     * @param inactiveIndicatorWidthInPx
     * @param inactiveIndicatorHeightInPx
     * */
    private fun createRectangleShape(
        context: Context,
        @ColorRes activeColor: Int,
        @ColorRes inactiveColor: Int,
        activeIndicatorWidthInPx: Int,
        activeIndicatorHeightInPx: Int,
        inactiveIndicatorWidthInPx: Int,
        inactiveIndicatorHeightInPx: Int
    ): StateListDrawable {

        val stateListDrawable = StateListDrawable()
        val roundRectShape = RoundRectShape(null, RectF(), null)
        val activeShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = activeIndicatorHeightInPx
            intrinsicWidth = activeIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, activeColor)
        }

        val inactiveShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = inactiveIndicatorHeightInPx
            intrinsicWidth = inactiveIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, inactiveColor)
        }

        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), activeShape)
        stateListDrawable.addState(intArrayOf(), inactiveShape)
        return stateListDrawable
    }

    /**
     * Create Rounded Rectangle Shape Drawable
     * @param context   Context
     * @param activeColor   Active Color
     * @param inactiveColor Inactive Color
     * @param activeIndicatorWidthInPx
     * @param activeIndicatorHeightInPx
     * @param inactiveIndicatorWidthInPx
     * @param inactiveIndicatorHeightInPx
     * */
    private fun createRoundedRectangleShape(
        context: Context,
        @ColorRes activeColor: Int,
        @ColorRes inactiveColor: Int,
        activeIndicatorWidthInPx: Int,
        activeIndicatorHeightInPx: Int,
        inactiveIndicatorWidthInPx: Int,
        inactiveIndicatorHeightInPx: Int
    ): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        val outerRadiusArray = floatArrayOf(
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS,
            ROUNDED_RECTANGLE_OUTER_RADIUS
        )
        val roundRectShape = RoundRectShape(outerRadiusArray, null, null)
        val activeShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = activeIndicatorHeightInPx
            intrinsicWidth = activeIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, activeColor)
        }

        val inactiveShape = ShapeDrawable(roundRectShape).apply {
            intrinsicHeight = inactiveIndicatorHeightInPx
            intrinsicWidth = inactiveIndicatorWidthInPx
            paint.color = ContextCompat.getColor(context, inactiveColor)
        }

        stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), activeShape)
        stateListDrawable.addState(intArrayOf(), inactiveShape)
        return stateListDrawable
    }

    /**
     * Create Vector Drawable
     *  @param context   Context
     *  @param activeVectorDrawableRes  Active Vector Res
     *  @param inactiveVectorDrawableRes Inactive Vector Res
     *  @param activeVectorDrawableSize Active Vector Size
     *  @param inactiveVectorDrawableSize Inactive Vector Size
     * */
    private fun createVectorShape(
        context: Context,
        @DrawableRes activeVectorDrawableRes: Int,
        @DrawableRes inactiveVectorDrawableRes: Int,
        activeVectorDrawableSize: Int,
        inactiveVectorDrawableSize: Int,
    ): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        try {
            // Active Drawable
            val activeVectorDrawable =
                ContextCompat.getDrawable(context, activeVectorDrawableRes) as VectorDrawable
            // Create Bitmap from Vector
            val activeBitmap = Bitmap.createBitmap(
                activeVectorDrawable.intrinsicWidth,
                activeVectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            // Draw it to Canvas
            val activeCanvas = Canvas(activeBitmap)
            activeVectorDrawable.setBounds(0, 0, activeCanvas.width, activeCanvas.height)
            activeVectorDrawable.draw(activeCanvas)
            // Create Scaled Bitmap as per given size
            val activeScaledBitmapDrawable =
                BitmapDrawable(
                    context.resources,
                    Bitmap.createScaledBitmap(
                        activeBitmap,
                        activeVectorDrawableSize,
                        activeVectorDrawableSize,
                        true
                    )
                )

            // Inactive Drawable
            val inactiveVectorDrawable =
                ContextCompat.getDrawable(context, inactiveVectorDrawableRes) as VectorDrawable
            // Create Bitmap from Vector
            val inactiveBitmap = Bitmap.createBitmap(
                inactiveVectorDrawable.intrinsicWidth,
                inactiveVectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            // Draw it to Canvas
            val inactiveCanvas = Canvas(inactiveBitmap)
            inactiveVectorDrawable.setBounds(0, 0, inactiveCanvas.width, inactiveCanvas.height)
            inactiveVectorDrawable.draw(inactiveCanvas)
            // Create Scaled Bitmap as per given size
            val inactiveScaledBitmapDrawable =
                BitmapDrawable(
                    context.resources,
                    Bitmap.createScaledBitmap(
                        inactiveBitmap,
                        inactiveVectorDrawableSize,
                        inactiveVectorDrawableSize,
                        true
                    )
                )

            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_selected),
                activeScaledBitmapDrawable
            )
            stateListDrawable.addState(intArrayOf(), inactiveScaledBitmapDrawable)
        } catch (e: Exception) {
            throw IllegalArgumentException("Please set indicator style as VECTOR and provide activeVectorDrawable/inactiveVectorDrawable")
        }
        return stateListDrawable
    }

    /**
     * Create Bitmap Drawable
     *  @param context   Context
     *  @param activeBitmapDrawable  Active Bitmap Res
     *  @param inactiveBitmapDrawable Inactive Bitmap Res
     *  @param activeBitmapDrawableSize Active Bitmap Drawable Size (W x H)
     *  @param inactiveBitmapDrawableSize Inactive Bitmap Drawable Size (W x H)
     * */
    private fun createBitmapShape(
        context: Context,
        @DrawableRes activeBitmapDrawable: Int,
        @DrawableRes inactiveBitmapDrawable: Int,
        activeBitmapDrawableSize: Int,
        inactiveBitmapDrawableSize: Int,
    ): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        try {
            val activeVectorDrawable =
                ContextCompat.getDrawable(context, activeBitmapDrawable) as BitmapDrawable
            val activeBitmap = activeVectorDrawable.bitmap
            val activeScaledBitmapDrawable =
                BitmapDrawable(
                    context.resources,
                    Bitmap.createScaledBitmap(
                        activeBitmap,
                        activeBitmapDrawableSize,
                        activeBitmapDrawableSize,
                        true
                    )
                )

            val inactiveVectorDrawable =
                ContextCompat.getDrawable(context, inactiveBitmapDrawable) as BitmapDrawable
            val inactiveBitmap = inactiveVectorDrawable.bitmap
            val inactiveScaledBitmapDrawable = BitmapDrawable(
                context.resources,
                Bitmap.createScaledBitmap(
                    inactiveBitmap,
                    inactiveBitmapDrawableSize,
                    inactiveBitmapDrawableSize,
                    true
                )
            )

            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_selected),
                activeScaledBitmapDrawable
            )
            stateListDrawable.addState(intArrayOf(), inactiveScaledBitmapDrawable)
        } catch (e: java.lang.IllegalArgumentException) {
            throw java.lang.IllegalArgumentException("Bitmap width/height should be >=0")
        } catch (e: Exception) {
            throw IllegalArgumentException("Please set indicator style as BITMAP and provide activeBitmapDrawable/inactiveBitmapDrawable")
        }
        return stateListDrawable
    }
}