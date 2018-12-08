package com.dfates.jetpackdemos.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dfates.jetpackdemos.room.entity.User

/**
 * Created by $USER_NAME on 2018/12/8.
 */
@Dao
interface UserDao {
    @get:Query("SELECT * FROM user")
    val all: List<User>

    @Query("SELECT * FROM user")
    fun findAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User?

    @Insert
    fun insertAll(vararg users: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

}