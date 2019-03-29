package com.arch.compsmvvm.data.repository

import com.arch.compsmvvm.data.local.dao.DBDao
import com.arch.compsmvvm.data.remote.ApiService
import com.google.gson.JsonObject
import io.reactivex.Observable

class AppRepository(private val apiService: ApiService, private val dbDao: DBDao) : Repository {
    override fun getMenu(page: Int, pageSize: Int): Observable<JsonObject> = apiService.requestSite(page, pageSize)

    override fun getQuestion(site: String, page: Int): Observable<JsonObject> = apiService.requestQuestion(site, page)
}