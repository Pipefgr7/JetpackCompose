package com.example.tallercompose04.domain.usecase

import com.example.tallercompose04.domain.model.Transaccion
import com.example.tallercompose04.domain.repository.TransaccionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObtenerTransaccionesUseCase @Inject constructor(
    private val repository: TransaccionRepository
) {
    operator fun invoke(): Flow<List<Transaccion>> {
        return repository.getAll()
    }
}
