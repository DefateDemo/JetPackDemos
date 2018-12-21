package com.dfates.jetpackdemos.view_model


import androidx.fragment.app.Fragment
import com.dfates.jetpackdemos.R
import com.dfates.jetpackdemos.base.databinding.BaseBindingTabFragment
import com.dfates.jetpackdemos.databinding.FragmentParentViewModelBinding
import java.util.*

class ParentViewModelFragment : BaseBindingTabFragment<FragmentParentViewModelBinding>(R.layout.fragment_parent_view_model) {

    override val pages: List<Pair<String, Fragment>> by lazy {
        Arrays.asList("TAB1" to ViewModelFragment(), "TAB2" to ViewModelFragment(), "TAB3" to ViewModelFragment())
    }
}
