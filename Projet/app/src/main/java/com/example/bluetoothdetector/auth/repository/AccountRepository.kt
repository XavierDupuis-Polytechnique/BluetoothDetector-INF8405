package com.example.bluetoothdetector.auth.repository

import android.content.Context
import android.net.Uri
import com.example.bluetoothdetector.common.sources.ImageFileProvider
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
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

    fun setProfilePicture(path: Uri, username: String, onComplete: ((Boolean) -> Unit)?) {
        uploadImage(path, username, onComplete)
    }

    private fun uploadImage(path: Uri, username: String, onComplete: ((Boolean) -> Unit)?) {
        val filename = username.lowercase()
        val imageReference = firebaseStorage.reference.child("images//${filename}.jpg")
        val uploadTask = imageReference.putFile(path)
        uploadTask
            .addOnFailureListener {
                onComplete?.invoke(false)
            }
            .addOnSuccessListener {
                onComplete?.invoke(true)
            }
    }

    fun getProfilePicture(username: String, onComplete: (Uri?) -> Unit) {
        val filename = username.lowercase()
        val imageReference = firebaseStorage.reference.child("images//${filename}.jpg")
        val downloadUrl = imageReference.downloadUrl
            .addOnFailureListener {
                onComplete(null)
            }
            .addOnSuccessListener {
                onComplete(it)
            }
    }

    private fun downloadImage(filename: String, context: Context, onComplete: (Uri?) -> Unit) {
        val imageReference = firebaseStorage.reference.child("images//${filename}.jpg")
        val downloadFile = ImageFileProvider.getImageFile(context)
        val downloadTask = imageReference.getFile(downloadFile)
        downloadTask
            .addOnFailureListener {
                onComplete(null)
            }
            .addOnSuccessListener {
                onComplete(ImageFileProvider.getUriForFile(context, downloadFile))
            }
    }

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