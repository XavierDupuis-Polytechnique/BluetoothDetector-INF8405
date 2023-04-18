package com.example.bluetoothdetector.auth.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {
    companion object {
        const val UserCollectionName = "USER_COLLECTION"
    }

    val currentUserId: String?
        get() = firebaseAuth.currentUser?.uid

    val hasUser: Boolean
        get() = firebaseAuth.currentUser != null

    val currentUser: Flow<FirebaseUser?>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser)
                }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose { firebaseAuth.removeAuthStateListener(listener) }
        }

    fun getUserCollection(uid: String) =
        firebaseFirestore
            .collection(UserCollectionName)
            .document(uid)

    suspend fun signup(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { onComplete.invoke(it.isSuccessful) }
            .await()
    }

    suspend fun login(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ): AuthResult = withContext(Dispatchers.IO) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onComplete.invoke(it.isSuccessful) }
            .await()
    }

    /*suspend*/ fun signOut() /*: AuthResult = withContext(Dispatchers.IO)*/ {
        firebaseAuth.signOut()
        // firebaseAuth.signInAnonymously().await()
    }
}