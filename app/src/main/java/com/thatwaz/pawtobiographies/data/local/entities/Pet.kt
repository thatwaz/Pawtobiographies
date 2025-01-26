package com.thatwaz.pawtobiographies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val age: String,
    val imageUri: String?, // URI for pet image
    val isMyPet: Boolean // New field to distinguish ownership
)

