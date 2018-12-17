package com.dfates.jetpackdemos.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseIntArray
import android.view.View
import android.widget.Toast
import androidx.core.util.set
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar


/**
 * Created by $USER_NAME on 2018/12/8.
 */

//不论该对象是否为空都执行,并返回执行结果
inline fun <T, R> T.next(function: (T) -> R): R {
    return function(this)
}

//不论该对象是否为空都执行,并返回执行结果
inline fun <T> T?.next(nullConsumer: () -> Unit, consumer: (T) -> Unit): T? {
    if (this != null) consumer(this)
    else nullConsumer()
    return this
}

//该对象为空抛异常,否则执行并返回结果
inline fun <T> T?.next(e: Exception, consumer: (T) -> Unit): T? {
    if (this != null) consumer(this)
    else throw e
    return this
}

//当某一对象为空时执行，否则不执行
inline fun <T> T.ifNull(consumer: () -> Unit): T {
    if (this == null) {
        consumer()
    }
    return this
}

//当某一对象不为空时执行，否则不执行
public inline fun <T> T?.ifNotNull(consumer: (T) -> Unit): T? {
    if (this != null) {
        consumer(this)
    }
    return this
}

//为真时执行闭包，否则不执行
inline fun Boolean?.ifTrue(consumer: () -> Unit): Boolean? {
    if (this != null && this) {
        consumer()
    }
    return this
}

//为假时执行闭包，否则不执行
inline fun Boolean?.ifFalse(consumer: () -> Unit): Boolean? {
    if (this != null && !this) {
        consumer()
    }
    return this
}

//跳转到其他Activity
fun <T : Context, T2 : Activity> T.gotoActivity(cls: Class<T2>, bundle: Bundle? = null) {
    val intent = Intent(this, cls)
    bundle.ifNotNull { intent.putExtras(it) }
    startActivity(intent)
}

//使用Snackbar显示
fun <T : View> T.snackbarShow(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
}

//使用Snackbar显示
fun <T : Activity> T.snackbarShow(text: String) {
    getCurrentFocus().ifNotNull { view ->
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}

//使用Toast显示
fun <T : Context> T.toastShow(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

//使用Toast显示
fun <T : Fragment> T.toastShow(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

//获取ViewModel
inline fun <T : Fragment, reified R : ViewModel> T.getViewModel(): R {
    return ViewModelProviders.of(this).get(R::class.java)
}

//获取ViewModel
inline fun <T : FragmentActivity, reified R : ViewModel> T.getViewModel(): R {
    return ViewModelProviders.of(this).get(R::class.java)
}

//fragment根据id获取view对象
fun <T : Fragment, R : View?> T.findViewById(id: Int): R? {
    return view?.findViewById<R>(id)
}

//转化
fun Array<Pair<Int, Int>>.toSparseIntArray(): SparseIntArray {
    val sparseArray = SparseIntArray()
    this.forEach {
        sparseArray[it.first] = it.second
    }
    return sparseArray
}