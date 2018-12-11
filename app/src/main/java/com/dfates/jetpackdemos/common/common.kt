package com.dfates.jetpackdemos.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


/**
 * Created by $USER_NAME on 2018/12/8.
 */

//不论该对象是否为空都执行,并返回执行结果
fun <T, R> T.next(function: (T) -> R): R {
    return function(this)
}

//不论该对象是否为空都执行,并返回执行结果
fun <T> T?.next(consumer: (T) -> Unit, nullConsumer: () -> Unit): T? {
    if (this != null) consumer(this)
    else nullConsumer()
    return this
}

//当某一对象为空时执行，否则不执行
fun <T> T.ifNull(consumer: () -> Unit): T {
    if (this == null) {
        consumer()
    }
    return this
}

//当某一对象不为空时执行，否则不执行
fun <T> T?.ifNotNull(consumer: (T) -> Unit): T? {
    if (this != null) {
        consumer(this)
    }
    return this
}

//跳转到其他Activity
fun <T : Context, T2 : Activity> T.gotoActivity(cls: Class<T2>) {
    startActivity(Intent(this, cls))
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