package com.mi.walkthroughandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.mi.walkthroughandroid.data.WalkThroughModel
import com.mi.walkthroughandroid.databinding.FragmentWalkThroughBinding
import com.mi.walkthroughandroid.main.WalkThroughScreenMaker
import com.mi.walkthroughandroid.ui.activity.WalkThroughActivity.Companion.WALK_THROUGH_KEY


class WalkThroughFragment : Fragment() {

    private var _binding: FragmentWalkThroughBinding? = null
    private val binding get() = _binding!!

    private var walkThroughModel: WalkThroughModel? = null
    private var walkThroughFragmentListener: WalkThroughFragmentListener? = null

    /**
     * OnBackPressedCallback to customize your back press event in fragment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is WalkThroughFragmentListener) {
            walkThroughFragmentListener = context
        } else {
            throw RuntimeException("$context must implement WalkThroughFragmentListener")
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish() // To prevent go back to previous screen (Ex : SplashScreen)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            walkThroughModel = bundle.getParcelable(WALK_THROUGH_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalkThroughBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        walkThroughModel?.let { model ->
            WalkThroughScreenMaker(
                binding.rootLayout,
                model,
                requireContext(),
                ::onSkip
            )
        }
    }

    private fun onSkip(isFromOnSkip: Boolean) {
        walkThroughFragmentListener?.onSkipOrFinish(isFromOnSkip)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        walkThroughFragmentListener = null
    }

    /**
     * Listener for callback from Fragment to Activity
     * */
    interface WalkThroughFragmentListener {
        fun onSkipOrFinish(isFromOnSkip: Boolean)
    }
}