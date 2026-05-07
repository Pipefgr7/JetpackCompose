package com.example.tallercompose04.domain.usecase

import com.example.tallercompose04.domain.model.Transaccion
import com.example.tallercompose04.domain.repository.TransaccionRepository
import javax.inject.Inject

class AgregarTransaccionUseCase @Inject constructor(
    private val repository: TransaccionRepository
) {
    suspend operator fun invoke(transaccion: Transaccion) {
        repository.insertar(transaccion)
    }
}
