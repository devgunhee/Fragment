package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
                if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
                    finish()
                } else {
                    supportFragmentManager.popBackStack()
//                if (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) is HomeFragment)
//                    finish()
//                else
//                    moveToHome()
                }
            }
        })

        binding.bottomNavigation.setOnItemSelectedListener {
            Log.e(TAG, "Bottom Navigation Selected >> $it")
            when (it.itemId) {
                Menu.HomeFragment.itemId -> {
                    replaceFragment(Menu.HomeFragment)
                    return@setOnItemSelectedListener true
                }

                Menu.DualFragment.itemId -> {
                    replaceFragment(Menu.DualFragment)
                    return@setOnItemSelectedListener true
                }

                Menu.FlowFragment.itemId -> {
                    replaceFragment(Menu.FlowFragment)
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            moveToHome()
        }
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.HomeFragment.itemId
    }

    fun fragmentTest(menu: Menu) {

        // 현재 추가하려는 Fragment랑 보이고있는 Fragment가 같은지 비교
        supportFragmentManager.findFragmentById(binding.fragmentContainer.id)

        // if 추가하려는 Fragment가 Home이면?
        supportFragmentManager.popBackStack(Menu.HomeFragment.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // else
            supportFragmentManager.findFragmentByTag(menu.name)


            // 해당 Fragment 이미 있으면? pop하고 다시 넣기
            supportFragmentManager.popBackStackImmediate(menu.name, 0)
            //TODO 백스택에서 특정 백스택만 Pop하는 방법 없나? 특정 백스택 위의 모든 백스택이 다 제거 됨


            // 아니면 그냥 넣기

    }


    private fun replaceFragment(menu: Menu) {
        val fragment = getFragment(menu)
        Log.e(TAG, "replaceFragment >> $fragment")
        supportFragmentManager.beginTransaction()
            .setPrimaryNavigationFragment(fragment)
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(menu.name)
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