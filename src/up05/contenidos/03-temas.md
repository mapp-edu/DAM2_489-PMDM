---
layout: doc
title: 3. Temas de Material 3
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 3. Temas de Material 3

## 3.1. Del XML al tema de Compose

En la UP03 viste cómo centralizar valores y crear una paleta de colores en `res/values/colors.xml`. Aunque funciona, **no sigue las bases de Material Design** y se debe evitar. En esa misma carpeta está `themes.xml`, que indica el **tema base** sobre el que se construye el tema de Jetpack Compose.

Al crear un proyecto, Android Studio genera un **tema de Jetpack Compose propio basado en Material Design** que se puede personalizar. Sus archivos están en **`ui/theme`** y, a partir de ahora, **el tema de la aplicación se configura en ellos** (no en `colors.xml`).

El tema se divide en tres **subsistemas**:

| Archivo | Subsistema |
|:---|:---|
| `Color.kt` | Paleta de colores. |
| `Type.kt` | Tipografía. |
| `Shape.kt` | Formas (no se crea por defecto). |

`Theme.kt` los reúne. Cualquier cambio en ellos se refleja automáticamente en todos los componentes que implementan Material 3.

::: tabs

== Theme.kt

```kotlin [ui/theme/Theme.kt]
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PruebasTemasAnimacionesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

== Color.kt

```kotlin [ui/theme/Color.kt]
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
```

== Type.kt

```kotlin [ui/theme/Type.kt]
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(…),
    labelSmall = TextStyle(…)
    */
)
```

:::

En `Theme.kt` se configuran por defecto dos opciones:

- **`darkTheme`**: consulta si el dispositivo está en **modo oscuro** y, en ese caso, usa ese esquema de colores. Si la pones a `false`, la aplicación solo tendrá el esquema `LightColorScheme`.
- **`dynamicColor`**: disponible desde **Android 12**; adapta el esquema de colores al **fondo de pantalla** del dispositivo. Si la pones a `false`, no se hace esta adaptación.

## 3.2. Modificar el tema

Para modificar el tema basta con **añadir o cambiar los valores** necesarios en los archivos de `ui/theme`, consultando la documentación para saber qué parámetros usar. También puedes **generar** su contenido con herramientas, que es siempre **lo más recomendable** para que el tema sea coherente y no contenga errores.

`Theme.kt` usa los colores de `Color.kt` con las funciones **`darkColorScheme`** y **`lightColorScheme`**, que tienen muchos parámetros para definir cada color de la aplicación (`primary`, `onPrimary`, `primaryContainer`, `onPrimaryContainer`, `inversePrimary`, `secondary`…).

## 3.3. Paleta de colores

La paleta de Material 3 se compone de **6 colores clave**: los 5 del tema (3 de acento y 2 neutros) más uno para los **errores**.

![Colores clave de Material 3: Primary, Secondary y Tertiary como colores de acento, Neutral y Neutral Variant como neutros, y Error](/img/contenidos/up05/up05-2-colores-clave.webp)

Cada color se define en **13 tonos**, desde el 0 (negro) hasta el 100 (blanco):

![Tonos del color primario del 0 al 100](/img/contenidos/up05/up05-2-tonos.webp)

Para cada color clave se eligen **4 tonalidades**, que en total generan **24 colores** con un nombre y un número:

![Roles de los colores de acento: Primary, On Primary, Primary Container y On Primary Container, y lo mismo para Secondary y Tertiary](/img/contenidos/up05/up05-2-roles-acento.webp)

![Roles de error: Error, On Error, Error Container y On Error Container](/img/contenidos/up05/up05-2-roles-error.webp)

![Roles neutros: Background, On Background, Surface, On Surface, Surface Variant, On Surface Variant y Outline](/img/contenidos/up05/up05-2-roles-neutros.webp)

Significado de los nombres:

| Nombre | Uso |
|:---|:---|
| **Surface** | Fondos y áreas grandes de poca relevancia. |
| **Primary, Secondary, Tertiary** | Colores de acento para destacar (o restar importancia a) elementos en primer plano. |
| **Container** | Relleno de elementos en primer plano, como botones. No se deben usar para texto ni iconos. |
| **On** | Color del texto o los iconos que van **encima** de su color pareja. Por ejemplo, `onPrimary` para el texto sobre un fondo `primary`. |
| **Variant** | Alternativa con menos énfasis que su pareja. Por ejemplo, `outlineVariant` es una versión menos marcada de `outline`. |

Tienes más información sobre cuándo usar cada color en la documentación de Material: [Color roles](https://m3.material.io/styles/color/roles).

### Usar los colores del tema

Para usar los colores del tema (el de por defecto o el personalizado) se usa la clase **`MaterialTheme`**:

```kotlin
Text(
    text = "Hola Rick!",
    color = MaterialTheme.colorScheme.error,
    modifier = Modifier
        .padding(10.dp)
        .background(MaterialTheme.colorScheme.errorContainer)
        .padding(10.dp)
)
```

### Crear la paleta con Material Theme Builder

Elegir una paleta de colores es algo primordial que requiere un estudio exhaustivo. Con suficientes conocimientos de diseño se puede crear a mano; si no, usa la herramienta oficial **[Material Theme Builder](https://material-foundation.github.io/material-theme-builder/)**: eliges los colores clave y genera automáticamente todos los tonos, mostrando cómo se verá la aplicación.

![Material Theme Builder: selección de los colores clave a la izquierda y vista previa de la aplicación](/img/contenidos/up05/up05-2-theme-builder.webp)

1. Elige los **colores clave** (Primary, Secondary, Tertiary, Error, Neutral y Neutral Variant) o genera la paleta a partir de una imagen. Puedes previsualizar los modos claro y oscuro y añadir colores extendidos.
2. Pulsa **Pick your fonts** para elegir las fuentes de títulos y de cuerpo (puedes usar Google Fonts).
3. Pulsa **Export theme** y elige **Jetpack Compose (Theme.kt)**.

   ![Menú de exportación del tema con la opción Jetpack Compose (Theme.kt)](/img/contenidos/up05/up05-2-exportar-tema.webp){width=380px}

Obtendrás un archivo `material-theme.zip` con:

| Archivo | Qué hacer |
|:---|:---|
| `Color.kt` | Sustituir el del proyecto, indicando el `package` correcto. |
| `Theme.kt` | Sustituir el del proyecto, indicando el `package` correcto y, al final del archivo, el **nombre correcto del tema** del proyecto. |
| `Type.kt` | Sustituir el del proyecto, indicando el `package` correcto. Si elegiste fuentes, copia el contenido de la carpeta `res` del zip a la carpeta `res` del proyecto e importa las dependencias. |

### Dynamic Color

Una característica muy importante de Material 3 es **Dynamic Color**, un algoritmo que genera colores **derivados del fondo de pantalla** del dispositivo. Los proyectos de Android Studio con Jetpack Compose lo habilitan por defecto si la versión de Android lo permite, como se ve en la rama `dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S` de `Theme.kt`.

Si quieres desactivarlo, quita las líneas que lo habilitan (o pon `dynamicColor = false`). Ten en cuenta que, si lo dejas activado, tu paleta personalizada **no se verá** en dispositivos con Android 12 o superior.

## 3.4. Tipografía

Material 3 define **5 roles de tipografía**, cada uno con **3 tamaños** (*Large*, *Medium* y *Small*):

| Rol | Uso |
|:---|:---|
| **Display** | Textos más grandes de la pantalla: números o textos cortos e importantes. |
| **Headline** | Textos de alto énfasis en pantallas pequeñas. |
| **Title** | Textos de énfasis medio, relativamente cortos. |
| **Body** | Textos largos. |
| **Label** | Textos pequeños de componentes, como botones o pestañas. |

Cada uno de los [5 roles tiene una funcionalidad específica](https://m3.material.io/styles/typography/applying-type) y puedes consultar [las características detalladas de cada rol](https://m3.material.io/styles/typography/type-scale-tokens) (fuente, peso, tamaño, interlineado y espaciado). Por ejemplo, *Display Large* usa Roboto 400 a 57 pt con 64 pt de interlineado.

Igual que con los colores, para usar las tipografías se usa **`MaterialTheme`**:

```kotlin
Text(
    text = "Hola Rick!",
    style = MaterialTheme.typography.bodySmall,
    color = MaterialTheme.colorScheme.error,
    modifier = Modifier
        .padding(10.dp)
        .background(MaterialTheme.colorScheme.errorContainer)
        .padding(10.dp)
)
```

## 3.5. Formas

Material 3 define una serie de **formas** que se aplican a los componentes. Ya viste que `Button`, `TextField`, `Card`… se dibujan con una forma a su alrededor: por defecto es **redondeada**, pero se puede personalizar.

![Escala de formas de Material 3: None, Extra small, Small, Medium, Large, Extra large y Full, en versión redondeada y cortada](/img/contenidos/up05/up05-2-formas.webp){width=320px}

Las formas pueden ser **redondeadas** (`RoundedCornerShape`) o **angulares** (`CutCornerShape`), y **simétricas o no**. Se usan con `MaterialTheme.shapes`:

```kotlin
Card(
    shape = MaterialTheme.shapes.large
) {
    Text(
        text = "Hola Rick!",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(10.dp)
    )
}
```

### Personalizar las formas: Shape.kt

Android Studio no crea por defecto el archivo de formas: créalo a mano en la carpeta `theme`. Hay distintos constructores de `RoundedCornerShape` y `CutCornerShape` (tamaño común, por esquina, en porcentaje…):

```kotlin [ui/theme/Shape.kt]
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraSmall = CutCornerShape(4.dp),
    small = CutCornerShape(
        topStart = 5.dp,
        topEnd = 5.dp,
        bottomEnd = 10.dp,
        bottomStart = 10.dp
    ),
    medium = CutCornerShape(
        topStartPercent = 20,
        topEndPercent = 20,
        bottomStartPercent = 5,
        bottomEndPercent = 5
    ),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)
