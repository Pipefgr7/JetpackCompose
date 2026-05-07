package com.example.tallercompose03.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CiudadDao {
    @Query("SELECT * FROM ciudades_historial ORDER BY timestamp DESC LIMIT 5")
    fun getUltimas5(): Flow<List<CiudadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(ciudad: CiudadEntity)
}
