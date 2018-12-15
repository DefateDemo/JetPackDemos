package com.dfates.jetpackdemos.lifecycle

import android.os.Parcel
import android.os.Parcelable
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Kotlin 中的一个类可以有一个主构造函数和一个或多个次构造函数  ？？？
 * 在类名里的是主构造函数
 */
class LiveDataTimerViewModel : ViewModel {

    val ONE_SECOND = 1000

    var mElapsedTime = MutableLiveData<Long>()

    var mInitialTime: Long = 0

    constructor(parcel: Parcel) : this() {
        mInitialTime = parcel.readLong()
    }

    // 副构造函数  !!! 这个函数需要注意  必须返回 this(test)
    constructor(){
        if(mElapsedTime == null)
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())

    }
}