package com.example.tallercompose03.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ciudades_historial")
data class CiudadEntity(
    @PrimaryKey
    val nombre: String,
    val timestamp: Long = System.currentTimeMillis(),
)
