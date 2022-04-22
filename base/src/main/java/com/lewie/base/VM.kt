package com.lewie.base


import com.lewie.base.net.http.Api
import com.lewie.base.net.http.ApiException
import com.lewie.base.net.http.ApiInterface

import com.lewie.base.net.http.BaseResponse

import com.lewie.base.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


/**
 * 简单的请求，没有加try-catch捕获异常，使用的时候切记！！！！！需要手动try-catch或使用runCatching方法捕获
 * @receiver BaseViewModel
 * @param request [@kotlin.ExtensionFunctionType] SuspendFunction1<ApiInterface, BaseResponse<T>?>
 * @return BaseResponse<T>
 */
suspend fun <T> requestFlow(request: suspend ApiInterface.() -> BaseResponse<T>?):
        Flow<BaseResponse<T>> {
    /*withContext表示挂起块  配合Retrofit声明的suspend函数执行 该块会挂起直到里面的网络请求完成 最一行就是返回值*/
    return flow {

        val response = request(Api)?: throw IllegalArgumentException("数据非法，获取响应数据为空")
        log("===requestFlow==request==${Thread.currentThread().name}")
        if (response.errorCode != 0) {
            throw  ApiException(response.errorCode, response.errorMsg ?: "")
        }
        emit(response)


    }.onStart {
        log("===requestFlow==onStart==${Thread.currentThread().name}")
    }.flowOn(Dispatchers.IO).catch {
            cause ->
        run {
            throw  cause
        }
    }.onCompletion {
        log("===requestFlow==onCompletion==${Thread.currentThread().name}")
    }

}
