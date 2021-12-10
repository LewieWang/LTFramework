package com.lewie.base.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListProjectBean{
    var curPage: Int = 0
    var pageCount: Int = 0
    var total: Int = 0
}
