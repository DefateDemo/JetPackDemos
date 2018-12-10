package com.dfates.jetpackdemos.lifecycle


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dfates.jetpackdemos.R


class LifecycleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        lifecycle.addObserver(TestLifecycleObserver(context))
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }

}
