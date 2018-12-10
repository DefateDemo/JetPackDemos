package com.dfates.jetpackdemos.liveData

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dfates.jetpackdemos.R

class LiveDataActivity : AppCompatActivity() {

    private lateinit var mModel: NameViewModel

    private lateinit var tvText: TextView
    private lateinit var btnModify: Button

    private var value = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_data)


        tvText = findViewById(R.id.tv_text)
        btnModify = findViewById(R.id.btn_modify)

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            tvText.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)

        btnModify.setOnClickListener {
            mModel.currentName.value = value.toString()
            value++
        }
    }

}
