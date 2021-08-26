package com.example.jetpacksample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacksample.R
import com.example.jetpacksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelect.setOnClickListener {  }
    }
}