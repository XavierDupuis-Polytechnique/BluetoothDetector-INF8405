package com.example.unblockme.game.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.common.view.CenteredVerticalContainer
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.menu.viewmodel.GameViewModel
import com.example.unblockme.ui.theme.Moves
import com.example.unblockme.ui.theme.Record

@Composable
fun Statistics(
    viewModel: GameViewModel = viewModel()
) {
    CenteredVerticalContainer {
        Text(Moves)
        Text(viewModel.currentMoveCount.value.toString())
        Text("$Record\n${GameManager.getCurrentBestScore()} / ${GameManager.getCurrentMin()}")
    }
}