package com.example.bluetoothdetector.common.sources

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.bluetoothdetector.R
import java.io.File

class ImageFileProvider : FileProvider(R.xml.paths) {


    companion object {

        private fun getAuthority(context: Context): String = context.packageName

        fun getImageUri(context: Context): Uri {
            return getUriForFile(context, getImageFile(context))
        }

        fun getUriForFile(
            context: Context,
            file: File
        ): Uri = getUriForFile(
            context,
            getAuthority(context),
            file,
        )

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