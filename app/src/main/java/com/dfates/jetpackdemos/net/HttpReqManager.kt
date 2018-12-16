package com.dfates.jetpackdemos.net

import com.dfates.jetpackdemos.net.api.ApiClient
import com.dfates.jetpackdemos.net.api.ApiResponse
import com.dfates.jetpackdemos.net.data.Repo
import com.dfates.jetpackdemos.net.service.PicService
import com.dfates.jetpackdemos.net.tools.SchedulersThread

/**
 * 二次封装请求库  防止要更换http请求库
 */
class HttpReqManager {

    fun getPicData(response : ApiResponse<List<Repo>>){
        return ApiClient.instance.retrofit
                .create(PicService::class.java)
                .listRepos("desFate")
                .compose(SchedulersThread.main())  //设置主线程回调
                .subscribe(response);
    }


}