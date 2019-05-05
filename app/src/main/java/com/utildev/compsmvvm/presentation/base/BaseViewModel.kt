package com.utildev.compsmvvm.presentation.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.utildev.compsmvvm.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(private val repository: Repository) : ViewModel() {
    val loadingView = ObservableInt(View.GONE)
    val msgView = ObservableInt(View.GONE)
    val msgText: ObservableField<String> = ObservableField()
    val enabledView = ObservableBoolean(false)

    val compositeDisposable = CompositeDisposable()

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

    fun showMessage(msg: String) {
        if (msgView.get() != View.VISIBLE) {
            msgView.set(View.VISIBLE)
            msgText.set(msg)
        }
    }

    fun hideMessage() {
        if (msgView.get() != View.GONE) {
            msgView.set(View.GONE)
        }
    }
}