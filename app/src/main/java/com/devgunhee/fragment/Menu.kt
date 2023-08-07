package com.devgunhee.fragment

import androidx.annotation.IdRes
import androidx.annotation.StringRes

enum class Menu(@IdRes val itemId: Int, @StringRes val titleId: Int) {
    HomeFragment(R.id.home, R.string.home),
    DualFragment(R.id.dual, R.string.dual),
    FlowFragment(R.id.flow, R.string.flow),
}

enum class Flow(@StringRes val titleId: Int) {
    FlowStartFragment(R.string.flow_start),
    FlowFirstFragment(R.string.flow_first),
    FlowSecondFragment(R.string.flow_second),
    FlowThirdFragment(R.string.flow_third),
    FlowFinishFragment(R.string.flow_finish),
}