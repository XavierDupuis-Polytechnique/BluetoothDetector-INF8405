package com.example.bluetoothdetector.common.sources

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.bluetoothdetector.R
import java.io.File

class ImageFileProvider : FileProvider(R.xml.paths) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            val a = directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.packageName
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}