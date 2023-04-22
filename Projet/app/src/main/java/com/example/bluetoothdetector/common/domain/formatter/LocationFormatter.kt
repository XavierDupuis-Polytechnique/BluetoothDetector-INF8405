package com.example.bluetoothdetector.common.domain.formatter

// Formats location values to n = 6 decimal points
fun Double.formatLocation(digits: Int = 6) = "%.${digits}f".format(this)
