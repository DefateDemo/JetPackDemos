package com.dfates.jetpackdemos.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by $USER_NAME on 2018/12/8.
 */
@Entity(tableName = "t_user")
data class User(
        @PrimaryKey
        @ColumnInfo(name = "user_acct")
        var userAcct: String,

        var age: Int? = 0,

        var name: String? = ""
)
