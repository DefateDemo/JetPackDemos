package com.dfates.jetpackdemos.common.bind

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.ifNull
import com.dfates.jetpackdemos.common.ifTrue
import com.dfates.jetpackdemos.common.next
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

const val BIND_NONE = 0x00
const val BIND_VIEW = 0x01
const val BIND_ON_CLICK = 0x02
const val BIND_PARAM = 0x04
const val BIND_VIEW_MODEL = 0x08
const val BIND_ALL = (BIND_VIEW or BIND_ON_CLICK or BIND_PARAM or BIND_VIEW_MODEL)


interface IBind {

    //绑定
    fun initBind(bindType: Int) {
        val bView = bindType and BIND_VIEW == BIND_VIEW
        val bParam = bindType and BIND_PARAM == BIND_PARAM
        val bViewModel = bindType and BIND_VIEW_MODEL == BIND_VIEW_MODEL
        val bOnCLick = bindType and BIND_ON_CLICK == BIND_ON_CLICK

        if (bView || bParam || bViewModel) {
            getFields().forEach { field ->
                //初始化绑定View对象
                bView.ifTrue {
                    field.getAnnotation(BindView::class.java).ifNotNull { bindView ->
                        bindView(field, bindView)
                    }
                }
                //初始化绑定参数
                bParam.ifTrue {
                    field.getAnnotation(BindParam::class.java).ifNotNull { bindParam ->
                        bindParam(field, bindParam)
                    }
                }
                //初始化绑定ViewModel对象
                bViewModel.ifTrue {
                    field.getAnnotation(BindViewModel::class.java).ifNotNull { bindViewModel ->
                        bindViewModel(field, bindViewModel)
                    }
                }
            }
        }
        if (bOnCLick) {
            getMethods().forEach { method ->
                //初始化View对象点击事件
                bOnCLick.ifTrue {
                    method.getAnnotation(BindOnClick::class.java).ifNotNull { bindOnClick ->
                        bindOnClick(method, bindOnClick)
                    }
                }
            }
        }
    }

    //绑定View
    private fun bindView(field: Field, bindView: BindView) {
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

    //绑定点击事件
    private fun bindOnClick(method: Method, bindOnClick: BindOnClick) {
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

    //绑定参数
    private fun bindParam(field: Field, bindParam: BindParam) {
        getParam(bindParam.key).ifNotNull { value ->
            field.isAccessible = true
            field.set(this@IBind, value)
        }
    }

    //绑定ViewModel
    private fun bindViewModel(field: Field, bindViewModel: BindViewModel) {
        @Suppress("UNCHECKED_CAST")
        getViewModel(field.type as Class<ViewModel>).next(RuntimeException("Can't find the ViewModel of class ${field.type}")) { viewModel ->
            field.isAccessible = true
            field.set(this@IBind, viewModel)
        }
    }

    //根据Id获取View
    private fun getView(id: Int): View {
        return when {
            this is Activity -> findViewById<View>(id).ifNull { throw RuntimeException("Can't find the view from id: $id") }
            this is Fragment -> view!!.findViewById<View>(id).ifNull { throw RuntimeException("Can't find the view from id: $id") }
            else -> throw RuntimeException("this class must be Activity or Fragment")
        }
    }

    //根据key获取传入的参数
    private fun getParam(key: String): Any? {
        return when {
            this is Activity -> if (intent?.extras == null || !intent.extras!!.containsKey(key)) null else intent.extras!![key]
            this is Fragment -> if (arguments == null || !arguments!!.containsKey(key)) null else arguments!![key]
            else -> throw RuntimeException("this class must be Activity or Fragment")
        }
    }

    //根据class获取ViewModel
    private fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return when {
            this is FragmentActivity -> ViewModelProviders.of(this).get(clazz)
            this is Fragment -> ViewModelProviders.of(this).get(clazz)
            else -> throw RuntimeException("this class must be FragmentActivity or Fragment")
        }
    }

    //获取所有字段，包括父类的字段
    private fun getFields(): List<Field> {
        val fields = LinkedList<Field>()
        var tempClazz: Class<*>? = this.javaClass
        val exceptClasss = arrayOf(Any::class.java, Fragment::class.java, Activity::class.java)
        while (tempClazz != null && tempClazz !in exceptClasss) {
            fields.addAll(tempClazz.declaredFields)
            tempClazz = tempClazz.superclass
        }
        return fields
    }

    // 获取所有方法，包括父类的方法
    private fun getMethods(): List<Method> {
        val methods = LinkedList<Method>()
        var tempClazz: Class<*>? = this.javaClass
        val exceptClasss = arrayOf(Any::class.java, Fragment::class.java, Activity::class.java)
        while (tempClazz != null && tempClazz !in exceptClasss) {
            methods.addAll(tempClazz.declaredMethods)
            tempClazz = tempClazz.superclass
        }
        return methods
    }

}
