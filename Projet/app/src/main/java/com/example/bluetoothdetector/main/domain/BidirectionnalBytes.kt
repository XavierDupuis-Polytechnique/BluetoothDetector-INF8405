package com.example.bluetoothdetector.main.domain

// Holds a (T)ransfered and (R)eceived bytes value
data class BidirectionalBytes(
    val Tx: Long,
    val Rx: Long,
) {
    operator fun minus(other: BidirectionalBytes): BidirectionalBytes {
        return BidirectionalBytes(
            Tx - other.Tx,
            Rx - other.Rx,
        )
    }

    val combined: Long
        get() {
            return Tx + Rx
        }
}