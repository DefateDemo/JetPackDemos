package com.dfates.jetpackdemos.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Created by $USER_NAME on 2018/12/10.
 */
@Dao
interface BaseDao<T> {
    @Insert
    fun insert(t: T)

    @Insert
    fun insertAll(vararg t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)
}