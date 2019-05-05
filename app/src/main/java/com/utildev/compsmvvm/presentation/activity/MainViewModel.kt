package com.utildev.compsmvvm.presentation.activity

import android.util.Log
import com.utildev.compsmvvm.common.helper.SingleLiveData
import com.utildev.compsmvvm.data.remote.response.site.SiteItemResponse
import com.utildev.compsmvvm.data.remote.response.site.SiteResponse
import com.utildev.compsmvvm.data.repository.Repository
import com.utildev.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: Repository) : BaseViewModel(repository) {
    var menuLive: SingleLiveData<MutableList<SiteItemResponse>>? = null

    fun loadMenu(page: Int) {
        if (menuLive == null) {
            menuLive = SingleLiveData()
        }
        val disposables = repository.getMenu(page, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    val type = object : TypeToken<SiteResponse>() {}.type
                    val site = Gson().fromJson(it, type) as SiteResponse
                    menuLive!!.value = site.items
                }
            }, {
                Log.d("aaa", it.printStackTrace().toString())
            })
        compositeDisposable.add(disposables)
    }
}