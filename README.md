# Talleres Guiados — Kotlin + Jetpack Compose

**Programación Móvil · Universidad Libre · Facultad de Ingeniería de Sistemas**  
Docente: Raul Gaviria · Estudiante: Felipe Gallego Rengifo y Jorge Andres Lopez · 2025–2026

Serie de cinco talleres prácticos de dificultad progresiva para la transición
del paradigma XML al paradigma declarativo con Kotlin + Jetpack Compose,
con integración transversal de Inteligencia Artificial en el diseño de UI.

---

## Estructura del Repositorio
TallerCompose01/ → Hola Compose: Primera pantalla declarativa
TallerCompose02/ → Estado y Navegación: App de Lista de Tareas
TallerCompose03/ → ViewModel + StateFlow: App del Clima con API REST
TallerCompose04/ → Room + Hilt + Clean Architecture: App de Finanzas
Tallercompose05/ → App Completa con IA: Asistente de Recetas Inteligente
NewsAPP/ → Proyecto adicional: App de Noticias
Parcial2.pdf → Informe de talleres
informe noticias.pdf → Informe NewsAPP

---

## Talleres

### Taller 01 — Hola Compose
**Nivel:** Principiante · **Duración:** 2–3 horas

Primera UI declarativa sin XML. Composables básicos: Text, Column, Row,
Surface y Modifier. Uso de @Preview para iterar el diseño sin ejecutar
el emulador. Actividad con IA: tarjeta de perfil personal con ChatGPT.

**Tecnologías:** Jetpack Compose · Material3 · @Preview · ChatGPT

---

### Taller 02 — App de Lista de Tareas
**Nivel:** Básico-Intermedio · **Duración:** 3–4 horas

Estado reactivo con remember y mutableStateOf. Listas dinámicas con
LazyColumn. Navegación declarativa con NavHost y NavController.
Validación de formularios con TextField y AlertDialog.

**Tecnologías:** Navigation Compose · LazyColumn · State Hoisting · AlertDialog

---

### Taller 03 — App del Clima con API REST
**Nivel:** Intermedio · **Duración:** 4–5 horas

Arquitectura MVVM con ViewModel y StateFlow. Consumo de la API de
OpenWeatherMap con Retrofit y Coroutines. Estados de UI (Loading,
Success, Error) modelados con sealed class. Historial de ciudades
persistido con Room Database.

**Tecnologías:** Retrofit · MVVM · StateFlow · Coroutines · Room · OpenWeatherMap API

---

### Taller 04 — App de Finanzas Personales
**Nivel:** Intermedio-Avanzado · **Duración:** 5–6 horas

Clean Architecture en tres capas (data, domain, presentation).
Inyección de dependencias con Hilt. Persistencia CRUD con Room.
Dashboard con gráfica de ingresos vs gastos usando Canvas.
Sistema de diseño completo generado con IA.

**Tecnologías:** Hilt · Room · Clean Architecture · @HiltViewModel · Canvas · ChatGPT

---

### Taller 05 — Asistente de Recetas Inteligente
**Nivel:** Avanzado · **Duración:** 6–8 horas

Proyecto integrador. Captura de imágenes con CameraX, reconocimiento
de ingredientes on-device con ML Kit Image Labeling, generación de
recetas con Google Gemini API. Efecto typewriter animado con
LaunchedEffect. Favoritos persistidos en Room. Prototipado con Uizard.

**Tecnologías:** CameraX · ML Kit · Gemini API · Hilt · Room · Retrofit · Uizard.io

---

## Tecnologías Utilizadas

### Lenguaje y Plataforma

| Tecnología | Versión | Uso |
|---|---|---|
| Kotlin | 1.9.22 | Lenguaje principal |
| Android SDK | API 24+ (Android 7.0) | Plataforma mínima |
| Android Studio | Hedgehog 2023.1.1+ | IDE de desarrollo |
| Gradle KTS | 8.x | Sistema de build |

### UI y Diseño

