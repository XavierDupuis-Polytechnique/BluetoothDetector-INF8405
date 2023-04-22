package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EnergyRepository @Inject constructor(
    @ApplicationContext private val context: Context,
){

    private val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }

    val batteryPct: Float? = batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        level * 100 / scale.toFloat()
    }

    private var activityCreatedBatteryPct: Float = 0.0f
    private var activityResumedBatteryPct: Float = 0.0f

    fun updateResumedPct(){
        activityResumedBatteryPct = batteryPct?: 0.0f
    }
    fun updateCreatedPct(){
        activityCreatedBatteryPct = batteryPct?: 0.0f
    }

    val batteryPctSinceCreated = mutableStateOf(getBatteryPctFromCreated())
    val batteryPctSinceResumed = mutableStateOf(getBatteryPctFromResumed())

    private fun getBatteryPctFromCreated() = batteryPct?.minus(activityCreatedBatteryPct)
    private fun getBatteryPctFromResumed() = batteryPct?.minus(activityResumedBatteryPct)


}