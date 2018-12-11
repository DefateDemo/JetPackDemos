package com.dfates.jetpackdemos.viewModel


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.R

class ViewModelFragment : Fragment() {

    private lateinit var mModel: NameViewModel

    private lateinit var tvText: TextView
    private lateinit var btnModify: Button
    private var value = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvText = view.findViewById(R.id.tv_text)
        btnModify = view.findViewById(R.id.btn_modify)

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tvText.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)

        btnModify.setOnClickListener { v ->
            mModel.currentName.value = value.toString()
            value++
        }
    }


}

