package com.example.bluetoothdetector

import android.Manifest
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED
import android.bluetooth.BluetoothDevice
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
import com.example.bluetoothdetector.main.repository.BluetoothRepository
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
    lateinit var bluetoothRepository: BluetoothRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(themeRepository)
        }
        bluetoothRepository.bluetoothStarted = false
        startBTScan()
    }

    override fun onResume() {
        super.onResume()
        locationRepository.resumeLocationUpdatesAsync()
        // Start bluetooth scan when app is resumed
        startBTScan()
    }

    override fun onPause() {
        super.onPause()
        locationRepository.pauseLocationUpdatesAsync()
        // Stop bluetooth scan when app is paused
        if (bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.stopDiscovery()
            bluetoothRepository.bluetoothStarted = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop bluetooth scan and unregister the intent receiver when app is destroyed
        if (bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.stopDiscovery()
            bluetoothRepository.bluetoothStarted = false
        }
        if (bluetoothRepository.bluetoothReceiver != null) {
            unregisterReceiver(bluetoothRepository.bluetoothReceiver)
        }
    }

    // Start bluetooth scan
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
        if (!bluetoothRepository.bluetoothStarted) {
            bluetoothRepository.bluetoothStarted = true
        } else {
            return
        }
        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter().apply {
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(ACTION_DISCOVERY_FINISHED)
        }
        registerReceiver(bluetoothRepository.bluetoothReceiver, filter)
        bluetoothRepository.startDiscovery()
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