```

Después, indica en `Theme.kt` que el tema use esas formas. Ahí se ve cómo se cargan **los tres subsistemas**:

```kotlin
MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    shapes = shapes,
    content = content
)
```

## 3.6. Modificar el tema en línea

A veces necesitas una **variación** de un valor del tema **sin modificar el tema ni añadir valores nuevos**. La función **`copy`** hace una «copia temporal» de la característica con los parámetros que quieras cambiar. Según la característica (`colorScheme`, `typography`, `shapes`) admite unos parámetros u otros:

```kotlin
Text(
    text = "Hola Rick!",
    style = MaterialTheme.typography.bodyLarge.copy(
        shadow = Shadow(
            offset = Offset(5f, 5f),
            blurRadius = 2f,
            color = Color(0xFFFF8000)
        )
    )
)
```

## 3.7. Barra de estado y barra de navegación

Android muestra por defecto dos barras: la **barra de estado** (arriba) y la **barra de navegación** (abajo). Su estilo depende de los colores del tema, pero como las ofrece el sistema operativo, para cambiarlas de forma independiente hay que seguir un procedimiento específico.

Por defecto, Jetpack Compose las hace **transparentes** con la función **`enableEdgeToEdge`**, que se llama en el `onCreate` de `MainActivity`:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebasTemasAnimacionesTheme {
                Navigation()
            }
        }
    }
}
```

