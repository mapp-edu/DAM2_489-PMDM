---
layout: doc
title: "Fase 1 · Solución de referencia"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Fase 1 · Solución de referencia

Solución de referencia de la **entrega de la 1.ª evaluación** del proyecto *Explora*: la pantalla principal que se construye en el vídeo 2 de la UP03, adaptada a seis lugares del patrimonio de la Comunitat Valenciana, con los recursos, idiomas y pruebas de los vídeos 1 y 3. Cubre lo que se puede hacer con las unidades 1 a 3; el resto de la [fase 1](./fase-1) se completa con las unidades siguientes.

Hay muchas soluciones correctas: úsala para **comparar** con tu proyecto, no para copiarla. Tu temática, tus datos y tus decisiones tienen que ser tuyos.

::: tip-box Descarga
Proyecto completo: <a :href="withBase('/descargas/proyecto/explora-fase1-solucion.zip')" download>explora-fase1-solucion.zip</a>. Descomprímelo y, en Android Studio, elige **File → Open** y la carpeta `Explora`.
:::

<img :src="withBase('/img/proyecto/explora-fase1-maqueta.png')" alt="Maqueta aproximada de la pantalla principal de Explora" style="max-width: 360px; width: 100%;" />

*Maqueta aproximada de la pantalla principal, no una captura del emulador.*

## Qué hace la app

- Muestra una **lista de lugares** con imagen, ciudad, número de retos y puntos.
- Un **buscador** filtra por nombre o ciudad mientras se escribe. Si no hay coincidencias, muestra un aviso.
- Cada lugar se puede **marcar como visitado**. El contador «Visitados: x de y» se actualiza solo.
- Está en **español** (idioma por defecto), **inglés** y **valenciano**. Además, el usuario puede elegir el idioma de la app en los ajustes de Android 13 o posterior.
- Registra el **ciclo de vida** de la Activity en Logcat.

## Cómo abrirlo

1. Descomprime el archivo y, en Android Studio, elige **File › Open** y la carpeta `Explora`.
2. Espera a que termine el **Gradle Sync**. Si Android Studio ofrece actualizar el Android Gradle Plugin, acepta con el **AGP Upgrade Assistant**.
3. Ejecuta con **Run ▶** en un emulador con API 26 o superior, mejor con API 36.

| Componente | Versión |
|---|---|
| Gradle (wrapper) | 8.14.3 |
| Android Gradle Plugin | 8.10.1 |
| Kotlin | 2.1.21 |
| Compose BOM | 2025.05.00 |
| compileSdk / targetSdk / minSdk | 36 / 36 / 26 |

## Estructura

```
Explora/
├── app/
│   ├── build.gradle.kts                  # SDK, Compose, generateLocaleConfig
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/es/dam/explora/
│       │   ├── MainActivity.kt           # setContent + registro del ciclo de vida
│       │   ├── data/
│       │   │   ├── Modelo.kt             # Lugar, Reto, Dificultad, TipoReto
│       │   │   └── DatosEjemplo.kt       # 6 lugares y 14 retos en memoria
│       │   └── ui/
│       │       ├── PantallaLugares.kt    # Scaffold, buscador, LazyColumn, estado
│       │       ├── TarjetaLugar.kt       # Card sin estado + ImagenLugar
│       │       └── theme/                # Color.kt, Theme.kt, Type.kt
│       └── res/
│           ├── drawable/                 # ilustraciones e iconos vectoriales
│           ├── mipmap-anydpi/            # icono adaptable propio
│           ├── values/ values-en/ values-ca/
│           └── resources.properties      # idioma de values/ (es)
├── gradle/libs.versions.toml             # catálogo de versiones
└── docs/maqueta-pantalla-principal.png
```

## Correspondencia con la entrega

**R** = requisito. **M** = mejora para llegar al nivel 4.

