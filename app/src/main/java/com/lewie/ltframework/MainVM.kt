package com.lewie.ltframework



import androidx.lifecycle.*
import com.lewie.base.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    repository: ListRepository
) : ViewModel() {

     val result = repository.getList()

}

