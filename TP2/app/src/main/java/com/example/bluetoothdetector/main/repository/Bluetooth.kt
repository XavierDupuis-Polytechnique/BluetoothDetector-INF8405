package com.example.bluetoothdetector.main.repository

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.bluetoothdetector.main.model.Device
import java.util.*

class Bluetooth(
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

        val parsedDevice = Device(
            device.name, device.address, Date(), className,
            typeName, bondedStateName, device.uuids, locationRepository.currentLocation
        )
        deviceRepository.devices[device.address] = parsedDevice
        println(parsedDevice)
    }

    fun getDeviceList(): MutableMap<String, Device> {
        return deviceRepository.devices
    }
}