package com.lewie.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lewie.base.common.RecyclerSupport
import com.lewie.base.common.ext.checkTarget
import com.lewie.base.common.vb.VbItemCell
import com.lewie.base.common.vb.VbRecyclerVH
import com.lewie.video.databinding.ItemVedioScreenBinding

class VideoItemCell() : VbItemCell {

    override fun layoutResId(): Int = R.layout.item_vedio_screen

    override fun itemId(): String = javaClass.name

    override fun itemContent(): String = String()

    override fun onCreateViewHolder(parent: ViewGroup, support: RecyclerSupport): VbRecyclerVH {
        return VideoItemHolder(
            ItemVedioScreenBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), support
        )
    }
}

class VideoItemHolder(vb: ViewBinding, support: RecyclerSupport) :
    VbRecyclerVH(vb, support) {
    init {
//        itemView.btn_rent_now.debounceClick {
//            support.clickCallback?.invoke(bindingAdapterPosition, it.id)
//        }
    }

    override fun bind(itemCell: VbItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        checkTarget<VideoItemCell, ItemVedioScreenBinding>(
            itemCell, vb
        ) { item, targetVB ->

        }
    }
}