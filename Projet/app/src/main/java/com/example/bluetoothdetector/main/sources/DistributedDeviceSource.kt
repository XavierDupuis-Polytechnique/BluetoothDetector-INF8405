package com.example.bluetoothdetector.main.sources

import androidx.compose.runtime.MutableState
import com.example.bluetoothdetector.auth.repository.AuthRepository
import com.example.bluetoothdetector.main.model.Device
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DistributedDeviceSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : CollectionSource<Device, String> {

    companion object {
        const val UserCollectionName = "USER_COLLECTION"
        const val DeviceCollectionName = "DEVICE_COLLECTION"
    }

//    val devices: Flow<List<Device>>
//        get() =
//            authRepository.currentUser!!.flatMpa { user ->
//                currentCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
//            }

//        currentCollection()?.addSnapshotListener { value, error ->
//
//        }

    override fun populate(
        instances: MutableMap<String, Device>,
        favorites: MutableState<Set<Device>>
    ) {
        currentCollection()?.get()?.addOnSuccessListener {
            println("POPULATE")
            println(it)
        }
    }

    override fun observeInstanceCount(): Flow<Int> {
        // TODO
        return flowOf(999)
    }

    override suspend fun getAll(): List<Device> {
        // TODO
        return listOf()
    }

    override suspend fun get(id: String): Device? {
        return currentCollection()?.document(id)?.get()?.await()?.toObject()
    }

    override suspend fun delete(instance: Device) {
        currentCollection()?.document(instance.id)?.delete()?.await()
    }

    override suspend fun deleteAll() {
        currentCollection()?.get()?.await()?.map { it.reference.delete().asDeferred() }?.awaitAll()
    }

    override suspend fun insert(instance: Device) {
        currentCollection()?.document(instance.id)?.set(instance, SetOptions.merge())
    }

    private fun currentCollection(uid: String? = authRepository.currentUser?.uid): CollectionReference? =
        uid?.let {
            firebaseFirestore
                .collection(UserCollectionName)
                .document(it)
                .collection(DeviceCollectionName)
        }

}