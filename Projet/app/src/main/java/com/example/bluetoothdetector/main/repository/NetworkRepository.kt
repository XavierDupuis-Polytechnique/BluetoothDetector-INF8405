package com.example.bluetoothdetector.main.repository

import androidx.compose.runtime.mutableStateOf
import com.example.bluetoothdetector.main.domain.BytesStats

// Manages all operations related to network
class NetworkRepository {

    // Holds the network bytes stats WHEN the application was created
    private var activityCreatedBytes: BytesStats = BytesStats()

    // Holds the network bytes stats WHEN the application was resumed
    private var activityResumedBytes: BytesStats = BytesStats()

    fun updateResumedBytes(bytesStats: BytesStats = BytesStats()) {
        activityResumedBytes = bytesStats
    }

    fun updateCreatedBytes(bytesStats: BytesStats = BytesStats()) {
        activityCreatedBytes = bytesStats
    }

    // Holds the current estimation network bytes stats SINCE the application was created
    val bytesSinceCreated = mutableStateOf(getBytesStatsFromCreated())

    // Holds the current estimation network bytes stats SINCE the application was resumed
    val bytesSinceResumed = mutableStateOf(getBytesStatsFromResumed())

    private fun getBytesStatsFromCreated() = BytesStats() - activityCreatedBytes
    private fun getBytesStatsFromResumed() = BytesStats() - activityResumedBytes

    // Refreshes current network bytes stats evaluations
    fun refresh() {
        bytesSinceCreated.value = getBytesStatsFromCreated()
        bytesSinceResumed.value = getBytesStatsFromResumed()
    }

}