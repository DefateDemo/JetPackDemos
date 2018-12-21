package com.dfates.jetpackdemos.proxy

import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.common.ifNotNull
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Statement(val name: String = "")

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Param(val name: String = "")

interface ICommonQuery {
    @Statement("statement1")
    fun fun1()

    @Statement("statement2")
    fun fun2(@Param("userAcct") userAcct: String)

    @Statement("statement3")
    fun fun3(): String?

    @Statement("statement4")
    fun fun4(@Param("userAcct") userAcct: String): String?
}

inline fun <reified T> Any.createProxy(): T {
    @Suppress("UNCHECKED_CAST")
    return Proxy.newProxyInstance(this.javaClass.classLoader, arrayOf(T::class.java)) { proxy: Any?, method: Method?, args: Array<out Any>? ->
        println("_____________________________________________")
        val statement = method?.getAnnotation(Statement::class.java)?.name
        val parameters = ArrayList<String>()
        args?.forEachIndexed { index, arg ->
            (method?.parameterAnnotations?.get(index)?.find { it is Param } as Param?)?.ifNotNull { annotation ->
                parameters.add(annotation.name + ":" + arg.toString())
            }
        }
        println("$statement -> ")
        parameters.forEach(::println)

        return@newProxyInstance when (method?.returnType) {
            Unit::class.java -> Unit
            String::class.java -> "result = " + statement
            else -> {
                null
            }
        }
    } as T
}

class ProxyTestFragment : BaseFragment(R.layout.fragment_proxy_test) {

    override fun initData() {
        super.initData()
        val proxy = createProxy<ICommonQuery>()
        proxy.fun1()
        proxy.fun2("hello")
        var res = proxy.fun3() ?: "null"
        println(res.toString())
        res = proxy.fun4("hello") ?: "null"
        println(res.toString())
    }
}
