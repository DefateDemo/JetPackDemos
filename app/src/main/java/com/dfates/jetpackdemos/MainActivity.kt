package com.dfates.jetpackdemos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.dfates.jetpackdemos.common.gotoActivity
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.navigate
import com.dfates.jetpackdemos.databinding.DataBindingActivity
import com.dfates.jetpackdemos.liveData.LiveDataActivity
import com.dfates.jetpackdemos.room.RoomActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
                navigate(R.id.mainFragment,R.id.action_mainFragment_to_dataBindingFragment)
            }
            R.id.lifecycles -> {

            }
            R.id.livedata -> {
                navigate(R.id.mainFragment,R.id.action_mainFragment_to_liveDataFragment)
            }
            R.id.navigation -> {
                navigate(R.id.mainFragment,R.id.action_mainFragment_to_firstFragment)
            }
            R.id.room -> {
                navigate(R.id.mainFragment,R.id.action_mainFragment_to_roomFragment)
            }
            R.id.viewModel -> {

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
        return Navigation.findNavController(this, R.id.mainFragment).navigateUp()
    }
}