package com.dfates.jetpackdemos.common.adapter

import android.content.Context
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.base.adapter.BaseRecycleViewAdapter
import com.dfates.jetpackdemos.common.toSparseIntArray

////数据-视图转换函数别名
typealias CommonRecycleViewAdapterConvert<M> = ((holder: RecycleViewHolder, data: M?, position: Int, viewType: Int) -> Unit)

/**
 * RecycleView简洁适配器，，使用传入闭包处理的方式实现
 */
open class CommonRecycleViewAdapter<M>(mContext: Context, layoutIds: SparseIntArray?, mDatas: List<M?>?, private var convert: CommonRecycleViewAdapterConvert<M>? = null) : BaseRecycleViewAdapter<M>(mContext, layoutIds, mDatas) {
    /**
     * 使用arrayOf(viewType to layoutId,...) 的形式传入多个布局
     */
    constructor(mContext: Context, layoutIds: Array<Pair<Int, Int>>, mDatas: List<M?>?, convert: CommonRecycleViewAdapterConvert<M>? = null) : this(mContext, layoutIds.toSparseIntArray(), mDatas, convert)

    /**
     * 传入单个布局
     */
    constructor(mContext: Context, layoutId: Int, mDatas: List<M?>?, convert: CommonRecycleViewAdapterConvert<M>? = null) : this(mContext, arrayOf(0 to layoutId), mDatas, convert)


    override fun onBindViewHolder(holder: RecycleViewHolder, data: M?, position: Int, viewType: Int) {
        convert(holder, data, position, viewType)
    }

    open fun convert(holder: RecycleViewHolder, data: M?, position: Int, viewType: Int) {
        convert?.invoke(holder, data, position, viewType)
    }

}

/**
 *
 */
class RecycleViewHolder(override var itemView: View, override val views: SparseArray<View> = SparseArray()) : RecyclerView.ViewHolder(itemView), IViewHolder

