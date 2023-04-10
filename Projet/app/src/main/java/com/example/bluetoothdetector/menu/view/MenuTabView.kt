package com.example.bluetoothdetector.menu.view

import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.SelectableListItem
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MenuTabView(
    viewModel: MenuViewModel,
    navController: NavHostController,
    menuState: DrawerState,
    menuScope: CoroutineScope,
    page: Page,
) {
    val isSelected = { viewModel.isSelectedTab(page) }
    val select = { viewModel.navigate(navController, menuState, menuScope, page) }
    SelectableListItem(
        isSelected = isSelected,
        select = select,
        mainText = page.denomination,
        secondaryText = page.description,
        icon = page.icon
    )
}