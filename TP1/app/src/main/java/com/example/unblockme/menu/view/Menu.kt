package com.example.unblockme.menu.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.menu.viewmodel.MenuViewModel
import com.example.unblockme.ui.theme.About
import com.example.unblockme.ui.theme.Exit
import com.example.unblockme.ui.theme.Play

@Composable
fun Menu(
    navigateTo: (Page) -> Unit,
    viewModel: MenuViewModel = viewModel(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.navigateToGame(navigateTo) }
        ) {
            Text(Play)
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.navigateToAbout(navigateTo) }
        ) {
            Text(About)
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.navigateToExit() }
        ) {
            Text(Exit)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview(viewModel: MenuViewModel = viewModel()) {
    Menu({})
}