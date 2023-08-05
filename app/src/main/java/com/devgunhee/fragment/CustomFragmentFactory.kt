package com.devgunhee.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.devgunhee.fragment.dual.DualFragment
import com.devgunhee.fragment.flow.FlowFinishFragment
import com.devgunhee.fragment.flow.FlowFirstFragment
import com.devgunhee.fragment.flow.FlowFragment
import com.devgunhee.fragment.flow.FlowSecondFragment
import com.devgunhee.fragment.flow.FlowStartFragment
import com.devgunhee.fragment.flow.FlowThirdFragment
import com.devgunhee.fragment.home.HomeFragment

class CustomFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.d("CustomFragmentFactory", "instantiate >> classLoader $classLoader, className $className")
        return when (loadFragmentClass(classLoader, className)) {
            HomeFragment::class.java -> HomeFragment(R.string.home)
            FlowFragment::class.java -> FlowFragment(R.string.flow)
            FlowStartFragment::class.java -> FlowStartFragment(R.string.flow_start)
            FlowFirstFragment::class.java -> FlowFirstFragment(R.string.flow_first)
            FlowSecondFragment::class.java -> FlowSecondFragment(R.string.flow_second)
            FlowThirdFragment::class.java -> FlowThirdFragment(R.string.flow_third)
            FlowFinishFragment::class.java -> FlowFinishFragment(R.string.flow_finish)
            DualFragment::class.java -> DualFragment(R.string.dual)
            else -> super.instantiate(classLoader, className)
        }
    }
}