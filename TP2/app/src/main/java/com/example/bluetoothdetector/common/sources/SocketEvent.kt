package com.example.bluetoothdetector.common.sources

abstract class SocketEvent(open val eventName: String)
abstract class OnEvent(override val eventName: String) : SocketEvent(eventName)
abstract class EmitEvent(override val eventName: String) : SocketEvent(eventName)