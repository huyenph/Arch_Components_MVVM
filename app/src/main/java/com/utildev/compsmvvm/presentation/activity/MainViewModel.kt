package com.utildev.compsmvvm.presentation.activity

import android.arch.lifecycle.MutableLiveData
import com.utildev.compsmvvm.data.remote.response.site.SiteItemResponse
import com.utildev.compsmvvm.data.remote.response.site.SiteResponse
import com.utildev.compsmvvm.data.repository.Repository
import com.utildev.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainViewModel(private val repository: Repository) : BaseViewModel(repository) {
    val menuLive: MutableLiveData<MutableList<SiteItemResponse>> = MutableLiveData()

    fun loadMenu(page: Int) {
        apiClient.request(5, object : TypeToken<SiteResponse>() {}.type, repository.getMenu(page, 10))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val site = Gson().fromJson(response, type) as SiteResponse
        menuLive.value = site.items
    }
}