package com.example.bluetoothdetector.main.repository

import android.Manifest
import android.bluetooth.BluetoothAdapter
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
//    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter


    fun testBluetooth(): String {

//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.BLUETOOTH_ADMIN
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
////        requestPermissionLauncher.launch(
////            Manifest.permission.BLUETOOTH_CONNECT
////
////        )
//
//            return "No permissions"
//
//        }
//        val a = bluetoothAdapter.bondedDevices
//
//////    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
//////    pairedDevices?.forEach { device ->
//////        val deviceName = device.name
//////        val deviceHardwareAddress = device.address // MAC address
//////    }
//
        return "a.toString()"
    }

    fun startDiscovery() {
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
        if (android.os.Build.VERSION.SDK_INT > 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT > 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        bluetoothManager.adapter.startDiscovery()
        println("--- Discovery Started ---")
    }

    // TODO Maybe remove if not useful
    fun stopDiscovery() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT > 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        bluetoothManager.adapter.cancelDiscovery()
    }

//    fun getDiscoveredDevices(): MutableSet<Device>{
//        return listOfDevice
//    }

    fun addDeviceToList(device: BluetoothDevice?) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT > 31 && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (device == null || device.name == null) {
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

        if (deviceRepository.listOfDevice.contains(device.address)) {
            return
        }
        deviceRepository.listOfDevice[device.address] = parsedDevice
        println(parsedDevice)
        var a: android.bluetooth.BluetoothClass.Device
    }

    fun getDeviceList(): MutableMap<String, Device> {
        return deviceRepository.listOfDevice
    }
}