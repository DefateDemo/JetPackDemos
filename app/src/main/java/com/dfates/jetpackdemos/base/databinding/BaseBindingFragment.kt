package com.dfates.jetpackdemos.base.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dfates.jetpackdemos.base.BaseFragment

/**
 * Created by $USER_NAME on 2018/12/8.
 */
abstract class BaseBindingFragment<VB : ViewDataBinding>(layoutId: Int) : BaseFragment(layoutId) {

    protected lateinit var binding: VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

}
