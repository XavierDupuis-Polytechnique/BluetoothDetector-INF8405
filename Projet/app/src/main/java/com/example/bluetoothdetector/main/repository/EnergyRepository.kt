package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateOf
import com.example.bluetoothdetector.main.domain.BytesStats
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EnergyRepository @Inject constructor(
    @ApplicationContext private val context: Context
){

    private val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }

    val batteryPct: Float? = batteryStatus?.let { intent ->
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        level * 100 / scale.toFloat()
    }

    var activityCreatedBatteryPct: Float = 0.0f
    var activityResumedBatteryPct: Float = 0.0f


    /*fun updateResumedBytes(bytesStats: BytesStats = BytesStats()) {
        activityResumedBytes = bytesStats
    }

    fun updateCreatedBytes(bytesStats: BytesStats = BytesStats()) {
        activityCreatedBytes = bytesStats
    }

    private var activityCreatedBytes: BytesStats = BytesStats()
    private var activityResumedBytes: BytesStats = BytesStats()

    val bytesSinceCreated = mutableStateOf(getBytesStatsFromCreated())
    val bytesSinceResumed = mutableStateOf(getBytesStatsFromResumed())

    private fun getBytesStatsFromCreated() = BytesStats() - activityCreatedBytes
    private fun getBytesStatsFromResumed() = BytesStats() - activityResumedBytes

    fun refresh() {
        bytesSinceCreated.value = getBytesStatsFromCreated()
        bytesSinceResumed.value = getBytesStatsFromResumed()
    }*/

}