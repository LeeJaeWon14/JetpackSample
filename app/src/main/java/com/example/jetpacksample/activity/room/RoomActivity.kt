package com.example.jetpacksample.activity.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpacksample.databinding.ActivityRoomBinding
import com.google.android.material.snackbar.Snackbar

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // UI init.
        binding.apply {
            btnRoom.setOnClickListener {
                Thread {
                    Snackbar.make(it, "저장되었습니다.", Snackbar.LENGTH_SHORT).show()

                    val dao = MyRoomDatabase.getInstance(this@RoomActivity).getRoomDAO()
                    dao.insertRoom(
                        RoomEntity(0, edtRoom.text.toString())
                    )

                    val entityList = dao.selectRoom()
                    runOnUiThread {
                        tvRoom.text = ""
                        entityList.forEach {
                            tvRoom.text = tvRoom.text.toString().plus("\r\n ${it.testValue}")
                        }
                    }
                }.start()
            }
        }

        //                CoroutineScope(Dispatchers.IO).launch {
//                    val dao = MyRoomDatabase.getInstance(this@RoomActivity).getRoomDAO()
//                    dao.insertRoom(
//                        RoomEntity(0, edtRoom.text.toString())
//                    )
//
//                    val entityList = dao.selectRoom()
//                    withContext(Dispatchers.Main) {
//                        tvRoom.text = ""
//                        entityList.forEach {
//                            tvRoom.text = tvRoom.text.toString().plus("\r\n ${it.testValue}")
//                        }
//                    }
//                }
    }
}