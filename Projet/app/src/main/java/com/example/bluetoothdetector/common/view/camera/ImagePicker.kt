package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.viewmodel.ImagePickerViewModel

@Composable
fun ImagePicker(
    displayedImageUri: MutableState<Uri?>,
    viewModel: ImagePickerViewModel = hiltViewModel(),
    imageContent: @Composable (Uri) -> Unit = { ImagePreview(it) }
) {
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { viewModel.onPickImageResult(it, displayedImageUri) }
    )

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { viewModel.onTakePictureResult(it, displayedImageUri) }
    )

    val context = LocalContext.current
    CenteredHorizontalContainer {
        CardContainer {
            CenteredVerticalContainer {
                Text(viewModel.storedImageUri.value.toString())
                Text(displayedImageUri.value.toString())
                displayedImageUri.value?.let {
                    imageContent(it)
                }
            }
        }
        CenteredVerticalContainer {
            Button(onClick = { viewModel.pickImage(pickImageLauncher) }) {
                Text("Select Image")
            }
            Button(onClick = { viewModel.takePicture(context, takePictureLauncher) }) {
                Text("Take photo")
            }
        }
    }
}

@Composable
fun ImagePreview(uri: Uri) {
    AsyncImage(
        model = uri,
        contentDescription = uri.toString(),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 64.dp)
            .clip(CircleShape)
            .width(128.dp)
            .height(128.dp)
    )
}

