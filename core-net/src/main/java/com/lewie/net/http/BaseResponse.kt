package com.lewie.net.http


data class BaseResponse<T> (
    val data: T ,
    var errorCode: Int ,
    var errorMsg: String,
    var exception: Exception,
)
