package com.example.bluetoothdetector.auth.repository

import android.content.Context
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth
) {
    val currentUser: FirebaseUser? = firebaseAuth.currentUser
    fun hasUser(): Boolean = currentUser != null

    fun getUserId(): String = currentUser?.uid.orEmpty()

    suspend fun signup(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }


    suspend fun login(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else {
                    onComplete.invoke(false)
                }
            }.await()
    }

    fun signOut() {
        firebaseAuth.signOut()
    }


}