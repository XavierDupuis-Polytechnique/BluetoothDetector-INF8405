package com.example.bluetoothdetector.menu.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    val color =
        if (viewModel.isSelectedTab(page))
            MaterialTheme.colors.secondary
        else
            MaterialTheme.colors.primary
    val fontWeight =
        if (viewModel.isSelectedTab(page))
            FontWeight.Bold
        else
            null
    ListItem(
        modifier = Modifier
            .clickable { viewModel.navigate(navController, menuState, menuScope, page) }
            .background(color.copy(alpha = 0.2f)),
        text = {
            Text(
                text = stringResource(page.denomination),
                fontWeight = fontWeight
            )
        },
        secondaryText = {
            Text(
                text = stringResource(page.description),
                fontWeight = fontWeight
            )
        },
        icon = {
            Icon(
                imageVector = page.icon,
                contentDescription = page.icon.toString(),
            )
        }
    )
}