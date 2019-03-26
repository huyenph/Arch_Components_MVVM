package com.arch.compsmvvm.data.repository

import com.google.gson.JsonObject
import io.reactivex.Observable

interface Repository {
    fun getMenu(page: Int, pageSize: Int): Observable<JsonObject>
}