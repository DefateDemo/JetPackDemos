package com.dfates.jetpackdemos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 基本Fragment，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseFragment(val layoutId: Int, override val bindType: Int= BIND_ALL) : Fragment(), IViewInit {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAll()   //初始化绑定，以及调用initView,initListener,initData方法
    }
}