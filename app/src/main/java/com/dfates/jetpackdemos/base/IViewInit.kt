package com.dfates.jetpackdemos.base

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RunPriority(val value: Priority)

enum class Priority(value: Int) {
    HIGH(-1), NORMAL(0), LOW(1)
}

/**
 * 视图初始化接口，已经默认实现
 */
interface IViewInit {
    fun init() {

        val methods = arrayOf(javaClass.getMethod("initView"),
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
