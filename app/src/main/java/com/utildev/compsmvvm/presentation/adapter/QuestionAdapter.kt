package com.utildev.compsmvvm.presentation.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.utildev.compsmvvm.BR
import com.utildev.compsmvvm.R
import com.utildev.compsmvvm.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_question.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*

class QuestionAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager?,
    adapterListener: BaseAdapter.AdapterListener?
): BaseAdapter(R.layout.item_question, 0, 0, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position]
            holder.binding.setVariable(BR.question, item)
            holder.binding.executePendingBindings()
            val v = holder.binding.root
            v.fmMain_decorator.visibility = if (position == items.size - 1) View.GONE else View.VISIBLE
        } else if (holder is LoadingViewHolder) {
            holder.binding.root.vLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
        }
    }
}
