package com.dfates.jetpackdemos.net.service

import com.dfates.jetpackdemos.net.data.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PicService {
    //请添加相应的`API`调用方法
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>> //每个方法的返回值即一个Observable
}

