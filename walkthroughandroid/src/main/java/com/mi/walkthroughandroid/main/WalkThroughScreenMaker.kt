package com.mi.walkthroughandroid.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mi.walkthroughandroid.R
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType
import com.mi.walkthroughandroid.data.IndicatorSelectorModel
import com.mi.walkthroughandroid.data.WalkThroughModel
import com.mi.walkthroughandroid.data.WalkThroughScreenModel
import com.mi.walkthroughandroid.extensions.*
import com.mi.walkthroughandroid.listeners.OnSwipeListener
import com.mi.walkthroughandroid.ui.adapter.PagerIndicatorAdapter
import com.mi.walkthroughandroid.ui.common.SpaceItemDecoration
import com.mi.walkthroughandroid.utils.AnimUtils
import com.mi.walkthroughandroid.utils.ContentAnimUtils
import com.mi.walkthroughandroid.utils.ShapeUtils
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NAME_SHADOWING")
class WalkThroughScreenMaker
/**
 * Main constructor for create a Paper Onboarding Engine
 *
 * @param rootLayout    Main root layout
 * @param walkThroughModel   Object of WalkThrough which contains list of page and property of each page
 * @param context       Context
 * @param finishCallBack Call Back for Finish Button click
 */(
    rootLayout: View, walkThroughModel: WalkThroughModel, context: Context,
    private val finishCallBack: (isFromOnSkip: Boolean) -> Unit
) {

    private val TAG = "WalkThroughScreenMaker"

    // scale factor for converting dp to px
    private var dpToPixelsScaleFactor = 0f

    // Main Root Layout Parts
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var ivContent: AppCompatImageView
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var rvIndicator: RecyclerView
    private lateinit var btnNextDone: AppCompatButton
    private lateinit var tvSkip: AppCompatTextView
    private var indicatorList = ArrayList<IndicatorSelectorModel>()

    // WalkThrough
    private lateinit var walkThroughModel: WalkThroughModel

    // List of Screens
    private var walkThroughScreenModels: ArrayList<WalkThroughScreenModel> = arrayListOf()

    // Current Screen Index - vary as per user swipe
    private var currentScreenIndex = 0
    private var prevScreenIndex = -1

    // Context
    private lateinit var context: Context

    // Adapter
    private lateinit var pagerIndicatorAdapter: PagerIndicatorAdapter

    // Animation
    private lateinit var contentAnimation: Animation

    // Animation for slider type
    private lateinit var prevAnimation: Animation
    private lateinit var nextAnimation: Animation

    init {
        if (walkThroughModel.walkThroughScreenModels.isNullOrEmpty()) {
            throw IllegalArgumentException("Data not provided")
        }
        initViews(walkThroughModel, context, rootLayout)
        setUpPagerIndicator()
        setInitialState()
        attachSwipeListener()
        handleNextFinishButtonClick()
        handleOnSkipClickListener()
    }

    /**
     * Initialize required things
     * */
    private fun initViews(walkThroughModel: WalkThroughModel, context: Context, rootLayout: View) {
        this.context = context
        this.walkThroughModel = walkThroughModel
        this.walkThroughScreenModels.addAll(walkThroughModel.walkThroughScreenModels)
        this.rootLayout = rootLayout as ConstraintLayout
        this.ivContent = rootLayout.findViewById(R.id.ivContent) as AppCompatImageView
        this.tvTitle = rootLayout.findViewById(R.id.tvTitle) as AppCompatTextView
        this.tvDescription = rootLayout.findViewById(R.id.tvDescription) as AppCompatTextView
        this.rvIndicator = rootLayout.findViewById(R.id.rvIndicator)
        this.btnNextDone = rootLayout.findViewById(R.id.btnNextDone) as AppCompatButton
        this.tvSkip = rootLayout.findViewById(R.id.tvSkip) as AppCompatTextView
        this.dpToPixelsScaleFactor = context.resources.displayMetrics.density
        this.contentAnimation =
            ContentAnimUtils.getAnimation(this.context, walkThroughModel.contentAnimationType)

        // If Animation type is SLIDER then we should animate left_in and right_in for next and prev
        if (walkThroughModel.contentAnimationType == ContentAnimationType.SLIDER) {
            prevAnimation = AnimUtils.getAnimation(this.context, IndicatorAnimationType.LEFT_IN)
            nextAnimation = AnimUtils.getAnimation(this.context, IndicatorAnimationType.RIGHT_IN)
        }
    }

    private fun setUpPagerIndicator() {
        Log.d(TAG, "setUpPagerIndicator: Current Time Before => ${Calendar.getInstance().time}")
        for (i in 0 until walkThroughModel.walkThroughScreenModels.size) {
            Log.d(TAG, "setUpPagerIndicator: ")
            indicatorList.add(
                IndicatorSelectorModel(
                    ShapeUtils.getShapeDrawable(
                        context = context,
                        shape = walkThroughModel.indicatorStyle,
                        activeColor = walkThroughModel.activeIndicatorColor,
                        inactiveColor = walkThroughModel.inactiveIndicatorColor,
                        activeVectorDrawable = walkThroughModel.activeVectorDrawableRes,
                        inactiveVectorDrawable = walkThroughModel.inactiveVectorDrawableRes,
                        activeBitmapDrawable = walkThroughModel.activeBitmapDrawableRes,
                        inactiveBitmapDrawable = walkThroughModel.inactiveBitmapDrawableRes,
                        activeBitmapDrawableSize = walkThroughModel.activeBitmapDrawableSize,
                        inactiveBitmapDrawableSize = walkThroughModel.inactiveBitmapDrawableSize,
                        activeVectorDrawableSize = walkThroughModel.activeVectorDrawableSize,
                        inactiveVectorDrawableSize = walkThroughModel.inactiveVectorDrawableSize,
                        activeIndicatorWidthInPx = walkThroughModel.activeIndicatorWidth.dpToPx(
                            context
                        ),
                        activeIndicatorHeightInPx = walkThroughModel.activeIndicatorHeight.dpToPx(
                            context
                        ),
                        inactiveIndicatorWidthInPx = walkThroughModel.inactiveIndicatorWidth.dpToPx(
                            context
                        ),
                        inactiveIndicatorHeightInPx = walkThroughModel.inactiveIndicatorHeight.dpToPx(
                            context
                        )
                    )
                )
            )
        }

        Log.d(TAG, "setUpPagerIndicator: Current Time After => ${Calendar.getInstance().time}")
        pagerIndicatorAdapter = PagerIndicatorAdapter(
            animation = AnimUtils.getAnimation(context, walkThroughModel.indicatorAnimationType),
            dataList = indicatorList
        ) { currentScreenIndex }
        rvIndicator.adapter = pagerIndicatorAdapter
        rvIndicator.addItemDecoration(
            SpaceItemDecoration(
                walkThroughModel.indicatorGap.dpToPx(
                    context
                )
            )
        )
    }

    /**
     * Attach SwipeListener to RootLayout
     * */
    @SuppressLint("ClickableViewAccessibility")
    private fun attachSwipeListener() {
        rootLayout.setOnTouchListener(object : OnSwipeListener(context) {
            override fun onSwipeLeft() {
                // Right to Left
                getNextScreen()
            }

            override fun onSwipeRight() {
                // Left to Right
                getPreviousScreen()
            }
        })
    }

    /**
     * Initial State of the screen
     * */
    private fun setInitialState() {
        //region Set Initial Content
        getCurrentWalkThroughScreen()?.let { walkThroughScreen ->
            // Set Image
            ivContent.setImage(walkThroughScreen.image, context, walkThroughScreen.imageUrl)

            // Set title text and font size
            tvTitle.apply {
                setText(walkThroughScreen.title)
                setFontSize(walkThroughModel.titleFontSize)
            }

            // Set description text and font size
            tvDescription.apply {
                setText(walkThroughScreen.description)
                setFontSize(walkThroughModel.descriptionFontSize)
            }
        }
        //endregion

        getWalkThrough().let { walkThrough ->
            //region Set Title color and font
            tvTitle.apply {
                setTextColor(context, walkThrough.titleColor)
                setFontFamily(walkThrough.titleFontFamily)
            }
            //endregion

            //region Set Description color and font
            tvDescription.apply {
                setTextColor(context, walkThrough.descriptionColor)
                setFontFamily(walkThrough.descriptionFontFamily)
            }
            //endregion

            //region Set Screen background color
            rootLayout.setBackgroundColor(context, walkThrough.backgroundColor)
            //endregion

            //region Set Next/Finish Button Properties
            btnNextDone.apply {
                text = context.getString(R.string.next)
                setBackgroundColor(context, walkThroughModel.buttonColor)
                setTextColor(context, walkThroughModel.buttonTextColor)
            }
            //endregion

            //region Set Skip Button Properties
            tvSkip.apply {
                if (walkThrough.skipButtonColor == 0) {
                    // If 0 that means user has not given Color for skipButton, So set buttonColor default
                    setTextColor(context, walkThroughModel.buttonTextColor)
                } else {
                    setTextColor(context, walkThroughModel.skipButtonColor)
                }

                if (walkThrough.skipButtonFontFamily == 0) {
                    // If 0 that means user has not given FF for skipButton, So set descriptionFontFamily default
                    setFontFamily(walkThrough.descriptionFontFamily)
                } else {
                    setFontFamily(walkThrough.skipButtonFontFamily)
                }

                setFontSize(walkThroughModel.skipButtonFontSize)
            }
            //endregion

            //region Animate Content
            animateContent(false)
            //endregion
        }
    }

    /**
     * Get Previous Screen
     * */
    private fun getPreviousScreen() {
        val previousScreen = getPreviousScreenContent()
        previousScreen?.let { previousScreen ->
            setContentOnScreen(previousScreen, isPrevious = true)
            updateIndicatorState()
        }
    }

    /**
     * Get Next Screen
     * */
    private fun getNextScreen() {
        val nextScreen = getNextScreenContent()
        nextScreen?.let { nextScreen ->
            setContentOnScreen(nextScreen)
            updateIndicatorState()
        }
    }

    /**
     * Set PagerIndicator Active/InActive based on currentScreenIndex
     * */
    private fun updateIndicatorState() {
        pagerIndicatorAdapter.notifyDataSetChanged()
    }

    /**
     * Set content on current screen
     * */
    private fun setContentOnScreen(
        walkThroughScreenModel: WalkThroughScreenModel?,
        isPrevious: Boolean = false
    ) {
        walkThroughScreenModel?.let { walkThroughScreen ->
            //region Set content as per swipe
            // Set Image
            ivContent.setImage(walkThroughScreen.image, context, walkThroughScreen.imageUrl)

            // Set title text
            tvTitle.setText(walkThroughScreen.title)

            // Set description text
            tvDescription.setText(walkThroughScreen.description)
            //endregion

            //region Set Button Text Next/Finish
            if (currentScreenIndex == walkThroughModel.walkThroughScreenModels.size - 1) {
                // Last Screen
                btnNextDone.text = context.getString(R.string.finish)
            } else {
                // Prev Screen
                btnNextDone.text = context.getString(R.string.next)
            }
            //endregion

            //region Animate Content
            animateContent(isPrevious)
            //endregion

            // Set Skip Button Show/Hide
            if (currentScreenIndex == walkThroughModel.walkThroughScreenModels.size - 1) {
                tvSkip.hide()
            } else {
                tvSkip.show()
            }
        }
    }

    private fun animateContent(isPrevious: Boolean = false) {
        if (this.walkThroughModel.contentAnimationType == ContentAnimationType.SLIDER) {
            // If Animation type is SLIDER then and only then apply two animation
            if (isPrevious) {
                ivContent.startAnimation(prevAnimation)
                tvTitle.startAnimation(prevAnimation)
                tvDescription.startAnimation(prevAnimation)
            } else {
                ivContent.startAnimation(nextAnimation)
                tvTitle.startAnimation(nextAnimation)
                tvDescription.startAnimation(nextAnimation)
            }
        } else {
            ivContent.startAnimation(contentAnimation)
            tvTitle.startAnimation(contentAnimation)
            tvDescription.startAnimation(contentAnimation)
        }
    }

    /**
     * Finish Activity If The Screen is Last when user is clicked on button
     * Else Navigate To Next Screen
     * */
    private fun handleNextFinishButtonClick() {
        btnNextDone.setOnClickListener {
            if (currentScreenIndex == walkThroughModel.walkThroughScreenModels.size - 1) {
                // Finish Activity
                finishCallBack.invoke(false)
            } else {
                // Navigate To Next Screen
                getNextScreen()
            }
        }
    }

    /**
     * Finish Activity
     * */
    private fun handleOnSkipClickListener() {
        tvSkip.setOnClickListener { finishCallBack.invoke(true) }
    }

    /**
     * Get WalkThrough
     * */
    private fun getWalkThrough(): WalkThroughModel {
        return walkThroughModel
    }

    /**
     * Get Current Screen
     * */
    private fun getCurrentWalkThroughScreen(): WalkThroughScreenModel? {
        return if (currentScreenIndex < walkThroughScreenModels.size) walkThroughScreenModels[currentScreenIndex] else null
    }

    /**
     * Get Previous Screen onRightSwipe
     * */
    private fun getPreviousScreenContent(): WalkThroughScreenModel? {
        // CurrentIndex - 1
        return if (currentScreenIndex - 1 >= 0) {
            prevScreenIndex = currentScreenIndex
            currentScreenIndex--
            return if (currentScreenIndex < walkThroughScreenModels.size)
                walkThroughScreenModels[currentScreenIndex]
            else null
        } else {
            null
        }
    }

    /**
     * Get Next Screen onLeftSwipe
     * */
    private fun getNextScreenContent(): WalkThroughScreenModel? {
        // CurrentIndex + 1
        return if (currentScreenIndex + 1 < walkThroughScreenModels.size) {
            prevScreenIndex = currentScreenIndex
            currentScreenIndex++
            return if (currentScreenIndex < walkThroughScreenModels.size)
                walkThroughScreenModels[currentScreenIndex]
            else null
        } else {
            null
        }
    }
}