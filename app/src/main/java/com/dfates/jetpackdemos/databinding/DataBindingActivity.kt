package com.dfates.jetpackdemos.databinding

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingActivity
import com.dfates.jetpackdemos.base.adapter.BaseDataBindingRecycleViewAdapter
import com.dfates.jetpackdemos.base.adapter.BaseDataBindingSimpleAdapter

class DataBindingActivity : BaseBindingActivity<ActivityDataBindingBinding>(R.layout.activity_data_binding) {

    override fun initView() {
        binding.text1 = "单向绑定"

        binding.text2 = ObservableString("双向绑定")
        //列表绑定
        val adapter = BaseDataBindingSimpleAdapter<String>(this,
                R.layout.layout_binding_list_item, arrayListOf("ListView绑定1", "ListView绑定2", "ListView绑定3"))
        binding.listView.adapter = adapter

        binding.recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recycleView.adapter = BaseDataBindingRecycleViewAdapter<String>(this,
                R.layout.layout_binding_list_item, arrayListOf("RecycleView绑定1", "RecycleView绑定2", "RecycleView绑定3"))
    }
}
