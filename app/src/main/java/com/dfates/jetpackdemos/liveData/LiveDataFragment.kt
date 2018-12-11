package com.dfates.jetpackdemos.liveData


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class LiveDataFragment : Fragment() {


    private lateinit var tvText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liva_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvText = view.findViewById(R.id.tv_text)

        userDao().all.observe(this, Observer<List<User>> { users ->
            val string = StringBuilder()
            users?.forEach {
                string.append(it.toString() + "\n")
            }
            tvText.text = string
        })
    }


}
