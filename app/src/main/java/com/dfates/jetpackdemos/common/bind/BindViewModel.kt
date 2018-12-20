package com.dfates.jetpackdemos.common.bind

/**
 * Created by $USER_NAME on 2018/12/15.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class BindViewModel(val getActivity: Boolean = false) //当在fragment中使用时，且fragment所在的Activity为FragmentActivity时，getActivity表示获取父类的ViewModel