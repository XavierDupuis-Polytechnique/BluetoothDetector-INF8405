package com.example.bluetoothdetector.main.sources

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
            Toast.makeText(context, "Could not share device", Toast.LENGTH_SHORT).show()
            exception.printStackTrace()
        }
    }

    fun getItinerary(device: Device, zoom: Int = 18) {
        device.location?.let {
            // https://developers.google.com/maps/documentation/urls/android-intents
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${it.latitude},${it.longitude} (${device.name})")
            )
            try {
                startActivity(context, intent, null)
            } catch (exception: Exception) {
                Toast.makeText(context, "Google Maps is not installed or is disabled", Toast.LENGTH_SHORT).show()
                exception.printStackTrace()
            }
        }
    }
}
