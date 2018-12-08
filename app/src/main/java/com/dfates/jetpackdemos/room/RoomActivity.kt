package com.dfates.jetpackdemos.room

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.room.database.AppDatabase
import com.dfates.jetpackdemos.room.entity.User
import com.google.android.material.snackbar.Snackbar

class RoomActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        findViewById<Button>(R.id.btn_insert).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read).setOnClickListener(this)
        findViewById<Button>(R.id.btn_update).setOnClickListener(this)
        findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_insert -> {
                AppDatabase.getAppDatabase(this).userDao().insertAll(User("Gong", "Bo"))
            }

            R.id.btn_read -> {
                AppDatabase.getAppDatabase(this).userDao().all.forEach { user ->
                    Snackbar.make(view, user.firstName + user.lastName, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
            }

            R.id.btn_update -> {
                val user = AppDatabase.getAppDatabase(this).userDao().findByName("Gong", "Bo")
                if(user != null) {
                    user.firstName = "123"
                    user.lastName = "456"
                    AppDatabase.getAppDatabase(this).userDao().update(user)
                }
            }

            R.id.btn_delete -> {
                AppDatabase.getAppDatabase(this).userDao().all.forEach { user ->
                    AppDatabase.getAppDatabase(this).userDao().delete(user)
                }
            }
        }
    }

}