| Tecnología | Versión | Uso |
|---|---|---|
| Jetpack Compose | 1.6.x | Framework de UI declarativa |
| Material Design 3 | 1.2.x | Sistema de diseño de Google |
| Compose Navigation | 2.7.x | Navegación entre pantallas |
| Compose Animation | 1.6.x | Animaciones y transiciones |

### Arquitectura y Estado

| Tecnología | Uso |
|---|---|
| MVVM | Patrón arquitectónico T01–T03 |
| Clean Architecture | Patrón arquitectónico T04–T05 |
| ViewModel | Gestión de estado y ciclo de vida |
| StateFlow / Flow | Flujos reactivos de datos |
| Kotlin Coroutines | Operaciones asíncronas sin bloquear |

### Inyección de Dependencias

| Tecnología | Versión | Uso |
|---|---|---|
| Hilt (Dagger) | 2.50 | Inyección automática en T04–T05 |
| KSP | 1.9.22-1.0.17 | Procesador de anotaciones |

### Persistencia

| Tecnología | Versión | Uso |
|---|---|---|
| Room Database | 2.6.1 | Base de datos local SQLite |
| Flow<List<T>> | — | Observación reactiva de datos |

### Red y APIs Externas

| Tecnología | Versión | Uso |
|---|---|---|
| Retrofit 2 | 2.9.0 | Cliente HTTP para APIs REST |
| OkHttp | 4.x | Cliente HTTP subyacente |
| Gson | 2.10.x | Serialización/deserialización JSON |
| OpenWeatherMap API | v2.5 | Datos del clima en tiempo real (T03) |
| Google Gemini API | v1beta | Generación de recetas con IA (T05) |

### Cámara e IA on-device

| Tecnología | Versión | Uso |
|---|---|---|
| CameraX | 1.3.1 | Preview y captura de imágenes (T05) |
| ML Kit Image Labeling | 17.0.4 | Reconocimiento de ingredientes (T05) |

### Herramientas de IA para Desarrollo

| Herramienta | Talleres | Uso |
|---|---|---|
| ChatGPT | T01–T05 | Generación de Composables y debug |
| Claude.ai | T03–T04 | Diseño de pantallas y sistemas de color |
| Uizard.io | T05 | Prototipado visual de pantallas |

---

## Configuración y Ejecución

### Prerrequisitos
- Android Studio Hedgehog 2023.1.1 o superior
- JDK 17
- Android SDK API 24+
- Dispositivo o emulador con cámara (para Taller 05)

### Clonar el repositorio
```bash
git clone https://github.com/Pipefgr7/NOMBRE_DEL_REPO.git
```

### Abrir un taller
Cada carpeta es un proyecto Android independiente.
Abre Android Studio → Open → selecciona la carpeta del taller.

### API Keys requeridas

**Taller 03 — OpenWeatherMap**
1. Regístrate en openweathermap.org
2. Ve a API Keys y genera una key gratuita
3. Pégala en WeatherRepository.kt

**Taller 05 — Google Gemini**
1. Ve a aistudio.google.com
2. Crea una API Key gratuita
3. Pégala en RecetaRepositoryImpl.kt

> Advertencia: Nunca subas tus API Keys al repositorio público.

---

## Rúbricas de Evaluación

| Taller | Criterios principales | Puntos |
|---|---|---|
| T01 | Composables básicos + @Preview + Extensión + IA | 100 |
| T02 | Estado reactivo + LazyColumn + Navegación + IA | 100 |
| T03 | MVVM + API REST + Estados de UI + Room | 100 |
| T04 | Clean Architecture + Hilt + Room + Gráfica + IA | 100 |
| T05 | CameraX + ML Kit + Gemini + Animaciones + Reflexión | 100 |

---

## Autor

**Felipe Gallego Rengifo**
**Jorge Andres Lopez Lopez**  
Ingeniería de Sistemas — Universidad Libre  
Pereira, Risaralda, Colombia  
GitHub: https://github.com/Pipefgr7

---

## Licencia

Proyecto académico — Universidad Libre   
Uso educativo únicamente.
