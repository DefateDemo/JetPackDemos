package com.dfates.jetpackdemos.liveData


import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseListFragment
import com.dfates.jetpackdemos.common.adapter.SimpleViewHolder
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class LiveDataFragment : BaseListFragment<User>(R.layout.fragment_live_data, R.layout.layout_list_item) {

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
