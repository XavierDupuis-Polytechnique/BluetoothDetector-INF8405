package com.example.bluetoothdetector.menu.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme

// Displays the menu drawer with page selection
@Composable
fun MenuDrawer(
    menuState: DrawerState,
    navigate: (Page) -> Unit,
    viewModel: MenuViewModel,
    content: @Composable () -> Unit
) {
    ModalDrawer(
        drawerState = menuState,
        gesturesEnabled = false,
        drawerContent = {
            LazyColumn {
                items(Page.MenuPages) {
                    MenuTabView(viewModel, navigate, it)
                    Divider()
                }
            }
        },
        content = content
    )
}

@SuppressLint("VisibleForTests")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MenuDrawerPreview() {
    MenuDrawer(
        rememberDrawerState(DrawerValue.Closed),
        {},
        hiltViewModel()
    ) {}
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, device = Devices.PIXEL_C)
@Composable
fun MenuDrawerDarkPreview() {
    BluetoothDetectorTheme(mutableStateOf(true)) {
        MenuDrawerPreview()
    }
}