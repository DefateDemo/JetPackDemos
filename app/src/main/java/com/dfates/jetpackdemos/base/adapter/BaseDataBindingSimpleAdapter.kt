package com.dfates.jetpackdemos.base.adapter

import android.content.Context
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dfates.jetpackdemos.BR
import com.dfates.jetpackdemos.common.toSparseIntArray

open class BaseDataBindingSimpleAdapter<M>(val mContext: Context, var layoutIds: SparseIntArray?, var mDatas: List<M?>?) : BaseAdapter() {

    protected open var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

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

    @Suppress("NAME_SHADOWING")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewType = getItemViewType(position)
        val viewDataBinding: ViewDataBinding
        if (convertView == null) {
            viewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutIds!![viewType], parent, false)
            convertView = viewDataBinding.root
            convertView.tag = viewDataBinding
        } else {
            @Suppress("UNCHECKED_CAST")
            viewDataBinding = convertView.tag as ViewDataBinding
        }
        onBindView(viewDataBinding, getItem(position)!!, position)
        return convertView
    }

    protected open fun <VB : ViewDataBinding> onBindView(viewDataBinding: VB, data: M, position: Int) {
        viewDataBinding.setVariable(BR.item, data)
    }

    override fun getItem(position: Int): M? = if (mDatas == null) null else mDatas!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = if (mDatas == null) 0 else mDatas!!.size

    override fun getItemViewType(position: Int): Int = 0
}