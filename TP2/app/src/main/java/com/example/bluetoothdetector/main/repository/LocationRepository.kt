package com.example.bluetoothdetector.main.repository

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.gms.tasks.Task


// Manages all operations related to Location
class LocationRepository(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {
    companion object {
        const val LocationRequestPriority: Int = Priority.PRIORITY_BALANCED_POWER_ACCURACY
        const val LocationRequestInterval: Long = 10000
        val DefaultLocation: Location = Location("Polytechnique").apply {
            latitude = 45.5048
            longitude = -73.6132
        }
    }

    // Holds the current (or last known) location
    val currentLocation: MutableState<Location?> = mutableStateOf(null)

    // Holds the location request with desired priority and interval
    private var locationRequest: LocationRequest = LocationRequest.Builder(
        LocationRequestPriority,
        LocationRequestInterval
    ).build()

    // Redirects a location update
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) =
            onLocationUpdate(locationResult)
    }

    // Update current known location
    private fun onLocationUpdate(locationResult: LocationResult) {
        currentLocation.value = locationResult.lastLocation
    }


    init {
        getLastLocationAsync()
        getCurrentLocationAsync()
    }

    // Resumes location updates
    @SuppressLint("MissingPermission")
    fun resumeLocationUpdatesAsync() {
        val requestTask = fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        applyLocationTask(requestTask, "resumeLocationUpdates")
    }

    // Request the last location value
    @SuppressLint("MissingPermission")
    fun getLastLocationAsync() {
        val requestTask = fusedLocationProviderClient.lastLocation
        applyLocationTask(requestTask, "getLastLocation") {
            currentLocation.value = it
        }
    }

    // Request the current location value
    @SuppressLint("MissingPermission")
    fun getCurrentLocationAsync() {
        val requestTask = fusedLocationProviderClient.getCurrentLocation(
            LocationRequestPriority,
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

    // Pauses location updates
    fun pauseLocationUpdatesAsync() {
        val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        applyLocationTask(removeTask, "removeLocationUpdates")
    }

    // Launches a location request and logs related listeners
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