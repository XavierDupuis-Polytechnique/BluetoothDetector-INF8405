package com.example.unblockme.game.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.viewmodel.GameViewModel

@Composable
fun Game(
    navigateTo: (Page) -> Unit,
    viewModel: GameViewModel = viewModel(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(" - GAME PAGE - ")
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.navigateToMenu(navigateTo) }
        ) {
            Text("BACK TO MENU")
        }
        Board()
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview(viewModel: GameViewModel = viewModel()) {
    Game({})
}