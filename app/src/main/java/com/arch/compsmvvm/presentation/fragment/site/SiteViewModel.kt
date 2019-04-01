package com.arch.compsmvvm.presentation.fragment.site

import android.arch.lifecycle.MutableLiveData
import com.arch.compsmvvm.data.remote.response.site.SiteItemResponse
import com.arch.compsmvvm.data.remote.response.site.SiteResponse
import com.arch.compsmvvm.data.repository.Repository
import com.arch.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SiteViewModel(private val repository: Repository): BaseViewModel(repository) {
    var siteLive: MutableLiveData<MutableList<SiteItemResponse>> = MutableLiveData()

    fun loadAllSite(page: Int, loading: Boolean) {
        if (loading) showLoading()
        val disposables = repository.getAllSite(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<SiteResponse>() {}.type
                    val site = Gson().fromJson(it, type) as SiteResponse
                    siteLive.value = site.items
                }
                dismissLoading()
                hideMessage()
            }, {
                dismissLoading()
                showMessage(it.message!!)
            })
        compositeDisposable.add(disposables)
    }
}