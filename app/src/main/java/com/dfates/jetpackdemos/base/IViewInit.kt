package com.dfates.jetpackdemos.base

//运行优先级级别，优先级越高越先执行
enum class Priority {
    HIGH, NORMAL, LOW
}

//运行优先级注解，定义在initView,initListener,initData等方法上
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RunPriority(val value: Priority)


/**
 * 视图初始化接口
 */
interface IViewInit {

    //根据优先级调用initView，initListener，initData方法
    fun initAll() {
        val methods = arrayOf(
                javaClass.getMethod("initView"),
                javaClass.getMethod("initListener"),
                javaClass.getMethod("initData"))

        methods.map { method ->
            method to method.getAnnotation(RunPriority::class.java)
        }.sortedBy {
            it.second?.value ?: Priority.NORMAL
        }.forEach {
            it.first.invoke(this)
        }
    }


    /**
     * 初始化视图
     */
    fun initView() {}

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 初始化数据
     */
    fun initData() {}
}
