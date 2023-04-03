package com.example.bluetoothdetector.main.domain

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

    @Query("SELECT * FROM device WHERE macAddress IN (:macAddresses)")
    suspend fun loadAllByMacAddresses(macAddresses: IntArray): List<Device>

    @Query("SELECT * FROM device WHERE macAddress LIKE :macAddress LIMIT 1")
    suspend fun findByMacAddress(macAddress: String): Device

    @Query("SELECT COUNT() FROM device")
    fun observeDeviceCount(): Flow<Int>

    @Query("SELECT COUNT() FROM device")
    suspend fun getDeviceCount(): Int

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg devices: Device)

    @Insert(onConflict = REPLACE)
    suspend fun insert(device: Device)

    @Update
    suspend fun update(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Delete
    suspend fun delete(vararg devices: Device)

    @Query("DELETE FROM device")
    suspend fun deleteAll()

}