| Elemento | Dónde está | CE | Tipo | Material |
|---|---|---|---|---|
| Proyecto Empty Activity con paquete propio | `app/build.gradle.kts` | RA1 c | R | UP03 · vídeo 1 |
| `minSdk` y `targetSdk` justificados | `app/build.gradle.kts` (comentarios) y apartado «Propuesta» | RA1 e | R | UP03 · vídeo 1 |
| Icono propio (adaptable, con versión monocroma) | `res/mipmap-anydpi/`, `drawable/ic_launcher_foreground.xml` | RA2 b | R | Solo se menciona `mipmap` (UP03 · vídeo 1); se crea con New › Image Asset |
| `data class` y `enum class` | `data/Modelo.kt` | RA2 a | R | UP02 · vídeo 3 |
| `sealed interface` y propiedades calculadas | `data/Modelo.kt` | RA2 a | M | UP02 · vídeo 3 |
| Datos en memoria (6 lugares, 14 retos) | `data/DatosEjemplo.kt` | RA2 a | R | UP03 · vídeo 2 |
| `Scaffold` + `TopAppBar` | `ui/PantallaLugares.kt` | RA2 b | R | UP03 · vídeo 2 / UP04 · apartado 3 |
| Buscador con estado (`rememberSaveable`) | `CampoBusqueda` | RA2 b | R | UP03 · vídeo 2 / UP04 · apartado 2 |
| `Card`, imagen con `painterResource` y `Checkbox` | `ui/TarjetaLugar.kt` | RA2 b | R | UP03 · vídeo 2 / UP04 · apartados 1–2 |
| Elevación del estado y `copy()` | `TarjetaLugar` (sin estado) + `PantallaLugares` | RA2 a, b | R | UP03 · vídeo 2 |
| `LazyColumn` con `key` | `ListaLugares` | RA2 b | R | UP03 · vídeo 2 / UP04 · apartado 3 |
| Iconos vectoriales con `Icon` | `ic_lugar`, `ic_buscar` | RA2 b | R | UP03 · vídeo 3 |
| Ningún texto de interfaz en el código | `res/values*/strings.xml` | RA2 b, i | R | UP03 · vídeos 2–3 |
| Segundo idioma | `values-en/` (y `values-ca/`) | RA1 e | R | UP03 · vídeo 3 |
| `plurals` | `retos_disponibles` | RA2 b | M | UP03 · vídeo 3 |
| Idioma por aplicación | `generateLocaleConfig` + `resources.properties` | RA1 e | M | UP03 · vídeo 3 |
| Caso límite: búsqueda sin resultados | `sin_resultados` | RA2 g | M | UP03 · vídeo 3 |
| Previews: idiomas, oscuro, fuente grande y tablet | final de `TarjetaLugar.kt` y `PantallaLugares.kt` | RA1 h, RA2 g | R | UP03 · vídeos 2–3 |
| Ciclo de vida en Logcat | `MainActivity.kt` | RA1 h | R | UP03 · vídeos 1 y 3 |
| Documentación | este `README.md` | RA2 i | R | — |
| Repositorio Git | — | — | Opcional | No se ha visto en clase |

## Ejemplo de propuesta y análisis

> Este apartado es un **modelo**. Cada alumno debe redactar el suyo con su propia temática.

**Temática.** Patrimonio de la Comunitat Valenciana. La app propone lugares y, en cada uno, retos de visita, fotografía o grabación de sonido que suman puntos.

**Nativa frente a multiplataforma.** Se elige una app nativa en Kotlin con Jetpack Compose por estos motivos:
- Explora usará intensivamente el hardware del móvil: ubicación, cámara, micrófono, sensores y Bluetooth o NFC. Con una app nativa se accede a todo ello directamente y con la documentación oficial.
- Es la tecnología recomendada por Google para Android.
- El módulo está orientado a Android.

Una opción multiplataforma, como Flutter o Kotlin Multiplatform, permitiría publicar también en iOS, pero añadiría una capa intermedia que no necesitamos.

**Limitaciones del dispositivo que afectan a la app:**
- **Batería**: la ubicación y la cámara consumen mucho, así que se activarán solo cuando hagan falta y se pararán en `onPause`.
- **Conectividad**: en exteriores puede no haber cobertura, así que los datos básicos estarán disponibles sin conexión.
- **Pantalla**: el tamaño es variable, de ahí la interfaz en `dp`, los textos escalables y la prueba en tablet.
- **Almacenamiento**: imágenes vectoriales o WebP comprimidas.

