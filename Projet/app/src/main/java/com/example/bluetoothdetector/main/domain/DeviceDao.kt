package com.example.bluetoothdetector.main.domain

import androidx.room.*
import com.example.bluetoothdetector.main.model.Device
import kotlinx.coroutines.flow.Flow


// Offers device database access through defined operations
@Dao
interface DeviceDao : CollectionDao<Device> {
    @Query("SELECT * FROM devices")
    suspend fun getAll(): List<Device>

    @Query("SELECT * FROM devices")
    fun observeAll(): Flow<List<Device>>

    @Query("SELECT * FROM devices WHERE id IN (:ids)")
    suspend fun getInstancesByIds(ids: List<String>): List<Device>

    @Query("SELECT * FROM devices WHERE id LIKE :id LIMIT 1")
    suspend fun getInstanceById(id: String): Device

    @Query("SELECT COUNT() FROM devices")
    fun observeInstanceCount(): Flow<Int>

    @Query("SELECT COUNT() FROM devices")
    suspend fun getInstanceCount(): Int

    @Query("DELETE FROM devices")
    suspend fun deleteAll()
}