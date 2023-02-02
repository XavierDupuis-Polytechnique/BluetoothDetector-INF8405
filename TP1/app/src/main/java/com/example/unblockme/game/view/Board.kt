package com.example.unblockme.game.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.game.models.Coordinates
import com.example.unblockme.game.viewmodel.BoardViewModel

const val BoardDimension = 6
val BoardSize = 350.dp
val BoardPadding = 10.dp
val BlockPadding = 4.dp
val GridSize = BoardSize - BoardPadding.times(2)
val GridDivisionSize = GridSize / BoardDimension
const val RoundCorners = 20f
val ExitLocation = Coordinates(5, 2)

@Composable
fun Board(
    viewModel: BoardViewModel = viewModel()
) {
    val surfaceColor = Color.Blue
    val onSurfaceColor = Color.Green
    val exitColor = Color.Green
    val infiniteTransition = rememberInfiniteTransition()
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    fun DrawScope.drawBackground() {
        drawRoundRect(
            color = surfaceColor,
            topLeft = Offset(0f, 0f),
            size = Size(GridSize.toPx(), GridSize.toPx()),
            alpha = 0.9f,
            cornerRadius = CornerRadius(RoundCorners)
        )
    }

    fun DrawScope.drawColumnDivider(
        currentDivisionOffset: Float,
        strokeWidth: Float
    ) {
        drawLine(
            color = onSurfaceColor,
            start = Offset(currentDivisionOffset, 0f),
            end = Offset(currentDivisionOffset, GridSize.toPx()),
            strokeWidth = strokeWidth
        )
    }

    fun DrawScope.drawRowDivider(
        currentDivisionOffset: Float,
        strokeWidth: Float
    ) {
        drawLine(
            color = onSurfaceColor,
            start = Offset(0f, currentDivisionOffset),
            end = Offset(GridSize.toPx(), currentDivisionOffset),
            strokeWidth = strokeWidth
        )
    }

    fun DrawScope.drawDividers() {
        for (gridDivisionIndex in 0..BoardDimension) {
            val currentDivisionOffset = gridDivisionIndex * GridDivisionSize.toPx()
            drawColumnDivider(currentDivisionOffset, Stroke.DefaultMiter)
            drawRowDivider(currentDivisionOffset, Stroke.DefaultMiter)
        }
    }

    fun minCoordinatesComparator(
        coordinates: Coordinates,
        currentMin: Coordinates
    ): Coordinates {
        return if (coordinates.x < currentMin.x || coordinates.y < currentMin.y) coordinates else currentMin
    }

    fun maxCoordinatesComparator(
        coordinates: Coordinates,
        currentMax: Coordinates
    ): Coordinates {
        return if (coordinates.x > currentMax.x || coordinates.y > currentMax.y) coordinates else currentMax
    }

    fun DrawScope.drawBlocks() {
        viewModel.currentState.value.blocks.forEach { block ->
            val max = block.coordinates.fold(Coordinates(Int.MIN_VALUE, Int.MIN_VALUE)) {
                    currentMax, coordinates -> maxCoordinatesComparator(coordinates, currentMax)
            }
            val min = block.coordinates.fold(Coordinates(Int.MAX_VALUE, Int.MAX_VALUE)) {
                    currentMin, coordinates -> minCoordinatesComparator(coordinates, currentMin)
            }
            val startX = min.x * GridDivisionSize.toPx() + BlockPadding.toPx()
            val startY = min.y * GridDivisionSize.toPx() + BlockPadding.toPx()
            val width = (max.x - min.x + 1) * GridDivisionSize.toPx() - 2 * BlockPadding.toPx()
            val height = (max.y - min.y + 1) * GridDivisionSize.toPx() - 2 * BlockPadding.toPx()

            drawRoundRect(
                color = block.color,
                topLeft = Offset(startX, startY),
                size = Size(width, height),
                alpha = 0.9f,
                cornerRadius = CornerRadius(RoundCorners)
            )
        }
    }

    fun DrawScope.drawExit() {
        val exit = Offset(
            ((ExitLocation.x + 0.5f) * GridDivisionSize.toPx()),
            ((ExitLocation.y + 0.5f) * GridDivisionSize.toPx())
        )
        val trianglePath = Path().apply {
            // Rightmost point
            moveTo(
                exit.x + GridDivisionSize.toPx() * 0.20f,
                exit.y + GridDivisionSize.toPx() * 0f
            )
            // Bottom Left point
            lineTo(
                exit.x - GridDivisionSize.toPx() * 0.15f,
                exit.y - GridDivisionSize.toPx() * 0.25f
            )
            // Top Left point
            lineTo(
                exit.x - GridDivisionSize.toPx() * 0.15f,
                exit.y + GridDivisionSize.toPx() * 0.25f
            )
        }
        drawPath(
            color = exitColor,
            path = trianglePath,
            alpha = animatedAlpha
        )
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
        drawBackground()
        // TODO : ONLY ENABLE DIVIDERS FOR DEBUG
        // drawDividers()
        drawBlocks()
        drawExit()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun BoardPreview() {
    Board()
}