package com.dfates.jetpackdemos.common.bind

/**
 * Created by $USER_NAME on 2018/12/15.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindParam(val key: String)

//@Target(AnnotationTarget.FIELD)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class BindArgument(val key: String)
//
//@Target(AnnotationTarget.FIELD)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class BindExtra(val key: String)