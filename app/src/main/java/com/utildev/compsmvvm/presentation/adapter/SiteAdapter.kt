package com.utildev.compsmvvm.presentation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.utildev.compsmvvm.BR
import com.utildev.compsmvvm.R
import com.utildev.compsmvvm.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_site.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*

class SiteAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager?,
    private val adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(R.layout.item_site, 0, 0, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position]
            holder.binding.setVariable(BR.site, item)
            holder.binding.executePendingBindings()
            val v = holder.binding.root
            v.itSite_cvContainer.setOnClickListener {
                adapterListener?.onItemClick(item, position)
            }
        } else if (holder is LoadingViewHolder) {
            holder.binding.root.vLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
        }
    }
}