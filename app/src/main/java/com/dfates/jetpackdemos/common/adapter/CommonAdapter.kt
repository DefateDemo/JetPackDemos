package com.dfates.jetpackdemos.common.adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import com.dfates.jetpackdemos.base.adapter.BaseSimpleAdapter

/**
 * ListView,GridView简洁适配器，使用传入闭包处理的方式实现
 */
open class CommonAdapter<M>(mContext: Context, layoutId: Int, mDatas: List<M?>?, var convert: (holder: SimpleViewHolder, data: M?, position: Int) -> Unit) : BaseSimpleAdapter<M, SimpleViewHolder>(mContext, layoutId, mDatas) {

    override fun setView(convertView: View, holder: SimpleViewHolder, data: M?, position: Int) {
        convert.invoke(holder, data, position)
    }

    override fun getViewHolder(convertView: View?): SimpleViewHolder {
        return SimpleViewHolder(convertView!!)
    }

}

class SimpleViewHolder(override var itemView: View, override val views: SparseArray<View> = SparseArray()) : IViewHolder