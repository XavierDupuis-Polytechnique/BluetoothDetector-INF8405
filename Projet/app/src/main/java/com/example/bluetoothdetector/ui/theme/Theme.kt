package com.example.bluetoothdetector.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val Colors.accepted: Color
    @Composable
    get() = if (isLight) LightValidColor else DarkValidColor

val Colors.defaultDevice: Color
    @Composable
    get() = if (isLight) LightColorPalette.primary else DarkColorPalette.primary

val Colors.favoriteDevice: Color
    @Composable
    get() = if (isLight) LightColorPalette.secondary else DarkColorPalette.secondary

val Colors.highlightedDevice: Color
    @Composable
    get() = if (isLight) LightColorPalette.error else DarkColorPalette.error


@Composable
fun BluetoothDetectorTheme(
    isDarkTheme: MutableState<Boolean> = mutableStateOf(isSystemInDarkTheme()),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme.value) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}