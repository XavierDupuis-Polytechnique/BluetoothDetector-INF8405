package com.example.bluetoothdetector.common.di

import com.example.bluetoothdetector.common.repository.ThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {
    @Singleton
    @Provides
    fun provideThemeRepository() = ThemeRepository()
}