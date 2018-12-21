package com.dfates.jetpackdemos.base

import androidx.viewpager.widget.ViewPager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.common.bind.BindView
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/13.
 */
abstract class BaseTabFragment(layoutId: Int) : BaseFragment(layoutId), ITabFragment {

    @BindView(R.id.tab_titles)
    protected open lateinit var tabLayout: TabLayout
    @BindView(R.id.vp_content)
    protected open lateinit var viewPager: ViewPager

    override fun initView() {
        super.initView()
        initTab(context!!, tabLayout, viewPager, childFragmentManager)
    }

}