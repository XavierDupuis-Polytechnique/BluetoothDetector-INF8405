package com.example.bluetoothdetector.common.sources

abstract class SocketHandler(private val EventTypes: Map<SocketEvent, Class<out Any>>) {

    abstract val name: String

    protected lateinit var socket: Any

    abstract fun setSocket()

    abstract fun connect()

    abstract fun disconnect()

    abstract fun on(onEvent: OnEvent, callback: (eventContent: Any?) -> Unit)

// TODO : KEEP (if useful) or DELETE
//
//    @JvmName("onObject") // To prevent platform declaration clash
//    @OptIn(DelicateCoroutinesApi::class)
//    @Synchronized
//    fun <T> on(event: OnEvent, callback: (formattedContent: T?) -> Unit) {
//        socket.on(event.eventName) { args ->
//            val contentType = EventTypes[event]!!
//            val formattedContent = formatObjectResponse(args, contentType as Class<T>)
//            try {
//                GlobalScope.launch(Dispatchers.Main) {
//                    callback(formattedContent)
//                }
//            } catch (e: Exception) {
//                println("SocketHandler -> onObject -> Error")
//                e.printStackTrace()
//            }
//        }
//    }
//
//    @JvmName("onArray") // To prevent platform declaration clash
//    @OptIn(DelicateCoroutinesApi::class)
//    @Synchronized
//    fun <T> on(event: OnEvent, callback: (formattedContent: List<T>?) -> Unit) {
//        socket.on(event.eventName) { args ->
//            val contentType = EventTypes[event]
//            val formattedContent = formatArrayResponse(args, contentType as Class<Array<T>>)
//            try {
//                GlobalScope.launch(Dispatchers.Main) {
//                    callback(formattedContent)
//                }
//            } catch (e: Exception) {
//                println("SocketHandler -> onArray -> Error")
//                e.printStackTrace()
//            }
//        }
//    }
//
//    @Synchronized
//    fun <T> emit(event: EmitEvent, content: T) {
//        val contentType = EventTypes[event] as Class<T>
//        val formattedContent: Any? =
//            if (isContentPrimitive(content)) {
//                content
//            } else {
//                formatRequest(content, contentType)
//            }
//        socket.emit(event.eventName, formattedContent)
//    }
//
//    private fun <T> isContentPrimitive(content: T): Boolean {
//        return content is String || content is Int || content is Boolean
//    }
//
//    private fun <T> formatArrayResponse(args: Array<Any>, type: Class<Array<T>>): List<T>? {
//        return try {
//            if (args.isEmpty()) {
//                return null
//            }
//            Gson().fromJson(args[0].toString(), type).toList()
//        } catch (e: Exception) {
//            println("Error -> formatArrayResponse -> $type")
//            e.printStackTrace()
//            null
//        }
//    }
//
//    private fun <T> formatObjectResponse(args: Array<Any>, type: Class<T>): T? {
//        return try {
//            if (args.isEmpty()) {
//                return null
//            }
//
//            if (type == String::class.java) {
//                return args[0].toString() as T
//            }
//            Gson().fromJson(args[0].toString(), type) as T
//        } catch (e: Exception) {
//            println("Error -> formatObjectResponse -> $type")
//            e.printStackTrace()
//            null
//        }
//    }
//
//    private fun <T> formatRequest(content: T, type: Class<T>): JSONObject? {
//        return try {
//            JSONObject(Gson().toJson(content))
//        } catch (e: Exception) {
//            println("Error -> formatRequest -> $type")
//            e.printStackTrace()
//            null
//        }
//    }
}