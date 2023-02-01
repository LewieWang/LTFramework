package com.lewie.base.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ch
 * on 2017/12/19.14:24
 * 作用：
 */
abstract class BaseRecAdapter<T, K : RecyclerView.ViewHolder>(var data: List<T>?) :
    RecyclerView.Adapter<K>() {
    var itemClickListener: onItemClickListener? = null
    var itemLongClickListener: OnItemLongClickListener? = null
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K {
        context = parent.context
        val holder = onCreateHolder()
        //绑定listener
        bindListener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: K, position: Int) {
        onHolder(holder, data!![position], position)
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    /**
     * 填充数据
     *
     * @param holder
     * @param position
     */
    abstract fun onHolder(holder: K, bean: T, position: Int)
    abstract fun onCreateHolder(): K

    /**
     * 通过资源res获得view
     *
     * @param res
     * @return
     */
    fun getViewByRes(res: Int): View {
        return LayoutInflater.from(context).inflate(res, null)
    }

    /**
     * 通过资源res获得view
     *
     * @param res
     * @return
     */
    fun getViewByRes(res: Int, prent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(res, prent)
    }

    /**
     * 绑定事件
     *
     * @param holder
     */
    private fun bindListener(holder: K?) {
        if (holder == null) {
            return
        }
        val itemView = holder.itemView ?: return
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        itemView.layoutParams = params
        if (itemClickListener != null) {
            itemView.setOnClickListener { v ->
                itemClickListener!!.onItemClick(
                    this@BaseRecAdapter,
                    v,
                    holder.layoutPosition
                )
            }
        }
        if (itemLongClickListener != null) {
            itemView.setOnLongClickListener { v ->
                itemLongClickListener!!.onItemLongClick(
                    this@BaseRecAdapter,
                    v,
                    holder.layoutPosition
                )
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(adapter: BaseRecAdapter<*, *>?, view: View?, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(adapter: BaseRecAdapter<*, *>?, view: View?, position: Int): Boolean
    }

    fun setNewData(lt: List<T>?) {
        var lt = lt
        if (data == null) {
            data = ArrayList()
        }
        if (lt == null) {
            lt = ArrayList()
        }
        data = lt
        notifyDataSetChanged()
    }
}