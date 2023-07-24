package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /**
                 * TODO
                 *  if Home Fragment finish
                 *  else replace Home Fragment
                  */
            }
        })

        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.dual -> {
                    replaceFragment(DualFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.flow -> {
                    replaceFragment(FlowFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    Log.e("", "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}