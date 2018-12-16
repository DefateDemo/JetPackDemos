package com.dfates.jetpackdemos.base

import android.view.View
import androidx.lifecycle.ViewModel
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
        initBindView()      //初始化绑定View对象
        initBindViewModel() //初始化绑定ViewModel对象
        initBindParam()     //初始化绑定参数

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

    //初始化绑定View对象
    fun initBindView() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindView::class.java).ifNotNull { bindView ->
                bindView.id.next(RuntimeException("Please declare the id on the BindView annotation")) { it ->
                    val view: View = getViewById(it)!!
                    field.isAccessible = true
                    field.set(this, view)
                    if (bindView.onClick.isNotEmpty()) {
                        try {
                            val method = javaClass.getMethod(bindView.onClick, View::class.java)
                            view.setOnClickListener {
                                method.invoke(this, it)
                            }
                        } catch (e: NoSuchMethodException) {
                            throw RuntimeException("Can't find method: " + bindView.onClick + " on " + field.declaringClass.canonicalName)
                        }
                    }
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
                bindParam.key.next(RuntimeException("Please declare the key on the BindParam annotation")) {
                    getParam(bindParam.key, field.type).ifNotNull { value ->
                        field.isAccessible = true
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
