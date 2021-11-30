package com.example.jetpacksample.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
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


    lateinit var model : MyViewModel
    val liveData : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data)

        var count = 0
        model = ViewModelProvider(this).get(MyViewModel::class.java)

//        val testObserver = Observer<String> {
//            binding.tvLiveData.text = it
//        }

//        model.textValue.observe(this, Observer {
//            binding.tvLiveData.text = it
//        })

//        binding.apply {
//            lifecycleOwner = this@LiveDataActivity
//            act = this@LiveDataActivity
//            btnLiveData.setOnClickListener {
//                count ++
//                model.textValue.value = count.toString()
//                CoroutineScope(Dispatchers.Default).launch {
//                    count += 50
//                    model.textValue.postValue(count.toString())
//                    delay(1000)
//
//                    // LiveData must run on Main Thread.
//                    // if you LiveDate run on other Thread, will use postValue() func.
//                }
//            }
//        }

        binding.apply {
            lifecycleOwner = this@LiveDataActivity
            act = this@LiveDataActivity

            btnLiveData.setOnClickListener {
                CoroutineScope(Dispatchers.Default).launch {
                    for(idx in 0 .. 10) {
                        model.textValue.postValue(idx.toString())
                        delay(300)
                    }
                }
            }

//            model.textValue.observe(this@LiveDataActivity, Observer {
//                tvLiveData.text = it
//            })
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

    inner class TestClass {
        val testText = ""
    }
}