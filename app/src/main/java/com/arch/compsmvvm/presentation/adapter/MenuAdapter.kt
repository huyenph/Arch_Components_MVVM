package com.arch.compsmvvm.presentation.adapter

import android.support.v7.widget.RecyclerView
import com.arch.compsmvvm.BR
import com.arch.compsmvvm.R
import com.arch.compsmvvm.presentation.base.BaseAdapter

class MenuAdapter(
    recyclerView: RecyclerView,
    adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(R.layout.item_site, 0, recyclerView, null, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position]
            holder.bindind.setVariable(BR.site, item)
            holder.bindind.executePendingBindings()
        }
    }
}