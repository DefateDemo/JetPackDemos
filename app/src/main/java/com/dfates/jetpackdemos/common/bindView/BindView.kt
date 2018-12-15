package com.dfates.jetpackdemos.common.bindView

/**
 * Created by $USER_NAME on 2018/12/15.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val id:Int)