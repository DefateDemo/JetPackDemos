package com.dfates.jetpackdemos.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本Activity，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseActivity(val layoutId: Int, override val bindType: Int = BIND_ALL) : AppCompatActivity(), IViewInit {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initAll()   //初始化绑定，以及调用initView,initListener,initData方法
    }

    protected open fun setContentView() {
        setContentView(layoutId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (bindType and BIND_PARAM == BIND_PARAM) {
            initBindParam()
        }
    }

}
