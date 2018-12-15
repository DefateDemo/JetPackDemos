package com.dfates.jetpackdemos.liveData


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.common.adapter.CommonRecycleViewAdapter
import com.dfates.jetpackdemos.common.bindView.BindView
import com.dfates.jetpackdemos.room.database.userDao
import com.dfates.jetpackdemos.room.entity.User

class LiveDataFragment : BaseFragment(R.layout.fragment_live_data) {

    private lateinit var adapter: CommonRecycleViewAdapter<User>

    @BindView(R.id.recycler_view)
    private lateinit var recyclerView: RecyclerView

    override fun initView() {
        super.initView()

        adapter = object : CommonRecycleViewAdapter<User>(context!!, R.layout.layout_list_item, null, { holder, data, _, _ ->
            //            holder.getView<TextView>(R.id.tv_text)?.text = data.toString()
            holder.setText(R.id.tv_text, data.toString())
        }) {
            override fun getItemViewType(position: Int): Int {
                return super.getItemViewType(position)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun initData() {
        super.initData()

        userDao().all.observe(this, Observer<List<User>> { users ->
            adapter.update(users)
        })
    }

}
