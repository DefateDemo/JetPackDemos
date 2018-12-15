package com.dfates.jetpackdemos.databinding


import androidx.recyclerview.widget.LinearLayoutManager
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingFragment
import com.dfates.jetpackdemos.base.adapter.BaseDataBindingRecycleViewAdapter
import com.dfates.jetpackdemos.base.adapter.BaseDataBindingSimpleAdapter

class DataBindingFragment : BaseBindingFragment<FragmentDataBindingBinding>(R.layout.fragment_data_binding) {

    override fun initView() {
        binding.text1 = "单向绑定"
        binding.text2 = ObservableString("双向绑定")
        //列表绑定
        val adapter = BaseDataBindingSimpleAdapter<String, LayoutBindingListItemBinding>(context!!,
                R.layout.layout_binding_list_item, arrayListOf("ListView绑定1", "ListView绑定2", "ListView绑定3"))
        binding.listView.adapter = adapter

        binding.recycleView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        binding.recycleView.adapter = BaseDataBindingRecycleViewAdapter<String, LayoutBindingListItemBinding>(context!!,
                R.layout.layout_binding_list_item, arrayListOf("RecycleView绑定1", "RecycleView绑定2", "RecycleView绑定3"))
    }

}
