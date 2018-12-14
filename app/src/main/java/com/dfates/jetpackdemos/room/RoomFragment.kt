package com.dfates.jetpackdemos.room


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.adapter.CommonAdapter
import com.dfates.jetpackdemos.common.ifNotNull
import com.dfates.jetpackdemos.common.snackbarShow
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User
import kotlinx.android.synthetic.main.fragment_room.*

class RoomFragment : Fragment(), View.OnClickListener {
    private lateinit var adapter: CommonAdapter<User>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_insert.setOnClickListener(this)
        btn_read.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)

        adapter = CommonAdapter<User>(context!!, R.layout.layout_list_item, null) { holder, data, _ ->
            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
        }
        list_view.adapter = adapter

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
