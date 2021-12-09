package com.example.jetpacksample.activity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomEntity::class], version = 1, exportSchema = true)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun getRoomDAO() : RoomDAO

    companion object {
        private var instance: MyRoomDatabase? = null

        fun getInstance(context : Context) : MyRoomDatabase {
            instance?.let {
                return it
            } ?: run {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "testRoom.db"
                ).build()
                return instance!!
            }
        }
    }
}