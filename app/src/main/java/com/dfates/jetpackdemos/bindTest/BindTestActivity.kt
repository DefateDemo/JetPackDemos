package com.dfates.jetpackdemos.bindTest

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.bindView.BindView
import com.dfates.jetpackdemos.common.param.BindParam
import com.dfates.jetpackdemos.common.viewModel.BindViewModel
import com.dfates.jetpackdemos.viewModel.NameViewModel

class BindTestActivity : BaseActivity(R.layout.activity_bind_test) {

    //绑定ViewModel
    @BindViewModel
    private lateinit var mModel: NameViewModel

    //绑定视图
    @BindView(R.id.tv_text)
    private lateinit var tvText: TextView
    //绑定视图
    @BindView(R.id.btn_modify)
    private lateinit var btnModify: Button

    //绑定参数
    @BindParam("value")
    private var value: Int = 0

    override fun initData() {
        super.initData()
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tvText.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)
    }

    override fun initListener() {
        super.initListener()
        btnModify.setOnClickListener { v ->
            mModel.currentName.value = value.toString()
            value++
        }
    }

}
