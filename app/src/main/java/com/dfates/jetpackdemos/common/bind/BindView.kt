package com.dfates.jetpackdemos.common.bind

/**
 * Created by $USER_NAME on 2018/12/15.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val id: Int, val onClick: String = "") //id为View的Id，onClick是点击事件的处理函数，该函数必须是一个只有View作为参数的方法！！
