# Explora · Fase 1 — Proyecto de referencia

Solución de referencia de la entrega de la 1.ª evaluación del proyecto **Explora** (PMDM · DAM2). Implementa la pantalla principal que se construye en el vídeo 2 de la UP03 («Primera app con Jetpack Compose»), adaptada a seis lugares del patrimonio de la Comunitat Valenciana. Incluye además los recursos, idiomas y pruebas de los vídeos 1 y 3 de la UP03.

![Maqueta aproximada de la pantalla principal](docs/maqueta-pantalla-principal.png)

> La imagen es una **maqueta aproximada**, no una captura del emulador.

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

## Créditos

- Ilustraciones de los lugares: creadas para este proyecto como gráficos vectoriales, de uso libre en el módulo.
- Iconos `ic_lugar` e `ic_buscar`: Material Icons de Google, con licencia Apache 2.0.
