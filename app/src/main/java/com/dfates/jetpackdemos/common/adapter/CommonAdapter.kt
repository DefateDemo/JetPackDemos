package com.dfates.jetpackdemos.common.adapter

import android.content.Context
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.View
import com.dfates.jetpackdemos.base.adapter.BaseSimpleAdapter
import com.dfates.jetpackdemos.common.toSparseIntArray

//数据-视图转换函数别名
typealias CommonAdapterConvert<M> = (holder: SimpleViewHolder, data: M?, position: Int) -> Unit

/**
 * ListView,GridView简洁适配器，使用传入闭包处理的方式实现
 */
open class CommonAdapter<M>(mContext: Context, layoutIds: SparseIntArray?, mDatas: List<M?>?, var convert: CommonAdapterConvert<M>? = null) : BaseSimpleAdapter<M, SimpleViewHolder>(mContext, layoutIds, mDatas) {

    /**
     * 使用arrayOf(viewType to layoutId,...) 的形式传入多个布局
     */
    constructor(mContext: Context, layoutIds: Array<Pair<Int, Int>>, mDatas: List<M?>?, convert: CommonAdapterConvert<M>? = null) : this(mContext, layoutIds.toSparseIntArray(), mDatas, convert)

    /**
     * 传入单个布局
     */
    constructor(mContext: Context, layoutId: Int, mDatas: List<M?>?, convert: CommonAdapterConvert<M>? = null) : this(mContext, arrayOf(0 to layoutId), mDatas, convert)

    override fun setView(convertView: View, holder: SimpleViewHolder, data: M?, position: Int) {
        convert(holder, data, position)
    }

    open fun convert(holder: SimpleViewHolder, data: M?, position: Int) {
        convert?.invoke(holder, data, position)
    }

    override fun getViewHolder(convertView: View?): SimpleViewHolder {
        return SimpleViewHolder(convertView!!)
    }

}

class SimpleViewHolder(override var itemView: View, override val views: SparseArray<View> = SparseArray()) : IViewHolder