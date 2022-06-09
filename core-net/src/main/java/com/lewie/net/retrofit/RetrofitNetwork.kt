package com.lewie.net.retrofit

import com.lewie.base.BuildConfig
import com.lewie.base.model.BannerData
import com.lewie.base.model.Data
import com.lewie.net.NetworkDataSource
import com.lewie.net.http.ApiInterface

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(

) : NetworkDataSource {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(ApiInterface::class.java)


    override suspend fun getHomeList(): Data =
        retrofit.getListProject().data


    override suspend fun getHomeBanner(): List<BannerData> =
        retrofit.getHomeBanner().data


}

private fun getOkHttpClient(): OkHttpClient {
    val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS) //设置读取超时时间
        .writeTimeout(30, TimeUnit.SECONDS) //设置写的超时时间
        .connectTimeout(30, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
    }
    return builder.build()
}