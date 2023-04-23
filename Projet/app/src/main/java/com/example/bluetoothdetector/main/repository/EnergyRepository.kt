package com.example.bluetoothdetector.main.repository

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EnergyRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    // Receives the system notification, when battery changes value
    private val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
        context.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                extractBatteryLevel(intent)?.let { newBatteryLevel ->
                    currentBatteryLevel.value = newBatteryLevel
                }
            }
        }, it)
    }

    //calculates the battery current level
    private fun extractBatteryLevel(intent: Intent? = batteryStatus): Float? {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        if (scale == null || level == null) {
            return null
        }
        return level * 100 / scale.toFloat()
    }

    val currentBatteryLevel = mutableStateOf(extractBatteryLevel() ?: 100.0f)
    private var activityCreatedBatteryLevel: Float = currentBatteryLevel.value
    private var activityResumedBatteryLevel: Float = currentBatteryLevel.value

    //updates activityResumedBatteryLevel to know the percentage when activity is resumed
    fun updateResumedLevel() {
        activityResumedBatteryLevel = currentBatteryLevel.value
    }

    //updates activityCreatedBatteryLevel to know the percentage when activity is created
    fun updateCreatedLevel() {
        activityCreatedBatteryLevel = currentBatteryLevel.value
    }

    //calculates the battery percentage used since app created
    fun getBatteryLevelSinceCreated(): Float {
        return activityCreatedBatteryLevel - currentBatteryLevel.value
    }

    //calculates the battery percentage used since app resumed
    fun getBatteryLevelSinceResumed(): Float {
        return activityResumedBatteryLevel - currentBatteryLevel.value
    }
}