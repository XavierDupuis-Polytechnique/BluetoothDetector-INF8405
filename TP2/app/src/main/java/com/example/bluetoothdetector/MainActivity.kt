package com.example.bluetoothdetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluetoothdetector.common.view.Navigation
import com.example.bluetoothdetector.common.viewmodel.ThemeSelectorViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
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