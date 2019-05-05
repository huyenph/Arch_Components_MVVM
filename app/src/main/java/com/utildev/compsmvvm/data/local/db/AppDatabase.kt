package com.utildev.compsmvvm.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.utildev.compsmvvm.data.local.dao.DBDao
import com.utildev.compsmvvm.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DBDao
}