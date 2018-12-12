package com.dfates.jetpackdemos.viewModel


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.R
import kotlinx.android.synthetic.main.fragment_view_model.*

class ViewModelFragment : Fragment() {

    private lateinit var mModel: NameViewModel

    private var value = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tv_text.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)

        btn_modify.setOnClickListener { v ->
            mModel.currentName.value = value.toString()
            value++
        }
    }


}

