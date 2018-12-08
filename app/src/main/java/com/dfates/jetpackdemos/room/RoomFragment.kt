package com.dfates.jetpackdemos.room


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.snackbarShow
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class RoomFragment : Fragment(), View.OnClickListener {

    private lateinit var tvResult: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_insert).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_read).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_update).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
        tvResult = view.findViewById(R.id.tv_result)
        userDao().findAll().observe(this, Observer { users ->
            val string = StringBuilder()
            users.forEach {
                string.append(it.firstName + it.lastName + "\n")
            }
            tvResult.text = string
        })
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_insert -> {
                userDao().insertAll(User("Gong", "Bo"))
            }

            R.id.btn_read -> {
                userDao().all.lastOrNull().ifNotNull {
                    view.snackbarShow(it?.firstName + it?.lastName)
                }
            }

            R.id.btn_update -> {
                userDao().findByName("Gong", "Bo").ifNotNull {
                    it!!.firstName = "123"
                    it.lastName = "456"
                    userDao().update(it)
                }
            }

            R.id.btn_delete -> {
                userDao().all.lastOrNull().ifNotNull {
                    userDao().delete(it!!)
                }
            }
        }
    }

}
