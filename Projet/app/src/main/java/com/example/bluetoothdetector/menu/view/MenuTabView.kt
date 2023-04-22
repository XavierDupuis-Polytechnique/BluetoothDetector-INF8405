package com.example.bluetoothdetector.menu.view

import androidx.compose.runtime.Composable
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.SelectableListItem
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel

// Page menu option view
@Composable
fun MenuTabView(
    viewModel: MenuViewModel,
    navigate: (Page) -> Unit,
    page: Page,
) {
    val isSelected = { viewModel.isSelectedTab(page) }
    val select = { navigate(page) }
    SelectableListItem(
        isSelected = isSelected,
        select = select,
        mainText = page.denomination,
        secondaryText = page.description,
        icon = page.icon
    )
}