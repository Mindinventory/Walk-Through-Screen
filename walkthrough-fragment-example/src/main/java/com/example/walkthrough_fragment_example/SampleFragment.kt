package com.example.walkthrough_fragment_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.walkthrough_fragment_example.databinding.FragmentSampleBinding
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType
import com.mi.walkthroughandroid.data.WalkThroughScreenModel
import com.mi.walkthroughandroid.dsl.walkThrough
import com.mi.walkthroughandroid.ui.enums.IndicatorStyle

class SampleFragment : Fragment() {

    private var _binding: FragmentSampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        val walkThroughScreens = ArrayList<WalkThroughScreenModel>().apply {
            add(
                WalkThroughScreenModel(
                    title = R.string.choose_a_tasty_dish,
                    description = R.string.order_anything,
                    image = R.drawable.tasty_dish
                )
            )
            add(
                WalkThroughScreenModel(
                    title = R.string.order,
                    description = R.string.place_order,
                    image = R.drawable.order
                )
            )
            add(
                WalkThroughScreenModel(
                    title = R.string.pick_up_or_delivery,
                    description = R.string.we_make_food,
                    image = R.drawable.delivery
                )
            )
        }

        val walkThroughIntent = walkThrough {
            with { requireContext() } // Pass Content
            walkThroughScreens { walkThroughScreens } // Pass Screens List
            titleColor { R.color.black } // Title Color
            descriptionColor { R.color.black } // Description Color
            titleFontFamily { R.font.robotobold } // Title FontFamily
            descriptionFontFamily { R.font.robotolight } // Description FontFamily
            titleFontSize { 22.0F } // Title Font Size
            descriptionFontSize { 15.0F } // Description Font Size
            skipButtonFontFamily { R.font.robotolight } // Skip Button FontFamily
            skipButtonColor { R.color.yellow } // Skip Button Color
            backgroundColor { R.color.white } // Background Color for screen
            indicatorStyle { IndicatorStyle.ROUNDED_RECTANGLE }
            activeIndicatorWidth { R.dimen.dp_30 }
            activeIndicatorHeight { R.dimen.dp_5 }
            inactiveIndicatorWidth { R.dimen.dp_5 }
            inactiveIndicatorHeight { R.dimen.dp_5 }
            indicatorGap { R.dimen.dp_5 } // Gap Between Indicators
            indicatorAnimationType { IndicatorAnimationType.NONE } // Indicator Animation Type  SCALE, SMOOTH_SCALE, LEFT_IN, RIGHT_IN, FLIP
            contentAnimationType { ContentAnimationType.NONE } // Content Animation Type   FADE, SLIDER, SCALE, TOP_IN, BOTTOM_IN, BOUNCE
            buttonColor { R.color.yellow } // Next/Finish Button Color
            buttonTextColor { R.color.white } // Next/Finish Button Text color
            skipButtonFontSize { 15.0F } // Skip Button Font Size
        }

        val walkThroughBundle = walkThroughIntent.extras
        findNavController().navigate(
            R.id.action_sampleFragment_to_walkThroughFragment,
            walkThroughBundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}