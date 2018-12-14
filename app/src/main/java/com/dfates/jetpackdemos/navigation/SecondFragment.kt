package com.dfates.jetpackdemos.navigation


import androidx.navigation.Navigation
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : BaseFragment(R.layout.fragment_second) {

    override fun initListener() {
        super.initListener()
        btn_back.setOnClickListener { v ->
            Navigation.findNavController(v).navigateUp()
        }
    }

}
