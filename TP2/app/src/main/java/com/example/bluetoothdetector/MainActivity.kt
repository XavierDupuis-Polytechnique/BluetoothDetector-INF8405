package com.example.bluetoothdetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.PermissionsViewModel
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
        languageRepository.recreate = { recreate() }
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