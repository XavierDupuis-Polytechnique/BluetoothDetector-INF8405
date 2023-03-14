package com.example.bluetoothdetector.main.sources

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.gms.tasks.Task


class LocationSource(private val fusedLocationProviderClient: FusedLocationProviderClient) {

    companion object {
        // TODO : FIND HOW TO USE NEXT LINE
        // const val LocationRequestAccuracy: Int = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        const val LocationRequestAccuracy: Int = LocationRequest.PRIORITY_HIGH_ACCURACY
        const val LocationRequestInterval: Long = 10000
        const val LocationRequestFastestInterval: Long = 5000
    }

    val currentLocation: MutableState<Location?> = mutableStateOf(null)

    private var locationRequest: LocationRequest = LocationRequest.create().apply {
        priority = LocationRequestAccuracy
        interval = LocationRequestInterval
        fastestInterval = LocationRequestFastestInterval
    }

    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) =
            onLocationUpdate(locationResult)
    }

    private fun onLocationUpdate(locationResult: LocationResult) {
        currentLocation.value = locationResult.lastLocation
    }


    init {
        getLastLocationAsync()
        getCurrentLocationAsync()
    }


    // TODO : CHECK PERMISSIONS AND FAIL GRACEFULLY
    @SuppressLint("MissingPermission")
    fun resumeLocationUpdatesAsync() {
        val requestTask = fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        applyLocationTask(requestTask, "resumeLocationUpdates")
    }

    // TODO : CHECK PERMISSIONS AND FAIL GRACEFULLY
    @SuppressLint("MissingPermission")
    fun getLastLocationAsync() {
        val requestTask = fusedLocationProviderClient.lastLocation
        applyLocationTask(requestTask, "getLastLocation") {
            currentLocation.value = it
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocationAsync() {
        val requestTask = fusedLocationProviderClient.getCurrentLocation(
            LocationRequestAccuracy,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                    return this
                }

                override fun isCancellationRequested(): Boolean {
                    return false
                }
            })
        applyLocationTask(requestTask, "getCurrentLocation") {
            if (it != null) currentLocation.value = it
        }
    }

    fun pauseLocationUpdatesAsync() {
        val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        applyLocationTask(removeTask, "removeLocationUpdates")
    }

    private fun <T> applyLocationTask(
        task: Task<T>,
        taskName: String? = null,
        successCallback: ((T?) -> Unit)? = null
    ) {
        val taskLabel = taskName ?: task.toString()
        task.addOnCanceledListener { println("canceled $taskLabel :") }
        task.addOnCompleteListener { println("complete $taskLabel : $it") }
        task.addOnFailureListener { println("failure $taskLabel : $it") }
        task.addOnSuccessListener {
            println("success $taskLabel : $it")
            if (successCallback != null) {
                successCallback(it)
            }
        }
    }
}