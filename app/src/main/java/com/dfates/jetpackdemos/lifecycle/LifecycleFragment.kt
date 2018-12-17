package com.dfates.jetpackdemos.lifecycle


import android.os.Bundle
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.base.Priority
import com.dfates.jetpackdemos.base.RunPriority


class LifecycleFragment : BaseFragment(R.layout.fragment_lifecycle) {

    @RunPriority(Priority.HIGH)
    override fun initView() {
        super.initView()
        lifecycle.addObserver(TestLifecycleObserver(context))
    }

}
