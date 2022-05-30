package com.lewie.base.common.vb

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

import com.lewie.base.common.RecyclerSupport


open class VbRecyclerVH(val vb: ViewBinding, val support: RecyclerSupport) :
    RecyclerView.ViewHolder(vb.root) {

    open fun bind(itemCell: VbItemCell, payloads: MutableList<Any> = mutableListOf()) {
        //empty
    }
}