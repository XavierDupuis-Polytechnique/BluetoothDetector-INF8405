package com.example.bluetoothdetector.menu.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import com.example.bluetoothdetector.ui.theme.BluetoothDetectorTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun MenuDrawer(
    menuState: DrawerState,
    menuScope: CoroutineScope,
    navController: NavHostController,
    viewModel: MenuViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    ModalDrawer(
        drawerState = menuState,
        gesturesEnabled = false,
        drawerContent = {
            Page.MenuPages.forEach {
                TextButton(
                    onClick = {
                        viewModel.navigate(navController, menuState, menuScope, it)
                    }
                ) {
                    Text(text = it.denomination)
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
        rememberCoroutineScope(),
        rememberNavController(),
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