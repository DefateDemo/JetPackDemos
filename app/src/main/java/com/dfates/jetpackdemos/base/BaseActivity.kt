package com.dfates.jetpackdemos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(val layoutId: Int) : AppCompatActivity(), IViewInit {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        init()
    }

    protected open fun setContentView() {
        setContentView(layoutId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
