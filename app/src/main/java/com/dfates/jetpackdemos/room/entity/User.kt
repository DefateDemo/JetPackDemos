package com.dfates.jetpackdemos.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by $USER_NAME on 2018/12/8.
 */
@Entity(tableName = "t_user")
data class User(
        @PrimaryKey
        var id: Int? = null,

        var age: Int = 0,

        var name: String? = ""
) : Serializable
