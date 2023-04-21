package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.hardware.*
import android.widget.Toast


open class SensorRepository(private val context: Context, private val deviceRepository: DeviceRepository) : /*TriggerEventListener(),*/
    SensorEventListener {
    private val mSensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager


//    private val mSigMotion: Sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION)

//    private val mListener: TriggerEventListener = mSigMotionTrigger : TriggerEventListener() {
//        override fun onTrigger(event: SensorEvent) {
//            // Do work.
//        }
//    }

//    fun getSensorList() {
//        println("----------------------------------------------------------------AAA----------------------------------------------------------------")
//        var a = mSensorManager.getSensorList(Sensor.TYPE_ALL)
//        for (i in a) {
//            println(i.name)
//        }
//
//
//        println("----------------------------------------------------------------BBB----------------------------------------------------------------")
//        println(mSensorManager.getSensorList(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR))
//        println(mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD))
//    }

//    fun registerSignificantMotion() {
//        mSensorManager.requestTriggerSensor(this, mSigMotion)
//    }
//
//    fun unregisterSignificantMotion() {
//        mSensorManager.cancelTriggerSensor(this, mSigMotion)
//    }

//    override fun onTrigger(event: TriggerEvent?) {
////        TODO("Not yet implemented")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        println("----------------------------------------------------------------Significant Motion Detected----------------------------------------------------------------")
//        // Register significant motion again because it is a one-shot trigger
//        registerSignificantMotion()
//    }


    private var mLastX = -1.0f
    private var mLastY = -1.0f
    private var mLastZ = -1.0f
    private var mLastTime: Long = 0

    //    private var mShakeListener: OnShakeListener? = null
    private val mContext: Context
    private var mShakeCount = 0
    private var mLastShake: Long = 0
    private var mLastForce: Long = 0

//    interface OnShakeListener {
//    }

    //
    private fun onShake() {
        // TODO remove println
        println("ShakeListener onShake invoked---->")
        println("----------------------------------------------------------------Shake Detected----------------------------------------------------------------")
        val selectedDevice = deviceRepository.highlightedDevice.value
        if (selectedDevice != null) {
            deviceRepository.share(selectedDevice)
        }
        println("TYPE_GYROSCOPE")
        println(mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE))
        println("TYPE_ROTATION_VECTOR")
        println(mSensorManager.getSensorList(Sensor.TYPE_ROTATION_VECTOR))
        println("TYPE_MOTION_DETECT")
        println(mSensorManager.getSensorList(Sensor.TYPE_MOTION_DETECT))
        println("TYPE_PROXIMITY")
        println(mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY))
        println("TYPE_GAME_ROTATION_VECTOR")
        println(mSensorManager.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR))
        println("TYPE_STATIONARY_DETECT")
        println(mSensorManager.getSensorList(Sensor.TYPE_STATIONARY_DETECT))


    }

    init {
        println("ShakeListener invoked---->")
        mContext = context
        shakeResume()
    }

//    fun setOnShakeListener(listener: OnShakeListener?) {
//        println("ShakeListener setOnShakeListener invoked---->")
//        mShakeListener = listener
//    }

    fun shakeResume() {
        println("ShakeListener shakeResume invoked---->")
        if (mSensorManager == null) {
            throw UnsupportedOperationException("Sensors not supported")
        }
        var supported = false
        try {
            supported = mSensorManager.registerListener(
                this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        } catch (e: Exception) {
            Toast.makeText(mContext, "Shaking not supported", Toast.LENGTH_LONG)
                .show()
        }
//        if (!supported) {
//            mSensorManager.unregisterListener(this)
//            println("ShakeListener shaking not supported---->")
//        }
    }

    fun shakePause() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER) return
        val now = System.currentTimeMillis()
        if (now - mLastForce > SHAKE_TIMEOUT) {
            mShakeCount = 0
        }
        if (now - mLastTime > TIME_THRESHOLD) {
            val diff = now - mLastTime
            val speed = Math.abs(
                (event.values[SensorManager.DATA_X]
                        + event.values[SensorManager.DATA_Y]
                        + event.values[SensorManager.DATA_Z]) - mLastX - mLastY
                        - mLastZ
            ) / diff * 10000
            if (speed > FORCE_THRESHOLD) {
                if (((++mShakeCount >= SHAKE_COUNT)
                            && (now - mLastShake > SHAKE_DURATION))
                ) {
                    mLastShake = now
                    mShakeCount = 0
                    println("ShakeListener mShakeListener---->")
//                    if (mShakeListener != null) {
                    onShake()
//                    }
                }
                mLastForce = now
            }
            mLastTime = now
            mLastX = event.values[SensorManager.DATA_X]
            mLastY = event.values[SensorManager.DATA_Y]
            mLastZ = event.values[SensorManager.DATA_Z]
        }
    }

    companion object {
        private const val FORCE_THRESHOLD = 800
        private const val TIME_THRESHOLD = 100
        private const val SHAKE_TIMEOUT = 500
        private const val SHAKE_DURATION = 1000
        private const val SHAKE_COUNT = 5
    }

}