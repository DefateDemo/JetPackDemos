package com.dfates.jetpackdemos.databinding

import androidx.databinding.ObservableField

/**
 * Created by $USER_NAME on 2018/12/7.
 */

class ObservableString(value: String?) : ObservableField<String>(value) {
    constructor() : this(null)
}