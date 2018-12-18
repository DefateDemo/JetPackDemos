package com.dfates.jetpackdemos.base

import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.dfates.jetpackdemos.common.findViewById
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/13.
 */
abstract class BaseTabFragment<VB : ViewDataBinding>(layoutId: Int, val tabLayoutId: Int, val viewPagerId: Int) : BaseBindingFragment<VB>(layoutId), ITabFragment {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun initView() {
        super.initView()

        tabLayout = findViewById(tabLayoutId)!!
        viewPager = findViewById(viewPagerId)!!

        initTab(context!!, tabLayout, viewPager, childFragmentManager)
    }

}