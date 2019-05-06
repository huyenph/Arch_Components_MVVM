package com.utildev.compsmvvm.presentation.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.google.gson.JsonObject
import com.utildev.compsmvvm.common.extensions.REQUEST_ERROR
import com.utildev.compsmvvm.data.remote.ApiClient
import com.utildev.compsmvvm.data.repository.Repository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.reflect.Type

@Suppress("LeakingThis")
open class BaseViewModel(private val repository: Repository) : ViewModel(), ApiClient.ResponseListener {
    val loadingView = ObservableInt(View.GONE)
    val msgView = ObservableInt(View.GONE)
    val msgText: ObservableField<String> = ObservableField()
    private val enabledView = ObservableBoolean(false)

    val apiClient = ApiClient(this)

    fun showLoading() {
        if (loadingView.get() != View.VISIBLE) {
            loadingView.set(View.VISIBLE)
            enabledView.set(false)
        }
    }

    private fun dismissLoading() {
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
        apiClient.request(6, null, repository.requestBody(builder.build()))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        dismissLoading()
        hideMessage()
    }

    override fun onFailure() {
        dismissLoading()
        showMessage(REQUEST_ERROR)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribeViewModel() = apiClient.clearCompositeDisposable()
}