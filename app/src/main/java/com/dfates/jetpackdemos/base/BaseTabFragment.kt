package com.dfates.jetpackdemos.base

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/13.
 */
abstract class BaseTabFragment(layoutId: Int) : BaseFragment(layoutId), ITabFragment {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun initView() {
        super.initView()
        initTab(context!!, tabLayout, viewPager, childFragmentManager)
    }

}