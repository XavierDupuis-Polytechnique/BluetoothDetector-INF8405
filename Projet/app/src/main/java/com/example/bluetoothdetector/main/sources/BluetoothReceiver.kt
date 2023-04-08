package com.example.bluetoothdetector.main.sources

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.bluetoothdetector.main.repository.BluetoothRepository

open class BluetoothReceiver(
    private val applicationContext: Context,
    private val bluetoothRepository: BluetoothRepository
) : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        // Permission check
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        when (intent.action) {
            BluetoothDevice.ACTION_FOUND -> {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                val device: BluetoothDevice? =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)

                bluetoothRepository.addDeviceToList(device)
            }
            BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                // When bluetooth scan ends, restart it
                if (bluetoothRepository.bluetoothStarted) {
                    bluetoothRepository.startDiscovery()
                }
            }
        }
    }
}