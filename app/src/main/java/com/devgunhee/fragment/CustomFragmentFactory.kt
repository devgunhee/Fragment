package com.devgunhee.fragment

import androidx.annotation.StringRes
import androidx.fragment.app.FragmentFactory
import com.devgunhee.fragment.dual.DualFragment
import com.devgunhee.fragment.flow.FlowFinishFragment
import com.devgunhee.fragment.flow.FlowFirstFragment
import com.devgunhee.fragment.flow.FlowFragment
import com.devgunhee.fragment.flow.FlowSecondFragment
import com.devgunhee.fragment.flow.FlowStartFragment
import com.devgunhee.fragment.flow.FlowThirdFragment
import com.devgunhee.fragment.home.HomeFragment
import java.lang.IllegalArgumentException

class CustomFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String) =
        when (loadFragmentClass(classLoader, className)) {
            HomeFragment::class.java -> { HomeFragment(R.string.home) }
            DualFragment::class.java -> { DualFragment(R.string.dual) }
            FlowFragment::class.java -> { FlowFragment(R.string.flow) }
            FlowStartFragment::class.java -> { FlowStartFragment(R.string.flow_start) }
            FlowFirstFragment::class.java -> { FlowFirstFragment(R.string.flow_first) }
            FlowSecondFragment::class.java -> { FlowSecondFragment(R.string.flow_second) }
            FlowThirdFragment::class.java -> { FlowThirdFragment(R.string.flow_third) }
            FlowFinishFragment::class.java -> { FlowFinishFragment(R.string.flow_finish) }
            else -> { super.instantiate(classLoader, className) }
        }

    /**
     * get Fragment by StringRes [id]
     *
     * @throws IllegalArgumentException
     */
    fun getFragment(@StringRes id: Int) = when (id) {
        R.string.home -> { HomeFragment(id) }
        R.string.dual -> { DualFragment(id) }
        R.string.flow -> { FlowFragment(id) }
        R.string.flow_start -> { FlowStartFragment(id) }
        R.string.flow_first -> { FlowFirstFragment(id) }
        R.string.flow_second -> { FlowSecondFragment(id) }
        R.string.flow_third -> { FlowThirdFragment(id) }
        R.string.flow_finish -> { FlowFinishFragment(id) }
        else -> { throw IllegalArgumentException() }
    }
}