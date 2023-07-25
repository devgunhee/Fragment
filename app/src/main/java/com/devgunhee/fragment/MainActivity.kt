package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devgunhee.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    enum class Menu(val id: Int) {
        Home(R.id.home),
        Dual(R.id.dual),
        Flow(R.id.flow),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) is HomeFragment)
                    finish()
                else
                    binding.bottomNavigation.selectedItemId = Menu.Home.id
            }
        })

        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                Menu.Home.id -> {
                    replaceFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }

                Menu.Dual.id -> {
                    replaceFragment(DualFragment())
                    return@setOnItemSelectedListener true
                }

                Menu.Flow.id -> {
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