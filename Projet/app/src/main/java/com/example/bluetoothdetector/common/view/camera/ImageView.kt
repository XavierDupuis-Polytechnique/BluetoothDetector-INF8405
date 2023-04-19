package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.bluetoothdetector.common.view.SpinnerView

@Composable
fun ImageView(
    uri: Uri?,
    size: Dp = 128.dp,
    padding: Dp = 12.dp
) {
    if (uri == null) {
        SpinnerView(
            size = size,
            padding = padding
        )
    } else {
        SubcomposeAsyncImage(
            model = uri,
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