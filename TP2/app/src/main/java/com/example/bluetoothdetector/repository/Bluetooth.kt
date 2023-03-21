package com.example.bluetoothdetector.repository

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass.Device
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class Bluetooth (private val context: Context) {
    private val bluetoothManager: BluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
    val listOfDevice: MutableSet<Device> = mutableSetOf()

    fun testBluetooth(device: BluetoothDevice?): String{

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
//        requestPermissionLauncher.launch(
//            Manifest.permission.BLUETOOTH_CONNECT
//
//        )

                return "No permissions"

            }
            val a = bluetoothAdapter.bondedDevices
//
//////    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
//////    pairedDevices?.forEach { device ->
//////        val deviceName = device.name
//////        val deviceHardwareAddress = device.address // MAC address
//////    }
//
            return a.toString()
    }
}