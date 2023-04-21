package com.example.bluetoothdetector.main.sources

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface CollectionSource<InstanceType, IdType> {

    // Fills requested map and set with stored instances data
    fun populate(
        instances: MutableMap<IdType, InstanceType>,
        favorites: MutableState<Set<InstanceType>>,
        association: (InstanceType) -> IdType,
        favoriteFilter: (InstanceType) -> Boolean,
    ) {
        savedInstancesProvider { savedInstances: List<InstanceType> ->
            CoroutineScope(Dispatchers.IO).launch {
                safeOperation {
                    instances.putAll(savedInstances.associateBy { association(it) })
                    favorites.value += favorites.value.plus(savedInstances.filter {
                        favoriteFilter(
                            it
                        )
                    })
                }
            }
        }
    }

    fun savedInstancesProvider(caller: (List<InstanceType>) -> Unit)

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

    companion object {
        // Execute a store operation safely
        suspend fun safeOperation(
            operation: suspend () -> Unit
        ) {
            try {
                operation()
            } catch (exception: Exception) {
                println("COULD NOT EXECUTE $operation")
                exception.printStackTrace()
            }
        }
    }
}