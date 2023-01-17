package com.example.unblockme.game.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.game.viewmodel.BoardViewModel

const val BoardDimension = 6
val BoardSize = 200.dp
val BoardPadding = 10.dp
val GridSize = BoardSize - BoardPadding.times(2)
val GridDivisionSize = GridSize / BoardDimension

@Composable
fun Board(
    viewModel: BoardViewModel = viewModel()
) {
    fun DrawScope.drawSomething() {
        // TODO
    }
    
    Canvas(
        modifier = androidx.compose.ui.Modifier
            .size(BoardSize)
            .padding(BoardPadding)
            .onGloballyPositioned {
                // TODO
                // currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        // TODO
                    },
                    onDrag = { change, dragAmount ->
                        // TODO
                        // change.consumeAllChanges()
                    },
                    onDragEnd = {
                        // TODO
                    },
                    onDragCancel = {
                        // TODO
                    },
                )
            }
    ) {
        var gridDivisionSize = GridDivisionSize.toPx()
        var boardPadding = BoardPadding.toPx()
        drawSomething()
    }
}

