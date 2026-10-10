---
layout: doc
title: Actividades UP06
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP06 — Corrutinas, side effects y MVVM

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

## Actividad 1 — Lista de elementos {#actividad-1}

Crea un proyecto llamado **ListTuNombre**. Replica el [caso práctico de la lista de libros](../contenidos/03-caso-practico-libros), pero en lugar de libros usa algo que te guste: animes, películas, actores o actrices, juegos, grupos musicales…

La `data class` de tus elementos debe tener **al menos 3 propiedades**, y una de ellas será `favorite`, como en el ejemplo:

```kotlin
data class Book(
    val title: String = "",
    val author: String = "",
    var favorite: Boolean = false,
)
```

En la pantalla principal, añade en la parte inferior una **foto tuya** tipo carné (actual) y tu **nombre y apellido**.

## Actividad 2 — Añadiendo favoritos {#actividad-2}

Amplía la actividad 1 para poder **marcar y desmarcar como favorito** un elemento desde la pantalla de **información** del elemento. En la pantalla **principal** se debe ver si cada elemento está marcado como favorito.

::: tip-box Recuerda
El estado vive en el **ViewModel compartido**. Para que Compose detecte el cambio, actualiza el estado creando **copias** de los objetos (`copy(...)`), nunca modificando el objeto existente.
:::

## Actividad 3 — Entrenador personal {#actividad-3}

Crea un proyecto llamado **WorkoutTuNombre**. La aplicación permitirá al usuario introducir su **nombre** y el **número de repeticiones** para hacer series de ejercicios. Al pulsar *Comenzar* se mostrará un **GIF** con el ejercicio e irá **descontando repeticiones cada 2 segundos**; al llegar a cero, **cambiará el GIF** y volverá a empezar la cuenta.

La aplicación tendrá **dos pantallas** y se deben usar **rutas y navegación**.

**Pantalla principal**

- Una **frase motivadora**.
- Un campo para el **nombre** del usuario.
- Un método para introducir el **número de repeticiones**. No se permiten números negativos y, como no tiene sentido hacer una única repetición, una opción es exigir **al menos 3**.
- Un botón para navegar a la siguiente pantalla, **desactivado** mientras el nombre esté vacío.
- En la parte inferior, una **foto tuya** tipo carné (actual) y tu **nombre y apellido**.

**Pantalla de ejercicio**

- El **nombre** del usuario.
- El **número de repeticiones**.
- Un espacio para la **imagen** del ejercicio.
- Un botón para **comenzar** el ejercicio.
- Un botón para **volver** a la pantalla anterior.

Para que los datos estén disponibles en las dos pantallas se usa un **ViewModel**.

### ViewModel

El ViewModel contendrá los **estados** (`StateFlow`) con los datos y todos los métodos necesarios. Puedes crear más si lo necesitas.

| Datos | Métodos |
|:---|:---|
| Nombre de usuario | Actualizar el nombre de usuario |
| Número de repeticiones | Actualizar el número de repeticiones |
| Ejercicio actual (el GIF que se debe mostrar) | Empezar el ejercicio |
| Haciendo ejercicio (si se está haciendo ejercicio) | Parar el ejercicio |

::: info-box LiveData o StateFlow
El enunciado original habla de `LiveData`. En los apuntes se usa **`StateFlow`**, que es la opción recomendada actualmente con Jetpack Compose: usa `StateFlow`.
:::

Un posible algoritmo para **empezar el ejercicio**:

1. Crear una lista de *drawables* con todos los GIF de los ejercicios.
2. Actualizar *haciendo ejercicio* para indicar que se está haciendo ejercicio.
3. Actualizar *ejercicio actual* con un *drawable* aleatorio de la lista.
4. Lanzar una **corrutina** (`viewModelScope.launch`) con un bucle que se repita mientras *haciendo ejercicio* sea verdadero:
   - Esperar 2 segundos (`delay(2000)`).
   - Restar uno al *número de repeticiones*.
   - Si llega a cero, reiniciar el *número de repeticiones* y actualizar *ejercicio actual* con otro *drawable* aleatorio.

