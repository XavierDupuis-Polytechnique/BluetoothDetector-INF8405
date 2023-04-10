package com.example.bluetoothdetector.common.view.page

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bluetoothdetector.common.view.HeaderView
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import kotlinx.coroutines.CoroutineScope

// Full page component for main views with theme selection
@Composable
fun PageWithHeader(
    menuState: DrawerState,
    menuScope: CoroutineScope,
    headerContent: @Composable () -> Unit = { HeaderView(menuState, menuScope) },
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