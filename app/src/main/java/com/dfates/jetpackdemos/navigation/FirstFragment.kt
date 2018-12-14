package com.dfates.jetpackdemos.navigation


import androidx.navigation.Navigation
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment(R.layout.fragment_first) {

    override fun initListener() {
        super.initListener()
        btn_next.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

}
