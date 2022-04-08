package com.lewie.base.comment.vb

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

import com.lewie.base.comment.RecyclerSupport


open class VbRecyclerVH(val vb: ViewBinding, val support: RecyclerSupport) :
    RecyclerView.ViewHolder(vb.root) {

    open fun bind(itemCell: VbItemCell, payloads: MutableList<Any> = mutableListOf()) {
        //empty
    }
}