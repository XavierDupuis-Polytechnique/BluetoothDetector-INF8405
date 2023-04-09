package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import kotlinx.coroutines.CoroutineScope

// Full page component for main views
@Composable
fun Page(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

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
                modifier = Modifier.fillMaxWidth()
            ) {
                headerContent()
            }
            content()
        }
    }
}