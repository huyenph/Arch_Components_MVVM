package com.arch.compsmvvm.presentation.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View
import com.arch.compsmvvm.data.repository.Repository

open class BaseViewModel(private val repository: Repository): ViewModel() {
    val loadingView = ObservableInt(View.GONE)
    val enabledView = ObservableBoolean(false)

    fun showLoading() {
        if (loadingView.get() != View.VISIBLE) {
            loadingView.set(View.VISIBLE)
            enabledView.set(false)
        }
    }

    fun dismissLoading() {
        if (loadingView.get() != View.GONE) {
            loadingView.set(View.GONE)
            enabledView.set(true)
        }
    }
}