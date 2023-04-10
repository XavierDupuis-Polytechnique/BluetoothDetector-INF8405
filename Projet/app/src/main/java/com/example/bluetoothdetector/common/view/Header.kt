package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.languages.LanguagesHeader
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.menu.view.MenuHeader
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

// Header container
@Composable
fun HeaderView(
    menuState: DrawerState,
    menuScope: CoroutineScope,
) {
    val viewModel: MenuViewModel = hiltViewModel()
    CenteredHorizontalContainer(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        MenuHeader(menuState, menuScope, viewModel)
        Subtitle(stringResource(viewModel.selectedTab.value.denomination).uppercase())
        Spacer(modifier = Modifier.weight(1f))
        ThemeSelector()
        Spacer(modifier = Modifier.weight(1f))
        LanguagesHeader()
    }
}