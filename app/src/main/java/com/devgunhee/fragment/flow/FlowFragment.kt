package com.devgunhee.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.CustomFragmentFactory
import com.devgunhee.fragment.Flow
import com.devgunhee.fragment.MainActivity
import com.devgunhee.fragment.databinding.FragmentFlowBinding

/**
 * 특정 Flow가 존재하는 케이스
 *
 *  Start => A => B => C => Finish 순으로 화면이 진행되며 각각 stack에 쌓임.
 *  즉, backPress를 눌렀을 때 이전 Fragment로 전환이 됨.
 *  하지만 마지막 Finish Fragment의 경우는 backPress 되었을 떄 첫 화면인 Start Fragment로 전환 되어야 함.
 */

class FlowFragment(@StringRes private val resId: Int) : Fragment() {

    private var _binding: FragmentFlowBinding? = null
    private val binding get() = _binding!!
    private val customFragmentFactory : CustomFragmentFactory by lazy {
        childFragmentManager.fragmentFactory as CustomFragmentFactory
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val backStackList = arrayListOf<String>()
        for (index in 0 until childFragmentManager.backStackEntryCount)
            childFragmentManager.getBackStackEntryAt(index).name?.let { backStackList.add(it) }
        outState.putStringArrayList(CURRENT_FLOW_FRAGMENTS, backStackList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        _binding = FragmentFlowBinding.inflate(inflater, container, false)

        Log.e(TAG, "${savedInstanceState?.getStringArrayList(CURRENT_FLOW_FRAGMENTS)}")

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount == 0)
                    (activity as MainActivity).moveToHome()
                else
                    childFragmentManager.popBackStack()
            }
        })

        restoreCurrentFragments(savedInstanceState?.getStringArrayList(CURRENT_FLOW_FRAGMENTS))

        return binding.root
    }

    private fun restoreCurrentFragments(fragmentClassNames: List<String>?) {
        Log.e(TAG, "restored Fragments >> $fragmentClassNames")
        if (fragmentClassNames.isNullOrEmpty()) {
            replaceFragment(Flow.FlowStartFragment)
        } else {
            fragmentClassNames.map { Flow.valueOf(it) }.forEach { replaceFragment(it) }
        }
    }

    private fun replaceFragment(flow: Flow) {
        childFragmentManager.beginTransaction()
            .replace(binding.flowFragmentContainer.id, customFragmentFactory.getFragment(flow))
            .addToBackStack(flow.name)
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = getString(resId)
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "FlowFragment"
        private const val CURRENT_FLOW_FRAGMENTS = "CURRENT_FLOW_FRAGMENTS"
    }
}