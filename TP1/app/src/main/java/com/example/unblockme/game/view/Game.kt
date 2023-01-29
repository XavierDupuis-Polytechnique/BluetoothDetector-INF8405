package com.example.unblockme.game.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.common.view.CenteredHorizontalContainer
import com.example.unblockme.common.view.CenteredVerticalContainer
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.game.viewmodel.GameViewModel

@Composable
fun Game(
    navigateTo: (Page) -> Unit,
    viewModel: GameViewModel = viewModel(),
) {
    CenteredVerticalContainer {
        Header(viewModel)
        Board()
        Footer(navigateTo, viewModel)
    }
}

@Composable
fun Header(
    viewModel: GameViewModel = viewModel()
) {
    CenteredHorizontalContainer {
        Card(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            LevelSelection(viewModel)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Card(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Statistics(viewModel)
        }
    }
}

@Composable
fun Footer(
    navigateTo: (Page) -> Unit,
    viewModel: GameViewModel = viewModel(),
) {
    CenteredHorizontalContainer {
        GameButton(
            Icons.Default.Pause,
            { true }
        ) {
            viewModel.navigateToMenu(navigateTo)
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.25f))
        GameButton(
            Icons.Default.Undo,
            { viewModel.canUndo() }
        ) {
            viewModel.undo()
        }
        GameButton(
            Icons.Default.Sync,
            { viewModel.canReset() }
        ) {
            viewModel.reset()
        }
        // TODO : REMOVE
        GameButton(
            Icons.Default.PlusOne,
            { GameManager.currentMoveCount.value < 3 }
        ) {
            viewModel.addState()
        }
    }
}

@Composable
fun GameButton(
    icon: ImageVector,
    isEnabled: () -> Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(2.dp),
        enabled = isEnabled()
    ) {
        Icon(imageVector = icon, contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview(viewModel: GameViewModel = viewModel()) {
    Game({})
}