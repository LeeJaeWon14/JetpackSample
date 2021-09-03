package com.example.jetpacksample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.example.jetpacksample.R
import com.example.jetpacksample.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.viewModel = ViewModel()
    }

    inner class ViewModel {
        var text = ObservableField<String>()

        fun click() {
            finish()
        }
    }
}