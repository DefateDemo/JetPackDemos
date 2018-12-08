package com.dfates.jetpackdemos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by $USER_NAME on 2018/12/8.
 */
abstract class BaseBindingFragment<VB : ViewDataBinding> : Fragment() {

    protected lateinit var binding: VB

    protected abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

}
