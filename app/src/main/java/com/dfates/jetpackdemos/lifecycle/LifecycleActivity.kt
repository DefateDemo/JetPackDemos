package com.dfates.jetpackdemos.lifecycle

import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.base.Priority
import com.dfates.jetpackdemos.base.RunPriority

class LifecycleActivity : BaseActivity(R.layout.activity_lifecycle) {


    @RunPriority(Priority.HIGH)
    override fun initView() {
        super.initView()
        lifecycle.addObserver(TestLifecycleObserver(this))
    }

}
