---
layout: doc
title: 2. Primera app con Jetpack Compose
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 2. Primera aplicación con Jetpack Compose: contador de clics

## 2.1. El proyecto inicial

Este es el contenido de `MainActivity.kt` en un proyecto recién creado con la plantilla **Empty Activity**:

```kotlin [MainActivity.kt]
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickCounterTheme {
        Greeting("Android")
    }
}
```

Se distinguen tres partes:

**Clase `MainActivity`**
- Hereda de **`ComponentActivity`**, una *activity* que admite componentes de Jetpack Compose.
- Su método **`onCreate`** (ciclo de vida) se ejecuta al iniciar la aplicación.
- Dentro de `onCreate`, `setContent` carga el **tema** del proyecto y, dentro de él, un **`Scaffold`** que llama a la función `Greeting`.

**Función `Greeting`**
- Recibe un `String` y un `Modifier` y genera un componente `Text`.
- Es un componente de Jetpack Compose porque está anotada con **`@Composable`**.

**Función `GreetingPreview`**
- Carga el tema y llama a `Greeting`.
- Es un componente (`@Composable`) y además permite **previsualizar** su contenido porque está anotada con **`@Preview`**.

### Componentes que aparecen

| Componente | Tipo | Descripción |
|:---|:---|:---|
| `Scaffold` | Del sistema | Estructura base con Material Design para crear las pantallas. |
| `Text` | Del sistema | Muestra un texto. |
| `ClickCounterTheme` | Propio | Tema del proyecto, que extiende el tema de Material Design. Está en `ui/theme/Theme.kt`. |
| `Greeting` | Propio | Extiende la funcionalidad de `Text`. |
| `GreetingPreview` | Propio | Previsualiza el componente `Greeting`. |

### `@Composable`

Todos los componentes de Jetpack Compose, **tanto los del sistema como los propios**, son funciones anotadas con `@Composable`:

::: tabs

== Propios

```kotlin
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) { /* … */ }

@Composable
fun ClickCounterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) { /* … */ }
```

== Del sistema

```kotlin
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    // …
)

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    // …
)
```

:::

::: info-box Convención de nombres
Las funciones `@Composable` que dibujan interfaz se nombran en **PascalCase** (`Greeting`, `ClickCounter`), como si fueran clases, y no devuelven nada.
:::

### `@Preview`

