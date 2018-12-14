package com.dfates.jetpackdemos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本Activity，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
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
