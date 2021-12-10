package com.lewie.ltframework

import androidx.lifecycle.viewModelScope
import com.lewie.base.BaseViewModel
import com.lewie.base.model.ListProjectBean

import com.lewie.base.net.WxArticleRepository
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch

class MainVM : BaseViewModel() {

    private val repository by lazy { WxArticleRepository() }

     val stateFlow by lazy { MutableStateFlow(ListProjectBean()) }
//     fun funFlowRequest() {
//        viewModelScope.launch {
//            requestFlow {
//                getListProject()
//            }.catch{ cause ->
//                run {
//                    when(cause){
//                        is ApiException ->{
//
//                        }
//                    }
//
//                }
//            }.collect {
//                stateFlow.value = "asdasd"
//                Log.e("data===>", "data==requestFlow===>${it.data}")
//            }
//        }
//    }

    fun requestNet() {
        viewModelScope.launch {
            println("------------>" + repository.getListProject())
//            stateFlow.value = repository.getListProject().data!!
        }
    }
}

