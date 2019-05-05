package com.utildev.compsmvvm.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("sites?key=KCTJhLJ5*JRozzNhBK20og((")
    fun requestSite(
        @Query("page") order: Int,
        @Query("pagesize") sort: Int
    ): Observable<JsonObject>

    @GET("questions?key=KCTJhLJ5*JRozzNhBK20og((&pagesize=10")
    fun requestQuestion(@Query("site") site: String, @Query("page") page: Int): Observable<JsonObject>

    @GET("sites?key=KCTJhLJ5*JRozzNhBK20og((")
    fun requestAllSite(@Query("page") page: Int): Observable<JsonObject>

//    @GET("users")
//    fun requestUser(
//        @Query("order") order: String,
//        @Query("sort") sort: String,
//        @Query("site") site: String,
//        @Query("page") page: Int
//    ): Call<JsonObject>
//
//    @FormUrlEncoded
//    @POST("key, object")
//    fun requestNormal(@FieldMap body: Map<String, Any>): Observable<JsonObject>
//
//    @POST("storeList")
//    fun requestList(@Body list: List<BaseModel>): Observable<JsonObject>
//
//    @GET("not params")
//    fun requestNotParams(): Observable<JsonObject>
//
//    @POST("upload file")
//    fun requestFile(@Body file: RequestBody): Observable<JsonObject>
}