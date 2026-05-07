package com.example.tallercompose04.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tallercompose04.domain.model.Transaccion
import com.example.tallercompose04.domain.repository.TransaccionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinanzasViewModel @Inject constructor(
    private val repo: TransaccionRepository
) : ViewModel() {

    val transacciones: StateFlow<List<Transaccion>> = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalIngresos: StateFlow<Double> = transacciones.map { list ->
        list.filter { it.tipo == "INGRESO" }.sumOf { it.monto }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val totalGastos: StateFlow<Double> = transacciones.map { list ->
        list.filter { it.tipo == "GASTO" }.sumOf { it.monto }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    fun agregar(descripcion: String, monto: Double, tipo: String, categoria: String) {
        if (descripcion.isBlank() || monto <= 0) return
        viewModelScope.launch {
            repo.insertar(Transaccion(descripcion = descripcion, monto = monto, tipo = tipo, categoria = categoria))
        }
    }

    fun eliminar(t: Transaccion) {
        viewModelScope.launch { repo.eliminar(t) }
    }
}