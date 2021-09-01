package com.example.jetpacksample.activity.vm

import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var count : Int = 0
        set(value) { field = value }
        get() = field


}