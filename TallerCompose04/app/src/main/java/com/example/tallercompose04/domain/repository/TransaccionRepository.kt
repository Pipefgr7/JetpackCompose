package com.example.tallercompose04.domain.repository

import com.example.tallercompose04.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow

interface TransaccionRepository {
    fun getAll(): Flow<List<Transaccion>>
    suspend fun insertar(t: Transaccion)
    suspend fun eliminar(t: Transaccion)
    suspend fun getTotalPorTipo(tipo: String): Double
}