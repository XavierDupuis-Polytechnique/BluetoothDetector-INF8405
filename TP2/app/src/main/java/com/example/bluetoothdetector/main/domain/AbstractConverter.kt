package com.example.bluetoothdetector.main.domain

import com.google.gson.Gson

// Converts data from and to JSON format
abstract class AbstractConverter {
    protected fun <T> encode(value: T?): String? {
        return value?.let { Gson().toJson(it) }
    }

    protected fun <T> decode(value: String?, type: Class<T>): T? {
        return value?.let { Gson().fromJson(it, type) }
    }

    protected fun <T> decode(value: String?, type: Class<Array<T>>): List<T>? {
        return value?.let { Gson().fromJson(it, type).toList() }
    }
}