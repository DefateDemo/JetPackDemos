package com.dfates.jetpackdemos.base

import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.common.findViewById
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/13.
 */
abstract class BaseTabFragment<VB : ViewDataBinding>(layoutId: Int, val tabLayoutId: Int, val viewPagerId: Int) : BaseBindingFragment<VB>(layoutId) {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    protected abstract val pages: List<Pair<String, BaseFragment>>

    override fun initView() {
        super.initView()

        tabLayout = findViewById<TabLayout>(tabLayoutId)!!
        viewPager = findViewById<ViewPager>(viewPagerId)!!

        tabLayout.setTabMode(TabLayout.MODE_FIXED)
        tabLayout.setTabTextColors(ContextCompat.getColor(context!!, R.color.abc_background_cache_hint_selector_material_dark),
                ContextCompat.getColor(context!!, R.color.abc_background_cache_hint_selector_material_dark))
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context!!, R.color.abc_background_cache_hint_selector_material_dark))

//        binding.vpContent.setElevation(10);
        viewPager.setOffscreenPageLimit(pages.size)

        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment = pages[position].second
            override fun getCount(): Int = pages.size
            override fun getPageTitle(position: Int): CharSequence = pages[position].first
        }

        tabLayout.setupWithViewPager(viewPager)
    }

}