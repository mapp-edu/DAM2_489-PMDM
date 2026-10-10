---
layout: doc
title: 3. Recursos, idiomas y depuración
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 3. Recursos, idiomas y depuración

## 3.1. Centralizar valores

Hasta ahora hemos escrito los textos y los tamaños directamente en el código (*hardcoded values*):

```kotlin
Text(
    text = "¡PÚLSAME!",
    fontSize = 30.sp,
    modifier = Modifier.padding(16.dp)
)
```

**No es una buena práctica**:

- ¿Qué pasa si decides que todos los botones tengan el mismo tamaño de texto y en el futuro quieres cambiarlo?
- ¿Qué haces si quieres que la aplicación esté en varios idiomas?

Android permite **centralizar valores** en archivos de recursos (algo parecido a crear constantes) y usarlos desde cualquier punto del proyecto. Así, los cambios y el mantenimiento son mucho más sencillos.

::: danger-box Buena práctica obligatoria
Centraliza, **como mínimo, todas las cadenas de texto** de la aplicación en `strings.xml`.
:::

Android Studio crea por defecto dos archivos en `res/values`:

| Archivo | Contenido | Formato |
|:---|:---|:---|
| `colors.xml` | Colores | `<color name="NOMBRE">#CODIGO</color>` |
| `strings.xml` | Cadenas | `<string name="NOMBRE">CADENA</string>` |

```xml [colors.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

```xml [strings.xml]
<resources>
    <string name="app_name">Contador de clics</string>
</resources>
```

El código de color usa dos dígitos hexadecimales por componente: `#RRGGBB` (sin transparencia) o `#AARRGGBB` (con canal *alpha* de transparencia).

::: tip-box Selector de color
En `colors.xml`, junto al número de línea aparece un cuadro con el color. Al hacer clic en él se abre un selector para elegirlo de forma visual.

![Selector de color de Android Studio](/img/contenidos/up03/up03-3-p06-1.webp)
:::

### Archivo de dimensiones

Para centralizar medidas y tamaños, crea un archivo `dimens.xml`: clic derecho sobre la carpeta `values` → **New → Values Resource File**, con nombre `dimens.xml` y el resto de opciones por defecto.

![Creación del archivo dimens.xml](/img/contenidos/up03/up03-3-p07-2.webp)

```xml [dimens.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="button_text">25sp</dimen>
</resources>
```

## 3.2. Usar los valores centralizados

Android Studio genera automáticamente la clase **`R`** con todos los recursos de la carpeta `res`. A través de ella se accede a cada valor:

```kotlin
R.string.counter_text
R.color.orange
R.dimen.button_text
```

Y en Compose se leen con estas funciones:

| Recurso | Función |
|:---|:---|
| Cadena | `stringResource(id = R.string.…)` |
| Dimensión | `dimensionResource(id = R.dimen.…)` (si es `sp`, añade `.value.sp`) |
| Color | `colorResource(id = R.color.…)` |

::: tabs

== Antes

```kotlin
Text(
    text = "¡PÚLSAME!",
    fontSize = 30.sp,
    modifier = Modifier.padding(16.dp)
)
```

== Después

```kotlin
Text(
    text = stringResource(id = R.string.clickme),
    fontSize = dimensionResource(id = R.dimen.button_text).value.sp,
    modifier = Modifier.padding(dimensionResource(id = R.dimen.button_padding)),
    color = colorResource(id = R.color.teal_200)
)
```

:::

## 3.3. Añadir valores centralizados

Hay dos formas:

**1. Escribirlos directamente en el XML**:

```xml
<resources>
    <string name="app_name">Contador de clics</string>
    <string name="greeting">Welcome to the application</string>
    <string name="clickme">¡PÚLSAME!</string>
</resources>

<resources>
    <dimen name="button_text">30sp</dimen>
    <dimen name="button_padding">16dp</dimen>
</resources>
```

**2. Desde el código Kotlin.** Escribe la referencia al recurso aunque todavía no exista; Android Studio la marcará como error:

```kotlin
Text(
    text = stringResource(id = R.string.reset)
)
```

Sitúa el cursor sobre el error y pulsa `Alt + Intro` (o pasa el ratón por encima) para ver la ayuda contextual, y elige **Create string value resource 'reset'**. Escribe el valor y pulsa **OK**: quedará añadido a `strings.xml`.

![Ayuda contextual para crear el recurso de cadena](/img/contenidos/up03/up03-3-p12-3.webp)

