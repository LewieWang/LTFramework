package com.lewie.net.http

import com.lewie.base.model.BannerData
import com.lewie.base.model.Data
import retrofit2.http.GET


interface ApiInterface {

    @GET("/article/listproject/0/json")
    suspend fun getListProject(): BaseResponse<Data>

    @GET("/banner/json")
    suspend fun getHomeBanner(): BaseResponse<List<BannerData>>
}