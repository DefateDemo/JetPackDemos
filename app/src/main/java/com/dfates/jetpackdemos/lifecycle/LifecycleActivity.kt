package com.dfates.jetpackdemos.lifecycle

import android.os.Bundle
import android.os.PersistableBundle
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity

class LifecycleActivity : BaseActivity(R.layout.activity_lifecycle) {

    override fun initView() {
        super.initView()
        lifecycle.addObserver(TestLifecycleObserver(this))
    }

}
