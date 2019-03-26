package com.arch.compsmvvm.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arch.compsmvvm.R
import kotlin.collections.ArrayList

class BaseAdapter(
    private val layoutRes: Int,
    private val layoutHeaderRes: Int,
    recyclerView: RecyclerView,
    private val layoutManager: GridLayoutManager?,
    private val adapterListener: AdapterListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<Any> = ArrayList()
    var isLoading = true

    companion object {
        const val TYPE_HEADER = 888
        const val TYPE_LOADING = 999
    }

    init {
        if (layoutManager != null && adapterListener != null) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var visibleItemCount: Int = 0
                var totalItemCount: Int = 0
                var firstVisibleItem: Int = 0
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        visibleItemCount = layoutManager.childCount
                        totalItemCount = layoutManager.itemCount
                        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                        if (isLoading) {
                            if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                                adapterListener.onLoadMore()
                                isLoading = false
                            }
                        }
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return if (layoutManager == null) {
            items.size
        } else {
            if (items.size == 0) items.size else items.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager != null) {
            when (position) {
                0 -> if (layoutHeaderRes != 0) TYPE_HEADER else position
                layoutManager.itemCount - 1 -> TYPE_LOADING
                else -> position
            }
        } else {
            position
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), layoutHeaderRes, viewGroup, false
                )
            )
            TYPE_LOADING -> LoadingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), R.layout.view_loadmore, viewGroup, false
                )
            )
            else -> ItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), layoutRes, viewGroup, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    private fun addAll(list: List<Any>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun add(any: Any) {
        items.add(any)
        notifyDataSetChanged()
    }

    fun set(list: List<Any>) {
        items.clear()
        addAll(list)
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface AdapterListener {
        fun onItemClick(`object`: Any, position: Int)
        fun onItemLongClick(`object`: Any, position: Int): Boolean
        fun onLoadMore()
    }

    class HeaderViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val bindind: ViewDataBinding) : RecyclerView.ViewHolder(bindind.root)
    class LoadingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}