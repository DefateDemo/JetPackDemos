package com.dfates.jetpackdemos.liveData


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.fragment_liva_data.*

class LiveDataFragment : Fragment() {

    private lateinit var adapter: CommonRecycleViewAdapter<User>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liva_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CommonRecycleViewAdapter(context!!, R.layout.layout_list_item, null) { holder, data, _, _ ->
//            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
            holder.setText(R.id.tv_text,data.toString())
        }

        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter

        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })


    }


}
