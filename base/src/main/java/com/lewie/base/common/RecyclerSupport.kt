package com.lewie.base.common

import android.view.View
import com.lewie.base.common.vb.VbStableAdapter
import com.lewie.base.common.vb.VbRecyclerAdapter


/**
 * 组装适配器需要的图片加载、点击回调
 */
open class RecyclerSupport(val pageCount: Int = 10) {


    /**
     * 图片加载
     */
//    var imageLoader: ImageLoader? = null

    /**
     * 简单点击
     */
    var simpleCallback: ((position: Int) -> Unit)? = null

    /**
     * 同一个View多个事件，点击或长按
     */
    var clickCallback: ((position: Int, type: Int) -> Unit)? = null

    /**
     * 同一个item中多个View的监听
     */
    var clickViewCallback: ((view: View, position: Int) -> Unit)? = null

    /**
     * 错误页面item点击触发重试功能，
     */
    var retry: (() -> Unit)? = null

    /**
     * 空白页面Item点击监听
     */
    var emptyCallBack: (() -> Unit)? = null

    /**
     * 登录页面Item点击监听
     */
    var loginCallBack: (() -> Unit)? = null

    /**
     * 登录页面Item点击监听
     */
//    var onSuperLinkCallback: OnSuperLinkCallback? = null

    /**
     * 详情页面Item点击事件
     */
    var detailClickCallback: ((position: Int, type: Int, value: Any?) -> Unit)? = null

    infix fun onSimpleCallback(block: (position: Int) -> Unit) {
        simpleCallback = block
    }

    infix fun onClickCallback(block: (position: Int, type: Int) -> Unit) {
        clickCallback = block
    }


    infix fun onClickViewCallback(block: (view: View, position: Int) -> Unit) {
        clickViewCallback = block
    }

    infix fun onRetry(block: () -> Unit) {
        retry = block
    }

    infix fun onEmptyCallBack(block: () -> Unit) {
        emptyCallBack = block
    }

    infix fun onLoginCallBack(block: () -> Unit) {
        loginCallBack = block
    }

    infix fun onDetailClickCallback(block: (position: Int, type: Int, value: Any?) -> Unit) {
        detailClickCallback = block
    }

}



inline fun createVbAdapter(crossinline support: RecyclerSupport.() -> Unit): VbRecyclerAdapter {
    return VbRecyclerAdapter(RecyclerSupport().apply(support))
}

inline fun createVbStableAdapter(crossinline support: RecyclerSupport.() -> Unit): VbStableAdapter {
    return VbStableAdapter(RecyclerSupport().apply(support))
}



/**
 * 获取某种Cell在适配器中的位置
 */
//inline fun <reified T> getSelfPos(
//    itemCell: ItemCell,
//    recyclerVH: RecyclerVH,
//    andAdd: Int = 0
//): Int {
//    val bindingAdapter = recyclerVH.bindingAdapter
//    return if ((bindingAdapter is StableAdapter || bindingAdapter is RecyclerAdapter) && itemCell is T) {
//        val stableAdapter = bindingAdapter as StableAdapter
//        val filterIsInstance = stableAdapter.currentList.filterIsInstance<T>()
//        if (!filterIsInstance.isNullOrEmpty() && filterIsInstance.contains(itemCell)) {
//            filterIsInstance.indexOf(itemCell) + andAdd
//        } else {
//            recyclerVH.absoluteAdapterPosition + andAdd
//        }
//    } else {
//        recyclerVH.absoluteAdapterPosition + andAdd
//    }
//}
