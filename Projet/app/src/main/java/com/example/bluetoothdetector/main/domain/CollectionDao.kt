package com.example.bluetoothdetector.main.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface CollectionDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg instances: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(instance: T)

    @Update
    suspend fun update(instance: T)

    @Delete
    suspend fun delete(instance: T)

    @Delete
    suspend fun delete(vararg instances: T)
}