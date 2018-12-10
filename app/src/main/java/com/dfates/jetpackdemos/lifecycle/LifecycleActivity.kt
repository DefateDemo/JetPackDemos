package com.dfates.jetpackdemos.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dfates.jetpackdemos.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(TestLifecycleObserver(this))
    }
}
