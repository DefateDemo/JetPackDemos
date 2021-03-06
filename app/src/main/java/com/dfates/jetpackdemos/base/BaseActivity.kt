package com.dfates.jetpackdemos.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dfates.jetpackdemos.common.bind.BIND_ALL
import com.dfates.jetpackdemos.common.bind.BIND_PARAM
import com.dfates.jetpackdemos.common.bind.IBind

/**
 * 基本Activity，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseActivity(val layoutId: Int) : AppCompatActivity(), IViewInit, IBind {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initBind(BIND_ALL)  //初始化绑定
        initAll()           //调用initView,initListener,initData方法
    }

    protected open fun setContentView() {
        setContentView(layoutId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initBind(BIND_PARAM)
    }

}
