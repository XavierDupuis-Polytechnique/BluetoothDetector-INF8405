package com.example.bluetoothdetector.common.sources

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.bluetoothdetector.R
import java.io.File

// Allows storing and retrieving files in device application cache
class ImageFileProvider : FileProvider(R.xml.paths) {

    companion object {

        // Provides authority from the application package name
        private fun getAuthority(context: Context): String = context.packageName

        // Retrieve a new URI for stored temp file in device application cache
        fun getTempFileUri(context: Context): Uri {
            return getUriForFile(context, getImageFile(context))
        }

        // Retrieves URI from file stored in device application cache
        fun getUriForFile(
            context: Context,
            file: File
        ): Uri = getUriForFile(
            context,
            getAuthority(context),
            file,
        )

        // Creates a temp file in device application cache
        fun getImageFile(context: Context, prefix: String = "image"): File {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            return File.createTempFile(
                "${prefix}_",
                ".jpg",
                directory,
            )
        }
    }
}