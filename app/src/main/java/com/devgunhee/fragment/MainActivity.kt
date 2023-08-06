package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.databinding.ActivityMainBinding
import com.devgunhee.fragment.dual.DualFragment
import com.devgunhee.fragment.flow.FlowFragment
import com.devgunhee.fragment.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentFragmentId: String? = null

    enum class Menu(@IdRes val id: Int) {
        HomeFragment(R.id.home),
        DualFragment(R.id.dual),
        FlowFragment(R.id.flow),
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.findFragmentById(binding.fragmentContainer.id)?.let {
            outState.putString(CURRENT_FRAGMENT, it.javaClass.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onCreate(savedInstanceState)
        currentFragmentId = savedInstanceState?.getString(CURRENT_FRAGMENT)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) is HomeFragment)
                    finish()
                else
                    moveToHome()
            }
        })

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                Menu.HomeFragment.id -> {
                    replaceFragment(HomeFragment(R.string.home))
                    return@setOnItemSelectedListener true
                }

                Menu.DualFragment.id -> {
                    replaceFragment(DualFragment(R.string.dual))
                    return@setOnItemSelectedListener true
                }

                Menu.FlowFragment.id -> {
                    replaceFragment(FlowFragment(R.string.flow))
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }

        moveToCurrentFragment()
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.HomeFragment.id
    }

    fun printBackStack() {
        for (index in 0 until supportFragmentManager.backStackEntryCount) {
            Log.e(TAG, "${supportFragmentManager.getBackStackEntryAt(index)}")
        }
    }

    private fun moveToCurrentFragment() {
        binding.bottomNavigation.selectedItemId = currentFragmentId?.let { Menu.valueOf(it).id } ?: Menu.HomeFragment.id
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val CURRENT_FRAGMENT = "CURRENT_FRAGMENT"
    }
}