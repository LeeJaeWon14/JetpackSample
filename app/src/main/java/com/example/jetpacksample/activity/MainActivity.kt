package com.example.jetpacksample.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jetpacksample.R
import com.example.jetpacksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelect.setOnClickListener {
            callDialog()
        }
    }

    private fun callDialog() {
        val dlg = AlertDialog.Builder(this)
        val arr = arrayOf("LiveData", "ViewModel", "DataBinding")
        dlg.setItems(arr, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which) {
                    0 -> {
                        Toast.makeText(this@MainActivity, "not implement yet", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        startActivity(Intent(this@MainActivity, ViewModelActivity::class.java))
                    }
                    2 -> {
                        startActivity(Intent(this@MainActivity, DataBindingActivity::class.java))
                    }
                    else -> { }
                }
            }
        })

        dlg.setCancelable(false)
        dlg.show()
    }
}