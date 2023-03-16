package com.example.bluetoothdetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel
import com.example.bluetoothdetector.main.sources.LocationSource
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var locationSource: LocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocationSource()
        setContent {
            MainContent(locationSource)
        }
    }

    override fun onResume() {
        super.onResume()
        locationSource.resumeLocationUpdatesAsync()
    }

    override fun onPause() {
        super.onPause()
        locationSource.pauseLocationUpdatesAsync()
    }

    private fun initLocationSource() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationSource = LocationSource(fusedLocationProviderClient)
    }


}

@Composable
fun MainContent(locationSource: LocationSource) {
    val themeSelectorViewModel = ThemeSelectorViewModel(isSystemInDarkTheme())
    val permissionsViewModel = PermissionsViewModel()
    BluetoothDetectorTheme(themeSelectorViewModel.isDarkTheme) {
        Navigation(
            locationSource,
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