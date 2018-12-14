package com.dfates.jetpackdemos.base.adapter

import android.content.Context
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.isEmpty
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.common.adapter.RecycleViewHolder

/**
 * RecycleView通用适配器，需要继承实现
 * 单个布局文件时直接传入对应的layoutId即可，多个布局时传入 arrayOf(viewType to layoutId...)
 */
abstract class BaseRecycleViewAdapter<M>(val mContext: Context, var layoutIds: SparseIntArray?, var mDatas: List<M?>?) : RecyclerView.Adapter<RecycleViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val convertView = mLayoutInflater.inflate(layoutIds!![viewType], parent, false)
        return RecycleViewHolder(convertView)
    }

    override fun getItemCount(): Int = if (mDatas == null || layoutIds == null || layoutIds!!.isEmpty()) 0 else mDatas!!.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        onBindViewHolder(holder, mDatas?.get(position), position, getItemViewType(position))
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    abstract fun onBindViewHolder(holder: RecycleViewHolder, data: M?, position: Int, viewType: Int)
}

fun Array<Pair<Int, Int>>.toSparseIntArray(): SparseIntArray {
    val sparseArray = SparseIntArray()
    this.forEach {
        sparseArray[it.first] = it.second
    }
    return sparseArray
}

