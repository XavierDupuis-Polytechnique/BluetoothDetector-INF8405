package com.example.bluetoothdetector.main.domain

import android.net.TrafficStats
import android.os.Process

// Holds the BidirectionalBytes for all four types
data class BytesStats(
    val mobile: BidirectionalBytes = BidirectionalBytes(
        Tx = TrafficStats.getMobileTxBytes(),
        Rx = TrafficStats.getMobileRxBytes()
    ),
    val total: BidirectionalBytes = BidirectionalBytes(
        Tx = TrafficStats.getTotalTxBytes(),
        Rx = TrafficStats.getTotalRxBytes()
    ),
    val network: BidirectionalBytes = total - mobile,
    val process: BidirectionalBytes = BidirectionalBytes(
        Tx = TrafficStats.getUidTxBytes(Process.myUid()),
        Rx = TrafficStats.getUidRxBytes(Process.myUid())
    )
) {
    operator fun minus(other: BytesStats): BytesStats {
        return BytesStats(
            mobile - other.mobile,
            total - other.total,
            network - other.network,
            process - other.process,
        )
    }

    val processRatio: Float
        get() {
            return process.combined.toFloat().div(total.combined)
        }
}