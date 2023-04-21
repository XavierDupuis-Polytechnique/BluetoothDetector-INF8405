package com.example.bluetoothdetector.common.domain.formatter

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(
    @SuppressLint("SimpleDateFormat")
    dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
): String = dateFormat.format(this)