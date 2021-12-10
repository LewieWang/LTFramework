package com.lewie.base.net

import com.lewie.base.model.ListProjectBean

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/article/listproject/0/json")
    suspend fun getListProject(): ApiResponse<ListProjectBean>


//    @FormUrlEncoded
//    @POST("user/login")
//    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponse<User?>

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}