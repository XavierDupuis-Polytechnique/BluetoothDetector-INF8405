package com.example.bluetoothdetector.auth.di

import android.content.Context
import com.example.bluetoothdetector.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Auth module
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    // Provides a single instance of the AuthRepository
    @Singleton
    @Provides
    fun provideAuthRepository(
        @ApplicationContext context: Context
    ) = AuthRepository(context)
}