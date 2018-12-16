package com.dfates.jetpackdemos.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.dfates.jetpackdemos.base.BaseDao
import com.dfates.jetpackdemos.room.entity.User

/**
 * Created by $USER_NAME on 2018/12/8.
 */
@Dao
interface UserDao : BaseDao<User> {
    @get:Query("SELECT * FROM t_user")
    val all: LiveData<List<User>>

    @Query("SELECT * FROM t_user")
    fun findAll(): List<User>

    @Query("SELECT * FROM t_user WHERE id = :id")
    fun findById(id: Int): User?

    @Query("SELECT * FROM t_user WHERE name = :name")
    fun findByName(name: String): List<User>?

}
