package com.lewie.net

import com.lewie.base.model.BannerData
import com.lewie.base.model.Data

interface NetworkDataSource {

    suspend fun getHomeList(): Data

    suspend fun getHomeBanner(): List<BannerData>

//    suspend fun getHomeBanner(ids: List<String>? = null): List<BannerData>

}