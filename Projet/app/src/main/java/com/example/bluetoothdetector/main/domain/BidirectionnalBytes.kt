package com.example.bluetoothdetector.main.domain

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
}