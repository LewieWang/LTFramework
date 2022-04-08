package com.lewie.base.comment.vb

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.lewie.base.comment.RecyclerSupport

interface VbItemCell {

    @LayoutRes
    fun layoutResId(): Int

    /**
     * item标志，用于比较item是否一样
     */
    fun itemId(): String

    /**
     * item内容，用于比较内容是否一致
     */
    fun itemContent(): String

    /**
     * 创建ViewHolder
     */
    fun onCreateViewHolder(parent: ViewGroup, support: RecyclerSupport): VbRecyclerVH
}