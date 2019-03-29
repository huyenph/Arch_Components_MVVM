package com.arch.compsmvvm.presentation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arch.compsmvvm.BR
import com.arch.compsmvvm.R
import com.arch.compsmvvm.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.view_footer_menu.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*

class MenuAdapter(
    recyclerView: RecyclerView,
    private val headerRes: Int,
    footerRes: Int,
    layoutManager: GridLayoutManager?,
    private val adapterListener: BaseAdapter.AdapterListener?
) : BaseAdapter(R.layout.item_menu, headerRes, footerRes, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item: Any = items[if (headerRes != 0) position - 1 else position]
                holder.binding.setVariable(BR.menu, item)
                holder.binding.executePendingBindings()
                val v = holder.binding.root
                v.itMenu_clContainer.setOnClickListener {
                    adapterListener?.onItemClick(item, position)
                }
            }
            is FooterViewHolder -> {
                val v = holder.binding.root
                v.vFooterMenu_clContainer.setOnClickListener {
                    adapterListener?.onItemClick("footer", position)
                }
            }
            is HeaderViewHolder -> {

            }
            is LoadingViewHolder -> {
                holder.binding.root.vLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
            }
            is BlankViewHolder -> {

            }
        }
    }
}