package com.dfates.jetpackdemos.liveData


import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.common.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.fragment_liva_data.*

class LiveDataFragment : BaseFragment(R.layout.fragment_liva_data) {

    private lateinit var adapter: CommonRecycleViewAdapter<User>

    override fun initView() {
        super.initView()

        adapter = CommonRecycleViewAdapter(context!!, R.layout.layout_list_item, null) { holder, data, _, _ ->
            //            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
            holder.setText(R.id.tv_text, data.toString())
        }

        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
    }

    override fun initData() {
        super.initData()

        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

}
