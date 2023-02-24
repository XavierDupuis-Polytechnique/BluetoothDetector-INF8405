package com.example.bluetoothdetector.main.sources

import com.example.bluetoothdetector.common.sources.SocketRepository

object BluetoothRepository: SocketRepository<Any, BluetoothSocketHandler>() {

    override lateinit var model: Any
    override val socket = BluetoothSocketHandler

    init {
        setup()
    }

    override fun setup() {
        model = TODO()
        super.setup()
    }

    // TODO : REMOVE or UPDATE
    override fun setupEvents() {
        socket.on(OnBluetoothEvent.someOnEvent, onSomeEvent as (Any?) -> Unit)
    }

    private val onSomeEvent: (someEventContent: SomeOnBluetoothEventContent?) -> Unit = {
        // TODO
    }
}