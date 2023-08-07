package com.devgunhee.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.Flow
import com.devgunhee.fragment.R
import com.devgunhee.fragment.databinding.FragmentFlowThirdBinding

class FlowThirdFragment(@StringRes private val resId: Int) : Fragment() {

    private var _binding: FragmentFlowThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        _binding = FragmentFlowThirdBinding.inflate(inflater, container, false)

        binding.moveToFinish.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.flow_fragment_container, FlowFinishFragment(R.string.flow_finish))
                .addToBackStack(Flow.FlowFinishFragment.name)
                .commit()
        }
        
        return binding.root
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
        private const val TAG = "FlowThirdFragment"
    }
}