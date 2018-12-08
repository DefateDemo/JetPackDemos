package com.dfates.jetpackdemos.liveData

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dfates.jetpackdemos.R

class LiveDataActivity : AppCompatActivity() {

    private lateinit var mModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_data)

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)


        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            mNameTextView.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)
    }

}
