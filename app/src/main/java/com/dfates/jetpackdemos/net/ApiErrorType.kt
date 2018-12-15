package com.dfates.jetpackdemos.net

import android.content.Context
import androidx.annotation.StringRes

enum class ApiErrorType(val code: Int, val messageId: String) {
    //根据实际情况进行增删
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    NOT_FOUND(404, "Not Found"),
    CONNECTION_TIMEOUT(408, "Request Timed-Out"),
    NETWORK_NOT_CONNECT(499, "Not Found"),
    UNEXPECTED_ERROR(700, "Not Found");

    private val DEFAULT_CODE = 1

    fun getApiErrorModel(context: Context): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, messageId)
    }
}

data class ApiErrorModel(var status: Int, var message: String)