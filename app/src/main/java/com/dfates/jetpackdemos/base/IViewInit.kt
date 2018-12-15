package com.dfates.jetpackdemos.base

import android.view.View
import androidx.lifecycle.ViewModel
import com.dfates.jetpackdemos.common.bindView.BindView
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.ifNull
import com.dfates.jetpackdemos.common.param.BindParam
import com.dfates.jetpackdemos.common.runPriority.Priority
import com.dfates.jetpackdemos.common.runPriority.RunPriority
import com.dfates.jetpackdemos.common.viewModel.BindViewModel


/**
 * 视图初始化接口，已经默认实现
 */
interface IViewInit {
    fun init() {
        initBindView()      //初始化绑定View对象
        initBindViewModel() //初始化绑定ViewModel对象
        initBindParam()     //初始化绑定参数

        val methods = arrayOf(javaClass.getMethod("initView"),
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

    //初始化绑定View对象
    fun initBindView() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindView::class.java).ifNotNull { bindView ->
                bindView.id.ifNull {
                    throw RuntimeException("please set view id")
                }.ifNotNull {
                    field.isAccessible = true
                    field.set(this, getViewById(bindView.id))
                }
            }
        }
    }

    //初始化绑定ViewModel对象
    fun initBindViewModel() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindViewModel::class.java).ifNotNull {
                field.isAccessible = true
                field.set(this, getViewModel(field?.type))
            }
        }
    }

    //初始化绑定参数
    fun initBindParam() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindParam::class.java).ifNotNull { bindParam ->
                bindParam.key.ifNull {
                    throw RuntimeException("please set key")
                }.ifNotNull {
                    field.isAccessible = true
                    val value = getParam(bindParam.key, field.type)
                    if (value != null) {
                        field.set(this, value)
                    }
                }
            }
        }
    }

    //获取传入的参数
    fun getParam(key: String, clazz: Class<*>): Any?

    //根据id获取View
    fun <T : View> getViewById(id: Int): T?

    //获取ViewModel
    fun getViewModel(type: Class<*>?): ViewModel

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
