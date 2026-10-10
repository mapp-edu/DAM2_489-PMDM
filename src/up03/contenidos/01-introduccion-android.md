---
layout: doc
title: 1. Activities, Compose y proyecto Android
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Activities, Jetpack Compose y proyecto Android

## 1.1. Escritorio frente a móvil

Hay una diferencia muy importante entre programar aplicaciones de escritorio y aplicaciones móviles:

- **Aplicación de escritorio**: el punto de entrada siempre es el mismo. Por ejemplo, un videojuego siempre muestra el menú principal al terminar de cargar.
- **Aplicación móvil**: el punto de entrada puede ser **cualquiera de sus pantallas**.
  - Al abrir la aplicación de correo, lo habitual es ver la bandeja de entrada.
  - Pero si en una web pulsas un enlace de contacto, se abre la aplicación de correo directamente en la pantalla de redactar un mensaje.

## 1.2. Activity

La clase **`Activity`** es un componente fundamental de una aplicación Android.

- Originalmente, cada **pantalla** de una aplicación se definía en una `Activity`.
- Se puede configurar una `Activity` como **punto de entrada** de la aplicación.
- A medida que el usuario navega, sale y vuelve a la aplicación, sus *activities* pasan por distintos **estados** de su **ciclo de vida** (*lifecycle*).

![Pantallas de una aplicación de mensajería, cada una en una Activity: chats, ajustes, estados y conversación](/img/contenidos/up03/up03-1-p04-1.webp)
*Cada pantalla, una Activity: chats, ajustes, estados y conversación*

::: info-box Una sola Activity con Compose
Con Jetpack Compose lo habitual es tener **una única `Activity`** y cambiar de pantalla mediante la **navegación** entre funciones *composable* (lo verás en la UP05).
:::

## 1.3. Ciclo de vida de una Activity

![Ciclo de vida de una Activity: onCreate, onStart, onResume, onPause, onStop, onRestart y onDestroy](/img/contenidos/up03/up03-1-p05-1.webp)

La clase `Activity` proporciona una serie de **funciones de retorno** (*callbacks*) que avisan de cada cambio de estado, y que puedes sobrescribir para actuar en cada uno:

| Función | Cuándo se llama |
|:---|:---|
| `onCreate()` | Al crear la *activity*. Aquí se define la interfaz. |
| `onStart()` | Cuando la *activity* se hace visible. |
| `onResume()` | Cuando pasa a primer plano y el usuario puede interactuar con ella. |
| `onPause()` | Cuando otra *activity* pasa a primer plano. |
| `onStop()` | Cuando deja de ser visible. |
| `onRestart()` | Cuando vuelve a mostrarse tras haberse detenido. |
| `onDestroy()` | Antes de destruirse. |

::: danger-box ¡Ojo! La Activity se recrea
Android tiene un comportamiento peculiar: una *activity* activa **se destruye y se vuelve a crear** cuando:

- Se cambia la **orientación** del dispositivo.
- Se cambia entre los modos **claro y oscuro**.
- Se cambia la **configuración** del dispositivo (por ejemplo, el idioma).

Tenerlo en cuenta es muy importante: si no guardas el estado, el usuario lo perderá al girar el móvil.
:::

## 1.4. Programación imperativa frente a declarativa

Antes de empezar a desarrollar hay que comprender dos paradigmas:

- **Programación imperativa**: se indica **qué** se quiere y **cómo** hacerlo. Es la programación «típica» con `if`, `while`, `for`…
  > «Hemos visto que hay una mesa libre para dos personas justo al lado de la barra; nos gustaría sentarnos a cenar en ella y primero se sentará mi amigo y luego me sentaré yo».
- **Programación declarativa**: se indica **qué** se quiere. Por ejemplo, al usar SQL.
  > «Mesa para dos, por favor».

Kotlin tiene características que lo acercan a la programación declarativa. Por ejemplo, para transformar una lista en otra:

::: tabs

== Imperativa (estilo Java)

```kotlin
val names = listOf("rick", "morty", "summer")
val upperNames = mutableListOf<String>()
for (name in names) {
    upperNames.add(name.uppercase())
}
```

Un `for` recorre todos los elementos, se indica cómo se transforma cada uno y se almacena en la lista final.

== Declarativa (Kotlin)

