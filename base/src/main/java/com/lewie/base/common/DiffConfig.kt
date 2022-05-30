package com.lewie.base.common

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.lewie.base.common.vb.VbItemCell


object DiffConfig {

    fun defaultVb(): AsyncDifferConfig<VbItemCell> {
        return AsyncDifferConfig.Builder(object :
            DiffUtil.ItemCallback<VbItemCell>() {
            override fun areItemsTheSame(oldItem: VbItemCell, newItem: VbItemCell) =
                oldItem.layoutResId() == newItem.layoutResId() && oldItem.itemId() == newItem.itemId()


            override fun areContentsTheSame(oldItem: VbItemCell, newItem: VbItemCell) =
                oldItem.itemContent() == newItem.itemContent()

            override fun getChangePayload(oldItem: VbItemCell, newItem: VbItemCell): Any = "update"
        }).build()
    }

}