package com.dfates.jetpackdemos.live_data

import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseListActivity
import com.dfates.jetpackdemos.common.adapter.SimpleViewHolder
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class LiveDataActivity : BaseListActivity<User>(R.layout.activity_live_data, R.layout.layout_list_item) {

    override fun initData() {
        super.initData()

        userDao.all.observe(this, Observer<List<User>> { users ->
            adapter?.update(users)
        })
    }

    override fun convert(holder: SimpleViewHolder, data: User?, position: Int) {
        holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
    }

}