```kotlin
val names = listOf("rick", "morty", "summer")
val upperNames = names.map { it.uppercase() }
```

Con `map` se dice que una lista se transforme en otra, sin indicar cómo crearla, ni cómo añadir los elementos, ni en qué orden.

:::

## 1.5. Desarrollo nativo en Android

Hoy hay dos formas de desarrollar aplicaciones nativas para Android:

- **Tradicional, con *Views***: programación **imperativa**. La interfaz se define en archivos **XML** con los elementos gráficos (*views*) y el código indica cómo se realiza cada acción.
- **Jetpack Compose**: programación **declarativa** de la interfaz. En el propio código Kotlin se declaran los elementos gráficos y su funcionalidad.

### ¿Por qué interfaces declarativas?

Con las interfaces declarativas, los elementos de la interfaz se conectan al **estado** de la aplicación: cuando el estado cambia, la interfaz **se vuelve a dibujar** (se **recompone**) para representarlo, sin que el programador tenga que actualizarla a mano. Además, el sistema está muy optimizado y solo se redibujan las partes afectadas por el cambio.

Ventajas:

- Menos código, más sencillo y fácil de leer.
- Evita clases intermedias que pueden introducir errores.
- Intuitivas: al engancharse al estado, la vista se encarga de todo.
- Muy rápidas y potentes.
- Vista previa de cualquier componente.

*Frameworks* como **React Native**, **Flutter** o **SwiftUI** también usan el paradigma de interfaces declarativas.

::: danger-box ¡Importante!
En este curso se trabajará con **Jetpack Compose**, por ser la tendencia actual del mercado en el desarrollo para Android. Se pueden combinar *views* XML y Compose en una misma aplicación; en el aula virtual tienes los apuntes de cursos anteriores con el enfoque tradicional.
:::

## 1.6. Jetpack Compose

Es un *toolkit* **100 % Kotlin** para crear interfaces de usuario declarativas en Android.

![Logotipo de Jetpack Compose](/img/contenidos/up03/up03-1-p13-1.webp)

- Se incorporó a Android Studio en la versión **Arctic Fox** (2021).
- Las aplicaciones con Compose funcionan en **Android 5.0 (API 21)** y superiores.
- Jetpack Compose es solo para Android, pero JetBrains ha creado **[Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/)**, que permite compartir interfaces entre Android, iOS, escritorio y web.

![Plataformas soportadas por Compose Multiplatform: iOS, Android, escritorio y web](/img/contenidos/up03/up03-1-p14-1.webp)

## 1.7. Crear un proyecto Android

La mejor forma de entender Android Studio es crear una aplicación sencilla. En esta unidad iremos explicando los conceptos mientras construimos un **contador de clics**:

![Aplicación contador de clics: texto «Has hecho clic 0 veces» y botón «¡PÚLSAME!»](/img/contenidos/up03/up03-1-p15-1.webp)

Para crear el proyecto:

- Desde la ventana de bienvenida, pulsa **New Project**.
- O, desde la ventana principal, **File → New → New Project…**

![Ventana de bienvenida con el botón New Project](/img/contenidos/up03/up03-1-p16-1.webp)

Elige **Phone and Tablet** y la plantilla **Empty Activity**, que usa Jetpack Compose. Las plantillas con *Views* en el nombre usan la programación tradicional con XML.

![Selección de la plantilla Empty Activity](/img/contenidos/up03/up03-1-p17-1.webp)

Rellena las opciones del proyecto:

![Configuración del nuevo proyecto](/img/contenidos/up03/up03-1-p18-1.webp)

- **Name**: un nombre significativo.
- **Package name**: debe ser único. Durante el curso seguiremos la estructura `com.tunombretuapellido.nombreproyecto`.
- **Save location**: el directorio que quieras.
- **Minimum SDK**: API 28 («Pie», Android 9.0).

### SDK mínimo

Elegir el SDK mínimo es un paso crucial al iniciar un proyecto:

- **Versión más baja posible** → soporte para el mayor número de dispositivos.
- **Versión más alta posible** → todas las características y funcionalidades.

En las actividades del curso usaremos la **API 28**, con un alcance aproximado del 93 % de los dispositivos. Pulsa **Help me choose** para ver la distribución actualizada.

![Distribución acumulativa de versiones de Android en el asistente](/img/contenidos/up03/up03-1-p20-1.webp)

