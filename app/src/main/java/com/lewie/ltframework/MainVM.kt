package com.lewie.ltframework

import androidx.lifecycle.*
import com.lewie.base.model.BannerData
import com.lewie.base.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import com.lewie.net.http.asResult
import com.lewie.net.http.Result
import com.lewie.net.repository.OfflineHomeListRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    repository: OfflineHomeListRepository
) : ViewModel() {


     private val banner : Flow<Result<List<BannerData>>> = repository.getBanner().asResult()

    private val list : Flow<Result<Data>> = repository.getListStream().asResult()


    val uiState: StateFlow<MainUiState> =
        combine(
            banner,
            list
        ){  bannerResult , listResult ->


            val banner: BannerUiState = when (bannerResult) {
                is Result.Success -> BannerUiState.Success(bannerResult.data)
                is Result.Loading -> BannerUiState.Loading
                is Result.Error -> BannerUiState.Error
            }

            val list: ListUiState = when (listResult) {
                is Result.Success -> ListUiState.Success(listResult.data)
                is Result.Loading -> ListUiState.Loading
                is Result.Error -> ListUiState.Error
            }
            MainUiState(banner,list)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainUiState(BannerUiState.Loading, ListUiState.Loading)
        )
}


sealed interface BannerUiState {
    data class Success(val banner: List<BannerData>) : BannerUiState
    object Error : BannerUiState
    object Loading : BannerUiState
}

sealed interface ListUiState {
    data class Success(val data: Data) : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

data class MainUiState(
    val bannerState: BannerUiState,
    val listState: ListUiState
)
