package com.dfates.jetpackdemos.databinding


import android.view.View
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingFragment

class DataBindingFragment : BaseBindingFragment<FragmentDataBindingBinding>(R.layout.fragment_data_binding) {

    override fun initView() {
        binding.text1 = "单向绑定"
        binding.text2 = ObservableString("双向绑定")
    }

}
