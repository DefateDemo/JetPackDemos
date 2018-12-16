package com.dfates.jetpackdemos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * 基本Fragment，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseFragment(val layoutId: Int) : Fragment(), IViewInit {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAll()
    }

    //实现根据id获取View
    override fun <T : View> getViewById(id: Int): T? = view?.findViewById(id)

    //实现获取ViewModel
    override fun getViewModel(clazz: Class<*>?): ViewModel {
        return ViewModelProviders.of(this).get(clazz as Class<ViewModel>)
    }

    //实现获取传入的参数
    override fun getParam(key: String, clazz: Class<*>): Any? {
        if (arguments != null && arguments!!.containsKey(key)) {
            return arguments!![key]
        }
        return null
    }
}