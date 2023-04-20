package com.example.bluetoothdetector.menu.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MenuHeader(
    menuState: DrawerState,
    menuScope: CoroutineScope,
    viewModel: MenuViewModel = hiltViewModel(),
) {
    CenteredHorizontalContainer {
        MenuHamburger(viewModel, menuState, menuScope)
        Subtitle(stringResource(viewModel.currentPage.value.denomination).uppercase())
    }
}

@Composable
private fun MenuHamburger(
    viewModel: MenuViewModel,
    menuState: DrawerState,
    menuScope: CoroutineScope
) {
    if (viewModel.currentPage.value.inMenu) {
        IconButton(
            onClick = { viewModel.toggleMenu(menuState, menuScope) }
        ) {
            val isMenuOpen = viewModel.isMenuOpened(menuState)
            val icon = if (isMenuOpen) Icons.Default.Close else Icons.Default.Menu
            Icon(
                imageVector = icon,
                contentDescription = icon.toString()
            )
        }
    } else {
        Spacer(modifier = Modifier.size(48.dp))
    }
}