package com.utildev.compsmvvm.presentation.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.utildev.compsmvvm.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

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

    fun requestBody() {
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        val file = File("")
        builder.addFormDataPart(
            "key",
            file.absolutePath,
            RequestBody.create(MediaType.parse("multipart/form_data"), file)
        )
        val disposables = repository.requestBody(builder.build())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {})
        compositeDisposable.add(disposables)
    }
}