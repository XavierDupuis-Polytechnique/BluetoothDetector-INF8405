package com.example.bluetoothdetector.common.view.page

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bluetoothdetector.common.view.HeaderView
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope

// Generic Page view with header
@Composable
fun PageWithHeader(
    menuState: DrawerState,
    menuScope: CoroutineScope,
    menuViewModel: MenuViewModel,
    headerContent: @Composable () -> Unit = { HeaderView(menuState, menuScope, menuViewModel) },
    content: @Composable () -> Unit
) {
    Page {
        CenteredVerticalContainer {
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                headerContent()
            }
            content()
        }
    }
}