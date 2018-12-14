package com.dfates.jetpackdemos.base

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RunPriority(val value: Priority)

enum class Priority(value: Int) {
    HIGH(-1), NORMAL(0), LOW(1)
}


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

    fun initView() {}

    fun initListener() {}

    fun initData() {}
}
