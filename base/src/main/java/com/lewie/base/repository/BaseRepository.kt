package com.lewie.base.repository

import android.util.Log
import com.lewie.base.model.Data
import com.lewie.base.net.http.ApiException
import com.lewie.base.net.http.BaseResponse
import com.lewie.base.requestFlow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext

/**
 * 通常存储库
 * @author llw
 */
open class BaseRepository {

    fun <T> fire(block: suspend () -> BaseResponse<T>) =
        flow{
            requestFlow {
                block()
            }.catch{ cause ->
                run {
                    when(cause){
                        is ApiException ->{

                        }
                    }

                }
            }.collect {
                emit(it.data)
            }
        }

//            val result = try {
//                block()
//            } catch (e: Exception) {
//                Log.e("BaseRepository", "fire: "+ e.message)
////                Result.failure(e)
//            }
//            //通知数据变化
//            emit(result)




}