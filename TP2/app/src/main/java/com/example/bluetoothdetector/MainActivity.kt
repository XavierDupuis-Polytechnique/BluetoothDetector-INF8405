package com.example.bluetoothdetector

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // TODO: Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // TODO: Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }



        setContent {
            MainContent()
            requestPermissionLauncher.launch(
                Manifest.permission.BLUETOOTH_CONNECT
            )
        }
    }
}



@Composable
fun MainContent() {
    val themeSelectorViewModel = ThemeSelectorViewModel(isSystemInDarkTheme())
    BluetoothDetectorTheme(themeSelectorViewModel.isDarkTheme) {
        Navigation(themeSelectorViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BluetoothDetectorTheme {}
}