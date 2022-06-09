package com.lewie.net.repository


import com.lewie.base.model.BannerData
import com.lewie.base.model.Data
import kotlinx.coroutines.flow.Flow


interface HomeListRepository : Syncable {
    /**
     * Gets the available List as a stream
     */
    fun getListStream(): Flow<Data>

    fun getBanner() : Flow<List<BannerData>>

}