**SDK.**
- `minSdk 26` (Android 8.0): cubre la práctica totalidad de dispositivos en uso. El porcentaje exacto se consulta con **Help me choose** al crear el proyecto. Además, permite usar el icono adaptable sin PNG.
- `targetSdk 36` (Android 16): obligatorio en Google Play desde el 31/08/2026.

**Perfiles de prueba:**
- Móvil pequeño, por ejemplo con pantalla de 5″.
- Móvil actual (Pixel con API 36).
- Tablet de 10″.

## Plan de pruebas

| Prueba | Cómo | Resultado esperado |
|---|---|---|
| Buscar | Escribir «valen» | Quedan Lonja de la Seda y L'Albufera |
| Sin resultados | Escribir «xyz» | Aparece «No hay lugares que coincidan con «xyz»» |
| Marcar visitado | Pulsar dos casillas | «Visitados: 2 de 6» |
| Rotación | Girar el emulador | La búsqueda se conserva; las casillas se pierden (ver limitaciones) |
| Ciclo de vida | Logcat con `package:mine tag:Explora` y girar | `onPause → onStop → onDestroy → onCreate → onStart → onResume` |
| Idioma | Ajustes › Sistema › Idiomas de las apps › Explora › English | Interfaz en inglés |
| Modo oscuro | Activar el tema oscuro del sistema | Colores oscuros del tema, textos legibles |
| Fuente grande | Ajustes › Accesibilidad › Tamaño de fuente al máximo | Ningún texto cortado |
| Tablet | Emulador de tablet de 10″ | La lista ocupa el ancho y se lee bien |

Cada prueba debe acompañarse de su captura.

## Limitaciones conocidas

- **Las casillas marcadas se pierden al girar la pantalla.** La lista usa `remember`, que vive mientras exista la Activity. Se deja así a propósito: se resolverá con un `ViewModel` en una unidad posterior.
- **Los datos de ejemplo no se traducen.** Son contenido, no textos de la interfaz, y en la fase 2 vendrán de una base de datos o de la red.

## Código

### Actividad y modelo

