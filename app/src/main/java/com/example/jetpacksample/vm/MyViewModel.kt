package com.example.jetpacksample.activity.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var count : Int = 0
        set(value) { field = value }
        get() = field

    val textValue : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}