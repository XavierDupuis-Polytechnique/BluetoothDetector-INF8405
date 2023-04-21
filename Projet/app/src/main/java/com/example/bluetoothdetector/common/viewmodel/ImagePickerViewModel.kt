package com.example.bluetoothdetector.common.viewmodel

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.sources.ImageFileProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(

) : ViewModel() {
    val storedImageUri = mutableStateOf<Uri?>(null)

    fun takePicture(
        context: Context,
        takePicture: ManagedActivityResultLauncher<Uri, Boolean>
    ) {
        storedImageUri.value = ImageFileProvider.getImageUri(context)
        takePicture.launch(storedImageUri.value)
    }

    fun onTakePictureResult(result: Boolean, displayedImageUri: MutableState<Uri?>) {
        if (!result) {
            return
        }
        displayedImageUri.value = storedImageUri.value
    }

    fun pickImage(pickImageLauncher: ManagedActivityResultLauncher<String, Uri?>) {
        pickImageLauncher.launch("image/*")
    }

    fun onPickImageResult(uri: Uri?, displayedImageUri: MutableState<Uri?>) {
        storedImageUri.value = uri
        displayedImageUri.value = uri
    }
}