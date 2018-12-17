package com.dfates.jetpackdemos.common.bind

/**
 * Created by $USER_NAME on 2018/12/17.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindOnClick(vararg val ids: Int) //ids为View的Id集合
