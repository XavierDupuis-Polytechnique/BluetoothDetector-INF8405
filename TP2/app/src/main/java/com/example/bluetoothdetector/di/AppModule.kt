package com.example.bluetoothdetector.di

import android.content.Context
import com.example.bluetoothdetector.main.repository.Bluetooth
import com.example.bluetoothdetector.main.repository.DeviceRepository
import com.example.bluetoothdetector.main.repository.LocationRepository
import com.example.bluetoothdetector.main.sources.DeviceSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDeviceSource(
        @ApplicationContext context: Context
    ) = DeviceSource(context)

    @Singleton
    @Provides
    fun provideDeviceRepository(
        deviceSource: DeviceSource
    ) = DeviceRepository(deviceSource)

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ) = LocationServices.getFusedLocationProviderClient(context)

    @Singleton
    @Provides
    fun provideLocationRepository(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) = LocationRepository(fusedLocationProviderClient)

    @Singleton
    @Provides
    fun provideBluetoothManager(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideBluetoothRepository(
        context: Context,
        deviceRepository: DeviceRepository,
        locationRepository: LocationRepository
    ) = Bluetooth(context, deviceRepository, locationRepository)
}