![Diálogo New String Value Resource](/img/contenidos/up03/up03-3-p13-1.webp)

```xml
<resources>
    <string name="app_name">Contador de clics</string>
    <string name="greeting">Welcome to the application</string>
    <string name="clickme">¡PÚLSAME!</string>
    <string name="reset">Resetear la cuenta de clics</string>
</resources>
```

## 3.4. Aplicaciones multiidioma

Para que una aplicación esté en varios idiomas basta con crear **un archivo `strings.xml` por idioma**:

- Si solo hay un `strings.xml` (como al crear el proyecto), se usa **siempre**, sea cual sea el idioma del dispositivo.
- Si el dispositivo está en un idioma para el que no hay `strings.xml`, se usa el **archivo por defecto**.
- Si el dispositivo tiene configurados uno o varios idiomas con `strings.xml`, se usa **el primero** de su lista de preferencias.
- Si a un archivo le falta alguna cadena, esa cadena se muestra en el **idioma por defecto**.

::: info-box Idioma por defecto
Lo más habitual es que el `strings.xml` por defecto (el que se crea con el proyecto) esté en **inglés**.
:::

### Crear el archivo de un idioma

1. Clic derecho sobre `values` → **New → Values Resource File**.
2. Nombre del archivo: `strings`. Selecciona **Locale** y pulsa `>>`.

   ![Selección del calificador Locale](/img/contenidos/up03/up03-3-p16-1.webp)

3. Elige el idioma y, si quieres, la región. Pulsa **OK**.

   ![Selección del idioma español](/img/contenidos/up03/up03-3-p17-1.webp)

4. En la vista Android verás los dos archivos agrupados, con el idioma entre paréntesis. En disco, el nuevo está en la carpeta `values-es`.

   ![strings.xml y strings.xml (es) agrupados en la vista Android](/img/contenidos/up03/up03-3-p18-1.webp)

5. Copia el contenido del archivo por defecto y **traduce** los valores:

::: tabs

== strings.xml (por defecto, inglés)

```xml
<resources>
    <string name="app_name">Clicks counter</string>
    <string name="counter_text">You have clicked 0 times</string>
    <string name="clickme">CLICK ME!</string>
</resources>
```

== strings.xml (es)

```xml
<resources>
    <string name="app_name">Contador de clics</string>
    <string name="counter_text">Has hecho clic 0 veces</string>
    <string name="clickme">¡PÚLSAME!</string>
</resources>
```

:::

### Editor de traducciones

Desde cualquier `strings.xml`, pulsa **Open editor** para abrir el **Translations Editor**: muestra una columna por idioma y permite añadir cadenas e idiomas.

![Translations Editor con las columnas Default Value y Spanish (es)](/img/contenidos/up03/up03-3-p19-2.webp)

- Si una cadena no debe traducirse (por ejemplo, el nombre de la aplicación), marca **Untranslatable**.
- Cuando hay varios idiomas, al añadir una cadena a un archivo Android Studio avisa de que falta traducirla en los demás.

![Aviso de cadena sin traducir](/img/contenidos/up03/up03-3-p20-2.webp)

Para comprobar el resultado puedes usar previsualizaciones con `locale` o cambiar el idioma del emulador en **Ajustes → Sistema → Idiomas**:

![La aplicación en español y en inglés según el idioma del emulador](/img/contenidos/up03/up03-3-p22-1.webp)

### Cadenas con parámetros

El contador usa una cadena con una variable:

```kotlin
Text(
    text = "Has hecho clic $times veces",
    fontSize = 25.sp
)
```

Al centralizarla, hay que indicar que recibe un **parámetro** con el formato **`%NºParámetro$Tipo`**, donde el tipo es `s` (cadena) o `d` (número entero):

```xml
<string name="counter_text">Has hecho clic %1$d veces</string>
<string name="user_points">Hola %1$s, has conseguido %2$d puntos</string>
```

::: info-box Tipo `s`
En las versiones actuales puedes usar siempre `s`: Android convierte automáticamente el dato a `String`.
:::

Android Studio ayuda a crearlas: con el cursor sobre la cadena *hardcoded*, pulsa `Alt + Intro` → **Extract string resource**. Reconoce las variables como parámetros y, si hay varios `strings.xml`, permite añadir el valor a todos (luego solo tendrás que traducirlo).

![Extract string resource con parámetros](/img/contenidos/up03/up03-3-p27-1.webp)

Desde Kotlin, se pasan los parámetros a `stringResource`:

