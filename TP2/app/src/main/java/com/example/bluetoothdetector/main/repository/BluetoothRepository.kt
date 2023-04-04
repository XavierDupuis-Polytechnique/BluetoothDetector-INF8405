package com.example.bluetoothdetector.main.repository

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.bluetoothdetector.main.model.Device
import java.util.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class BluetoothRepository(
    private val context: Context,
    private val deviceRepository: DeviceRepository,
    private val locationRepository: LocationRepository
) {
    private val bluetoothManager: BluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    fun startDiscovery() {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        try {
            bluetoothManager.adapter.startDiscovery()
        } catch (exception: Exception) {
            // TODO : HANDLE EMULATION ERRORS
        }
    }

    fun stopDiscovery() {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        try {
            bluetoothManager.adapter.cancelDiscovery()
        } catch (exception: Exception) {
            // TODO : HANDLE EMULATION ERRORS
        }
    }

    fun addDeviceToList(device: BluetoothDevice?) {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        if (device == null || device.name == null) {
            return
        }

        // Prevent duplicate device from being added
        if (deviceRepository.devices.contains(device.address)) {
            return
        }

        val className = classMap.getOrDefault(
            device.bluetoothClass.deviceClass,
            device.bluetoothClass.deviceClass.toString()
        )
        val typeName = typeMap.getOrDefault(device.type, device.type.toString())
        val bondedStateName =
            bondStateMap.getOrDefault(device.bondState, device.bondState.toString())

        var newLocation = locationRepository.currentLocation.value?.let { randomizeGps(it) }

        val parsedDevice = Device(
            macAddress = device.address,
            name = device.name,
            bluetoothClass = className,
            type = typeName,
            bondState = bondedStateName,
            location = newLocation,
            parcelUuids = device.uuids?.toList()
        )
        deviceRepository.addDevice(parsedDevice)
    }

    private fun randomizeGps(location: Location): Location {
        return location.randomize()
    }
}

fun Location.randomize(radius: Double = 20.0): Location {
    val random = Random(System.currentTimeMillis())
    // Convert radius from meters to degrees
    val r = radius / 111319.9
    val u = random.nextDouble()
    val v = random.nextDouble()
    val w = r * sqrt(u)
    val t = 2.0 * Math.PI * v
    val y = w * sin(t)
    var x = w * cos(t)
    // Adjust the x-coordinate for the shrinking of the east-west distances
    x /= cos(Math.toRadians(latitude))
    val newLongitude = longitude + x
    val newLatitude = latitude + y
    return Location("").apply {
        longitude = newLongitude
        latitude = newLatitude
    }
}
