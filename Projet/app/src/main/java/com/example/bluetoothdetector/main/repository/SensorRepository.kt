package com.example.bluetoothdetector.main.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import com.example.bluetoothdetector.common.repository.ThemeRepository
import kotlin.math.abs


open class SensorRepository(
    private val context: Context,
    private val deviceRepository: DeviceRepository,
    private val themeRepository: ThemeRepository
) : SensorEventListener {
    private val mSensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // Param for shake detection
    private var mLastX = -1.0f
    private var mLastY = -1.0f
    private var mLastZ = -1.0f
    private var mLastTime: Long = 0
    private var mShakeCount = 0
    private var mLastShake: Long = 0
    private var mLastForce: Long = 0

    // Param for tilt detection
    private val tiltSensorCode = 22
    private var isFaceDown: Boolean = false

    // Initialize sensors
    init {
        sensorResume()
    }

    // Resume the sensor listener
    fun sensorResume() {
        println("ShakeListener shakeResume invoked---->")
        if (mSensorManager == null) {
            throw UnsupportedOperationException("Sensors not supported")
        }

        try {
            mSensorManager.registerListener(
                this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME

            )
        } catch (e: Exception) {
            Toast.makeText(context, "Shaking not supported", Toast.LENGTH_LONG)
                .show()
        }

        try {
            mSensorManager.registerListener(
                this,
                mSensorManager.getDefaultSensor(tiltSensorCode),
                SensorManager.SENSOR_DELAY_NORMAL

            )
        } catch (e: Exception) {
            Toast.makeText(context, "Tilt not supported", Toast.LENGTH_LONG)
                .show()
        }
    }

    // Stop the sensor listener
    fun sensorPause() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this)
        }
    }

    // Do nothing on accuracy changed
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    // Sensor events are received here
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            shakeSensorEvent(event)
        }

        if (event.sensor.type == tiltSensorCode) {
            tiltSensorEvent(event)
        }
        return

    }

    // Check accelerometer for shake event
    private fun shakeSensorEvent(event: SensorEvent) {
        // Check if the device is upside down
        isFaceDown = event.values[SensorManager.DATA_Z] < -9f

        // Check for shake action
        val now = System.currentTimeMillis()
        if (now - mLastForce > SHAKE_TIMEOUT) {
            mShakeCount = 0
        }
        if (now - mLastTime > TIME_THRESHOLD) {
            val diff = now - mLastTime
            val speed = abs(
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
                    onShake()
                }
                mLastForce = now
            }
            mLastTime = now
            mLastX = event.values[SensorManager.DATA_X]
            mLastY = event.values[SensorManager.DATA_Y]
            mLastZ = event.values[SensorManager.DATA_Z]
        }
    }

    // Action when shake event is detected
    private fun onShake() {
        val selectedDevice = deviceRepository.highlightedDevice.value
        if (selectedDevice != null) {
            deviceRepository.share(selectedDevice)
        }
    }

    // Action when tilt event is detected
    private fun tiltSensorEvent(event: SensorEvent) {
        if (isFaceDown) {
            themeRepository.toggleTheme()
        }
    }

    // Parameters for shake detection
    companion object {
        private const val FORCE_THRESHOLD = 800
        private const val TIME_THRESHOLD = 100
        private const val SHAKE_TIMEOUT = 500
        private const val SHAKE_DURATION = 1000
        private const val SHAKE_COUNT = 5
    }
}