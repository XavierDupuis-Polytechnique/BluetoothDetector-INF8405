package com.example.bluetoothdetector.main.sources

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.main.model.Device
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceSource @Inject constructor(
    private val context: Context
) {
    fun share(device: Device) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            // TODO : ADD OTHER INFORMATION
            putExtra(Intent.EXTRA_TEXT, device.name)
            type = "text/plain"
        }
        startActivity(context, shareIntent, null)
    }
}