::: code-group
```kotlin [MainActivity.kt]
package es.dam.explora

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.dam.explora.ui.PantallaLugares
import es.dam.explora.ui.theme.ExploraTheme

private const val TAG = "Explora"

/**
 * Única Activity de la app: aloja toda la interfaz de Compose.
 * Registra el ciclo de vida en Logcat (filtro: package:mine tag:Explora).
 * Al girar la pantalla se ve: onPause → onStop → onDestroy → onCreate → onStart → onResume.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate (¿estado guardado?: ${savedInstanceState != null})")
        enableEdgeToEdge()
        setContent {
            ExploraTheme {
                PantallaLugares()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
```
```kotlin [data/Modelo.kt]
package es.dam.explora.data

import androidx.annotation.DrawableRes

/** Dificultad de un reto: multiplica los puntos base. */
enum class Dificultad(val multiplicador: Int) {
    FACIL(1), MEDIA(2), DIFICIL(3)
}

/** Tipos de reto: jerarquía cerrada (mejora: sealed interface, UP02 · vídeo 3). */
sealed interface TipoReto {
    data object Visita : TipoReto
    data class Foto(val objeto: String) : TipoReto
    data class Sonido(val segundos: Int) : TipoReto
}

data class Reto(
    val titulo: String,
    val tipo: TipoReto,
    val dificultad: Dificultad,
    val puntosBase: Int = 10
) {
    /** Propiedad calculada: no se guarda, se obtiene a partir de otros datos. */
    val puntos: Int
        get() = puntosBase * dificultad.multiplicador
}

data class Lugar(
    val id: Int,
    val nombre: String,
    val ciudad: String,
    @DrawableRes val imagen: Int,
    val retos: List<Reto>,
    val visitado: Boolean = false
) {
    val puntos: Int
        get() = retos.sumOf { it.puntos }
}
```
```kotlin [data/DatosEjemplo.kt]
package es.dam.explora.data

import es.dam.explora.R

/**
 * Datos en memoria para la fase 1. En la fase 2 vendrán de una base de datos o de la red,
 * por eso no se traducen: son contenido, no textos de la interfaz.
 */
val lugaresEjemplo = listOf(
    Lugar(
        id = 1,
        nombre = "Lonja de la Seda",
        ciudad = "Valencia",
        imagen = R.drawable.lugar_lonja,
        retos = listOf(
            Reto("Entra en la sala de columnas", TipoReto.Visita, Dificultad.FACIL),
            Reto("Fotografía una gárgola", TipoReto.Foto("gárgola"), Dificultad.DIFICIL)
        )
    ),
    Lugar(
        id = 2,
        nombre = "Castillo de Sagunto",
        ciudad = "Sagunt",
        imagen = R.drawable.lugar_castillo,
        retos = listOf(
            Reto("Sube a la plaza de armas", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Fotografía el teatro romano", TipoReto.Foto("teatro romano"), Dificultad.DIFICIL)
        )
    ),
    Lugar(
        id = 3,
        nombre = "Faro de Cullera",
        ciudad = "Cullera",
        imagen = R.drawable.lugar_faro,
        retos = listOf(
            Reto("Llega al faro", TipoReto.Visita, Dificultad.FACIL),
            Reto("Graba las olas", TipoReto.Sonido(15), Dificultad.FACIL),
            Reto("Fotografía la bahía", TipoReto.Foto("bahía"), Dificultad.MEDIA)
        )
    ),
    Lugar(
        id = 4,
        nombre = "L'Albufera",
        ciudad = "Valencia",
        imagen = R.drawable.lugar_albufera,
        retos = listOf(
            Reto("Visita el embarcadero del Palmar", TipoReto.Visita, Dificultad.FACIL),
            Reto("Fotografía una barca al atardecer", TipoReto.Foto("barca"), Dificultad.DIFICIL, puntosBase = 15)
        )
    ),
    Lugar(
        id = 5,
        nombre = "Palmeral d'Elx",
        ciudad = "Elx",
        imagen = R.drawable.lugar_palmeral,
        retos = listOf(
            Reto("Encuentra la palmera imperial", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Graba el canto de los pájaros", TipoReto.Sonido(30), Dificultad.MEDIA)
        )
    ),
    Lugar(
        id = 6,
        nombre = "Castillo de Peñíscola",
        ciudad = "Peñíscola",
        imagen = R.drawable.lugar_peniscola,
        retos = listOf(
            Reto("Recorre la muralla", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Fotografía el bufador", TipoReto.Foto("bufador"), Dificultad.DIFICIL),
            Reto("Graba el mar desde las almenas", TipoReto.Sonido(20), Dificultad.FACIL)
        )
    )
)
```
:::

### Interfaz

