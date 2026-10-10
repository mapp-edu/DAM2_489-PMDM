---
layout: doc
title: Actividad 2 — Hello there!
sidebar: true
outline: [2, 3]
aside: true
---

# Actividad 2 — Hello there!

Para comprobar que todo funciona vas a crear una aplicación sencilla: **Hello there**.

## Crear el proyecto

1. Al abrir Android Studio, elige **New Project**.

   ![Pantalla de bienvenida: New Project](/img/contenidos/up01/act1-p09-1.webp)

2. Android Studio permite crear aplicaciones para móviles, tabletas, TV, relojes, vehículos… En este curso trabajaremos con **Phone and Tablet**. Selecciona la plantilla **Empty Activity**.

   ![Selección de la plantilla Empty Activity](/img/contenidos/up01/act1-p09-2.webp)

3. Configura el nombre del proyecto, el directorio, el lenguaje y el SDK mínimo:

   - **Name:** `Hello there`
   - **Package name:** `com.tunombretuapellido.hellothere`
   - **Language:** Kotlin
   - **Minimum SDK:** API 28 («Pie», Android 9.0)

::: danger-box ¡Importante! Nombre del paquete
A partir de ahora, **en todos los proyectos** el paquete debe seguir la estructura `com.tunombretuapellido.nombreproyecto`, **todo en minúsculas**. Por ejemplo: `com.alextorres.hellothere`.
:::

::: info-box ¿Qué SDK mínimo elijo?
Si eliges el SDK más reciente tendrás más funcionalidades disponibles, pero la aplicación solo funcionará en unos pocos dispositivos. Consulta la [distribución acumulativa](../contenidos/03-android#distribucion-acumulativa) antes de decidir.
:::

4. Pulsa **Finish**. La creación del proyecto tarda unos segundos.

   ![Configuración del proyecto y creación](/img/contenidos/up01/act1-p10-1.webp)

## Explorar el proyecto

Al crear el proyecto, Android Studio muestra toda su información. Tómate unos minutos para investigar las distintas secciones.

![Android Studio con el proyecto recién creado](/img/contenidos/up01/act1-p10-2.webp)

Por defecto, el panel del proyecto usa la vista **Android**, que muestra una estructura virtual de archivos. Para ver la estructura real en disco, elige la vista **Project**.

![Cambio entre las vistas Android y Project](/img/contenidos/up01/act1-p10-3.webp)

Estos son los directorios y archivos más importantes, todos dentro del directorio `app` (algunos no se crean hasta que se compila la aplicación):

| Ruta | Contenido |
|:---|:---|
| `build/` | Archivos generados al compilar (DEX, recursos procesados…). **No se toca**: se actualiza con cada cambio. |
| `libs/` | Librerías locales que usa el proyecto. |
| `src/androidTest/` | Pruebas instrumentadas que se ejecutan en el dispositivo. |
| `src/main/java/` | Código fuente de la aplicación (también el código Kotlin). |
| `src/main/java/.../MainActivity.kt` | Comportamiento de la pantalla principal. |
| `src/main/res/` | Recursos de la aplicación (cadenas, imágenes, temas…). |
| `src/main/AndroidManifest.xml` | Configuración de la aplicación. |
| `.gitignore` | Archivos y directorios que no se suben al control de versiones. |
| `build.gradle.kts` | Configuración de Gradle, la herramienta que compila la aplicación. |
| `proguard-rules.pro` | Reglas de la herramienta que optimiza y ofusca el código. |

Durante el curso usaremos sobre todo la vista **Android**; se indicará cuándo conviene cambiar de vista.

## Modificar el texto

Vas a modificar la pantalla para que muestre **Hello there!**.

1. Abre el archivo `MainActivity.kt` desde su pestaña o desde el panel del proyecto.

   ![Archivo MainActivity.kt en el panel del proyecto](/img/contenidos/up01/act1-p11-1.webp)

2. En la parte superior derecha puedes elegir entre ver el código (**Code**), una vista previa de la pantalla (**Design**) o ambas a la vez (**Split**).

   ![Botones Code, Split y Design](/img/contenidos/up01/act1-p12-1.webp)

3. La primera vez que eliges **Split** o **Design**, Android Studio indica que hay que construir el proyecto para mostrar la vista previa.

   ![Aviso para construir el proyecto](/img/contenidos/up01/act1-p12-2.webp)

4. Pulsa **Build & Refresh…** y, al terminar, verás la vista previa.

   ![Vista previa con el texto Hello Android!](/img/contenidos/up01/act1-p12-3.webp)

5. Busca en `MainActivity.kt` todas las apariciones de la palabra `Android` y sustitúyelas por `there`.

   ![Vista previa con el texto Hello there!](/img/contenidos/up01/act1-p13-1.webp)

Todavía no puedes ejecutar la aplicación: falta configurar un dispositivo en el que hacerlo. Eso es lo que harás en la siguiente actividad.

[Siguiente: Actividad 3 — Usando el emulador →](./03-emulador)
