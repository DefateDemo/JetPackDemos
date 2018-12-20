package com.dfates.jetpackdemos.base.databinding

import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.databinding.ViewDataBinding
import com.dfates.jetpackdemos.BR
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.base.adapter.BaseDataBindingSimpleAdapter
import com.dfates.jetpackdemos.common.bind.BindView

/**
 * Created by $USER_NAME on 2018/12/20.
 */

abstract class BaseBindingListFragment<M, VB : ViewDataBinding>(layoutId: Int, val itemLayoutId: Int) : BaseFragment(layoutId), AdapterView.OnItemClickListener {

    @BindView(R.id.list_view)
    protected lateinit var listView: ListView

    @BindView(R.id.empty_view)
    protected lateinit var emptyView: View

    protected var adapter: BaseDataBindingSimpleAdapter<M>? = null

    protected var mDatas: List<M?>? = null
        set(value) {
            field = value
            adapter?.update(value)
        }

    override fun initView() {
        super.initView()
        adapter = object : BaseDataBindingSimpleAdapter<M>(context!!, itemLayoutId, mDatas) {
            override fun onBindView(viewDataBinding: ViewDataBinding, data: M, position: Int) {
                this@BaseBindingListFragment.onBindView(viewDataBinding as VB, data, position)
            }
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

    open fun onBindView(viewDataBinding: VB, data: M, position: Int) {
        viewDataBinding.setVariable(BR.item, data)
    }

    open fun onItemClick(item: M?, position: Int, id: Long) {

    }
}
