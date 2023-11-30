package com.devgunhee.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.devgunhee.fragment.Flow
import com.devgunhee.fragment.R
import com.devgunhee.fragment.databinding.FragmentFlowFinishBinding

class FlowFinishFragment : Fragment() {

    private var _binding: FragmentFlowFinishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        _binding = FragmentFlowFinishBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                popAll()
            }
        })

        binding.moveToStart.setOnClickListener { popAll() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = getString(R.string.flow_finish)
    }

    private fun popAll() = parentFragmentManager.popBackStack(Flow.FlowFirstFragment.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "FlowFinishFragment"
    }
}