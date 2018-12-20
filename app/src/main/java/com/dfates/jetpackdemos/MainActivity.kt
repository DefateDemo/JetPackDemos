package com.dfates.jetpackdemos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.base.Priority
import com.dfates.jetpackdemos.base.RunPriority
import com.dfates.jetpackdemos.bindTest.BindTestActivity
import com.dfates.jetpackdemos.common.gotoActivity
import com.dfates.jetpackdemos.common.snackbarShow
import com.dfates.jetpackdemos.lifecycle.LifecycleActivity
import com.dfates.jetpackdemos.room.entity.User
import com.dfates.jetpackdemos.viewModel.ViewModelActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(R.layout.activity_main), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController


    @RunPriority(Priority.HIGH)
    override fun initView() {
        super.initView()
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navController = Navigation.findNavController(this, R.id.mainFragment)
    }

    @RunPriority(Priority.NORMAL)
    override fun initListener() {
        super.initListener()
        fab.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
        nav_view.setNavigationItemSelectedListener(this)

    }

    @RunPriority(Priority.LOW)
    override fun initData() {
        super.initData()
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
                snackbarShow("Not Found Impl")
            }
            R.id.android_ktx -> {
                snackbarShow("Not Found Impl")
            }
            R.id.multidex -> {
                snackbarShow("Not Found Impl")
            }
            R.id.test -> {
                snackbarShow("Not Found Impl")
            }
            //Architecture
            R.id.bind_test -> {
                val bundle = Bundle()
                bundle.putString("value_string", "hello")
                bundle.putInt("value_int", 11)
                bundle.putSerializable("value_user", User(null,10,"1234"))
//                gotoActivity(BindTestActivity::class.java,bundle)
                navController.navigate(R.id.bindTestFragment, bundle)
            }
            R.id.data_binding -> {
                navController.navigate(R.id.dataBindingFragment)
            }
            R.id.lifecycles -> {
//                gotoActivity(LifecycleActivity::class.java)
                navController.navigate(R.id.lifecycleFragment)
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
//                gotoActivity(ViewModelActivity::class.java)
//                navController.navigate(R.id.viewModelFragment)
                navController.navigate(R.id.parentViewModelFragment)
            }
            R.id.workManager -> {
                snackbarShow("Not Found Impl")
            }
            R.id.paging -> {
                snackbarShow("Not Found Impl")
            }
            //UI
            R.id.animations_transitions -> {
                snackbarShow("Not Found Impl")
            }
            R.id.fragment -> {
                snackbarShow("Not Found Impl")
            }
            R.id.palette -> {
                snackbarShow("Not Found Impl")
            }
            R.id.layout -> {
                snackbarShow("Not Found Impl")
            }
            //Behavior
            R.id.download_mananger -> {
                snackbarShow("Not Found Impl")
            }
            R.id.media_playback -> {
                snackbarShow("Not Found Impl")
            }
            R.id.permissions -> {
                snackbarShow("Not Found Impl")
            }
            R.id.notification -> {
                snackbarShow("Not Found Impl")
            }
            R.id.sharing -> {
                snackbarShow("Not Found Impl")
            }
            R.id.slices -> {
                snackbarShow("Not Found Impl")
            }

            R.id.retrofit -> {
                navController.navigate(R.id.retrofitFragment)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
