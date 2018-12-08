package com.dfates.jetpackdemos.common


/**
 * Created by $USER_NAME on 2018/12/8.
 */
fun <T> T.ifNotNull(consumer: (T) -> Unit) {
    if (this != null) {
        consumer(this)
    }
}
