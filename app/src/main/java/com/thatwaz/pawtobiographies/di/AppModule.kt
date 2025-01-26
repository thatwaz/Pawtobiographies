package com.thatwaz.pawtobiographies.di

import android.content.Context
import androidx.room.Room
import com.thatwaz.pawtobiographies.data.local.dao.PetDao
import com.thatwaz.pawtobiographies.data.local.database.AppDatabase
import com.thatwaz.pawtobiographies.data.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pawtobiographies_db"
        )
            .fallbackToDestructiveMigration() // Clear and recreate the database on schema change
            .build()
    }

    @Provides
    fun providePetDao(database: AppDatabase): PetDao {
        return database.petDao()
    }

    @Provides
    @Singleton
    fun providePetRepository(petDao: PetDao): PetRepository {
        return PetRepository(petDao)
    }
}

