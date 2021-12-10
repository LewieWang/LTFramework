package com.lewie.base.net.http

import com.lewie.base.model.ListProjectBean
import com.lewie.base.net.http.BaseResponse
import com.lewie.base.net.http.retrofit
import retrofit2.http.GET

val Api: ApiInterface by lazy {
    retrofit.create(ApiInterface::class.java)
}
interface ApiInterface {

    @GET("/article/listproject/0/json")
    suspend fun getListProject(): BaseResponse<ListProjectBean?>
}