package com.example.unblockme.game.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.common.view.CenteredHorizontalContainer
import com.example.unblockme.common.view.CenteredVerticalContainer
import com.example.unblockme.menu.viewmodel.GameViewModel
import com.example.unblockme.ui.theme.Puzzle

// Left header informations (level selection)
@Composable
fun LevelSelection(
    viewModel: GameViewModel = viewModel()
) {
    CenteredVerticalContainer {
        Text(Puzzle)
        CenteredHorizontalContainer {
            GameButton(
                icon = Icons.Default.ArrowBack,
                isEnabled = { viewModel.canSelectPreviousLevel() }
            ) {
                viewModel.selectPreviousLevel()
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(viewModel.currentLevel.value.toString())
            Spacer(modifier = Modifier.padding(10.dp))
            GameButton(
                icon = Icons.Default.ArrowForward,
                isEnabled = { viewModel.canSelectNextLevel() }
            ) {
                viewModel.selectNextLevel()
            }
        }
    }
}