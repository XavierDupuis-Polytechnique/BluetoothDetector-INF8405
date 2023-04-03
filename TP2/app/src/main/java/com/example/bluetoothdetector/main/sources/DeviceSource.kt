package com.example.bluetoothdetector.main.sources

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.example.bluetoothdetector.main.model.Device
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceSource @Inject constructor(
    private val context: Context
) {
    fun share(device: Device) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            // TODO : ADD OTHER INFORMATION
            putExtra(Intent.EXTRA_TEXT, device.name)
            type = "text/plain"
        }
        try {
            startActivity(context, intent, null)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun getItinerary(device: Device, zoom: Int = 18) {
        device.location?.let {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:${it.longitude}+${it.latitude}?z=$zoom)")
            )
            try {
                startActivity(context, intent, null)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}
