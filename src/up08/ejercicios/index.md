---
layout: doc
title: Actividades UP08
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP08 — Consumo de APIs

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

::: info-box Actividades propuestas
Las presentaciones originales de esta unidad no incluyen boletín. Estas actividades se proponen a partir de los contenidos y los criterios de evaluación de la unidad.
:::

## Actividad 1 — Primera petición HTTP (práctica, no evaluable) {#actividad-1}

Crea un proyecto llamado **HttpTuNombre** que, sin librerías externas, consulte una API pública con **`HttpURLConnection`**, como en el [apartado 1.6](../contenidos/01-servicios-web-api-rest#_1-6-httpurlconnection):

1. Añade el **permiso `INTERNET`** al manifiesto.
2. Un `TextField` para escribir un término de búsqueda y un botón *Buscar*.
3. Al pulsar el botón, lanza la petición **en una corrutina** con `Dispatchers.IO` y muestra el **JSON en bruto** en pantalla.
4. Comprueba qué ocurre si **desactivas la conexión** del emulador y muestra un mensaje de error en lugar de cerrar la aplicación.

## Actividad 2 — Catálogo con Retrofit {#actividad-2}

Crea un proyecto llamado **CatalogTuNombre** que consuma una **API REST pública** con **Retrofit**, siguiendo la arquitectura del [caso de estudio](../contenidos/02-retrofit). Usa una API **distinta** a la de Rick y Morty, por ejemplo:

| API | Endpoint de ejemplo |
|:---|:---|
| [PokéAPI](https://pokeapi.co/) | `https://pokeapi.co/api/v2/pokemon?limit=20` |
| [TheMealDB](https://www.themealdb.com/api.php) | `https://www.themealdb.com/api/json/v1/1/search.php?s=pasta` |
| [Open Library](https://openlibrary.org/developers/api) | `https://openlibrary.org/search.json?q=tolkien` |

La aplicación tendrá:

**Pantalla principal**

- Un **buscador** (o un filtro) que lance la petición a la API.
- Un **listado** (`LazyColumn` o `LazyVerticalGrid`) con al menos **una imagen** (cargada con Coil) y **dos datos** de cada elemento.
- Un **indicador de carga** mientras se espera la respuesta y un **mensaje de error** si la petición falla o no hay resultados.
- En la parte inferior, una **foto tuya** tipo carné y tu **nombre y apellido**.

**Pantalla de detalle**

- Al pulsar un elemento se **navega** a su detalle, que muestra **toda la información relevante**. Si la API tiene un endpoint por elemento, haz una **nueva petición** desde el detalle.
- Una flecha en la `TopAppBar` para **volver**.

Requisitos técnicos:

- **Retrofit** con el conversor de Gson (o kotlinx.serialization) y *data classes* para el JSON, usando `@SerializedName` cuando el nombre del campo no siga las convenciones de Kotlin.
- Una **interfaz de servicio** con `@GET`, `@Query` o `@Path` y un **objeto factoría** que cree el servicio.
- **ViewModel** con **`StateFlow`**; las peticiones se lanzan con `viewModelScope.launch` y se capturan las excepciones.
- **Permiso `INTERNET`** en el manifiesto.
- Tema de Material 3 **personalizado**.

::: details Ampliaciones opcionales
- **Paginación**: un botón al final de la lista que cargue la siguiente página de resultados.
- **Favoritos persistentes**: guarda los elementos favoritos en **Room** (UP07) y muéstralos aunque no haya conexión.
:::

## Entrega

Se entrega la **actividad 2**:

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques el código de la **capa de red** (data classes, servicio, factoría y ViewModel) y muestres la ejecución, incluido el caso **sin conexión**.
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.e** Establece conexiones HTTP/HTTPS y accede a servicios web<br>*Retrofit, endpoints, JSON* | Las peticiones fallan o los datos no se convierten | Servicio Retrofit con *data classes* que obtiene y muestra los datos | Se usan correctamente `@Query`/`@Path` y se justifica qué campos del JSON se mapean | Capa de red limpia y reutilizable; detalle con petición propia |
| **RA2.j** Establece los permisos requeridos<br>*Manifiesto y conectividad* | Falta el permiso `INTERNET` | Permiso declarado; la app funciona con conexión | Se gestiona la falta de conexión sin cerrar la app | Mensajes claros y opción de reintentar |
| **RA2.a** Genera la estructura de clases necesaria<br>*MVVM, modelo, servicio* | Peticiones lanzadas desde la interfaz | ViewModel con `StateFlow` y peticiones en `viewModelScope` | Estados de carga, éxito y error modelados y explicados | Arquitectura coherente con el resto del módulo (navegación, tema, recursos) |
| **RA2.b** Utiliza las clases de interfaz y controles<br>*Listado, imágenes, detalle* | Faltan pantallas o elementos pedidos | Listado con imágenes (Coil), indicador de carga y detalle | Interfaz adaptada a los datos de la API (imágenes que no cargan, textos largos) | Interfaz cuidada, accesible y coherente con Material 3 |
| **RA2.i** Documenta el desarrollo<br>*Vídeo* | Vídeo ausente | Vídeo dentro de los límites | El vídeo explica la capa de red y las decisiones técnicas | Vídeo claro y bien estructurado; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
