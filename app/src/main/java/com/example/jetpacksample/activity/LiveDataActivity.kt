package com.example.jetpacksample.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacksample.R
import com.example.jetpacksample.activity.vm.MyViewModel
import com.example.jetpacksample.databinding.ActivityLiveDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLiveDataBinding
    private lateinit var model : MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = 0
        model = ViewModelProvider(this).get(MyViewModel::class.java)

//        val testObserver = Observer<String> {
//            binding.tvLiveData.text = it
//        }

        model.textValue.observe(this, Observer {
            binding.tvLiveData.text = it
        })

        binding.btnLiveData.setOnClickListener {
            count ++
            model.textValue.value = count.toString()
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                count += 50
                model.textValue.value = count.toString()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_live_data, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_first -> {
                model.textValue.value = ""
            }
        }

        return true
    }


}