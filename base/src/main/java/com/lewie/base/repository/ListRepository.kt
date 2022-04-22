package com.lewie.base.repository


import com.lewie.base.model.Data
import com.lewie.base.net.http.Api
import com.lewie.base.net.http.ApiException
import com.lewie.base.net.http.BaseResponse
import com.lewie.base.requestFlow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import javax.inject.Inject


@ViewModelScoped
class ListRepository @Inject constructor() : BaseRepository() {


    companion object {
        lateinit var epidemicNews: Data
    }

    fun getList() = fire {
        Api.getListProject()
    }

}