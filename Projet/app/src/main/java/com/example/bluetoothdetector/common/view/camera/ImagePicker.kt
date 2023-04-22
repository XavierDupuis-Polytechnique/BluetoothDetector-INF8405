package com.example.bluetoothdetector.common.view.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.viewmodel.ImagePickerViewModel

// Image selector (from camera or memory) view
@Composable
fun ImagePicker(
    displayedImageUri: MutableState<Uri?>,
    viewModel: ImagePickerViewModel = hiltViewModel(),
    imageContent: @Composable (Uri?) -> Unit = { AsyncImageViewOrPlaceholder(it) }
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
    CenteredVerticalContainer {
        imageContent(displayedImageUri.value)
        CenteredHorizontalContainer {
            OutlinedButton(onClick = {
                viewModel.pickImage(pickImageLauncher)
            }) {
                Text(stringResource(R.string.select_image))
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedButton(onClick = {
                viewModel.takePicture(context, takePictureLauncher)
            }) {
                Text(stringResource(R.string.take_picture))
            }
        }
    }
}

