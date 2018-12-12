package com.dfates.jetpackdemos.liveData

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_data)

        userDao().all.observe(this, Observer<List<User>> { users ->
            val string = StringBuilder()
            users?.forEach {
                string.append(it.toString() + "\n")
            }
            tv_text.text = string
        })
    }

}
