package com.example.bluetoothdetector.main.domain

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.bluetoothdetector.main.model.Device
import kotlinx.coroutines.flow.Flow


@Dao
interface DeviceDao {
    @Query("SELECT * FROM device")
    suspend fun getAll(): List<Device>

    @Query("SELECT * FROM device")
    fun observeAll(): Flow<List<Device>>

    @Query("SELECT * FROM device WHERE id IN (:deviceIds)")
    fun loadAllByIds(deviceIds: IntArray): List<Device>

    @Query("SELECT * FROM device WHERE mac_address LIKE :macAddress LIMIT 1")
    fun findByMacAddress(macAddress: String): Device

    @Query("SELECT COUNT() FROM device")
    fun observeDeviceCount(): Flow<Int>

    @Query("SELECT COUNT() FROM device")
    fun getDeviceCount(): Int

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg devices: Device)

    @Insert(onConflict = REPLACE)
    suspend fun insert(device: Device)

    @Update
    suspend fun update(device: Device)

    @Delete
    fun delete(device: Device)
}
