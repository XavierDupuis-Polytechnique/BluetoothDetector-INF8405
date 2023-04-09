package com.example.bluetoothdetector.menu.view

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuTabView(
    viewModel: MenuViewModel,
    navController: NavHostController,
    menuState: DrawerState,
    menuScope: CoroutineScope,
    page: Page,
) {
    ListItem(
        modifier = Modifier.clickable { viewModel.navigate(navController, menuState, menuScope, page) },
        text = { Text(page.denomination) },
        secondaryText = { Text(page.description) },
        icon = {
            Icon(
                imageVector = page.icon,
                contentDescription = page.icon.toString()
            )
        }
    )
}