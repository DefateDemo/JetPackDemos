package com.dfates.jetpackdemos.base.adapter

import android.content.Context
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.isEmpty
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView

/**
 * RecycleView通用适配器，需要继承实现
 * 单个布局文件时直接传入对应的layoutId即可，多个布局时传入 arrayOf(viewType to layoutId...)
 */
abstract class BaseRecycleViewAdapter<M>(val mContext: Context, var layoutIds: SparseIntArray?, var mDatas: List<M?>?) : RecyclerView.Adapter<RecycleViewHolder>() {

    protected var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    constructor(mContext: Context, layoutIds: Array<Pair<Int, Int>>, mDatas: List<M?>?) : this(mContext, layoutIds.toSparseArray(), mDatas)

    constructor(mContext: Context, layoutId: Int, mDatas: List<M?>?) : this(mContext, arrayOf(0 to layoutId), mDatas)

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

/**
 * RecycleView简洁适配器，，使用传入闭包处理的方式实现
 */
class CommonRecycleViewAdapter<M>(mContext: Context, layoutIds: SparseIntArray?, mDatas: List<M?>?, var convert: ((RecycleViewHolder, M?, position: Int, viewType: Int) -> Unit)?) : BaseRecycleViewAdapter<M>(mContext, layoutIds, mDatas) {

    constructor(mContext: Context, layoutIds: Array<Pair<Int, Int>>, mDatas: List<M?>?, convert: ((RecycleViewHolder, M?, position: Int, viewType: Int) -> Unit)?) : this(mContext, layoutIds.toSparseArray(), mDatas, convert)

    constructor(mContext: Context, layoutId: Int, mDatas: List<M?>?, convert: ((RecycleViewHolder, M?, position: Int, viewType: Int) -> Unit)?) : this(mContext, arrayOf(0 to layoutId), mDatas, convert)

    override fun onBindViewHolder(holder: RecycleViewHolder, data: M?, position: Int, viewType: Int) {
        convert?.invoke(holder, data, position, viewType)
    }

}

fun Array<Pair<Int, Int>>.toSparseArray(): SparseIntArray {
    val sparseArray = SparseIntArray()
    this.forEach {
        sparseArray[it.first] = it.second
    }
    return sparseArray
}

