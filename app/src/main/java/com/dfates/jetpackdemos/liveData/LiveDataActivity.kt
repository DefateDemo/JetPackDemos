package com.dfates.jetpackdemos.liveData

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class LiveDataActivity : AppCompatActivity() {

    private lateinit var tvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_data)

        tvText = findViewById(R.id.tv_text)

        userDao().all.observe(this, Observer<List<User>> { users ->
            val string = StringBuilder()
            users?.forEach {
                string.append(it.toString() + "\n")
            }
            tvText.text = string
        })
    }

}
