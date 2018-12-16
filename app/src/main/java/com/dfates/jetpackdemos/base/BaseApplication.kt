package com.dfates.jetpackdemos.base

import android.app.Application
import com.dfates.jetpackdemos.net.api.ApiClient

class BaseApplication : Application() {

    //把程序初始化需要加载的东西  写在这里
    override fun onCreate() {
        super.onCreate()
        //初始化http通讯库
        ApiClient.instance.init();
    }

}