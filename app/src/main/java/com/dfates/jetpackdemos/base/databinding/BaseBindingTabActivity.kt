package com.dfates.jetpackdemos.base.databinding

import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.ITabFragment
import com.dfates.jetpackdemos.common.bind.BindView
import com.google.android.material.tabs.TabLayout

/**
 * Created by $USER_NAME on 2018/12/13.
 */

abstract class BaseBindingTabActivity<VB : ViewDataBinding>(layoutId: Int) : BaseBindingActivity<VB>(layoutId), ITabFragment {

    @BindView(R.id.tab_titles)
    lateinit var tabLayout: TabLayout
    @BindView(R.id.vp_content)
    lateinit var viewPager: ViewPager

    override fun initView() {
        super.initView()
        initTab(this, tabLayout, viewPager, supportFragmentManager)
    }

}