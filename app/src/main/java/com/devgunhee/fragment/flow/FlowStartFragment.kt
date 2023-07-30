package com.devgunhee.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.R
import com.devgunhee.fragment.databinding.FragmentFlowStartBinding

class FlowStartFragment : Fragment() {

    private var _binding: FragmentFlowStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        _binding = FragmentFlowStartBinding.inflate(inflater, container, false)

        binding.moveToFirst.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.flow_fragment_container, FlowFirstFragment()).addToBackStack(FlowFirstFragment.TAG).commit()
        }
        
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "FlowStartFragment"
    }
}