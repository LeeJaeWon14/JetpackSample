package com.example.jetpacksample.activity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "test_value") var testValue: String
)