package com.dfates.jetpackdemos.lifecycle

import android.os.Bundle
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.base.Priority
import com.dfates.jetpackdemos.base.RunPriority
import com.dfates.jetpackdemos.R
class LifecycleActivity : BaseActivity(R.layout.activity_lifecycle) {

    @RunPriority(Priority.HIGH)
    override fun initView() {
        super.initView()
        lifecycle.addObserver(TestLifecycleObserver(this))
    }
}
