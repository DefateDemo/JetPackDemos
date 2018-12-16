package com.dfates.jetpackdemos.liveData

import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.adapter.CommonAdapter
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import com.dfates.jetpackdemos.R

class LiveDataActivity : BaseActivity(R.layout.activity_live_data) {

    private lateinit var adapter: CommonAdapter<User>

    @BindView(R.id.list_view)
    private lateinit var listView: ListView
    @BindView(R.id.empty_view)
    private lateinit var emptyView: View

    override fun initView() {
        super.initView()

        adapter = CommonAdapter<User>(this, R.layout.layout_list_item, null) { holder, data, _ ->
            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
        }
        listView.emptyView =emptyView
        listView.adapter = adapter
    }

    override fun initData() {
        super.initData()

        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

}
