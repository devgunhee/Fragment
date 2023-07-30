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

    enum class Menu(@IdRes val id: Int) {
        Home(R.id.home),
        Dual(R.id.dual),
        Flow(R.id.flow),
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
                Menu.Home.id -> {
                    replaceFragment(HomeFragment(R.string.home))
                    return@setOnItemSelectedListener true
                }

                Menu.Dual.id -> {
                    replaceFragment(DualFragment(R.string.dual))
                    return@setOnItemSelectedListener true
                }

                Menu.Flow.id -> {
                    replaceFragment(FlowFragment(R.string.flow))
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }
        moveToHome()
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.Home.id
    }

    fun printBackStack() {
        for (index in 0 until supportFragmentManager.backStackEntryCount) {
            Log.e(TAG, "${supportFragmentManager.getBackStackEntryAt(index)}")
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}