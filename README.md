# WalkThroughAndroid
Make amazing OnBoarding Screens easily for your app with different colorful animations, fonts, styles, and many more. Customize your onboarding as per your requirements.

### Indicator Animation/Indicator Style

|               | DEFAULT                               | CIRCLE                                  | RECTANGLE                                  | SQUARE                                  | ROUNDED_RECTANGLE                           | VECTOR                                  | BITMAP                                                                                                             |
|---------------|---------------------------------------|-----------------------------------------|--------------------------------------------|-----------------------------------------|---------------------------------------------|-----------------------------------------|-------------------------------------------------------|
| NONE|         ![image](/media/none_circle.gif)        | ![image](/media/none_circle.gif)        | ![image](/media/none_rectangle.gif)        | ![image](/media/none_square.gif)        | ![image](/media/none_roundrectangle.gif)    | ![image](/media/none_vector.gif)        | ![image](/media/none_bitmap.gif)                |
| SCALE|        ![image](/media/scale_circle.gif)       | ![image](/media/scale_circle.gif)       | ![image](/media/scale_rectangle.gif)       | ![image](/media/scale_square.gif)       | ![image](/media/scale_roundrect.gif)        | ![image](/media/scale_vector.gif)       | ![image](/media/scale_bitmap.gif)              |
| SMOOTH_SCALE| ![image](/media/smoothscale_circle.gif) | ![image](/media/smoothscale_circle.gif) | ![image](/media/smoothscale_rectangle.gif) | ![image](/media/smoothscale_square.gif) | ![image](/media/smoothscale_roundrect.gif)  | ![image](/media/smoothscale_vector.gif) | ![image](/media/smoothscale_bitmap.gif) |
| LEFT_IN|      ![image](/media/leftin_circle.gif)      | ![image](/media/leftin_circle.gif)      | ![image](/media/leftin_rectangle.gif)      | ![image](/media/leftin_square.gif)      | ![image](/media/leftin_roundrectangle.gif)  | ![image](/media/leftin_vector.gif)      | ![image](/media/leftin_bitmap.gif)           |
| RIGHT_IN|     ![image](/media/rightin_circle.gif)     | ![image](/media/rightin_circle.gif)     | ![image](/media/rightin_rectangle.gif)     | ![image](/media/rightin_square.gif)     | ![image](/media/rightin_roundrectangle.gif) | ![image](/media/rightin_vector.gif)     | ![image](/media/rightin_bitmap.gif)         |
| FLIP|         ![image](/media/flip_circle.gif)        | ![image](/media/flip_circle.gif)        | ![image](/media/flip_rectangle.gif)        | ![image](/media/flip_square.gif)        | ![image](/media/flip_roundrect.gif)         | ![image](/media/flip_vector.gif)        | ![image](/media/flip_bitmap.gif)                |


### Content Animation Style

| Animation Type | Preview                                 |
|--------------- |-----------------------------------------|
| NONE           | ![image](/media/content_none.gif)       |
| FADE           | ![image](/media/content_fade.gif)       |
| SLIDER         | ![image](/media/content_slider.gif)     |
| SCALE          | ![image](/media/content_scale.gif)      |
| TOP_IN         | ![image](/media/content_topin.gif)      |
| BOTTOM_IN      | ![image](/media/content_bottomin.gif)   |
| BOUNCE         | ![image](/media/content_bounce.gif)     |


### Key features

* Simple implementation
* Set Title and Description
* Set Content Animation
* Set Pager Indicator Animation
* Customize Title and Description
* Set OnBoarding image with drawable and image url
* Support for both Activity and Fragment

# Usage

### Dependencies

* Step 1. Add the JitPack repository to your build file (Need to update after publishing)
* Step 2. Add the dependency (Need to update after publishing)

### Implementation

* Step 1. Prepare List for your OnBoarding screen

    ```kotlin
            val walkThroughScreens = ArrayList<WalkThroughScreenModel>().apply {
                       add(
                           WalkThroughScreenModel(
                               image = R.drawable.tasty_dish,
                               title = R.string.choose_a_tasty_dish,
                               description = R.string.order_anything
                           )
                       )
                       add(
                           WalkThroughScreenModel(
                               image = R.drawable.order,
                               title = R.string.order,
                               description = R.string.place_order
                           )
                       )
                       add(
                           WalkThroughScreenModel(
                               image = R.drawable.delivery,
                               title = R.string.pick_up_or_delivery,
                               description = R.string.we_make_food
                           )
                       )
                   }
    ```


