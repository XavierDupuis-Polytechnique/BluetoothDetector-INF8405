package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectableListItem(
    isSelected: () -> Boolean,
    select: () -> Unit,
    mainText: Int,
    secondaryText: Int? = null,
    icon: @Composable (() -> Boolean) -> Unit
) {
    val color =
        if (isSelected())
            MaterialTheme.colors.secondary
        else
            MaterialTheme.colors.primary
    val fontWeight =
        if (isSelected())
            FontWeight.Bold
        else
            null

    if (secondaryText !== null) {
        ListItem(
            modifier = Modifier
                .clickable { select() }
                .background(color.copy(alpha = 0.2f)),
            text = {
                Text(
                    text = stringResource(mainText),
                    fontWeight = fontWeight
                )
            },
            secondaryText = {
                Text(
                    text = stringResource(secondaryText),
                    fontWeight = fontWeight
                )
            },
            icon = { icon(isSelected) }
        )
    } else {
        ListItem(
            modifier = Modifier
                .clickable { select() }
                .background(color.copy(alpha = 0.2f)),
            text = {
                Text(
                    text = stringResource(mainText),
                    fontWeight = fontWeight
                )
            },
            icon = { icon(isSelected) }
        )
    }

}

@Composable
fun SelectableListItem(
    isSelected: () -> Boolean,
    select: () -> Unit,
    mainText: Int,
    secondaryText: Int? = null,
    icon: Int
) {
    SelectableListItem(
        isSelected = isSelected,
        select = select,
        mainText = mainText,
        secondaryText = secondaryText
    ) {
        Text(
            text = stringResource(icon),
            fontWeight = if (it()) FontWeight.Bold else null
        )
    }
}

@Composable
fun SelectableListItem(
    isSelected: () -> Boolean,
    select: () -> Unit,
    mainText: Int,
    secondaryText: Int,
    icon: ImageVector = Icons.Default.BrokenImage
) {
    SelectableListItem(
        isSelected = isSelected,
        select = select,
        mainText = mainText,
        secondaryText = secondaryText
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.toString(),
            modifier = Modifier.size(40.dp)
        )
    }
}