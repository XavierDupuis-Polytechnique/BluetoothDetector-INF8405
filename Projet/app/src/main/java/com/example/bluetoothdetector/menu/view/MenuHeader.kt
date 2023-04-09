package com.example.bluetoothdetector.menu.view

import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MenuHeader(
    menuState: DrawerState,
    menuScope: CoroutineScope,
    viewModel: MenuViewModel = hiltViewModel(),
) {
    IconButton(onClick = { viewModel.toggleMenu(menuState, menuScope) }) {
        val isMenuOpen = viewModel.isMenuOpened(menuState)
        // TODO : UX MAYBE
        // val icon = if (isMenuOpen) Icons.Default.MenuOpen else Icons.Default.Menu
        val icon = if (isMenuOpen) Icons.Default.Close else Icons.Default.Menu
        Icon(
            imageVector = icon,
            contentDescription = icon.toString()
        )
    }
}