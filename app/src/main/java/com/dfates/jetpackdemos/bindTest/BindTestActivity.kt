package com.dfates.jetpackdemos.bindTest

import android.widget.TextView
import androidx.lifecycle.Observer
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseActivity
import com.dfates.jetpackdemos.common.bind.BindOnClick
import com.dfates.jetpackdemos.common.bind.BindParam
import com.dfates.jetpackdemos.common.bind.BindView
import com.dfates.jetpackdemos.common.bind.BindViewModel
import com.dfates.jetpackdemos.room.entity.User
import com.dfates.jetpackdemos.viewModel.ValueViewModel

class BindTestActivity : BaseActivity(R.layout.activity_bind_test) {

    //绑定ViewModel
    @BindViewModel(true)
    private lateinit var mModel: ValueViewModel  //根据类型匹配

    //绑定视图
    @BindView(R.id.tv_user)
    private lateinit var tvUser: TextView   //通过传入的id绑定对应的View对象
    @BindView(R.id.tv_str)
    private lateinit var tvStr: TextView
    @BindView(R.id.tv_text)
    private lateinit var tvText: TextView
    //绑定视图
//    @BindView(R.id.btn_modify,"onClick")    //绑定View对象的点击事件处理函数，要求该函数必须是一个没有参数或只有一个View作为参数的函数，
//    private lateinit var btnModify: Button

    //绑定参数
    @BindParam("value_int")
    private var value: Int = 0    //通过key值绑定arguments携带的参数
    @BindParam("value_string")
    private var string: String = ""
    @BindParam("value_user")
    private var user: User? = null

    override fun initView() {
        super.initView()
        tvStr.text = string
        tvUser.text = user.toString()
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

    @BindOnClick(R.id.btn_modify)   //绑定view点击事件，要求该函数必须是一个没有参数或只有一个View作为参数的函数
    fun onClick() {
        mModel.mValue.value = ++value
    }

}