### Colores de las barras

`enableEdgeToEdge` es reciente y probablemente cambie en el futuro. Admite los parámetros **`statusBarStyle`** y **`navigationBarStyle`** para cambiar el color de las barras y el estilo de sus iconos:

```kotlin
enableEdgeToEdge(
    statusBarStyle = SystemBarStyle.light(Color.Red.toArgb(), Color.Blue.toArgb()),
    navigationBarStyle = SystemBarStyle.dark(Color.Green.toArgb()),
)
```

| Estilo | Significado | Parámetros |
|:---|:---|:---|
| `SystemBarStyle.light` | La barra es **clara**, así que los iconos se pintan **oscuros**. | El color de la barra y un color oscuro alternativo por si el dispositivo no tiene modo claro. |
| `SystemBarStyle.dark` | La barra es **oscura**, así que los iconos se pintan **claros**. | El color de la barra. |

### Ocultar las barras

Puedes ocultar la barra de estado, la de navegación o ambas añadiendo en el `onCreate` de `MainActivity`:

```kotlin
// Controla la visibilidad de las barras del sistema
val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

// Oculta las dos barras a la vez
windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

// Oculta la barra de estado (superior)
windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
// Oculta la barra de navegación (inferior)
windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
```

::: tip-box Practica
Ya puedes hacer la [Actividad 5 — Coloreando](../ejercicios/#actividad-5).
:::
