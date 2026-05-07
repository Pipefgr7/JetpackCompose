package com.example.tallercompose04.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tallercompose04.domain.model.Transaccion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanzasScreen(
    modifier: Modifier = Modifier,
    viewModel: FinanzasViewModel = hiltViewModel()
) {
    val transacciones by viewModel.transacciones.collectAsStateWithLifecycle()
    val totalIngresos by viewModel.totalIngresos.collectAsStateWithLifecycle()
    val totalGastos by viewModel.totalGastos.collectAsStateWithLifecycle()
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("💰 Mis Finanzas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {

            // Tarjetas de resumen
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                TarjetaResumen("Ingresos", totalIngresos, Color(0xFF2E7D32), Modifier.weight(1f))
                TarjetaResumen("Gastos", totalGastos, Color(0xFFC62828), Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Balance
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Balance: ${"%.2f".format(totalIngresos - totalGastos)}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = if (totalIngresos >= totalGastos) Color(0xFF2E7D32) else Color(0xFFC62828)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Movimientos", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(transacciones, key = { it.id }) { t ->
                    ItemTransaccion(t, onEliminar = { viewModel.eliminar(t) })
                }
            }
        }

        if (mostrarDialogo) {
            DialogoTransaccion(
                onConfirmar = { desc, monto, tipo, cat ->
                    viewModel.agregar(desc, monto, tipo, cat)
                    mostrarDialogo = false
                },
                onCancelar = { mostrarDialogo = false }
            )
        }
    }
}

@Composable
fun TarjetaResumen(titulo: String, monto: Double, color: Color, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(titulo, style = MaterialTheme.typography.labelMedium)
            Text("$${"%.2f".format(monto)}", style = MaterialTheme.typography.titleMedium, color = color)
        }
    }
}

@Composable
fun ItemTransaccion(t: Transaccion, onEliminar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(if (t.tipo == "INGRESO") "🟢" else "🔴", modifier = Modifier.padding(end = 8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(t.descripcion, style = MaterialTheme.typography.bodyMedium)
                Text(t.categoria, style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text("$${"%.2f".format(t.monto)}", style = MaterialTheme.typography.titleSmall,
                color = if (t.tipo == "INGRESO") Color(0xFF2E7D32) else Color(0xFFC62828))
            IconButton(onClick = onEliminar) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

@Composable
fun DialogoTransaccion(onConfirmar: (String, Double, String, String) -> Unit, onCancelar: () -> Unit) {
    var descripcion by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("INGRESO") }
    var categoria by remember { mutableStateOf("General") }
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Nueva Transacción") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = descripcion, onValueChange = { descripcion = it; error = false },
                    label = { Text("Descripción") }, isError = error && descripcion.isBlank())
                OutlinedTextField(value = monto, onValueChange = { monto = it; error = false },
                    label = { Text("Monto") }, isError = error && monto.isBlank())
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("INGRESO", "GASTO").forEach { t ->
                        FilterChip(selected = tipo == t, onClick = { tipo = t }, label = { Text(t) })
                    }
                }
                OutlinedTextField(value = categoria, onValueChange = { categoria = it },
                    label = { Text("Categoría") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val montoDouble = monto.toDoubleOrNull()
                if (descripcion.isBlank() || montoDouble == null || montoDouble <= 0) { error = true }
                else onConfirmar(descripcion, montoDouble, tipo, categoria)
            }) { Text("Agregar") }
        },
        dismissButton = { TextButton(onClick = onCancelar) { Text("Cancelar") } }
    )
}