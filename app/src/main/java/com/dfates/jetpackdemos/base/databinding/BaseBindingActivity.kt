package com.dfates.jetpackdemos.base.databinding

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dfates.jetpackdemos.base.BaseActivity

/**
 * Created by $USER_NAME on 2018/12/8.
 */
abstract class BaseBindingActivity<VB : ViewDataBinding>(layoutId: Int) : BaseActivity(layoutId) {

    protected lateinit var binding: VB

    override fun setContentView() {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

}
