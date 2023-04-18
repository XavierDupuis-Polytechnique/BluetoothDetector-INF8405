package com.example.bluetoothdetector.main.sources

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.Flow

interface CollectionSource<InstanceType, IdType> {
    // Fills requested map and set with stored instances data
    fun populate(
        instances: MutableMap<IdType, InstanceType>,
        favorites: MutableState<Set<InstanceType>>
    )

    // Observes the stored instance count
    fun observeInstanceCount(): Flow<Int>

    // Retrieves all stored instances
    suspend fun getAll(): List<InstanceType>

    // Retrieves selected instance
    suspend fun get(id: IdType): InstanceType?

    // Deletes selected instance from memory
    suspend fun delete(instance: InstanceType)

    // Deletes all instances from memory
    suspend fun deleteAll()

    // Inserts selected instance from memory
    suspend fun insert(instance: InstanceType)
}