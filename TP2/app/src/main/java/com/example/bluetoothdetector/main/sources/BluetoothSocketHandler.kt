package com.example.bluetoothdetector.main.sources

import com.example.bluetoothdetector.common.sources.OnEvent
import com.example.bluetoothdetector.common.sources.SocketHandler

object BluetoothSocketHandler: SocketHandler(BluetoothEventTypes) {

    override val name: String = BluetoothSocketHandler.javaClass.simpleName

    @Synchronized
    override fun setSocket() {
        // TODO
    }

    @Synchronized
    override fun connect() {
        // TODO
    }

    @Synchronized
    override fun disconnect() {
        // TODO
    }

    override fun on(onEvent: OnEvent, callback: (eventContent: Any?) -> Unit) {
        // TODO or REMOVE
    }
}