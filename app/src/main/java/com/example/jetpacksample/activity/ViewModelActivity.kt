package com.example.jetpacksample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacksample.activity.vm.MyViewModel
import com.example.jetpacksample.databinding.ActivityViewModelBinding

class ViewModelActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewModelBinding
    private lateinit var viewModel : MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel 인스턴스 생성
        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        ).get(MyViewModel::class.java)

        binding.tvViewModel.setText(viewModel.count.toString())
        binding.tvViewModel.setOnClickListener {
            binding.tvViewModel.text = (++viewModel.count).toString()
        }
        binding.btnBack.setOnClickListener { finish() }
    }
}