```kotlin
Text(
    text = stringResource(R.string.counter_text, times),
    fontSize = 25.sp
)

var userPoints = stringResource(R.string.user_points, name, points)
```

::: warning-box ¡Importante!
La traducción tiene un gran inconveniente: **la misma frase no tiene la misma longitud en todos los idiomas**. Estudia bien la interfaz para que se vea de forma similar en todos los idiomas en los que distribuyas la aplicación.
:::

## 3.5. Orientación vertical y horizontal

Móviles y tabletas permiten girar la pantalla; relojes, televisores o automóviles normalmente no (algunos ni siquiera tienen acelerómetro).

Con las antiguas interfaces XML había que crear un diseño por orientación. **Con Jetpack Compose, los componentes ocupan la pantalla tal y como se definen**, por lo que la aplicación se ve correctamente sea cual sea el tamaño y la orientación. Más adelante verás cómo adaptar los componentes al tamaño de pantalla para mejorar la experiencia de usuario.

Se puede **bloquear la orientación** añadiendo a la `activity` del `AndroidManifest.xml` la propiedad `android:screenOrientation`:

```xml [AndroidManifest.xml]
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:screenOrientation="portrait"
    android:label="@string/app_name"
    android:theme="@style/Theme.ContadorDeClics">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

Valores posibles: `portrait`, `landscape`, `sensorPortrait`, `sensorLandscape`, `reversePortrait`, `fullSensor`, `locked`, `unspecified`…

::: warning-box Desaconsejado a partir de Android 16
Desde **Android 16** (API 36), en pantallas grandes (tabletas, plegables) el sistema **ignora** las restricciones de orientación y tamaño: las aplicaciones deben adaptarse a las dimensiones del dispositivo. Usa esta propiedad solo si está muy justificado.
:::

## 3.6. Archivos de recursos por configuración

Igual que hay un `strings.xml` por idioma, se pueden crear **archivos de recursos para otras configuraciones** del dispositivo. Por ejemplo, un `colors.xml` para la orientación vertical y otro para la horizontal. Con las interfaces XML esto tenía más sentido, pero sigue siendo útil.

Se crean igual: clic derecho sobre `values` → **New → Values Resource File**, eligiendo el **calificador** (orientación, modo noche, tamaño de pantalla…). Android Studio crea el archivo en la carpeta correspondiente (por ejemplo, `values-land`) y Android lo carga según la configuración del dispositivo.

![Creación de un archivo de recursos para la orientación horizontal (values-land)](/img/contenidos/up03/up03-3-p34-1.webp)

## 3.7. Depuración

Android Studio tiene un **modo de depuración** como el de cualquier IDE: puntos de ruptura, ejecución paso a paso, inspección de variables…

![Depuración en Android Studio con un punto de ruptura](/img/contenidos/up03/up03-3-p35-1.webp)

Para comprobaciones sencillas se usa la clase **`Log`**. Los mensajes aparecen en el panel **Logcat**, donde se pueden filtrar por etiqueta:

```kotlin
Log.i("ETIQUETA", "MENSAJE")
```

| Método | Nivel |
|:---|:---|
| `Log.v` | *Verbose* |
| `Log.d` | *Debug* |
| `Log.i` | *Info* |
| `Log.w` | *Warning* |
| `Log.e` | *Error* |

![Mensajes de Log en el panel Logcat](/img/contenidos/up03/up03-3-p36-1.webp)

## 3.8. Actualizaciones de Android Studio

Android Studio recibe actualizaciones de muchos componentes: el propio IDE, el SDK de Android, el JDK, los *plugins*, Gradle y las dependencias. A veces, tras una actualización, un proyecto deja de compilar hasta que se configura correctamente. El error aparecerá en las pestañas **Build**, **Problems** o en una notificación, según su origen.

::: details Error de versión de Java
Ve a **File → Settings → Build, Execution, Deployment → Build Tools → Gradle** y selecciona el **Gradle JDK** correcto.

![Selección del Gradle JDK](/img/contenidos/up03/up03-3-p38-1.webp)
:::

::: details Error de `appcompat`
Las aplicaciones con XML (o que mezclan XML y Compose, por ejemplo durante una migración) usan la librería `appcompat`. Tras algunas actualizaciones hay que cambiar su versión en `build.gradle.kts (Module :app)` o en `libs.versions.toml`:

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
```
:::

::: tip-box Práctica
Realiza la [Actividad 3 — Basket 3x3](../ejercicios/#actividad-3).
:::