Pulsa **Finish**: Android Studio creará el proyecto y descargará todos los componentes necesarios. Puedes seguir el progreso en la parte inferior derecha.

::: warning-box Paciencia
No interactúes con Android Studio hasta que termine **por completo** la creación del proyecto (sincronización de Gradle incluida).
:::

![Proyecto recién creado en Android Studio](/img/contenidos/up03/up03-1-p22-1.webp)

::: tip-box Práctica
Realiza la [Práctica A — Crear el proyecto ClickCounter](../ejercicios/#guiada-a).
:::

## 1.8. Estructura de un proyecto Android

Según la vista que elijas en el panel del proyecto (**Android**, **Project**…), encontrarás los archivos en un lugar u otro.

![Panel del proyecto en la vista Android](/img/contenidos/up03/up03-1-p24-1.webp)

![Correspondencia entre la vista Android y la vista Project](/img/contenidos/up03/up03-1-p25-1.webp)

### `AndroidManifest.xml`

| Vista | Ruta |
|:---|:---|
| Android | `manifests/AndroidManifest.xml` |
| Project | `app/src/main/AndroidManifest.xml` |

Describe las características fundamentales de la aplicación y sus componentes: las *activities* que tiene y los **permisos** que necesita (cámara, contactos, internet…).

### Archivos `build.gradle.kts`

Android Studio usa **Gradle** para compilar y construir la aplicación. Hay un archivo `build.gradle.kts` para todo el proyecto y **otro por cada módulo**. Normalmente nos interesa el del módulo `app`, que contiene la configuración y las dependencias de la aplicación:

| Vista | Ruta |
|:---|:---|
| Android | `Gradle Scripts/build.gradle.kts (Module :app)` |
| Project | `app/build.gradle.kts` |

| Propiedad | Significado |
|:---|:---|
| `compileSdk` | Versión del SDK con la que se compila (por defecto, la más reciente instalada). |
| `applicationId` | Identificador completo de la aplicación (el paquete). |
| `minSdk` | Versión mínima que admite la aplicación (la elegida al crear el proyecto). |
| `targetSdk` | Versión más alta con la que se ha probado la aplicación. |
| `dependencies` | Librerías que usa la aplicación. |

```kotlin [build.gradle.kts (Module :app)]
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
```

### Catálogo de versiones: `libs.versions.toml`

En las versiones actuales de Android Studio, las dependencias se declaran primero en el **catálogo de versiones** `gradle/libs.versions.toml` y luego se usan en `build.gradle.kts` como `libs.…`:

```toml [libs.versions.toml]
[versions]
agp = "8.7.2"
kotlin = "2.0.0"
coreKtx = "1.10.1"
lifecycleRuntimeKtx = "2.6.1"
activityCompose = "1.8.0"
composeBom = "2024.04.01"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
```

Las últimas versiones de las librerías de Google se consultan en el [repositorio Maven de Google](https://maven.google.com/web/index.html); para otras dependencias, en su documentación oficial.

::: info-box Sincroniza
Cada vez que modifiques un archivo de Gradle, pulsa **Sync Now** para que Android Studio aplique los cambios.
:::

### `MainActivity.kt` y `ui/theme`

| Elemento | Vista Android | Contenido |
|:---|:---|:---|
| `MainActivity.kt` | `java/com.tunombre.proyecto/MainActivity.kt` | Comportamiento de la pantalla principal. |
| `ui.theme` | `java/com.tunombre.proyecto/ui.theme` | Archivos que configuran el **tema** de la aplicación (colores, tipografía…), basado en **Material Design**. |

### Recursos: carpeta `res`

| Carpeta | Contenido |
|:---|:---|
| `res` | Recursos de la aplicación. |
| `res/drawable` | Imágenes. |
| `res/mipmap` | Icono de la aplicación en distintas densidades de pantalla. |
| `res/values` | Cadenas (`strings.xml`), colores (`colors.xml`) y temas (`themes.xml`). |

En la vista Project, la carpeta `mipmap` se divide en una por densidad (`mipmap-hdpi`, `mipmap-xhdpi`…); en la vista Android se agrupan por nombre y se muestra la densidad junto a cada archivo:

![Carpetas mipmap en la vista Project y en la vista Android](/img/contenidos/up03/up03-1-p34-2.webp)

| Calificador | Densidad |
|:---|:---:|
| `mdpi` | 160 dpi |
| `hdpi` | 240 dpi |
| `xhdpi` | 320 dpi |
| `xxhdpi` | 480 dpi |
| `xxxhdpi` | 640 dpi |

## 1.9. Icono de la aplicación

El icono de la aplicación aparece en el menú de aplicaciones, en la pantalla principal (si se añade), durante el inicio (*splash screen*) y en la lista de aplicaciones abiertas. Puede ser una **imagen vectorial** (XML) o un **mapa de bits** (JPG, PNG…). Si es vectorial, conviene usar los [iconos adaptables](https://developer.android.com/develop/ui/views/launch/icon_design_adaptive?hl=es-419) de Android, que admiten efectos visuales.

Los pasos son los mismos para ambos tipos:

1. En la vista **Project**, elimina `ic_launcher_background.xml`, `ic_launcher_foreground.xml` y todas las carpetas cuyo nombre contenga `mipmap`.

   ![Archivos y carpetas del icono por defecto que hay que eliminar](/img/contenidos/up03/up03-1-p36-1.webp)

2. Copia la imagen nueva en `res/drawable`.
3. Abre el **Resource Manager**, pulsa **+** → **Image Asset**, deja la configuración por defecto y selecciona la imagen nueva en **Path**. Si hace falta, ajusta el escalado.

   ![Resource Manager: Image Asset](/img/contenidos/up03/up03-1-p37-1.webp)

   ![Configuración del icono con la imagen nueva](/img/contenidos/up03/up03-1-p38-1.webp)

4. En el último paso verás todos los archivos que se van a crear, que coinciden con los que eliminaste.

   ![Confirmación de los archivos del icono](/img/contenidos/up03/up03-1-p39-1.webp)

A partir de ese momento la aplicación tendrá su nuevo icono:

![El nuevo icono en el menú de aplicaciones, en la pantalla principal, en la splash screen y en las aplicaciones recientes](/img/contenidos/up03/up03-1-p40-1.webp)

## 1.10. Configuración de Android Studio

Jetpack Compose está totalmente integrado en Android Studio, pero algunos ajustes (**File → Settings**, `Ctrl + Alt + S`) mejoran la productividad:

::: details Barras laterales siempre visibles
Con una pantalla ancha puede interesarte tener siempre visibles los paneles **Project** y **Running Devices** (emulador): **Appearance & Behavior → Appearance → Tool Windows → Widescreen tool window layout**.

![Ajuste de barras laterales siempre visibles](/img/contenidos/up03/up03-1-p42-1.webp)
:::

::: details Formatear el código al estilo de Compose
`Ctrl + Alt + L` formatea el código según la guía de estilo de Kotlin. Para que las llamadas con muchos parámetros de Compose se lean mejor, en **Editor → Code Style → Kotlin → Wrapping and Braces** marca **Chop down if long** en las declaraciones y llamadas de funciones.

![Opciones de formato para Kotlin](/img/contenidos/up03/up03-1-p44-1.webp)
:::

::: details Pistas de código
En **Editor → Inlay Hints** activa las pistas de nombres de parámetros, tipos y lambdas (contexto `it`/`this` y valores de retorno).

![Ajustes de Inlay Hints](/img/contenidos/up03/up03-1-p45-1.webp)
:::

::: details Live Edit
Para que los cambios en el código actualicen automáticamente la aplicación en el emulador, activa **Editor → Live Edit → Push Edits Automatically**.

![Ajuste de Live Edit](/img/contenidos/up03/up03-1-p46-1.webp)
:::

::: details Plugins
Android Studio admite los mismos *plugins* que IntelliJ IDEA, como **Key Promoter X**.

![Instalación de plugins en Android Studio](/img/contenidos/up03/up03-1-p47-1.webp)
:::

### Dispositivo virtual del curso

Android Studio trae configurado un dispositivo genérico (*Medium Phone*). Para el curso lo eliminaremos y crearemos un **Pixel 9a** desde el **Device Manager**.

![Eliminar el dispositivo genérico y crear uno nuevo en el Device Manager](/img/contenidos/up03/up03-1-p48-1.webp)

::: tip-box Práctica
Realiza la [Práctica B — Dispositivo virtual del curso](../ejercicios/#guiada-b).
:::
