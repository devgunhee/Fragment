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
        Log.d(TAG, "className >> $className, classLoader >> $classLoader")
        return when (loadFragmentClass(classLoader, className)) {
            HomeFragment::class.java -> {
                HomeFragment(R.string.home)
            }

            DualFragment::class.java -> {
                DualFragment(R.string.dual)
            }

            FlowFragment::class.java -> {
                FlowFragment(R.string.flow)
            }

            FlowStartFragment::class.java -> {
                FlowStartFragment(R.string.flow_start)
            }

            FlowFirstFragment::class.java -> {
                FlowFirstFragment(R.string.flow_first)
            }

            FlowSecondFragment::class.java -> {
                FlowSecondFragment(R.string.flow_second)
            }

            FlowThirdFragment::class.java -> {
                FlowThirdFragment(R.string.flow_third)
            }

            FlowFinishFragment::class.java -> {
                FlowFinishFragment(R.string.flow_finish)
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }

    /**
     * get Fragment by Menu
     *
     * @sample [com.devgunhee.fragment.Menu]
     */
    fun getFragment(menu: Menu) = when (menu) {
        Menu.HomeFragment -> { HomeFragment(menu.titleId) }
        Menu.DualFragment -> { DualFragment(menu.titleId) }
        Menu.FlowFragment -> { FlowFragment(menu.titleId) }
    }

    /**
     * get Fragment by Flow
     *
     * @sample [com.devgunhee.fragment.Flow]
     */
    fun getFragment(flow: Flow) = when (flow) {
        Flow.FlowStartFragment -> { FlowStartFragment(flow.titleId) }
        Flow.FlowFirstFragment -> { FlowFirstFragment(flow.titleId) }
        Flow.FlowSecondFragment -> { FlowSecondFragment(flow.titleId) }
        Flow.FlowThirdFragment -> { FlowThirdFragment(flow.titleId) }
        Flow.FlowFinishFragment -> { FlowFinishFragment(flow.titleId) }
    }

    companion object {
        private const val TAG = "CustomFragmentFactory"
    }
}