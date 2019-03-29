package com.arch.compsmvvm.presentation.fragment.site

import com.arch.compsmvvm.common.helper.SingleLiveData
import com.arch.compsmvvm.data.remote.response.site.SiteItemResponse
import com.arch.compsmvvm.data.remote.response.site.SiteResponse
import com.arch.compsmvvm.data.repository.Repository
import com.arch.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SiteViewModel(private val repository: Repository): BaseViewModel(repository) {
    var siteLive: SingleLiveData<MutableList<SiteItemResponse>>? = null

    fun loadAllSite(page: Int, loading: Boolean) {
        if (loading) showLoading()
        if (siteLive == null) siteLive = SingleLiveData()

        val disposables = repository.getAllSite(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<SiteResponse>() {}.type
                    val site = Gson().fromJson(it, type) as SiteResponse
                    siteLive!!.value = site.items
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