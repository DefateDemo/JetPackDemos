package com.dfates.jetpackdemos.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by $USER_NAME on 2018/12/8.
 */
abstract class BaseBindingActivity<VB : ViewDataBinding>(layoutId: Int) : BaseActivity(layoutId) {

    protected lateinit var binding: VB

    override fun setContentView() {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

}