* Step 2. Customize title, description, indicator, animation

    ```kotlin
        val walkThroughIntent = walkThrough {
                   with { this@MainActivity } // Pass Content
                   walkThroughScreens { walkThroughScreens } // Pass Screens List
                   titleColor { R.color.black } // Title Color
                   descriptionColor { R.color.black } // Description Color
                   titleFontFamily { R.font.robotobold } // Title FontFamily
                   descriptionFontFamily { R.font.robotolight } // Description FontFamily
                   titleFontSize { 8.0F } // Title Font Size, Keep upto 8.0F
                   descriptionFontSize { 4.0F } // Description Font Size, Keep up to 4.0F
                   skipButtonFontFamily { R.font.robotolight } // Skip Button FontFamily
                   skipButtonColor { R.color.yellow } // Skip Button Color
                   backgroundColor { R.color.white } // Background Color for screen
                   activeIndicatorColor { R.color.yellow } // Active Indicator Color
                   inactiveIndicatorColor { R.color.light_yellow } // Inactive Indicator Color
                   indicatorStyle { IndicatorStyle.ROUNDED_RECTANGLE } // Indicator Style CIRCLE, RECTANGLE, SQUARE, ROUNDED_RECTANGLE, VECTOR, BITMAP
                   activeVectorDrawableRes { Your Vector } // Active Indicator Vector Drawable Res , Set If Indicator Style = VECTOR
                   inactiveVectorDrawableRes { Your Vector } // Inactive Indicator Vector Drawable Res , Set If Indicator Style = VECTOR
                   activeBitmapDrawableRes { R.drawable.tasty_dish } // Active Indicator Bitmap Drawable Res , Set If Indicator Style = BITMAP
                   inactiveBitmapDrawableRes { R.drawable.order } // Active Indicator Bitmap Drawable Res , Set If Indicator Style = BITMAP
                   activeVectorDrawableSize { 50 } // Active Vector Drawable Size in PX
                   inactiveVectorDrawableSize { 50 } // Inactive Vector Drawable Size in PX
                   activeBitmapDrawableSize { 50 } // Inactive Bitmap Drawable Size in PX
                   inactiveBitmapDrawableSize { 50 } // Inactive Bitmap Drawable Size in PX
                   indicatorGap { R.dimen.dp_5 } // Gap Between Indicators
                   indicatorAnimationType { IndicatorAnimationType.NONE } // Indicator Animation Type  SCALE, SMOOTH_SCALE, LEFT_IN, RIGHT_IN, FLIP
                   activeIndicatorWidth { R.dimen.dp_30 } // Active Indicator Width
                   activeIndicatorHeight { R.dimen.dp_8 } // Active Indicator Height
                   inactiveIndicatorWidth { R.dimen.dp_8 } // Inactive Indicator Width
                   inactiveIndicatorHeight { R.dimen.dp_8 } // Inactive Indicator Height
                   contentAnimationType { ContentAnimationType.FADE } // Content Animation Type   FADE, SLIDER, SCALE, TOP_IN, BOTTOM_IN, BOUNCE
                   buttonColor { R.color.yellow } // Next/Finish Button Color
                   buttonTextColor { R.color.white } // Next/Finish Button Text color
                   skipButtonFontSize { 4.0F } // Skip Button Font Size
               }
    ```

### For Activity

* Step 3. Add Launcher to start Next Activity After OnBoarding

             var resultLauncher =
                        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                            if (result.resultCode == Activity.RESULT_OK) {
                                startActivity(Intent(this, HomeActivity::class.java))
                                finish()
                            }
                        }

* Step 4. Launch Launcher using intent

            resultLauncher.launch(walkThroughIntent)

### For Fragment

* Step 3. Get Bundle from intent

                val walkThroughBundle = walkThroughIntent.extras

* Step 4. Implement WalkThroughFragment.WalkThroughFragmentListener in your FragmentHostingActivity

* Step 5. Override and Navigate to next fragment

             override fun onSkipOrFinish(isFromOnSkip: Boolean) {
                     findNavController(R.id.fragmentContainer).navigate(R.id.action_walkThroughFragment_to_homeFragment)
                 }



### How to contribute?
Contribution towards our repository is always welcome, we request contributors to create a pull request to the **develop** branch only.

### How to report an issue/feature request?
It would be great for us if the reporter can share the below things to understand the root cause of the issue.

* Library version
* Code snippet
* Logs if applicable
* Device specification like (Manufacturer, OS version, etc)
* Screenshot/video with steps to reproduce the issue

### Requirements

* minSdkVersion >= 23
* Androidx

### Library used

* [Glide](https://github.com/bumptech/glide)
* [Activity Ktx](https://developer.android.com/kotlin/ktx/extensions-list)
* [ViewBinding](https://developer.android.com/topic/libraries/view-binding)

# LICENSE!

WalkThroughAndroid is [MIT-licensed](/LICENSE).

# Let us know!
We’d be really happy if you send us links to your projects where you use our component. Just send an email to sales@mindinventory.com And do let us know if you have any questions or suggestion regarding our work.
