package com.dfates.jetpackdemos.lifecycle


import android.os.Bundle
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment


class LifecycleFragment : BaseFragment(R.layout.fragment_lifecycle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(TestLifecycleObserver(context))
        super.onCreate(savedInstanceState)
    }


}
