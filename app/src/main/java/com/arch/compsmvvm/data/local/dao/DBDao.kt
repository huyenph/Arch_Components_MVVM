package com.arch.compsmvvm.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.arch.compsmvvm.common.extensions.TB_NAME
import com.arch.compsmvvm.data.local.model.UserEntity
import io.reactivex.Flowable

@Dao
interface DBDao {
    @Query("SELECT COUNT(*) FROM `$TB_NAME`")
    fun getUserCount(): Flowable<Int>

    @Query("SELECT * FROM `$TB_NAME`")
    fun getAllUser(): Flowable<MutableList<UserEntity>>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

//  @Query("DELETE FROM $TB_NAME WHERE id = :id")
//  fun deleteMovie(id: String)

    @Query("DELETE FROM `$TB_NAME`")
    fun deleteAll()

    @Query("SELECT * FROM `$TB_NAME` LIMIT :pageSize OFFSET :pageIndex")
    fun getMoviePage(pageSize: Int, pageIndex: Int): Flowable<List<UserEntity>>

//  @Query("SELECT * FROM $TB_NAME WHERE $TB_NAME.sth = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
//  fun getFavorite(pageSize: Int, pageIndex: Int): Flowable<List<UserEntity>>
}