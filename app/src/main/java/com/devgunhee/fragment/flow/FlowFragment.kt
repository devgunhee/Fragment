package com.devgunhee.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.MainActivity
import com.devgunhee.fragment.databinding.FragmentFlowBinding

/**
 * 특정 Flow가 존재하는 케이스
 *
 *  Start => A => B => C => Finish 순으로 화면이 진행되며 각각 stack에 쌓임.
 *  즉, backPress를 눌렀을 때 이전 Fragment로 전환이 됨.
 *  하지만 마지막 Finish Fragment의 경우는 backPress 되었을 떄 첫 화면인 Start Fragment로 전환 되어야 함.
 */

class FlowFragment : Fragment() {

    private var _binding: FragmentFlowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        _binding = FragmentFlowBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount == 0)
                    (activity as MainActivity).moveToHome()
                else
                    childFragmentManager.popBackStack()
            }
        })

        childFragmentManager.beginTransaction().replace(binding.flowFragmentContainer.id, FlowStartFragment()).commit()
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "FlowFragment"
    }
}