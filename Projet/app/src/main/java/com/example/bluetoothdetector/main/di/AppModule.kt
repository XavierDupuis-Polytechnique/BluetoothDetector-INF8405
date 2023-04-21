package com.example.bluetoothdetector.main.di

import android.content.Context
import androidx.room.Room
import com.example.bluetoothdetector.common.repository.ThemeRepository
import com.example.bluetoothdetector.main.repository.*
import com.example.bluetoothdetector.main.sources.DeviceSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// App Module
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides a single instance of the DeviceRepository
    @Singleton
    @Provides
    fun provideDeviceRepository(
        @ApplicationContext context: Context,
        deviceSource: DeviceSource
    ) = DeviceRepository(context, deviceSource)

    // Provides a single instance of the FusedLocationProviderClient
    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ) = LocationServices.getFusedLocationProviderClient(context)

    // Provides a single instance of the LocationRepository
    @Singleton
    @Provides
    fun provideLocationRepository(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) = LocationRepository(fusedLocationProviderClient)

    // Provides a single instance of the DeviceSource
    @Singleton
    @Provides
    fun provideDeviceSource(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DeviceSource::class.java,
        DeviceSource.Name
    ).fallbackToDestructiveMigration().build()

    // Provides a single instance of the BluetoothRepository
    @Singleton
    @Provides
    fun provideBluetoothRepository(
        @ApplicationContext context: Context,
        deviceRepository: DeviceRepository,
        locationRepository: LocationRepository
    ) = BluetoothRepository(context, deviceRepository, locationRepository)

    // Provides a single instance of the SensorRepository
    @Singleton
    @Provides
    fun provideSensorRepository(
        @ApplicationContext context: Context,
        deviceRepository: DeviceRepository,
        themeRepository: ThemeRepository,
    ) = SensorRepository(context, deviceRepository, themeRepository)

    // Provides a single instance of the NetworkStatsManager
    @Singleton
    @Provides
    fun provideNetworkRepository() = NetworkRepository()
}

