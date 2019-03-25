package com.arch.compsmvvm.di

import com.arch.compsmvvm.BuildConfig
import com.arch.compsmvvm.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideGson() }
    single { createOkHttpClient() }
    single { createRetrofit(get(), get()) }
    single { createApiService(get()) }
}

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .addInterceptor { chain ->
//          val request = chain.request().newBuilder().addHeader("", "").build()
//          chain.proceed(request)
//        }
    .build()

fun createRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

fun createApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)