package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.viewmodel.ImagePickerViewModel

@Composable
fun ImagePicker(
    displayedImageUri: MutableState<Uri?>,
    viewModel: ImagePickerViewModel = hiltViewModel(),
    imageContent: @Composable (Uri) -> Unit = { ImageView(it) }
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
                Text(stringResource(R.string.select_image))
            }
            Button(onClick = { viewModel.takePicture(context, takePictureLauncher) }) {
                Text(stringResource(R.string.take_picture))
            }
        }
    }
}

