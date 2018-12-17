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

const val BIND_NONE = 0x00
const val BIND_VIEW = 0x01
const val BIND_VIEW_ON_CLICK = 0x02
const val BIND_PARAM = 0x04
const val BIND_VIEW_MODEL = 0x08
const val BIND_ALL = (BIND_VIEW or BIND_VIEW_ON_CLICK or BIND_PARAM or BIND_VIEW_MODEL)

/**
 * 视图初始化接口
 */
interface IViewInit {
    val bindType: Int

    //初始化所有
    fun initAll() {
        if (bindType and BIND_VIEW == BIND_VIEW) {
            initBindView()      //初始化绑定View对象
        }
        if (bindType and BIND_VIEW_ON_CLICK == BIND_VIEW_ON_CLICK) {
            initBindClick()      //初始化View对象点击事件
        }
        if (bindType and BIND_PARAM == BIND_PARAM) {
            initBindParam()     //初始化绑定参数
        }
        if (bindType and BIND_VIEW_MODEL == BIND_VIEW_MODEL) {
            initBindViewModel() //初始化绑定ViewModel对象
        }

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
                bindView.id.next(RuntimeException("Please declare the id on the BindView annotation")) { id ->
                    getView(id).ifNotNull { view ->
                        field.isAccessible = true
                        field.set(this, view)
                        if (bindView.onClick.isNotEmpty()) {
                            try {
                                val method = javaClass.getMethod(bindView.onClick, View::class.java)
                                view.setOnClickListener {
                                    method.invoke(this, it)
                                }
                            } catch (e: NoSuchMethodException) {
                                try {
                                    val method = javaClass.getMethod(bindView.onClick)
                                    view.setOnClickListener {
                                        method.invoke(this)
                                    }
                                } catch (e: NoSuchMethodException) {
                                    throw RuntimeException("Can't find method: " + bindView.onClick + " on " + field.declaringClass.canonicalName)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //绑定监听事件
    fun initBindClick() {
        javaClass.declaredMethods.forEach { method ->
            method.getAnnotation(BindOnClick::class.java).ifNotNull { bindClick ->
                bindClick.ids.forEach { id ->
                    getView(id).ifNotNull { view ->
                        if (method.parameterTypes.isEmpty()) {
                            view.setOnClickListener {
                                method.invoke(this)
                            }
                        } else if (method.parameterTypes.size == 1 && method.parameterTypes[0] is View) {
                            view.setOnClickListener {
                                method.invoke(this, it)
                            }
                        }
                    }
                }
            }
        }
    }

    //初始化绑定参数
    fun initBindParam() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindParam::class.java).ifNotNull { bindParam ->
                bindParam.key.next(RuntimeException("Please declare the key on the BindParam annotation")) { key ->
                    getParam(key).ifNotNull { value ->
                        field.isAccessible = true
                        field.set(this, value)
                    }
                }
            }
        }
    }

    //初始化绑定ViewModel对象
    fun initBindViewModel() {
        javaClass.declaredFields.forEach { field ->
            field.getAnnotation(BindViewModel::class.java).ifNotNull {
                @Suppress("UNCHECKED_CAST")
                getViewModel(field!!.type as Class<ViewModel>).next(RuntimeException("Can't find the ViewModel of class " + field.type)) { viewModel ->
                    field.isAccessible = true
                    field.set(this, viewModel)
                }
            }
        }
    }

    //根据Id获取View
    fun getView(id: Int): View {
        return when {
            this is Activity -> findViewById(id)!!
            this is Fragment -> view?.findViewById(id)!!
            else -> throw RuntimeException("this class must be Activity or Fragment")
        }
    }

    //根据key获取传入的参数
    fun getParam(key: String): Any? {
        return when {
            this is Activity -> if (intent?.extras == null || !intent.extras!!.containsKey(key)) null else intent.extras!![key]
            this is Fragment -> if (arguments == null || !arguments!!.containsKey(key)) null else arguments!![key]
            else -> throw RuntimeException("this class must be Activity or Fragment")
        }
    }

    //根据class获取ViewModel
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return when {
            this is FragmentActivity -> ViewModelProviders.of(this).get(clazz)
            this is Fragment -> ViewModelProviders.of(this).get(clazz)
            else -> throw RuntimeException("this class must be FragmentActivity or Fragment")
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
