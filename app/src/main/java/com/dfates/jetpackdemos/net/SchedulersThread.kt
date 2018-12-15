package com.dfates.jetpackdemos.net

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object SchedulersThread {
    fun <T> main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            //设置主线程回调
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}
