package com.example.tallercompose04.data.repository

import com.example.tallercompose04.data.local.TransaccionDao
import com.example.tallercompose04.data.local.TransaccionEntity
import com.example.tallercompose04.domain.model.Transaccion
import com.example.tallercompose04.domain.repository.TransaccionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransaccionRepositoryImpl @Inject constructor(
    private val dao: TransaccionDao
) : TransaccionRepository {

    override fun getAll(): Flow<List<Transaccion>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun insertar(t: Transaccion) =
        dao.insertar(t.toEntity())

    override suspend fun eliminar(t: Transaccion) =
        dao.eliminar(t.toEntity())

    override suspend fun getTotalPorTipo(tipo: String): Double =
        dao.getTotalPorTipo(tipo) ?: 0.0

    private fun TransaccionEntity.toDomain() = Transaccion(id, descripcion, monto, tipo, categoria, fecha)
    private fun Transaccion.toEntity() = TransaccionEntity(id, descripcion, monto, tipo, categoria, fecha)
}