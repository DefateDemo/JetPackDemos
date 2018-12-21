package com.dfates.jetpackdemos.base

import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.common.adapter.CommonAdapter
import com.dfates.jetpackdemos.common.adapter.SimpleViewHolder
import com.dfates.jetpackdemos.common.bind.BindView

/**
 * Created by $USER_NAME on 2018/12/20.
 */

abstract class BaseListFragment<M>(layoutId: Int, val itemLayoutId: Int) : BaseFragment(layoutId), AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    protected open lateinit var listView: ListView

    @BindView(R.id.empty_view)
    protected open lateinit var emptyView: View

    protected open var adapter: CommonAdapter<M>? = null

    protected open var mDatas: List<M?>? = null
        set(value) {
            field = value
            adapter?.update(value)
        }

    override fun initView() {
        super.initView()
        adapter = CommonAdapter(context!!, itemLayoutId, mDatas) { holder, data, position ->
            convert(holder, data, position)
        }
        listView.adapter = adapter
        listView.emptyView = emptyView
    }

    override fun initListener() {
        super.initListener()
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemClick(mDatas!![position], position, id)
    }

    open fun convert(holder: SimpleViewHolder, data: M?, position: Int) {
    }

    open fun onItemClick(item: M?, position: Int, id: Long) {

    }
}
