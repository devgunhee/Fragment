package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.databinding.ActivityMainBinding
import com.devgunhee.fragment.dual.DualFragment
import com.devgunhee.fragment.flow.FlowFragment
import com.devgunhee.fragment.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.findFragmentById(binding.fragmentContainer.id)?.let {
            outState.putString(CURRENT_FRAGMENT_CLASS_NAME, it.javaClass.simpleName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onCreate(savedInstanceState)
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
                Menu.HomeFragment.itemId -> {
                    replaceFragment(HomeFragment(R.string.home))
                    return@setOnItemSelectedListener true
                }

                Menu.DualFragment.itemId -> {
                    replaceFragment(DualFragment(R.string.dual))
                    return@setOnItemSelectedListener true
                }

                Menu.FlowFragment.itemId -> {
                    replaceFragment(FlowFragment(R.string.flow))
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }

        restoreCurrentFragment(savedInstanceState?.getString(CURRENT_FRAGMENT_CLASS_NAME))
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.HomeFragment.itemId
    }

    fun printBackStack() {
        for (index in 0 until supportFragmentManager.backStackEntryCount) {
            Log.e(TAG, "${supportFragmentManager.getBackStackEntryAt(index)}")
        }
    }

    private fun restoreCurrentFragment(fragmentClassName: String?) {
        binding.bottomNavigation.selectedItemId = if (fragmentClassName == null) Menu.HomeFragment.itemId else Menu.valueOf(fragmentClassName).itemId
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val CURRENT_FRAGMENT_CLASS_NAME = "CURRENT_FRAGMENT_CLASS_NAME"
    }
}