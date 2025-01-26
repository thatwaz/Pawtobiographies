package com.thatwaz.pawtobiographies.data.local.database



import androidx.room.Database
import androidx.room.RoomDatabase
import com.thatwaz.pawtobiographies.data.local.dao.PetDao
import com.thatwaz.pawtobiographies.data.local.entities.Pet

@Database(entities = [Pet::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}
