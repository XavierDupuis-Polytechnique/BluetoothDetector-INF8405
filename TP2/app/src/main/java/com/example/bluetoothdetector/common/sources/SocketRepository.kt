package com.example.bluetoothdetector.common.sources

abstract class SocketRepository<ModelType, SocketType : SocketHandler> {
    abstract var model: ModelType
    protected abstract val socket: SocketType
    protected open fun reset() {
        socket.disconnect()
        setup()
    }
    protected open fun setup() {
        setupSocket()
    }
    protected abstract fun setupEvents()
    private fun setupSocket() {
        socket.setSocket()
        setupEvents()
        socket.connect()
    }
}
