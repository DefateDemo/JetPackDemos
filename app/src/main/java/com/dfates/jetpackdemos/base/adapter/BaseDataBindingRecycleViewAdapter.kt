package com.dfates.jetpackdemos.base.adapter

import android.content.Context
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.BR
import com.dfates.jetpackdemos.common.toSparseIntArray

/**
 * Created by $USER_NAME on 2018/12/15.
 */
open class BaseDataBindingRecycleViewAdapter<M, VB : ViewDataBinding>(val mContext: Context, var layoutIds: SparseIntArray?, var mDatas: List<M?>?) : RecyclerView.Adapter<BindingViewHolder<VB>>() {

    protected var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    /**
     * 使用arrayOf(viewType to layoutId,...) 的形式传入多个布局
     */
    constructor(mContext: Context, layoutIds: Array<Pair<Int, Int>>, mDatas: List<M?>?) : this(mContext, layoutIds.toSparseIntArray(), mDatas)

    /**
     * 传入单个布局
     */
    constructor(mContext: Context, layoutId: Int, mDatas: List<M?>?) : this(mContext, arrayOf(0 to layoutId), mDatas)

    /**
     * 更新数据集
     */
    fun update(datas: List<M?>?) {
        mDatas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<VB> {
        val viewDataBinding: VB = DataBindingUtil.inflate(mLayoutInflater, layoutIds!![viewType], parent, false)
        return BindingViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = if (mDatas == null || layoutIds == null || layoutIds!!.isEmpty()) 0 else mDatas!!.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, position: Int, payloads: MutableList<Any>) {
        onBindView(holder.binding, mDatas!![position]!!, position, payloads)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<VB>, position: Int) {
        onBindView(holder.binding, mDatas!![position]!!, position)
    }

    protected open fun onBindView(viewDataBinding: VB, data: M, position: Int, payloads: List<Any>) {
        viewDataBinding.setVariable(BR.item, data)
    }

    protected open fun onBindView(viewDataBinding: VB, data: M, position: Int) {
        viewDataBinding.setVariable(BR.item, data)
    }
}

class BindingViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)