### GIF animados

GIF de los ejercicios para añadir a `res/drawable`:

| | | | |
|:---:|:---:|:---:|:---:|
| [e1.gif](/descargas/up06/e1.gif) | [e2.gif](/descargas/up06/e2.gif) | [e3.gif](/descargas/up06/e3.gif) | [e4.gif](/descargas/up06/e4.gif) |
| [e5.gif](/descargas/up06/e5.gif) | [e6.gif](/descargas/up06/e6.gif) | [e7.gif](/descargas/up06/e7.gif) | [e8.gif](/descargas/up06/e8.gif) |

Para mostrar GIF animados se usa **Coil** con su módulo de GIF. Si ya usas Coil 3 (como en la [UP04](../../up04/contenidos/01-modifier-componentes-basicos#_1-11-imagenes-de-internet)), añade la librería `coil-gif` con la misma versión:

::: tabs

== Coil 3 (recomendado)

```toml [gradle/libs.versions.toml]
[libraries]
# …
coil-gif = { group = "io.coil-kt.coil3", name = "coil-gif", version.ref = "coil" }
```

```kotlin [app/build.gradle.kts]
implementation(libs.coil.compose)
implementation(libs.coil.gif)
```

```kotlin
@Composable
fun ExerciseGif(exerciseImage: Int) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    AsyncImage(
        model = exerciseImage,
        imageLoader = imageLoader,
        contentDescription = "Exercise",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}
```

== Coil 2 (enunciado original)

```kotlin [app/build.gradle.kts]
implementation("io.coil-kt:coil-compose:2.5.0")
implementation("io.coil-kt:coil-gif:2.5.0")
```

```kotlin
@Composable
fun ExerciseGif(exerciseImage: Int) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(context)
                .data(data = exerciseImage)
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = "Exercise",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}
```

:::

::: warning-box No mezcles versiones
Coil 2 (`io.coil-kt`) y Coil 3 (`io.coil-kt.coil3`) tienen paquetes distintos. Usa solo una de las dos en el proyecto.
:::

## Entrega

Se entregan las **actividades 2 y 3**. Para cada una:

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques el código y muestres la ejecución. En la actividad 2, céntrate en la funcionalidad de **marcar como favorito**.
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.a** Genera la estructura de clases necesaria<br>*MVVM: modelo, ViewModel, pantallas* | Lógica dentro de las pantallas o sin ViewModel | Modelo, ViewModel con `StateFlow` y pantallas separadas | Las pantallas solo pintan y envían eventos; se explica el flujo de datos unidireccional | Arquitectura limpia y coherente en las dos aplicaciones |
| **RA2.a** Gestiona el estado en el ViewModel<br>*`StateFlow`, `copy`, ViewModel compartido* | La interfaz no se actualiza al cambiar los datos | Los cambios (favoritos, repeticiones, ejercicio) se reflejan en las dos pantallas | Se usa `copy(...)` y estado privado mutable / público inmutable, y se justifica | El estado sobrevive al giro de pantalla y no hay estados duplicados |
| **RA2.b** Utiliza las clases de interfaz y navegación<br>*Pantallas, navegación, GIF* | Faltan pantallas o elementos pedidos | Todas las pantallas y elementos funcionan | Validaciones correctas (nombre obligatorio, mínimo de repeticiones) y navegación bien gestionada | Interfaz cuidada y coherente con Material 3 |
| **RA2.g** Prueba la interacción en el emulador<br>*Corrutinas y casos límite* | El temporizador no funciona o bloquea la interfaz | La cuenta atrás funciona cada 2 s y cambia el ejercicio | Se puede parar y reanudar sin lanzar corrutinas duplicadas | Comportamiento robusto al navegar atrás o girar el móvil |
| **RA2.i** Documenta el desarrollo<br>*Vídeos* | Vídeo ausente | Vídeos dentro de los límites que muestran la ejecución | Los vídeos explican el código clave (favoritos, corrutina) | Vídeos claros y bien estructurados; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