::: code-group
```kotlin [ui/PantallaLugares.kt]
package es.dam.explora.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.dam.explora.R
import es.dam.explora.data.Lugar
import es.dam.explora.data.lugaresEjemplo
import es.dam.explora.ui.theme.ExploraTheme

/**
 * Pantalla principal. Guarda el estado (lista y búsqueda) y lo pasa hacia abajo;
 * recibe los eventos de los composables hijos (flujo unidireccional).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLugares(modifier: Modifier = Modifier) {
    // Estado. Limitación conocida: con remember, las casillas se pierden al girar
    // la pantalla. Se resolverá con un ViewModel en una unidad posterior.
    val lugares = remember { lugaresEjemplo.toMutableStateList() }
    var busqueda by rememberSaveable { mutableStateOf("") }

    // Valores calculados a partir del estado: no se guardan.
    val filtrados = lugares.filter {
        it.nombre.contains(busqueda, ignoreCase = true) ||
            it.ciudad.contains(busqueda, ignoreCase = true)
    }
    val visitados = lugares.count { it.visitado }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CampoBusqueda(valor = busqueda, onValorChange = { busqueda = it })
            Text(
                text = stringResource(R.string.visitados, visitados, lugares.size),
                style = MaterialTheme.typography.labelLarge
            )
            if (filtrados.isEmpty()) {
                // Caso límite: búsqueda sin resultados.
                Text(
                    text = stringResource(R.string.sin_resultados, busqueda),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                ListaLugares(
                    lugares = filtrados,
                    onVisitadoChange = { lugar, marcado ->
                        val i = lugares.indexOfFirst { it.id == lugar.id }
                        if (i >= 0) lugares[i] = lugar.copy(visitado = marcado)
                    }
                )
            }
        }
    }
}

@Composable
fun CampoBusqueda(
    valor: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(stringResource(R.string.buscar)) },
        leadingIcon = {
            Icon(painter = painterResource(R.drawable.ic_buscar), contentDescription = null)
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ListaLugares(
    lugares: List<Lugar>,
    onVisitadoChange: (Lugar, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(lugares, key = { it.id }) { lugar ->
            TarjetaLugar(
                lugar = lugar,
                onVisitadoChange = { marcado -> onVisitadoChange(lugar, marcado) }
            )
        }
    }
}

@Preview(name = "Español", locale = "es", showBackground = true)
@Preview(name = "English", locale = "en", showBackground = true)
@Preview(name = "Valencià", locale = "ca", showBackground = true)
@Preview(name = "Fuente grande", fontScale = 1.5f, showBackground = true)
@Preview(name = "Tablet", device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Composable
private fun PantallaLugaresPreview() {
    ExploraTheme {
        PantallaLugares()
    }
}
```
```kotlin [ui/TarjetaLugar.kt]
package es.dam.explora.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.dam.explora.R
import es.dam.explora.data.Lugar
import es.dam.explora.data.lugaresEjemplo
import es.dam.explora.ui.theme.ExploraTheme

/**
 * Tarjeta de un lugar. No guarda estado (state hoisting): recibe el lugar
 * y avisa con onVisitadoChange cuando el usuario marca la casilla.
 */
@Composable
fun TarjetaLugar(
    lugar: Lugar,
    onVisitadoChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagenLugar(id = lugar.imagen, descripcion = lugar.nombre)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(text = lugar.nombre, style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lugar),
                        contentDescription = null, // decorativo: el texto de al lado ya informa
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = lugar.ciudad, style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    text = pluralStringResource(
                        R.plurals.retos_disponibles,
                        lugar.retos.size,
                        lugar.retos.size,
                        lugar.puntos
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Checkbox(checked = lugar.visitado, onCheckedChange = onVisitadoChange)
        }
    }
}

@Composable
fun ImagenLugar(
    @DrawableRes id: Int,
    descripcion: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id),
        contentDescription = descripcion,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Preview(name = "Claro", showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TarjetaLugarPreview() {
    ExploraTheme {
        TarjetaLugar(lugar = lugaresEjemplo[0], onVisitadoChange = {})
    }
}
```
```kotlin [ui/theme/Color.kt]
package es.dam.explora.ui.theme

import androidx.compose.ui.graphics.Color

// Colores de marca de Explora: azul mediterráneo, terracota y arena.
val Mar40 = Color(0xFF00658E)
val Terracota40 = Color(0xFF9A4521)
val Arena40 = Color(0xFF6B5D2F)

val Mar80 = Color(0xFF86CFFF)
val Terracota80 = Color(0xFFFFB597)
val Arena80 = Color(0xFFD8C68E)
```
```kotlin [ui/theme/Theme.kt]
package es.dam.explora.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val EsquemaOscuro = darkColorScheme(
    primary = Mar80,
    secondary = Terracota80,
    tertiary = Arena80
)

private val EsquemaClaro = lightColorScheme(
    primary = Mar40,
    secondary = Terracota40,
    tertiary = Arena40
)

/**
 * Tema de Explora. dynamicColor = false para usar siempre los colores de marca;
 * con true, en Android 12+ los colores salen del fondo de pantalla.
 */
@Composable
fun ExploraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> EsquemaOscuro
        else -> EsquemaClaro
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```
:::

### Recursos e idiomas

