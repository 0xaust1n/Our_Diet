package com.odstudio.ourdiet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class Home : AppCompatActivity() {
    private var finished:Boolean = false
    //.....分割線開始 Left Navigation
    private lateinit var mDrawerLayout: DrawerLayout
    //.....分割線開始 Bottom Navigation Click Listener
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
        verifyAlreadyLoggedout()
        verifyUserIsLoggedIn()
        //Set Default Fragment => Home Fragment
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
        // Declare BottomNavigation on Create event
        val bottomNavi: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavi.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //------------------------------------------------------------------------------------
        // Starting Below For Drawer Navigation
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_menu_white_24dp)
        }
        mDrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.daw_personal -> {
                    val personalFragment = PersonalFragment.newInstance()
                    openFragment(personalFragment)
                }
                R.id.daw_setting -> {
                    Toast.makeText(this, "@string/setting", Toast.LENGTH_LONG).show()
                }
                R.id.daw_logout -> {
                    logout()
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun logout(){
        finished = true
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)

            }
    }
    private fun verifyAlreadyLoggedout(){
        if (finished) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    private fun verifyUserIsLoggedIn() {
        val logged = FirebaseAuth.getInstance().currentUser
        if (logged == null) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}