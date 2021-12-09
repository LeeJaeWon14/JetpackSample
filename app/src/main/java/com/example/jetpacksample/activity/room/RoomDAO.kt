package com.example.jetpacksample.activity.room

import androidx.room.*

@Dao
interface RoomDAO {
    @Query("SELECT * FROM RoomEntity")
    fun selectRoom() : List<RoomEntity>

    @Insert
    fun insertRoom(entity: RoomEntity)

    @Update
    fun updateRoom(entity: RoomEntity)

    @Delete
    fun deleteRoom(entity: RoomEntity)
}