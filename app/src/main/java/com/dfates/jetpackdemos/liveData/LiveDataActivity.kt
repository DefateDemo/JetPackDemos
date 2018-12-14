package com.dfates.jetpackdemos.liveData

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : BaseActivity(R.layout.activity_live_data) {

    private lateinit var adapter: CommonRecycleViewAdapter<User>

    override fun initView() {
        super.initView()

        adapter = CommonRecycleViewAdapter(this, R.layout.layout_list_item, null) { holder, data, _, _ ->
            //            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
            holder.setText(R.id.tv_text, data.toString())
        }

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
    }

    override fun initData() {
        super.initData()

        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

}
