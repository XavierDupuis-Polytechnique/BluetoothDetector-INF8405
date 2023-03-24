package com.example.bluetoothdetector.common

import android.content.Context
import com.example.bluetoothdetector.common.repository.LanguageRepository
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