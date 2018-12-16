package com.dfates.jetpackdemos.common.adapter

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.util.set

/**
 * Created by $USER_NAME on 2018/12/14.
 */

interface IViewHolder {

    var itemView: View

    val views: SparseArray<View>

    @Suppress("UNCHECKED_CAST")
    fun <T> getView(id: Int): T? {
        var view = views[id]
        if (view == null) {
            view = itemView.findViewById(id)
            views[id] = view
        }
        return view as T?
    }

    fun setText(id: Int, text: String): IViewHolder {
        getView<TextView>(id)?.text = text
        return this
    }
}




