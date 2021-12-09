package com.example.jetpacksample.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpacksample.R
import com.example.jetpacksample.activity.room.RoomActivity
import com.example.jetpacksample.databinding.ActivityMainBinding
import com.example.jetpacksample.util.Pref

class MainActivity : AppCompatActivity(), Pref.OnDataChanged {
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
        val arr = arrayOf("LiveData", "ViewModel", "DataBinding", "CameraX", "Room")
        dlg.setItems(arr) { _, which ->
            when (which) {
                0 -> startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, ViewModelActivity::class.java))
                2 -> startActivity(Intent(this@MainActivity, DataBindingActivity::class.java))
                3 -> startActivity(Intent(this@MainActivity, CameraXActivity::class.java))
                4 -> startActivity(Intent(this@MainActivity, RoomActivity::class.java))
            }
        }

//        val prefCommand = arrayOf("GET", "SET", "DELETE")
//        dlg.setItems(prefCommand) {dialog, which ->
//            when(which) {
//                0 -> {
//                    val pref = Pref.getInstance(this)?.getString(Pref.PREF_DATA)
//                    binding.tvText.text = pref
//                }
//            }
//        }

        dlg.setCancelable(false)
        dlg.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_data_store, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_data_set -> Pref.getInstance(this@MainActivity)?.setValueWithCallback(Pref.PREF_DATA, "SET", this)!!
            R.id.menu_data_remove -> Pref.getInstance(this@MainActivity)?.removeValueWithCallback(Pref.PREF_DATA, this)!!
        }

        return true
    }

    override fun onDataChanged(id: String?, data: String) {
        binding.tvText.text = data
        Toast.makeText(this, "Successfully data changed done", Toast.LENGTH_SHORT).show()
    }
}