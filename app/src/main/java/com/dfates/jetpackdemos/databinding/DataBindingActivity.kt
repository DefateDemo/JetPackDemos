package com.dfates.jetpackdemos.databinding

import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingActivity

class DataBindingActivity : BaseBindingActivity<ActivityDataBindingBinding>(R.layout.activity_data_binding) {

    override fun initView() {
        binding.text1 = "单向绑定"

        binding.text2 = ObservableString("双向绑定")
    }
}
