package com.thatwaz.pawtobiographies.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thatwaz.pawtobiographies.data.local.entities.Pet
import kotlinx.coroutines.flow.Flow


@Dao
interface PetDao {

    @Query("SELECT * FROM pets WHERE isMyPet = 1")
    fun getMyPets(): Flow<List<Pet>>

    @Query("SELECT * FROM pets WHERE isMyPet = 0")
    fun getFriendsPets(): Flow<List<Pet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("DELETE FROM pets")
    suspend fun deleteAllPets()
}

