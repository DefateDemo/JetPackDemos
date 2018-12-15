package com.dfates.jetpackdemos.room.database

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dfates.jetpackdemos.room.dao.UserDao
import com.dfates.jetpackdemos.room.entity.User


/**
 * Created by $USER_NAME on 2018/12/8.
 */

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private val DATABASE_NAME = "test_db"
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                        // allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


fun <T : Context> T.userDao(): UserDao {
    return AppDatabase.getAppDatabase(this).userDao()
}

fun <T : Fragment> T.userDao(): UserDao {
    return AppDatabase.getAppDatabase(this.context!!).userDao()
}