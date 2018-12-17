package com.dfates.jetpackdemos.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dfates.jetpackdemos.common.toastShow
import java.util.logging.Logger

/**
 * Created by $USER_NAME on 2018/12/10.
 */
class TestLifecycleObserver(val mContext: Context?) : LifecycleObserver {

    private val log:Logger = Logger.getLogger("TestLifecycleObserver")

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        log.info("onCreate")
        mContext?.toastShow("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        log.info("onDestroy")
        mContext?.toastShow("onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        log.info("onResume")
        mContext?.toastShow("onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        log.info("onPause")
        mContext?.toastShow("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        log.info("onStart")
        mContext?.toastShow("onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        log.info("onStop")
        mContext?.toastShow("onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        log.info("onAny")
        mContext?.toastShow("onAny")
    }
}
