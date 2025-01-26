package com.thatwaz.pawtobiographies.data.repository



import com.thatwaz.pawtobiographies.data.local.dao.PetDao
import com.thatwaz.pawtobiographies.data.local.entities.Pet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepository @Inject constructor(private val petDao: PetDao) {

    fun getMyPets(): Flow<List<Pet>> = petDao.getMyPets()

    fun getFriendsPets(): Flow<List<Pet>> = petDao.getFriendsPets()

    suspend fun insertPet(pet: Pet) {
        petDao.insertPet(pet)
    }

    suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet)
    }

    suspend fun deleteAllPets() {
        petDao.deleteAllPets()
    }
}
