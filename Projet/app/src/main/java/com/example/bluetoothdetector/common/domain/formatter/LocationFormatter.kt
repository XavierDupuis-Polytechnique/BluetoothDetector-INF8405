package com.example.bluetoothdetector.common.domain.formatter

fun Double.formatLocation(digits: Int = 6) = "%.${digits}f".format(this)
