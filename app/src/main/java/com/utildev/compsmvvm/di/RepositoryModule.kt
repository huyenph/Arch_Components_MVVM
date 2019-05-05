package com.utildev.compsmvvm.di

import android.arch.persistence.room.Room
import android.content.Context
import com.utildev.compsmvvm.common.extensions.DB_NAME
import com.utildev.compsmvvm.data.local.db.AppDatabase
import com.utildev.compsmvvm.data.repository.AppRepository
import com.utildev.compsmvvm.data.repository.Repository
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val repositoryModule = module {
    single<Repository> { create<AppRepository>() }
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createDBDao(get()) }
}

fun createDatabaseName() = DB_NAME

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

fun createDBDao(appDatabase: AppDatabase) = appDatabase.dbDao()