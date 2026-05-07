package com.example.tallercompose02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tallercompose02.ui.theme.TallerCompose02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerCompose02Theme {
                AppNavegacion()
            }
        }
    }
}

// ── NAVEGACIÓN ──────────────────────────────────────────
@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    var tareas by remember { mutableStateOf(listOf<Tarea>()) }

    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            ListaTareasScreen(
                tareas = tareas,
                onAgregarTarea = { nueva -> tareas = tareas + nueva },
                onEliminarTarea = { id -> tareas = tareas.filter { it.id != id } },
                onCambiarEstado = { id, checked ->
                    tareas = tareas.map { if (it.id == id) it.copy(completada = checked) else it }
                },
                navController = navController
            )
        }
        composable("detalle/{tareaId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("tareaId")?.toIntOrNull()
            val tarea = tareas.find { it.id == id }
            if (tarea != null) {
                DetalleTareaScreen(tarea = tarea, navController = navController)
            }
        }
    }
}

// ── PANTALLA LISTA ───────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTareasScreen(
    tareas: List<Tarea>,
    onAgregarTarea: (Tarea) -> Unit,
    onEliminarTarea: (Int) -> Unit,
    onCambiarEstado: (Int, Boolean) -> Unit,
    navController: NavController
) {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Tareas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(tareas, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onCheckedChange = { checked -> onCambiarEstado(tarea.id, checked) },
                    onEliminar = { onEliminarTarea(tarea.id) },
                    onClick = { navController.navigate("detalle/${tarea.id}") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if (mostrarDialogo) {
            DialogoNuevaTarea(
                onConfirmar = { titulo ->
                    val nueva = Tarea(id = (tareas.size + 1), titulo = titulo)
                    onAgregarTarea(nueva)
                    mostrarDialogo = false
                },
                onCancelar = { mostrarDialogo = false }
            )
        }
    }
}

// ── ITEM DE TAREA ────────────────────────────────────────
@Composable
fun TareaItem(
    tarea: Tarea,
    onCheckedChange: (Boolean) -> Unit,
    onEliminar: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = onCheckedChange
            )
            Text(
                text = tarea.titulo,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                textDecoration = if (tarea.completada) TextDecoration.LineThrough else null,
                color = if (tarea.completada)
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                else
                    MaterialTheme.colorScheme.onSurface
            )
            IconButton(onClick = onEliminar) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

// ── DIÁLOGO NUEVA TAREA ──────────────────────────────────
@Composable
fun DialogoNuevaTarea(onConfirmar: (String) -> Unit, onCancelar: () -> Unit) {
    var texto by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Nueva tarea") },
        text = {
            Column {
                OutlinedTextField(
                    value = texto,
                    onValueChange = {
                        texto = it
                        error = false
                    },
                    label = { Text("Título de la tarea") },
                    isError = error,
                    supportingText = {
                        if (error) Text(
                            "El título no puede estar vacío",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (texto.isBlank()) error = true else onConfirmar(texto.trim())
            }) { Text("Agregar") }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) { Text("Cancelar") }
        }
    )
}

// ── PANTALLA DETALLE ─────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleTareaScreen(tarea: Tarea, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Tarea") },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("← Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text("Tarea #${tarea.id}", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(tarea.titulo, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (tarea.completada) " Completada" else " Pendiente",
                style = MaterialTheme.typography.bodyLarge,
                color = if (tarea.completada)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        }
    }
}