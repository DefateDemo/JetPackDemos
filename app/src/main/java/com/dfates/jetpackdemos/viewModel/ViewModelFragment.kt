package com.dfates.jetpackdemos.viewModel


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_model.*

class ViewModelFragment : BaseFragment(R.layout.fragment_view_model) {

    private lateinit var mModel: NameViewModel

    private var value = 1

    override fun initData() {
        super.initData()
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tv_text.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)
    }

    override fun initListener() {
        super.initListener()
        btn_modify.setOnClickListener { v ->
            mModel.currentName.value = value.toString()
            value++
        }
    }

}

