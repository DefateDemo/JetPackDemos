package com.dfates.jetpackdemos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(val layoutId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initView()
        initListener()
        initData()
    }

    protected open fun setContentView(){
        setContentView(layoutId)
    }

    protected open fun initView() {

    }

    protected open fun initListener() {

    }

    protected open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
