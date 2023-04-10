package com.example.bluetoothdetector.main.repository

import androidx.compose.runtime.mutableStateOf
import com.example.bluetoothdetector.main.domain.BytesStats

class NetworkRepository(
) {

    fun updateResumedBytes(bytesStats: BytesStats = BytesStats()) {
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
    }

}