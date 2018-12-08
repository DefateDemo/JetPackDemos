package com.dfates.jetpackdemos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by $USER_NAME on 2018/12/8.
 */
abstract class BaseBindingActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }


}
