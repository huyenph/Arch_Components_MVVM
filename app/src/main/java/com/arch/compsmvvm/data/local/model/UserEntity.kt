package com.arch.compsmvvm.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.arch.compsmvvm.common.extensions.TB_NAME

@Entity(tableName = TB_NAME)
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "job")
    var job: String?
)