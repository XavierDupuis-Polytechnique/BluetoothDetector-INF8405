package com.example.bluetoothdetector.main.sources

import com.example.bluetoothdetector.auth.repository.AccountRepository
import com.example.bluetoothdetector.main.model.Device
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DistributedDeviceSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val accountRepository: AccountRepository
) : CollectionSource<Device, String> {

    companion object {
        const val DeviceCollectionName = "DEVICE_COLLECTION"
    }

    override fun savedInstancesProvider(caller: (List<Device>) -> Unit) {
        deviceCollection()?.get()?.addOnSuccessListener {
            caller(it.toObjects())
        }
    }

    override suspend fun getAll(): List<Device> {
        var allSavedDevices = listOf<Device>()
        deviceCollection()?.get()?.addOnSuccessListener {
            allSavedDevices = it.toObjects()
        }?.await()
        return allSavedDevices
    }

    override suspend fun get(id: String): Device? {
        return deviceCollection()?.document(id)?.get()?.await()?.toObject()
    }

    override suspend fun delete(instance: Device) {
        deviceCollection()?.document(instance.id)?.delete()?.await()
    }

    override suspend fun deleteAll() {
        deviceCollection()?.get()?.await()?.map { it.reference.delete().asDeferred() }?.awaitAll()
    }

    override suspend fun insert(instance: Device) {
        deviceCollection()?.document(instance.id)?.set(instance, SetOptions.merge())
    }

    private fun deviceCollection(
        uid: String? = accountRepository.currentUserId
    ): CollectionReference? =
        uid?.let { accountRepository.getUserCollection(uid).collection(DeviceCollectionName) }

    val onDeviceCollectionChange = accountRepository.currentUser
}