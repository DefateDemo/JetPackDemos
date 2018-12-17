package com.dfates.jetpackdemos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dfates.jetpackdemos.common.bind.BIND_ALL
import com.dfates.jetpackdemos.common.bind.IBind

/**
 * 基本Fragment，实现了IViewInit接口，可以继承initView，initListener，initData方法，使用这些方法时可以使用RunPriority注解声明方法执行顺序
 */
abstract class BaseFragment(val layoutId: Int, override val bindType: Int = BIND_ALL) : Fragment(), IViewInit, IBind {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBind()  //初始化绑定
        initAll()   //调用initView,initListener,initData方法
    }
}