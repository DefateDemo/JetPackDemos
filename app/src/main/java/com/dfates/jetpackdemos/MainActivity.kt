package com.dfates.jetpackdemos

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dfates.jetpackdemos.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            navController.navigate(R.id.mainFragment)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        navController = Navigation.findNavController(this, R.id.mainFragment)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            //Foundation
            R.id.appCompat -> {
                // Handle the camera action
            }
            R.id.android_ktx -> {

            }
            R.id.multidex -> {

            }
            R.id.test -> {

            }
            //Architecture
            R.id.data_binding -> {
                navController.navigate(R.id.dataBindingFragment)
            }
            R.id.lifecycles -> {

            }
            R.id.livedata -> {
                navController.navigate(R.id.liveDataFragment)
            }
            R.id.navigation -> {
                navController.navigate(R.id.firstFragment)
            }
            R.id.room -> {
                navController.navigate(R.id.roomFragment)
            }
            R.id.viewModel -> {
                navController.navigate(R.id.liveDataFragment)
            }
            R.id.workManager -> {

            }
            R.id.paging -> {

            }
            //UI
            R.id.animations_transitions -> {

            }
            R.id.fragment -> {

            }
            R.id.palette -> {

            }
            R.id.layout -> {

            }
            //Behavior
            R.id.download_mananger -> {

            }
            R.id.media_playback -> {

            }
            R.id.permissions -> {

            }
            R.id.notification -> {

            }
            R.id.sharing -> {

            }
            R.id.slices -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}