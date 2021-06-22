package com.example.walkthrough_fragment_example

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mi.walkthroughandroid.ui.fragment.WalkThroughFragment

/**
 * HostingActivity that contains NavHostFragment Container
 * */
class HostingActivity : AppCompatActivity(R.layout.activity_hosting),
    WalkThroughFragment.WalkThroughFragmentListener {

    /**
     * (1) Getting callback from WalkThroughFragment to Your Activity
     * (2) Navigate to Next Screen From OnBoarding Screen
     * */
    override fun onSkipOrFinish(isFromOnSkip: Boolean) {
        findNavController(R.id.fragmentContainer).navigate(R.id.action_walkThroughFragment_to_homeFragment)
    }
}