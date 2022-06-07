package com.lewie.base.state

import com.lewie.base.model.BannerData
import com.lewie.base.model.DataX

class HomeListState(
    var banner: List<BannerData> = emptyList(),
    var list: List<DataX> = emptyList(),
)