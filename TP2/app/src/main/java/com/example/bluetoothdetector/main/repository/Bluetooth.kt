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
//    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter  // TODO remove

    fun startDiscovery() {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }

        bluetoothManager.adapter.startDiscovery()

        // TODO Debug
        println("--- Discovery Started ---")
    }

    // TODO Maybe remove if not useful
    fun stopDiscovery() {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }

        bluetoothManager.adapter.cancelDiscovery()
    }

    fun addDeviceToList(device: BluetoothDevice?) {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }

        if (device == null || device.name == null) {
            return
        }

        // Prevent duplicate device from being added
        if (deviceRepository.listOfDevice.contains(device.address)) {
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

        deviceRepository.listOfDevice[device.address] = parsedDevice

        // TODO Debug
        println(parsedDevice)
    }

    fun getDeviceList(): MutableMap<String, Device> {
        return deviceRepository.listOfDevice
    }
}