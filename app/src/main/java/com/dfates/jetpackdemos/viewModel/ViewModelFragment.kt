package com.dfates.jetpackdemos.viewModel


import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import com.dfates.jetpackdemos.common.bind.BindParam
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.common.bind.BindViewModel
import com.dfates.jetpackdemos.common.getViewModel
import kotlinx.android.synthetic.main.fragment_view_model.*

class ViewModelFragment : BaseFragment(R.layout.fragment_view_model) {

    //绑定ViewModel
    @BindViewModel
    private lateinit var mModel: NameViewModel

    //绑定视图
    @BindView(R.id.tv_text)
    private lateinit var tvText: TextView

    @BindView(R.id.btn_modify)
    private lateinit var btnModify: Button

    private var value: Int = 0

    override fun initView() {
        super.initView()
        tvText.text =  value.toString()
    }

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
        btnModify.setOnClickListener {
            value++
            mModel.currentName.value = value.toString()
        }
    }

}

