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

    override fun onCreate(savedInstanceState: Bundle?) {
//        supportFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onCreate(savedInstanceState)
        Log.e(TAG, "savedInstanceState >> $savedInstanceState")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menu.setOnClickListener {
            Log.e(TAG, "MainActivity >> fragments ${supportFragmentManager.fragments}")
            for(i in 0 until supportFragmentManager.backStackEntryCount)
                Log.e(TAG, "MainActivity >> $i supportFragmentManager Backstack ${supportFragmentManager.getBackStackEntryAt(i)}")
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.e(TAG, "MainActivity >> handleOnBackPressed")
                if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) is HomeFragment)
                    finish()
                else
                    moveToHome()
            }
        })

        binding.bottomNavigation.setOnItemSelectedListener {
            Log.e(TAG, "Bottom Navigation Selected >> $it")
            when (it.itemId) {
                Menu.HomeFragment.itemId -> {
                    replaceFragment(getFragment(Menu.HomeFragment))
                    return@setOnItemSelectedListener true
                }

                Menu.DualFragment.itemId -> {
                    replaceFragment(getFragment(Menu.DualFragment))
                    return@setOnItemSelectedListener true
                }

                Menu.FlowFragment.itemId -> {
                    replaceFragment(getFragment(Menu.FlowFragment))
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null)
            moveToHome()
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.HomeFragment.itemId
    }

    private fun replaceFragment(fragment: Fragment) {
        Log.e(TAG, "replaceFragment >> $fragment")
        supportFragmentManager.beginTransaction()
            .setPrimaryNavigationFragment(fragment)
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    /**
     * get Fragment by Menu
     *
     * @sample [com.devgunhee.fragment.Menu]
     */
    fun getFragment(menu: Menu) = when (menu) {
        Menu.HomeFragment -> { HomeFragment() }
        Menu.DualFragment -> { DualFragment() }
        Menu.FlowFragment -> { FlowFragment() }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}