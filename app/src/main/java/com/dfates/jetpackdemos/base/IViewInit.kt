package com.dfates.jetpackdemos.base

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.common.bind.BindOnClick
import com.dfates.jetpackdemos.common.bind.BindParam
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.common.bind.BindViewModel
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.next

//运行优先级级别，优先级越高越先执行
enum class Priority {
    HIGH, NORMAL, LOW
}

//运行优先级注解，定义在initView,initListener,initData等方法上
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RunPriority(val value: Priority)


/**
 * 视图初始化接口
 */
interface IViewInit {

    //初始化所有
    fun initAll() {
        val methods = arrayOf(
                javaClass.getMethod("initView"),
                javaClass.getMethod("initListener"),
                javaClass.getMethod("initData"))

        methods.map { method ->
            method to method.getAnnotation(RunPriority::class.java)
        }.sortedBy {
            it.second?.value ?: Priority.NORMAL
        }.forEach {
            it.first.invoke(this)
        }
    }


    /**
     * 初始化视图
     */
    fun initView() {}

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 初始化数据
     */
    fun initData() {}
}
