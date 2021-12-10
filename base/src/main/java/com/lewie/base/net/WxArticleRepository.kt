package com.lewie.base.net

import com.lewie.base.model.ListProjectBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WxArticleRepository : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun getListProject(): ApiResponse<ListProjectBean> {
        return executeHttp {
            mService.getListProject()
        }
    }

}