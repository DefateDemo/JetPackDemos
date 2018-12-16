package com.dfates.jetpackdemos.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * 基本Activity，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseActivity(val layoutId: Int) : AppCompatActivity(), IViewInit {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initAll()
    }

    protected open fun setContentView() {
        setContentView(layoutId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initBindParam()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //实现根据id获取View
    override fun <T : View> getViewById(id: Int): T? = findViewById(id)

    //实现获取ViewModel
    override fun getViewModel(clazz: Class<*>?): ViewModel {
        return ViewModelProviders.of(this).get(clazz as Class<ViewModel>)
    }

    //实现获取传入的参数
    override fun getParam(key: String, clazz: Class<*>): Any? {
        if (intent?.extras != null && intent!!.extras.containsKey(key)) {
            return intent.extras[key]
        }
        return null
    }
}