Android Studio permite **previsualizar en tiempo real** cualquier componente anotado con `@Preview`, como `GreetingPreview`. Lo verás en detalle en el apartado [2.6. Previsualizaciones](#_2-6-previsualizaciones). Con las versiones actuales de Android Studio también puedes ver los cambios en tiempo real en el emulador mediante **Live Edit**, sin necesidad de previsualizaciones.

## 2.2. Unidades de medida

En Android es fundamental conocer las unidades de medida:

| Unidad | Nombre | Uso |
|:---|:---|:---|
| **`dp`** | *Density-independent pixels* | **Todos los tamaños, medidas y distancias salvo el texto.** Se adapta a los dpi de la pantalla para que los elementos se vean uniformes en pantallas con distintas densidades. |
| **`sp`** | *Scale-independent pixels* | **Texto.** Se adapta a la densidad y también al tamaño de letra que el usuario elige en el sistema. |
| `in` | Pulgadas | Pulgadas reales de la pantalla física. ❌ Evitar. |
| `mm` | Milímetros | Milímetros reales de la pantalla física. ❌ Evitar. |
| `pt` | Puntos | 1/72 de pulgada de la pantalla física. ❌ Evitar. |
| `px` | Píxeles | Un píxel real de la pantalla. ❌ Evitar. |

En Compose se escriben como extensiones numéricas: `16.dp`, `25.sp`.

## 2.3. Contenido de la aplicación

El contador necesita un texto y un botón.

1. Elimina la función `Greeting`, todas sus llamadas y la función de previsualización (usaremos siempre el emulador).
2. Crea un componente propio llamado **`Content`** y llámalo desde el `Scaffold`:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
}
```

`Scaffold` es la base de las pantallas con Material Design. Con `Modifier.fillMaxSize()` ocupa toda la pantalla. Además, **calcula los márgenes** necesarios para que su contenido no quede debajo de la barra de estado o de navegación y se los **inyecta** a su contenido como un `PaddingValues` (disponible como `it` o, como en el ejemplo, con el nombre `innerPadding`). Por eso a `Content` se le pasa un modificador con ese *padding*.

3. Añade en `Content` el texto con un **`Text`** y el botón con un **`Button`**:

```kotlin
@Composable
fun Content(modifier: Modifier = Modifier) {
    Text(text = "Has hecho clic 0 veces")
    Button(onClick = {

    }) {
        Text(text = "¡Púlsame!")
    }
}
```

`Button` recibe una lambda en el parámetro **`onClick`** (vacía de momento) y otra lambda, fuera de los paréntesis, con su **contenido**: otro `Text`.

::: tip-box Consultar la documentación
Para ver qué parámetros admite un componente, pasa el ratón por encima de su nombre o haz `Ctrl + clic` sobre él. En `Button`, tanto `onClick` como `content` son lambdas; como `content` es el **último parámetro**, se escribe fuera de los paréntesis:

```kotlin
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    // …
    content: @Composable RowScope.() -> Unit
)
```
:::

Las dos formas siguientes son válidas, pero la **guía de estilo** recomienda la segunda (*trailing lambda*):

::: tabs

== ❌ No recomendado

```kotlin
Button(
    onClick = {

    },
    content = {
        Text(text = "¡Púlsame!")
    }
)
```

== ✅ Recomendado

```kotlin
Button(onClick = {

}) {
    Text(text = "¡Púlsame!")
}
```

:::

### Organizar con `Column`

Si ejecutas ahora la aplicación, verás que el texto y el botón **se superponen** en la esquina superior, detrás de la barra de estado e incluso de la cámara:

![El texto y el botón superpuestos detrás de la barra de estado](/img/contenidos/up03/up03-2-p14-1.webp)

Ocurre porque no hay ningún componente de **layout** que organice la interfaz. Usaremos **`Column`**, que coloca sus elementos en columna, y le aplicaremos el modificador que recibe `Content`:

```kotlin
@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Has hecho clic 0 veces")
        Button(onClick = {

        }) {
            Text(text = "¡Púlsame!")
        }
    }
}
```

![Texto y botón en columna, debajo de la barra de estado](/img/contenidos/up03/up03-2-p16-1.webp)

### Mejorar la interfaz

Añadimos centrado vertical y horizontal, tamaños de texto y espaciado:

```kotlin
@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize().then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Has hecho clic 0 veces",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {

        }) {
            Text(
                text = "¡Púlsame!",
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

![Contador de clics con el texto y el botón centrados](/img/contenidos/up03/up03-2-p17-1.webp)

::: accent-box success ✨ Buenas prácticas en Compose
- Usa **argumentos con nombre** en las llamadas.
- Si hay varios parámetros, escribe **cada uno en una línea**.
- Si el último parámetro es una lambda, **sácala de los paréntesis**.
:::

## 2.4. Funcionalidad

La interfaz declarativa ya está terminada. Ahora necesitamos una variable entera que cuente los clics: la creamos a cero, la mostramos en el texto y la incrementamos en el `onClick` del botón:

```kotlin
@Composable
fun Content(modifier: Modifier = Modifier) {
    var times = 0
    Column(
        modifier = Modifier.fillMaxSize().then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Has hecho clic $times veces",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            times++
        }) {
            Text(
                text = "¡Púlsame!",
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```

## 2.5. Variables de estado

Si ejecutas la aplicación, el botón responde… pero **el número no cambia**. Recuerda que la interfaz está conectada al **estado**: si el estado no cambia, Compose no vuelve a dibujar (**recomponer**) la interfaz. Una variable normal no es estado.

La solución es convertir `times` en una **variable de estado** con `remember` y `mutableIntStateOf`. Así, cuando cambie, Compose recompondrá los componentes que la usan:

```kotlin
@Composable
fun Content(modifier: Modifier = Modifier) {
    // var times by remember { mutableStateOf(0) }
    var times by remember { mutableIntStateOf(0) }
    Column(
        // …
```

La línea comentada también funciona, pero `mutableIntStateOf` es más concreta con el tipo (y más eficiente para enteros).

::: info-box `remember` y `by`
- `mutableIntStateOf(0)` crea un **estado observable** con valor inicial 0.
- `remember { … }` hace que Compose **recuerde** ese estado entre recomposiciones; sin él, se volvería a crear a 0 cada vez.
- `by` (delegación) permite usar `times` como un `Int` normal en lugar de escribir `times.value`.
:::

### Sobrevivir a la recreación de la Activity

Como viste al principio de la unidad, la *activity* **se destruye y se vuelve a crear** al cambiar la orientación, el modo claro/oscuro o la configuración. Al recrearse, `remember` vuelve a empezar y **el número de clics se pierde**:

![El contador vuelve a 0 al girar el dispositivo](/img/contenidos/up03/up03-2-p21-1.webp)

Para conservarlo, se usa **`rememberSaveable`**:

```kotlin
var times by rememberSaveable { mutableIntStateOf(0) }
```

Ahora la aplicación funciona correctamente aunque se gire el dispositivo, se cambie entre modo claro y oscuro o se modifique la configuración.

::: tip-box Práctica
Realiza la [Actividad 0 — Contador de clics](../ejercicios/#actividad-0), la [Actividad 1 — Estadísticas](../ejercicios/#actividad-1) y la [Actividad 2 — Dice Game](../ejercicios/#actividad-2).
:::

## 2.6. Previsualizaciones

::: info-box Ampliación
Este apartado es opcional: durante el curso trabajaremos sobre todo con el emulador y Live Edit.
:::

Para previsualizar un componente se anota con `@Preview` (además de `@Composable`). Un componente **con parámetros no se puede previsualizar** directamente; se crea otro componente sin parámetros que lo envuelva:

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickCounterTheme {
        Greeting("Android")
    }
}
```

Es importante que la previsualización muestre **lo mismo** que la aplicación en ejecución, así que conviene reproducir la misma estructura que en `setContent`:

```kotlin
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickCounterTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
```

Para ver las previsualizaciones, elige la vista **Split** o **Design** en la parte superior derecha del editor. A veces habrá que pulsar **Build & Refresh…** para recargarlas.

![Vista Split con el código y la previsualización](/img/contenidos/up03/up03-x-p05-1.webp)

### Opciones de `@Preview`

Con `Ctrl + clic` sobre `@Preview` ves todas sus opciones:

```kotlin
annotation class Preview(
    val name: String = "",
    val group: String = "",
    @IntRange(from = 1) val apiLevel: Int = -1,
    val widthDp: Int = -1,
    val heightDp: Int = -1,
    val locale: String = "",
    @FloatRange(from = 0.01) val fontScale: Float = 1f,
    val showSystemUi: Boolean = false,
    val showBackground: Boolean = false,
    val backgroundColor: Long = 0,
    @UiMode val uiMode: Int = 0,
    @Device val device: String = Devices.DEFAULT,
    @Wallpaper val wallpaper: Int = Wallpapers.NONE,
)
```

Algunos ejemplos:

```kotlin
// Tamaño concreto
@Preview(showBackground = true, widthDp = 200, heightDp = 100)

// Con la interfaz del sistema y en un dispositivo concreto
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7)

// Varias previsualizaciones del mismo componente
@Preview(name = "SquareView", showBackground = true, widthDp = 200, heightDp = 200)
@Preview(name = "RectangleView", showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun GreetingPreview() { /* … */ }

// Modo claro y oscuro
@Preview(name = "Light Mode", showBackground = true, showSystemUi = true)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingPreview() { /* … */ }

// Distintos idiomas (lo estudiaremos más adelante)
@Preview(name = "English", widthDp = 200, heightDp = 100)
@Preview(name = "Spanish", group = "locale", locale = "es", widthDp = 200, heightDp = 100)
@Composable
fun LanguagePreview() {
    Text(stringResource(R.string.greeting))
}
```

![Previsualizaciones en modo claro y oscuro](/img/contenidos/up03/up03-x-p10-1.webp)

También puede haber varios componentes con previsualización en el mismo archivo, y Android Studio permite configurar todas las opciones desde el icono que aparece junto a cada `@Preview`.

### Limitaciones

- No pueden recibir parámetros.
- No tienen acceso a los archivos.
- No tienen acceso a la red (no cargarán datos de internet).
- Algunas API no funcionan del todo bien.

Toda la información está en la [documentación oficial de las previsualizaciones](https://developer.android.com/develop/ui/compose/tooling/previews?hl=es-419).
