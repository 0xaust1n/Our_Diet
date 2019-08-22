package com.odstudio.ourdiet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class Home : AppCompatActivity() {
    //.....分割線開始 Left Navigation


    //.....分割線開始 Bottom Navigation Click Listener
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_groups -> {
                val groupsFragment = GroupsFragment.newInstance()
                openFragment(groupsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_friends -> {
                val friendsFragment = FriendsFragment.newInstance()
                openFragment(friendsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // start override function On Create

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Set Default Fragment => Home Fragment
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
        // Declare BottomNavigation on Create event
        val  bottomNavi: BottomNavigationView  = findViewById(R.id.navigationView)
        bottomNavi.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
