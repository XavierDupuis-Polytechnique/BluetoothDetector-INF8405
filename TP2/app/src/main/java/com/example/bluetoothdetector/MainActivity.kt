package com.example.bluetoothdetector

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.main.repository.Bluetooth
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var themeRepository: ThemeRepository

    @Inject
    lateinit var bluetooth: Bluetooth

    private var bluetoothStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(themeRepository)
        }
        bluetoothStarted = false
        startBTScan()


    }

    private val btReceiver = object : BroadcastReceiver() {


        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
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

                    bluetooth.addDeviceToList(device)
                }
                ACTION_DISCOVERY_FINISHED -> {
                    // When bluetooth scan ends restart it
                    if (bluetoothStarted) {
                        bluetooth.startDiscovery()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        locationRepository.resumeLocationUpdatesAsync()
        startBTScan()
    }

    override fun onPause() {
        super.onPause()
        locationRepository.pauseLocationUpdatesAsync()
        if (bluetoothStarted) {
            bluetooth.stopDiscovery()
            bluetoothStarted = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bluetoothStarted) {
            bluetooth.stopDiscovery()
            bluetoothStarted = false
        }
        unregisterReceiver(btReceiver)
    }


    private fun startBTScan() {
        // Validate permissions
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_CONNECT
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

        // Only starts  Bluetooth scan once
        if (!bluetoothStarted) {
            bluetoothStarted = true
        } else {
            return
        }
//         Register for broadcasts when a device is discovered.
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(ACTION_DISCOVERY_FINISHED)
        registerReceiver(btReceiver, filter)
        bluetooth.startDiscovery()
    }

}

@Composable
fun MainContent(themeRepository: ThemeRepository) {
    themeRepository.init(isSystemInDarkTheme())
    val permissionsViewModel = PermissionsViewModel()
    BluetoothDetectorTheme(themeRepository.isDarkTheme) {
        Navigation(
            permissionsViewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BluetoothDetectorTheme {}
}