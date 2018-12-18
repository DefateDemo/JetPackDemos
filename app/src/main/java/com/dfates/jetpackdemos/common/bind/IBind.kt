package com.dfates.jetpackdemos.common.bind

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.ifTrue
import com.dfates.jetpackdemos.common.next
import java.lang.reflect.Field
import java.lang.reflect.Method

const val BIND_NONE = 0x00
const val BIND_VIEW = 0x01
const val BIND_VIEW_ON_CLICK = 0x02
const val BIND_PARAM = 0x04
const val BIND_VIEW_MODEL = 0x08
const val BIND_ALL = (BIND_VIEW or BIND_VIEW_ON_CLICK or BIND_PARAM or BIND_VIEW_MODEL)

interface IBind {

    fun initBind(bindType: Int) {
        if (bindType and BIND_VIEW == BIND_VIEW || bindType and BIND_PARAM == BIND_PARAM || bindType and BIND_VIEW_MODEL == BIND_VIEW_MODEL) {
            javaClass.declaredFields.forEach { field ->
                //初始化绑定View对象
                (bindType and BIND_VIEW == BIND_VIEW).ifTrue {
                    field.getAnnotation(BindView::class.java).ifNotNull { bindView ->
                        bindView(field, bindView)
                    }
                }
                //初始化绑定参数
                (bindType and BIND_PARAM == BIND_PARAM).ifTrue {
                    field.getAnnotation(BindParam::class.java).ifNotNull { bindParam ->
                        bindParam(field, bindParam)
                    }
                }
                //初始化绑定ViewModel对象
                (bindType and BIND_VIEW_MODEL == BIND_VIEW_MODEL).ifTrue {
                    field.getAnnotation(BindViewModel::class.java).ifNotNull { bindViewModel ->
                        bindViewModel(field, bindViewModel)
                    }
                }
            }
        }
        if (bindType and BIND_VIEW_ON_CLICK == BIND_VIEW_ON_CLICK) {
            javaClass.declaredMethods.forEach { method ->
                //初始化View对象点击事件
                (bindType and BIND_VIEW_ON_CLICK == BIND_VIEW_ON_CLICK).ifTrue {
                    method.getAnnotation(BindOnClick::class.java).ifNotNull { bindOnClick ->
                        bindOnClick(method, bindOnClick)
                    }
                }
            }
        }
    }

    fun bindView(field: Field, bindView: BindView) {
        val view: View = getView(bindView.id)
        field.isAccessible = true
        field.set(this@IBind, view)
        if (bindView.onClick.isNotEmpty()) {
            try {
                val method = javaClass.getMethod(bindView.onClick, View::class.java)
                view.setOnClickListener {
                    method.invoke(this@IBind, it)
                }
            } catch (e: NoSuchMethodException) {
                try {
                    val method = javaClass.getMethod(bindView.onClick)
                    view.setOnClickListener {
                        method.invoke(this@IBind)
                    }
                } catch (e: NoSuchMethodException) {
                    throw RuntimeException("Can't find method: " + bindView.onClick + " on " + field.declaringClass.canonicalName)
                }
            }
        }
    }

    fun bindOnClick(method: Method, bindOnClick: BindOnClick) {
        bindOnClick.ids.forEach { id ->
            getView(id).ifNotNull { view ->
                if (method.parameterTypes.isEmpty()) {
                    view.setOnClickListener {
                        method.invoke(this@IBind)
                    }
                } else if (method.parameterTypes.size == 1 && method.parameterTypes[0] == View::class.java) {
                    view.setOnClickListener {
                        method.invoke(this@IBind, it)
                    }
                }
            }
        }
    }

    fun bindParam(field: Field, bindParam: BindParam) {
        getParam(bindParam.key).ifNotNull { value ->
            field.isAccessible = true
            field.set(this@IBind, value)
        }
    }

    fun bindViewModel(field: Field, bindViewModel: BindViewModel) {
        @Suppress("UNCHECKED_CAST")
        getViewModel(field.type as Class<ViewModel>).next(RuntimeException("Can't find the ViewModel of class " + field.type)) { viewModel ->
            field.isAccessible = true
            field.set(this@IBind, viewModel)
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
}
