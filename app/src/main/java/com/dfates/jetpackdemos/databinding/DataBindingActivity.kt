package com.dfates.jetpackdemos.databinding

import android.os.Bundle
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseBindingActivity

class DataBindingActivity : BaseBindingActivity<ActivityDataBindingBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_data_binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    fun initView() {
        binding.text1 = "单向绑定"

        binding.text2 = ObservableString("双向绑定")
    }
}
