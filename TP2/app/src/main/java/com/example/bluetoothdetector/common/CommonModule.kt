package com.example.bluetoothdetector.common

import android.content.Context
import com.example.bluetoothdetector.common.repository.LanguageRepository
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
object CommonModule {

    @Singleton
    @Provides
    fun provideLanguageRepository(
        @ApplicationContext context: Context
    ) = LanguageRepository(context)

}