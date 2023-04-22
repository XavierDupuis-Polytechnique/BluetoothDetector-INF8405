package com.example.bluetoothdetector.common.di

import com.example.bluetoothdetector.common.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Theme module
@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    // Provides a single instance of the ThemeRepository
    @Singleton
    @Provides
    fun provideThemeRepository() = ThemeRepository()
}