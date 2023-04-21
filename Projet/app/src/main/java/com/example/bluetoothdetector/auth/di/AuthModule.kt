package com.example.bluetoothdetector.auth.di

import com.example.bluetoothdetector.auth.repository.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Auth module
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    // Provides a single instance of the Firebase.auth
    @Singleton
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    // Provides a single instance of the Firebase.storage
    @Singleton
    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    // Provides a single instance of the AccountRepository
    @Singleton
    @Provides
    fun provideAccountRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ) = AccountRepository(firebaseAuth, firebaseFirestore, firebaseStorage)
}