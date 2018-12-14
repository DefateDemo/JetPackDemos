package com.dfates.jetpackdemos.room

import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.adapter.CommonAdapter
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.snackbarShow
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : BaseActivity(R.layout.activity_room), View.OnClickListener {
    private lateinit var adapter: CommonAdapter<User>

    override fun initListener() {
        super.initListener()
        btn_insert.setOnClickListener(this)
        btn_read.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
    }

    override fun initView() {
        super.initView()
        adapter = CommonAdapter<User>(this, R.layout.layout_list_item, null) { holder, data, _ ->
            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
        }
        list_view.adapter = adapter
    }

    override fun initData() {
        super.initData()
        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_insert -> {
                userDao().insertAll(User(null, 0, "123456"))
            }

            R.id.btn_read -> {
                userDao().findAll().lastOrNull().ifNotNull {
                    view.snackbarShow(it.toString())
                }
            }

            R.id.btn_update -> {
                userDao().findByName("123456")?.forEach {
                    it.age++
                    userDao().update(it)
                }
            }

            R.id.btn_delete -> {
                userDao().findAll().lastOrNull().ifNotNull {
                    userDao().delete(it)
                }
            }
        }
    }

}
