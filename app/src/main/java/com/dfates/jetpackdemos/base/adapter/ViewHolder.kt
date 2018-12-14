package com.dfates.jetpackdemos.base.adapter

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by $USER_NAME on 2018/12/14.
 */

interface IViewHolder {

    var convertView: View

    val views: SparseArray<View>

    fun <T> getView(id: Int): T? {
        var view = views[id]
        if (view == null) {
            view = convertView.findViewById(id)
            views[id] = view
        }
        return view as T?
    }

    fun setText(id: Int, text: String): IViewHolder {
        getView<TextView>(id)?.text = text
        return this
    }

}

class ViewHolder(override var convertView: View, override val views: SparseArray<View> = SparseArray()) : IViewHolder


class RecycleViewHolder(override var convertView: View, override val views: SparseArray<View> = SparseArray()) : RecyclerView.ViewHolder(convertView), IViewHolder
