package com.arch.compsmvvm.data.repository

import com.google.gson.JsonObject
import io.reactivex.Observable

interface Repository {
    fun getMenu(page: Int, pageSize: Int): Observable<JsonObject>

    fun getQuestion(site: String, page: Int): Observable<JsonObject>

    fun getAllSite(page: Int): Observable<JsonObject>
}