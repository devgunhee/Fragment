package com.devgunhee.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
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

        if (savedInstanceState == null) {
            replaceFragment(Menu.HomeFragment)
//            if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(binding.fragmentContainer.id, getFragment(Menu.HomeFragment), Menu.HomeFragment.name)
//                .commit()
//            supportFragmentManager.beginTransaction()
//                .setPrimaryNavigationFragment(fragment)
//                .replace(binding.fragmentContainer.id, fragment, menu.name)
//                .addToBackStack(menu.name)
//                .commit()
//            moveToHome()
//            }
        }

        binding.menu.setOnClickListener {
            Log.e(TAG, "MainActivity >> fragments ${supportFragmentManager.fragments}")
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                Log.e(
                    TAG,
                    "MainActivity >> $i supportFragmentManager Backstack ${
                        supportFragmentManager.getBackStackEntryAt(i)
                    }"
                )
            }

//            for (i in 0 until binding.bottomNavigation.menu.size) {
//                Log.e(
//                    TAG,
//                    "MainActivity >> $i Menu itemId ${binding.bottomNavigation.menu[i].itemId}"
//                )
//            }

//            Log.e(
//                TAG,
//                "MainActivity >> findFragmentbyTag ${supportFragmentManager.findFragmentByTag(Menu.FlowFragment.name)}"
//            )

//
//            binding.bottomNavigation.menu.findItem(Menu.FlowFragment.itemId)?.isChecked = true

//            replaceFragment(Menu.DualFragment)
//            replaceFragment(Menu.FlowFragment)
//            binding.bottomNavigation.menu.get(0).itemId
//            binding.bottomNavigation.menu.getItem(3).isChecked = true
        }

        //TODO BottomNavigationView popBackStack어떻게 대응하지?

        binding.menu2.setOnClickListener {
//            Log.e(TAG, "findFragmentByTag >> ${supportFragmentManager.findFragmentByTag(Menu.DualFragment.name)}, ${Menu.DualFragment.name}")
//
//            val fr = supportFragmentManager.findFragmentByTag(Menu.HomeFragment.name)

            val backStacks = mutableListOf<BackStackEntry>()

            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                backStacks.add(supportFragmentManager.getBackStackEntryAt(i))
            }

//            Log.e(TAG, "backStacks >> ${backStacks}")

//            removeFragment(Menu.DualFragment)
            supportFragmentManager.popBackStack(Menu.DualFragment.name, POP_BACK_STACK_INCLUSIVE)

//                    supportFragmentManager.popBack

//            supportFragmentManager.beginTransaction()
//                .remove(getFragment(Menu.HomeFragment))
//                .re
//                .commit()
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

//        binding.bottomNavigation.selectedItemId = 0

