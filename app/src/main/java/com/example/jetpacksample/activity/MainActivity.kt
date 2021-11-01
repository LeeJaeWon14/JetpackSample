package com.example.jetpacksample.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpacksample.R
import com.example.jetpacksample.databinding.ActivityMainBinding
import com.example.jetpacksample.util.MyLogger
import com.example.jetpacksample.util.Pref

class MainActivity : AppCompatActivity(), Pref.OnDataChanged {
    private lateinit var binding : ActivityMainBinding
    private val dataMap = HashMap<String, String>().apply {
        set("SET", "setData")
        set("DELETE", "deleteData")
    }
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
                        startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_data_store, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_data_set -> {
                if (Pref.getInstance(this@MainActivity)?.setValue(Pref.PREF_DATA, dataMap["SET"]!!)!!) {
                    Handler(Looper.getMainLooper()).post(Runnable { onDataChanged(Pref.PREF_DATA, dataMap["SET"]!!) })
                    MyLogger.i("Preference Success")
                } else {
                    MyLogger.e("Preference Error!")
                    Toast.makeText(this@MainActivity, "Preference Error!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.menu_data_remove -> {
                if (Pref.getInstance(this@MainActivity)?.removeValue(Pref.PREF_DATA)!!) {
                    Handler(Looper.getMainLooper()).post(Runnable { onDataChanged(Pref.PREF_DATA, dataMap["DELETE"]!!) })
                    MyLogger.i("Delete Success")
                } else {
                    MyLogger.e("Delete Error!")
                    Toast.makeText(this@MainActivity, "Delete Error!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return true
    }

    override fun onDataChanged(id: String?, data: String) {
        binding.tvText.text = data
        Toast.makeText(this, "Successfully data changed done", Toast.LENGTH_SHORT).show()
    }
}