package com.dfates.jetpackdemos.base

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dfates.jetpackdemos.R
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/18.
 */

interface ITabFragment {

    val pages: List<Pair<String, Fragment>>

    fun initTab(context: Context, tabLayout: TabLayout, viewPager: ViewPager, fm: FragmentManager) {

        tabLayout.tabMode = if (pages.size > 4) TabLayout.MODE_SCROLLABLE else TabLayout.MODE_FIXED
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.colorTabTextNormal),
                ContextCompat.getColor(context, R.color.colorTabTextSelected))
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorSelectedTabIndicator))

//        binding.vpContent.setElevation(10);
        viewPager.offscreenPageLimit = pages.size

        viewPager.adapter = object : FragmentPagerAdapter(fm) {
            override fun getItem(position: Int): Fragment = pages[position].second
            override fun getCount(): Int = pages.size
            override fun getPageTitle(position: Int): CharSequence = pages[position].first
        }

        tabLayout.setupWithViewPager(viewPager)
    }
}