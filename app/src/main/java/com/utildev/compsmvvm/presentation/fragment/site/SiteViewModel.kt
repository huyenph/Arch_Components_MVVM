package com.utildev.compsmvvm.presentation.fragment.site

import android.arch.lifecycle.MutableLiveData
import com.utildev.compsmvvm.data.remote.response.site.SiteItemResponse
import com.utildev.compsmvvm.data.remote.response.site.SiteResponse
import com.utildev.compsmvvm.data.repository.Repository
import com.utildev.compsmvvm.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SiteViewModel(private val repository: Repository): BaseViewModel(repository) {
    var siteLive: MutableLiveData<MutableList<SiteItemResponse>> = MutableLiveData()

    fun loadAllSite(page: Int, loading: Boolean) {
        if (loading) showLoading()
        apiClient.request(3, object : TypeToken<SiteResponse>() {}.type, repository.getAllSite(page))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val site = Gson().fromJson(response, type) as SiteResponse
        siteLive.value = site.items
    }
}