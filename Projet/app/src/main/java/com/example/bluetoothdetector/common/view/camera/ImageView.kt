package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.bluetoothdetector.common.view.SpinnerView
import com.google.maps.android.compose.Circle

@Composable
fun ImageView(
    source: Uri?,
    size: Dp = 200.dp,
    padding: Dp = 12.dp,
) {
    if (source == null) {
        SpinnerView(
            size = size,
            padding = padding
        )
    } else {
        SubcomposeAsyncImage(
            model = source,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = { SpinnerView() },
            modifier = Modifier
                .padding(padding)
                .clip(CircleShape)
                .size(size),
        )
    }
}

@Composable
fun StaticImageView(
    source: Uri?,
    size: Dp = 200.dp,
    padding: Dp = 12.dp,
) {
    val color = MaterialTheme.colors.primary.copy(0.2f)
    if (source == null) {
        Spacer(modifier = Modifier
            .size(size)
            .padding(padding)
            .drawBehind {
                drawCircle(
                    color = color,
                    radius = size.toPx() / 2
                )
            }
        )
    } else {
        ImageView(source, size, padding)
    }
}

