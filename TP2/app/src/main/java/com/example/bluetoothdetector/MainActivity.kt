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
import com.example.bluetoothdetector.common.repository.LanguageRepository
import androidx.core.app.ActivityCompat
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel
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
    lateinit var languageRepository: LanguageRepository

    @Inject
    lateinit var bluetooth: Bluetooth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
        languageRepository.recreate = { recreate() }

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO
            return
        }
//         Register for broadcasts when a device is discovered.
        // TODO Gracefully restart after permissions granted if the permission check prevented it
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(ACTION_DISCOVERY_FINISHED)
        registerReceiver(btReceiver, filter)
        bluetooth.startDiscovery()

    }

    private val btReceiver = object : BroadcastReceiver() {


        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.BLUETOOTH_ADMIN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO
                return
            }
            if (android.os.Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO
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
                    // TODO Debug
                    println("--- Discovery Finished ---")
                    println(bluetooth.getDeviceList())
                    bluetooth.startDiscovery()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        languageRepository.updateCurrentLanguage()
        locationRepository.resumeLocationUpdatesAsync()
    }

    override fun onPause() {
        super.onPause()
        locationRepository.pauseLocationUpdatesAsync()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(btReceiver)
    }

}

@Composable
fun MainContent() {
    val themeSelectorViewModel = ThemeSelectorViewModel(isSystemInDarkTheme())
    val permissionsViewModel = PermissionsViewModel()
    BluetoothDetectorTheme(themeSelectorViewModel.isDarkTheme) {
        Navigation(
            themeSelectorViewModel,
            permissionsViewModel,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BluetoothDetectorTheme {}
}