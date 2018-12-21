package com.dfates.jetpackdemos.view_model

import androidx.fragment.app.Fragment
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.databinding.BaseBindingTabActivity
import com.dfates.jetpackdemos.databinding.ActivityViewModelBinding
import java.util.*

class ViewModelActivity : BaseBindingTabActivity<ActivityViewModelBinding>(R.layout.activity_view_model) {

    override val pages: List<Pair<String, Fragment>> by lazy {
        Arrays.asList("TAB1" to ViewModelFragment(), "TAB2" to ViewModelFragment(), "TAB3" to ViewModelFragment())
    }
}
