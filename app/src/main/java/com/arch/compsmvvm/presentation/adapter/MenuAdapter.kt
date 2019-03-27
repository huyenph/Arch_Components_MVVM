package com.arch.compsmvvm.presentation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arch.compsmvvm.BR
import com.arch.compsmvvm.R
import com.arch.compsmvvm.presentation.base.BaseAdapter

class MenuAdapter(
    recyclerView: RecyclerView,
    private val headerRes: Int,
    footerRes: Int,
    layoutManager: GridLayoutManager?,
    adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(R.layout.item_site, headerRes, footerRes, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item: Any = if (headerRes != 0) {
                    items[position - 1]
                } else {
                    items[position]
                }
                holder.binding.setVariable(BR.site, item)
                holder.binding.executePendingBindings()
            }
            is FooterViewHolder -> {

            }
            is HeaderViewHolder -> {

            }
        }
    }
}