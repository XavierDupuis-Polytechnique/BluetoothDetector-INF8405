package com.example.bluetoothdetector.main.sources

import android.annotation.SuppressLint
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.tasks.Task


class LocationSource(private val fusedLocationProviderClient: FusedLocationProviderClient) {

    companion object {
        const val LocationRequestAccuracy: Int = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        const val LocationRequestInterval: Long = 400
        const val LocationRequestFastestInterval: Long = 200
    }

    private var locationRequest: LocationRequest = LocationRequest.create()

    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) = onLocationUpdate(locationResult)
    }

    init {
        locationRequest.priority = LocationRequestAccuracy
        locationRequest.interval = LocationRequestInterval
        locationRequest.fastestInterval = LocationRequestFastestInterval
    }

    private fun onLocationUpdate(locationResult: LocationResult) {
        println("NEW LOCATIONS")
        for (location in locationResult.locations) {
            println(location)
        }
    }

    // TODO : CHECK AND FAIL GRACEFULLY
    @SuppressLint("MissingPermission")
    fun resumeLocationUpdates() {
        val requestTask = fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        logTaskStatus(requestTask)
    }

    fun pauseLocationUpdates() {
        val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        logTaskStatus(removeTask)
    }

    private fun logTaskStatus(task: Task<Void>) {
        task.addOnCanceledListener { println("canceled $task") }
        task.addOnCompleteListener { println("complete $task") }
        task.addOnFailureListener { println("failure $task") }
        task.addOnSuccessListener { println("success $task") }
    }
}