//        binding.bottomNavigation.menu.getItem(0).isChecked = true

        binding.bottomNavigation.setOnItemSelectedListener {
            Log.e(TAG, "Bottom Navigation Selected >> ${it.title}, ${it.itemId}")
            when (it.itemId) {
                Menu.HomeFragment.itemId -> {
                    fragmentTest(Menu.HomeFragment)
//                    replaceFragment(Menu.HomeFragment)
                    return@setOnItemSelectedListener true
                }

                Menu.DualFragment.itemId -> {
                    fragmentTest(Menu.DualFragment)
//                    replaceFragment(Menu.DualFragment)
                    return@setOnItemSelectedListener true
                }

                Menu.FlowFragment.itemId -> {
                    fragmentTest(Menu.FlowFragment)
//                    replaceFragment(Menu.FlowFragment)
                    return@setOnItemSelectedListener true
                }

                else -> {
                    Log.e(TAG, "nothing selected")
                    return@setOnItemSelectedListener false
                }
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {
            Log.e(TAG, "Bottom Navigation Reselected >> $it")
        }

//        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
//            moveToHome()
//        }
    }

    fun moveToHome() {
        binding.bottomNavigation.selectedItemId = Menu.HomeFragment.itemId
    }

    fun fragmentTest(menu: Menu) {

        //TODO Remove, popstack 활용해서 백스택 관리하는 로직 넣기

        val backStacks = mutableListOf<BackStackEntry>()

        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            backStacks.add(supportFragmentManager.getBackStackEntryAt(i))
        }

        Log.e(TAG, "[Test 1] >> $backStacks")

        val homeFilteredList = backStacks.filterIndexed { index, backStackEntry ->
            index != 0 || backStackEntry.name != Menu.HomeFragment.name
        }

        Log.e(TAG, "[Test 2] >> $homeFilteredList")

        homeFilteredList
            .find { it.name == menu.name }
            ?.run {
                // 디버깅용
                supportFragmentManager.popBackStackImmediate(menu.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                supportFragmentManager.popBackStack(menu.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                val backStacks2 = mutableListOf<BackStackEntry>()
                for (i in 0 until supportFragmentManager.backStackEntryCount) {
                    backStacks2.add(supportFragmentManager.getBackStackEntryAt(i))
                }

                Log.e(TAG, "[Test 3] >> $backStacks2")

                val homeFilteredFirst = homeFilteredList[0]

                homeFilteredList
                    .filterIndexed { index, _ -> index > homeFilteredList.indexOf(this) }
                    .forEachIndexed { index, backStackEntry ->
                        if (!((homeFilteredList[0].name == menu.name) && (index == 0 && backStackEntry.name == Menu.HomeFragment.name))) {
                            replaceFragment(Menu.valueOf(backStackEntry.name!!))
                        }
                    }
            }

        replaceFragment(menu)

        val backStacks3 = mutableListOf<BackStackEntry>()
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            backStacks3.add(supportFragmentManager.getBackStackEntryAt(i))
        }

        Log.e(TAG, "[Test 4] >> $backStacks3")

//
//        homeFilteredList.indexOfFirst { true }
//        val leftOverList =
//
//        //====================================================
//
//            // 현재 추가하려는 Fragment랑 보이고있는 Fragment가 같은지 비교 ==> Reselect로 대응가능?
//            supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
//
//        // if 추가하려는 Fragment가 Home이면?
//        supportFragmentManager.popBackStack(
//            Menu.HomeFragment.name,
//            FragmentManager.POP_BACK_STACK_INCLUSIVE
//        )
//
//        // else
//        supportFragmentManager.findFragmentByTag(menu.name)
//
//
//        // 해당 Fragment 이미 있으면? pop하고 다시 넣기
//        supportFragmentManager.popBackStackImmediate(menu.name, 0)
//        //TODO 백스택에서 특정 백스택만 Pop하는 방법 없나? 특정 백스택 위의 모든 백스택이 다 제거 됨


        // 아니면 그냥 넣기
    }


    private fun replaceFragment(menu: Menu) {
        val fragment = getFragment(menu)
        Log.e(TAG, "replaceFragment >> $fragment")
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
//            .setPrimaryNavigationFragment(fragment)
            .replace(binding.fragmentContainer.id, fragment, menu.name)
            .addToBackStack(menu.name)
            .commit()
    }

    private fun removeFragment(menu: Menu) {
        val fragment = getFragment(menu)
        Log.e(TAG, "removeFragment >> $fragment")
//        supportFragmentManager.beginTransaction()
//            .setReorderingAllowed(true)
//            .remove()
//            .commit()

        supportFragmentManager.findFragmentByTag(menu.name)?.let {
            supportFragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }

        supportFragmentManager.executePendingTransactions()


    }

    /**
     * get Fragment by Menu
     *
     * @sample [com.devgunhee.fragment.Menu]
     */
    fun getFragment(menu: Menu) = when (menu) {
        Menu.HomeFragment -> {
            HomeFragment()
        }

        Menu.DualFragment -> {
            DualFragment()
        }

        Menu.FlowFragment -> {
            FlowFragment()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}