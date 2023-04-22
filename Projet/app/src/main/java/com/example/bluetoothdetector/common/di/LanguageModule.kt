package com.example.bluetoothdetector.common.di

import android.content.Context
import com.example.bluetoothdetector.common.repository.LanguageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Language Module
@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    // Provides a single instance of the LanguageRepository
    @Singleton
    @Provides
    fun provideLanguageRepository(
        @ApplicationContext context: Context
    ) = LanguageRepository(context)
}