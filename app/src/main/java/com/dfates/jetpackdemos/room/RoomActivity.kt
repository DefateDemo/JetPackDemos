package com.dfates.jetpackdemos.room

import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.snackbarShow
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import com.dfates.jetpackdemos.R

class RoomActivity : BaseActivity(R.layout.activity_room) {
    private lateinit var adapter: CommonRecycleViewAdapter<User>

    @BindView(R.id.recycle_view)
    private lateinit var recyclerView: RecyclerView

    @BindView(R.id.btn_insert,"onClick")
    private lateinit var btnInsert: Button
    @BindView(R.id.btn_read,"onClick")
    private lateinit var btnRead: Button
    @BindView(R.id.btn_update,"onClick")
    private lateinit var btnUpdate: Button
    @BindView(R.id.btn_delete,"onClick")
    private lateinit var btnDelete: Button

    override fun initView() {
        super.initView()

        adapter = object : CommonRecycleViewAdapter<User>(this, R.layout.layout_list_item, null, { holder, data, _, _ ->
            //            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
            holder.setText(R.id.tv_text, data.toString())
        }) {
            override fun getItemViewType(position: Int): Int {
                return super.getItemViewType(position)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun initData() {
        super.initData()
        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

    fun onClick(view: View?) {
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
