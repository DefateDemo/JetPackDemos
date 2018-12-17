package com.dfates.jetpackdemos.base.adapter

import android.content.Context
import android.util.SparseIntArray
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.dfates.jetpackdemos.common.toSparseIntArray

/**
 * ListView,GridView通用适配器，使用继承的方式实现
 */
abstract class BaseSimpleAdapter<M, VH>(val mContext: Context, var layoutIds: SparseIntArray?, var mDatas: List<M?>?) : BaseAdapter() {

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
        val mHolder: VH
        if (convertView == null) {
            convertView = View.inflate(mContext, layoutIds!![getItemViewType(position)], null)
            mHolder = getViewHolder(convertView)
            convertView!!.tag = mHolder
        } else {
            @Suppress("UNCHECKED_CAST")
            mHolder = convertView.tag as VH
        }
        val data = getItem(position)
        setView(convertView, mHolder, data, position)

        return convertView
    }

    abstract fun setView(convertView: View, holder: VH, data: M?, position: Int)

    abstract fun getViewHolder(convertView: View?): VH

    override fun getItem(position: Int): M? = if (mDatas == null) null else mDatas!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = if (mDatas == null) 0 else mDatas!!.size

    override fun getItemViewType(position: Int): Int = 0

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    fun notifyDataSetChanged(listView: ListView, position: Int) {
        /**第一个可见的位置 */
        val firstVisiblePosition = listView.firstVisiblePosition
        /**最后一个可见的位置 */
        val lastVisiblePosition = listView.lastVisiblePosition

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新 */
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象 */
            val view = listView.getChildAt(position - firstVisiblePosition)
            getView(position, view, listView)
        }

    }

}






