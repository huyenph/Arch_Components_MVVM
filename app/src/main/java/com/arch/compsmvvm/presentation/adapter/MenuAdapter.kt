package com.arch.compsmvvm.presentation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arch.compsmvvm.BR
import com.arch.compsmvvm.R
import com.arch.compsmvvm.presentation.base.BaseAdapter
import java.lang.Exception

class MenuAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager?,
    adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(R.layout.item_site, R.layout.view_loadmore, R.layout.view_footer_menu, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item = items[position-1]
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