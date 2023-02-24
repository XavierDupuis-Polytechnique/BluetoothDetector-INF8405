package com.example.bluetoothdetector.main.sources

import com.example.bluetoothdetector.common.sources.EmitEvent
import com.example.bluetoothdetector.common.sources.OnEvent

class OnBluetoothEvent(override val eventName: String) : OnEvent(eventName) {
    companion object {
        val someOnEvent = OnBluetoothEvent("someOnEvent")
    }
}

class EmitBluetoothEvent(override val eventName: String) : EmitEvent(eventName) {
    companion object {
        val someEmitEvent = EmitBluetoothEvent("someEmitEvent")
    }
}

val BluetoothEventTypes = mapOf(
    Pair(
        OnBluetoothEvent.someOnEvent,
        SomeOnBluetoothEventContent::class.java
    ),
    Pair(
        EmitBluetoothEvent.someEmitEvent,
        SomeEmitBluetoothEventContent::class.java
    ),
)