package com.utildev.compsmvvm.data.repository

import com.utildev.compsmvvm.data.local.dao.DBDao
import com.utildev.compsmvvm.data.remote.ApiService
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody

class AppRepository(private val apiService: ApiService, private val dbDao: DBDao) : Repository {
    override fun getMenu(page: Int, pageSize: Int): Observable<JsonObject> = apiService.requestSite(page, pageSize)

    override fun getQuestion(site: String, page: Int): Observable<JsonObject> = apiService.requestQuestion(site, page)

    override fun getAllSite(page: Int): Observable<JsonObject> = apiService.requestAllSite(page)

    override fun requestBody(body: RequestBody): Observable<JsonObject> = apiService.requestBody(body)
}