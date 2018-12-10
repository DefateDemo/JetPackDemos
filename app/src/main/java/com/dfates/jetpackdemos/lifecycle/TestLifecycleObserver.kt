package com.dfates.jetpackdemos.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dfates.jetpackdemos.common.toastShow

/**
 * Created by $USER_NAME on 2018/12/10.
 */
class TestLifecycleObserver(val mContext: Context?) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        mContext?.toastShow("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mContext?.toastShow("onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        mContext?.toastShow("onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        mContext?.toastShow("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        mContext?.toastShow("onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mContext?.toastShow("onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        mContext?.toastShow("onAny")
    }
}
