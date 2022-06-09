package com.lewie.net.repository

import com.lewie.base.datastore.ChangeListVersions
import com.lewie.base.model.BannerData
import com.lewie.base.model.Data
import com.lewie.net.retrofit.RetrofitNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineHomeListRepository @Inject constructor(
//    private val authorDao: AuthorDao,
    private val network: RetrofitNetwork,
//    private val niaPreferences: NiaPreferencesDataSource,
) : HomeListRepository {

    override fun getListStream(): Flow<Data> = flow {
        emit(network.getHomeList())
    }

    override fun getBanner(): Flow<List<BannerData>> = flow {
        emit(network.getHomeBanner())
    }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        true

}