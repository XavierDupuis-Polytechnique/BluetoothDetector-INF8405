package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.viewmodel.ImagePickerViewModel

@Composable
fun ImagePicker(
    viewModel: ImagePickerViewModel = hiltViewModel(),
    imageContent: @Composable (Uri) -> Unit = { ImagePreview(it) }
) {
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { viewModel.onPickImageResult(it) }
    )

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { viewModel.onTakePictureResult(it) }
    )

    val context = LocalContext.current
    CenteredHorizontalContainer {
        CardContainer {
            CenteredVerticalContainer {
                Text(viewModel.storedImageUri.value.toString())
                Text(viewModel.displayedImageUri.value.toString())
                viewModel.displayedImageUri.value?.let {
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
    )
}

