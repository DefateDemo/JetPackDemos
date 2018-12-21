package com.dfates.jetpackdemos.view_model


import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.common.bind.BindParam
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.common.bind.BindViewModel

class ViewModelFragment : BaseFragment(R.layout.fragment_view_model) {

    //绑定ViewModel
    @BindViewModel(true)    //获取父类的ViewModel
    private lateinit var mModel: ValueViewModel

    //绑定视图
    @BindView(R.id.tv_text)
    private lateinit var tvText: TextView

    @BindView(R.id.btn_modify)
    private lateinit var btnModify: Button

    @BindParam("value")
    private var value: Int = 0

    override fun initView() {
        super.initView()
        tvText.text = value.toString()
    }

    override fun initData() {
        super.initData()
        val nameObserver = Observer<Int> { newValue ->
            // Update the UI, in this case, a TextView.
            value = newValue
            tvText.text = value.toString()
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.mValue.observe(this, nameObserver)
    }

    override fun initListener() {
        super.initListener()
        btnModify.setOnClickListener {
            mModel.mValue.value = ++value
        }
    }

}

