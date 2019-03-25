package com.arch.compsmvvm.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.arch.compsmvvm.data.local.dao.DBDao
import com.arch.compsmvvm.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DBDao
}