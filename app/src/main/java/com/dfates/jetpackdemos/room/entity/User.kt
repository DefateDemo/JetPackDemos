package com.dfates.jetpackdemos.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by $USER_NAME on 2018/12/8.
 */
@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    constructor(firstName: String?, lastName: String?) {
        this.firstName = firstName
        this.lastName = lastName
    }
}
