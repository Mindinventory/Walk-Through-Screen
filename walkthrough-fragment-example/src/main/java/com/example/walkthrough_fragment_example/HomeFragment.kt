package com.example.walkthrough_fragment_example

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    /**
     * OnBackPressedCallback to customize your back press event in fragment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish() // To prevent go back to OnBoarding again
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}