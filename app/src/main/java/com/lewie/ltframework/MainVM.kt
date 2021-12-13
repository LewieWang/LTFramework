package com.lewie.ltframework

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.lewie.base.BaseViewModel
import com.lewie.base.model.ListProjectBean

import com.lewie.base.net.http.ApiException
import com.lewie.base.requestFlow
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch

class MainVM : BaseViewModel() {


     val stateFlow by lazy { MutableStateFlow(ListProjectBean()) }
     fun funFlowRequest() {
        viewModelScope.launch {
            requestFlow {
                getListProject()
            }.catch{ cause ->
                run {
                    when(cause){
                        is ApiException ->{

                        }
                    }

                }
            }.collect {
                stateFlow.value = it.data!!
                Log.e("data===>", "data==requestFlow===>${it.data}")
            }
        }
    }

}