::: code-group
```xml [values/strings.xml]
<?xml version="1.0" encoding="utf-8"?>
<!-- Idioma por defecto (de reserva): español. Debe estar siempre completo. -->
<resources>
    <string name="app_name" translatable="false">Explora</string>
    <string name="buscar">Buscar lugar o ciudad</string>
    <string name="visitados">Visitados: %1$d de %2$d</string>
    <string name="sin_resultados">No hay lugares que coincidan con «%1$s»</string>
    <plurals name="retos_disponibles">
        <item quantity="one">%1$d reto · %2$d puntos</item>
        <item quantity="other">%1$d retos · %2$d puntos</item>
    </plurals>
</resources>
```
```xml [values-en/strings.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="buscar">Search place or town</string>
    <string name="visitados">Visited: %1$d of %2$d</string>
    <string name="sin_resultados">No places match “%1$s”</string>
    <plurals name="retos_disponibles">
        <item quantity="one">%1$d challenge · %2$d points</item>
        <item quantity="other">%1$d challenges · %2$d points</item>
    </plurals>
</resources>
```
```xml [values-ca/strings.xml]
<?xml version="1.0" encoding="utf-8"?>
<!-- Valencià -->
<resources>
    <string name="buscar">Cerca un lloc o una ciutat</string>
    <string name="visitados">Visitats: %1$d de %2$d</string>
    <string name="sin_resultados">Cap lloc coincideix amb «%1$s»</string>
    <plurals name="retos_disponibles">
        <item quantity="one">%1$d repte · %2$d punts</item>
        <item quantity="other">%1$d reptes · %2$d punts</item>
    </plurals>
</resources>
```
```properties [resources.properties]
unqualifiedResLocale=es
```
:::

### Configuración e icono

::: code-group
```kotlin [app/build.gradle.kts]
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "es.dam.explora"
    compileSdk = 36

    defaultConfig {
        applicationId = "es.dam.explora"
        // minSdk 26 (Android 8.0): iconos adaptables sin PNG y cobertura casi total
        // (consulta el porcentaje actual con «Help me choose» al crear el proyecto).
        minSdk = 26
        // Google Play exige targetSdk 36 (Android 16) desde el 31/08/2026.
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    androidResources {
        // Idioma por aplicación (Android 13+): genera la lista a partir de values-xx/
        generateLocaleConfig = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
}
```
```toml [gradle/libs.versions.toml]
# Catálogo de versiones: todas las versiones del proyecto en un único sitio.
[versions]
agp = "8.10.1"
kotlin = "2.1.21"
coreKtx = "1.16.0"
lifecycleRuntimeKtx = "2.8.7"
activityCompose = "1.10.1"
composeBom = "2025.05.00"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```
```xml [AndroidManifest.xml]
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.Explora">

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>
```
```xml [mipmap-anydpi/ic_launcher.xml]
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@color/ic_launcher_background" />
    <foreground android:drawable="@drawable/ic_launcher_foreground" />
    <monochrome android:drawable="@drawable/ic_launcher_foreground" />
</adaptive-icon>
```
```xml [drawable/ic_launcher_foreground.xml]
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <!-- Marcador de lugar centrado en la zona segura (círculo de 66 dp) -->
    <group
        android:scaleX="2"
        android:scaleY="2"
        android:translateX="30"
        android:translateY="28">
        <path
            android:fillColor="#FFFFFFFF"
            android:pathData="M12,2C8.13,2 5,5.13 5,9c0,5.25 7,13 7,13s7,-7.75 7,-13c0,-3.87 -3.13,-7 -7,-7zM12,11.5c-1.38,0 -2.5,-1.12 -2.5,-2.5s1.12,-2.5 2.5,-2.5 2.5,1.12 2.5,2.5 -1.12,2.5 -2.5,2.5z" />
    </group>
    <!-- Ola bajo el marcador -->
    <path
        android:fillColor="#FFFFB597"
        android:pathData="M32,80q5.5,-4 11,0t11,0t11,0t11,0v5q-5.5,-4 -11,0t-11,0t-11,0t-11,0z" />
</vector>
```
:::

## Créditos

- Ilustraciones de los lugares: creadas para este proyecto como gráficos vectoriales, de uso libre en el módulo.
- Iconos `ic_lugar` e `ic_buscar`: Material Icons de Google, con licencia Apache 2.0.
