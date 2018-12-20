package com.dfates.jetpackdemos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by $USER_NAME on 2018/12/8.
 */
class ValueViewModel : ViewModel() {

    val mValue: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    // Rest of the ViewModel...
}