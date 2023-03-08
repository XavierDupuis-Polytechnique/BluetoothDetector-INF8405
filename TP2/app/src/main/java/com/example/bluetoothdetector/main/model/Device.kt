package com.example.bluetoothdetector.main.model

import android.Manifest
import android.app.PendingIntent.getActivity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext

import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.MainActivity
import java.security.AccessController.getContext
import java.util.*

// TODO : REMOVE
val addresses = listOf(
    "24:1D:57:84:04:35",
//    MacAddress.fromString("12:34:56:78:90:ab"),
)

data class Device(
    val name: String = generateDeviceName(),
    val macAddress: String = addresses[0],
    val date: Date = Date(),
    // TODO : add others
    //    val location: Location
    //    val classType: ClassType
    //    val otherInfo: OtherInfo
) {

    companion object {
        private const val devicePrefix = "Device"
        private var currentDeviceId = 0
        fun generateDeviceName(): String {
            return "${devicePrefix}${currentDeviceId++}"
        }
    }
}

fun testBluetooth(context: Context): String{
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    val bluetoothAdapter = bluetoothManager.adapter

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

////    val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
////    pairedDevices?.forEach { device ->
////        val deviceName = device.name
////        val deviceHardwareAddress = device.address // MAC address
////    }

    return a.toString()
}

