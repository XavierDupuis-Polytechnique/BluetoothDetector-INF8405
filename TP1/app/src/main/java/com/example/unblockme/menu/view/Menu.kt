package com.example.unblockme.menu.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.menu.viewmodel.MenuViewModel
import com.example.unblockme.ui.theme.About
import com.example.unblockme.ui.theme.Exit
import com.example.unblockme.ui.theme.Play

@Composable
fun Menu(viewModel: MenuViewModel = viewModel()) {
    Column (modifier = Modifier.fillMaxWidth(1f)){
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToGame() }
        ) {
            Text(Play)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToAbout() }
        ) {
            Text(About)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.navigateToExit() }
        ) {
            Text(Exit)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview(viewModel: MenuViewModel = viewModel()) {
    Surface {
        Menu()
    }
}