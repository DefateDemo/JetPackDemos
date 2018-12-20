package com.dfates.jetpackdemos


import androidx.navigation.Navigation
import com.dfates.jetpackdemos.base.databinding.BaseBindingFragment
import com.dfates.jetpackdemos.databinding.FragmentMainBinding

class MainFragment : BaseBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun initListener() {
        super.initListener()
        binding.setClickListener {
            Navigation.findNavController(binding.root)
                    .navigate(R.id.action_mainFragment_to_navigationDetailFragment)
        }
    }

}
