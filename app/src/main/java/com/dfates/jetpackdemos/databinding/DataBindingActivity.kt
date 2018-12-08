package com.dfates.jetpackdemos.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dfates.jetpackdemos.R

class DataBindingActivity : AppCompatActivity() {

    lateinit var binding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        initView()
    }

    fun initView(){
        binding.text1 = "单向绑定"

        binding.text2 = ObservableString("双向绑定")
    }
}
