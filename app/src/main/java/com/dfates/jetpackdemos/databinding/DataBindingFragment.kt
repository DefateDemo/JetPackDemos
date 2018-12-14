package com.dfates.jetpackdemos.databinding


import android.os.Bundle
import android.view.View
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingFragment

class DataBindingFragment : BaseBindingFragment<FragmentDataBindingBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_data_binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        binding.text1 = "单向绑定"
        binding.text2 = ObservableString("双向绑定")
    }

}
