package com.devgunhee.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.devgunhee.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Log.d("", "homeSelcted")
                    return@setOnItemSelectedListener true
                }
                R.id.dual -> {
                    Log.d("", "dualSelcted")
                    return@setOnItemSelectedListener true
                }
                R.id.flow -> {
                    Log.d("", "flowSelcted")
                    return@setOnItemSelectedListener true
                }
                else -> {
                    Log.d("", "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }
    }
}