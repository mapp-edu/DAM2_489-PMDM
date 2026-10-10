---
layout: doc
title: 1. Modifier y componentes básicos
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Organización del código, Modifier y componentes básicos

::: warning-box Antes de empezar
Jetpack Compose ofrece una cantidad enorme de componentes, cada uno con muchas opciones, así que en clase no se pueden estudiar todos. Aquí verás la **funcionalidad de cada componente y sus puntos más importantes**.

Los componentes evolucionan rápido: es posible que algún ejemplo haya cambiado ligeramente en la versión de Android Studio que uses. Estudiar cada componente y hacer tus propias pruebas es parte del trabajo; las dudas, en clase o en el foro del aula virtual.
:::

## 1.1. Introducción

En esta unidad harás un recorrido por los **componentes de Jetpack Compose** para diseñar interfaces:

- Componentes de **presentación e interacción**: textos, imágenes, botones, diálogos…
- Componentes de **layout**: columnas, filas, rejillas, tarjetas…

Las dos fuentes de referencia son:

| Fuente | Qué ofrece |
|:---|:---|
| [Android Developers](https://developer.android.com/develop/ui/compose/components) | Documentación de cada componente y cómo usarlo en Compose. |
| [Material Design 3](https://m3.material.io/components) | Guías de estilo: cuándo y cómo usar cada componente. |

La última versión de Jetpack Compose utiliza **Material Design 3**, el sistema de diseño de interfaces de Google. Para cada componente (por ejemplo, [`Switch`](https://m3.material.io/components/switch)) la web de Material Design tiene un apartado **Guidelines** con recomendaciones de uso, anatomía, colocación y comportamiento, además de ejemplos de lo que se debe y no se debe hacer.

![Apartado Guidelines del componente Switch en Material Design 3](/img/contenidos/up04/up04-1-material-guidelines.webp)
*Guidelines del componente Switch en la web de Material Design 3*

## 1.2. Organizar el código

Antes de estudiar los componentes conviene organizar bien el código de la aplicación:

- Se pueden crear **tantos archivos** como se quiera.
- El archivo que contiene la clase que extiende de `Activity` (`MainActivity.kt`) **no debe definir componentes**.
- Debe existir un componente con la **base de la aplicación** para poder usarlo desde cualquier sitio.
- Si la aplicación tiene **varias pantallas**, cada una tendrá su interfaz general en **su propio archivo**.
- Todos los archivos con componentes de la interfaz van dentro de la carpeta **`ui`**.
- Si varios componentes forman una zona o sección de la pantalla, se crea un **componente que los agrupe**.
- Si un componente se repite con las mismas características de estilo, se crea un **componente propio** que lo extienda.

### Ejemplo: reorganizar el contador de clics

Vas a aplicar estas reglas al proyecto **ClickCounter** de la [UP03](../../up03/contenidos/02-primera-app-compose).

1. Crea el paquete **`screens`** dentro de la carpeta `ui`: clic derecho sobre `ui` → **New → Package**, escribe `screens` y pulsa <kbd>Enter</kbd>.

   ![Menú New → Package sobre la carpeta ui](/img/contenidos/up04/up04-1-nuevo-paquete.webp)

2. Crea el archivo de la pantalla: clic derecho sobre `screens` → **New → Kotlin Class/File**, escribe `ClickCounter`, elige **File** y pulsa <kbd>Enter</kbd>.
3. Mueve la función `Counter` de `MainActivity.kt` al nuevo archivo `ClickCounter.kt`, renómbrala a **`ClickCounter`** y envuelve todo su contenido en un **`Scaffold`**:

```kotlin [ui/screens/ClickCounter.kt]
@Composable
fun ClickCounter() {
    Scaffold { paddingValues ->
        var times by rememberSaveable { mutableIntStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.counter_text, times),
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    times++
                    Log.i("BOTON CLICK", "Se ha pulsado el botón. Valor de times: $times")
                }
            ) {
                Text(
                    text = stringResource(id = R.string.clickme),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { times = 0 },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.Red),
                enabled = times != 0
            ) {
                Text(
                    text = stringResource(id = R.string.reset),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}
```

4. En `MainActivity.kt` deja dentro del tema **solo una llamada** al componente `ClickCounter`:

```kotlin [MainActivity.kt]
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                ClickCounter()
            }
        }
    }
}
```

De esta manera el proyecto queda mucho más organizado.

![Estructura de paquetes con las pantallas detail y main y los componentes compartidos en shared](/img/contenidos/up04/up04-1-estructura-screens.webp){width=260px}

::: tip-box Cuando la aplicación crece
- Si una pantalla se compone de varios archivos, crea un **paquete con el nombre de la pantalla** dentro de `screens` (`detail`, `main`…).
- Si hay componentes que se **comparten entre pantallas**, guárdalos en un paquete llamado `common`, `shared` o `partials`.

En la UP05 verás cómo navegar entre las distintas pantallas.
:::

## 1.3. Componentes de Jetpack Compose

Los componentes que ofrece Jetpack Compose se pueden clasificar en dos grandes grupos:

| Layout<br>*Organizan los elementos de la interfaz* | Presentación de información<br>*Muestran información; el usuario puede interactuar con ellos* |
|:---|:---|
| `Box`, `Surface` | `Text`, `TextField` |
| `Column`, `Row` | `Button`, `Image` |
| `Card`, `ConstraintLayout` | `Spacer`, `Switch` |
| `Scaffold`, `TopAppBar` | `Slider`, `Checkbox` |
| `BottomAppBar`, `ModalNavigationDrawer` | `RadioButton`, `ElevatedButton` |
| `LazyColumn`, `LazyRow` | `Icon`, `IconButton` |
| `LazyVerticalGrid`, `LazyHorizontalGrid` | `Snackbar`, `BadgedBox` |
| `LazyVerticalStaggeredGrid`, `LazyHorizontalStaggeredGrid` | `FloatingActionButton` |

En la UP03 ya usaste `Column`, `Row`, `Text`, `Button`, `Spacer` y `Scaffold`. **Toda la interfaz** de tus aplicaciones se construirá con:

- Los **componentes que ofrece Jetpack Compose**.
- **Componentes propios**, creados extendiendo o agrupando los anteriores.

Todos los componentes de Jetpack Compose son **funciones anotadas con `@Composable`**, como puedes comprobar en su código fuente:

```kotlin
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    // …
)

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    // …
)

@Composable
@NonRestartableComposable
fun Spacer(modifier: Modifier) {
    Layout(measurePolicy = SpacerMeasurePolicy, modifier = modifier)
}
```

## 1.4. Modifier

Los componentes de Jetpack Compose tienen un parámetro **`modifier`**. Por defecto reciben un objeto `Modifier` con la configuración básica.

- `Modifier` es un **companion object** con muchas **funciones de extensión** para modificar los componentes.
- Sigue el patrón **builder**: las llamadas se **concatenan** con el punto.
- **El orden de las llamadas importa**: cada orden produce un efecto distinto.

Las funciones de `Modifier` se pueden clasificar en:

| Tipo | Ejemplos |
|:---|:---|
| Posicionamiento y tamaño | `fillMaxSize`, `fillMaxWidth`, `fillMaxHeight`, `wrapContentSize`, `wrapContentHeight`, `width`, `height`, `size`, `aspectRatio`… |
| Funcionalidad | `clickable`, `toggleable`, `horizontalScroll`, `verticalScroll`… |
| Apariencia | `padding`, `background`, `border`, `alpha`, `clip`… |
| Manejadores de eventos (*listeners*) | `clickable`, `onFocusChanged`, `onKeyEvent`, `onSizeChanged`… |

### El orden importa

Compara estos dos `Text`. Solo cambia la posición de `padding` respecto a `border`:

::: tabs

== padding antes del borde

```kotlin
Text(
    text = "Hello Rick!",
    modifier = Modifier
        .background(Color.LightGray)
        .padding(16.dp)
        .border(
            width = 2.dp,
            color = Color.DarkGray
        ),
    color = Color.Red,
    fontWeight = FontWeight.Bold
)
```

El fondo ocupa todo, el margen se aplica después y el **borde queda pegado al texto**.

== padding después del borde

```kotlin
Text(
    text = "Hello Rick!",
    modifier = Modifier
        .background(Color.LightGray)
        .border(
            width = 2.dp,
            color = Color.DarkGray
        )
        .padding(16.dp),
    color = Color.Red,
    fontWeight = FontWeight.Bold
)
```

El borde rodea todo el fondo y el **margen queda dentro del borde**, separando el texto.

:::

Como `Modifier` es un *builder*, puedes **repetir funciones**. Con dos `padding` consigues un margen fuera y otro dentro del borde:

```kotlin
Text(
    text = "Hello Rick!",
    modifier = Modifier
        .background(Color.LightGray)
        .padding(16.dp)      // margen exterior al borde
        .border(width = 2.dp, color = Color.DarkGray)
        .padding(16.dp),     // margen interior al borde
    color = Color.Red,
    fontWeight = FontWeight.Bold
)
```

### Cualquier componente puede ser clicable

Gracias a `Modifier`, **cualquier componente** puede responder a pulsaciones con `clickable`:

```kotlin
Text(
    text = "Hello Rick!",
    modifier = Modifier
        .background(Color.LightGray)
        .border(width = 2.dp, color = Color.DarkGray)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable {
            Log.i("Text", "Se ha pulsado")
        },
    color = Color.Red,
    fontWeight = FontWeight.Bold
)
```

El lugar donde aparece `clickable` también cambia el comportamiento: aquí solo es clicable la zona interior al `padding`. Existen más manejadores de eventos, como `onFocusChanged` u `onKeyEvent`.

### Contenido desplazable

Si el contenido de un componente no cabe, `verticalScroll` y `horizontalScroll` permiten **deslizarlo**:

```kotlin
Text(text = "Jules Winnfield:")
Text(
    text = """"El camino del hombre recto está por todos lados
        |rodeado por las injusticias de los egoístas y la
        |tiranía de los hombres malos. …"""".trimMargin(),
    modifier = Modifier
        .height(100.dp)
        .width(200.dp)
        .verticalScroll(rememberScrollState())
        .horizontalScroll(rememberScrollState())
)
```

::: warning-box Cuidado con el scroll
- Si un componente con scroll ocupa **toda la pantalla**, el resto de elementos no se verán a menos que le indiques un **tamaño**.
- Un elemento con scroll vertical **no debe contener** otro con scroll vertical (y lo mismo con el horizontal).

`rememberScrollState()` guarda la posición del desplazamiento; lo estudiarás más adelante.
:::

### Ámbito de las funciones

En la [lista de modificadores](https://developer.android.com/develop/ui/compose/modifiers-list) tienes todas las funciones disponibles. La mayoría se pueden usar en **cualquier ámbito** (*Scope: Any*), como `Modifier.border`, pero algunas solo existen en un **ámbito concreto**: por ejemplo, `Modifier.align(Alignment.Vertical)` solo se puede usar dentro de una `Row` (`RowScope`).

## 1.5. Componentes propios

Para crear un componente propio basta con escribir una **función anotada con `@Composable`**. Según la documentación:

- Si la función **no devuelve nada** (lo más habitual), su nombre empieza por **mayúscula**.
- Si la función **devuelve algo**, su nombre empieza por **minúscula**.

```kotlin
@Composable
fun MyText(value: String) {
    Text(
        text = value,
        fontSize = 18.sp,
        color = Color.DarkGray
    )
}
```

Los componentes propios sirven sobre todo para dos cosas:

::: tabs

== Extender componentes

Se ahorra código cuando un componente se usa repetidamente con el mismo estilo. Puedes añadir parámetros para lo que cambia:

```kotlin
@Composable
fun MyText(value: String, color: Color = Color.DarkGray) {
    Text(
        text = value,
        fontSize = 18.sp,
        color = color,
        modifier = Modifier.fillMaxWidth()
    )
}
```

== Agrupar componentes

Se reutiliza un bloque completo en distintos lugares de la aplicación:

```kotlin
@Composable
fun AuthorInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Rick Sanchez", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = " © ", fontSize = 36.sp, color = Color.Blue)
        Text(text = "2025-26", fontSize = 24.sp, color = Color.Yellow)
    }
}
```

:::

### Ejemplos

```kotlin
// Text con un formato predefinido
@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .background(Color.LightGray)
            .border(width = 1.dp, color = Color.DarkGray)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

// Text para saludar que acepta Modifier
@Composable
fun HeaderGreetingText(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hola $name",
        modifier = modifier
    )
}

// Componente propio para mostrar un usuario
@Composable
fun UserItemList(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(user.name)
        Spacer(Modifier.width(10.dp))
        Text("(")
        Icon(
            imageVector = Icons.Default.Mail,
            contentDescription = "mail",
            modifier = Modifier.size(18.dp)
        )
        Text(" ${user.mail})")
    }
}
```

Y su uso:

```kotlin
HeaderText("¡Bienvenido!")
HeaderGreetingText(
    name = "Rick",
    modifier = Modifier.background(Color.Yellow)
)
UserItemList(User(name = "Rick", mail = "rick@mail.com"))
```

### Recibir un Modifier como parámetro

Al crear un componente que extiende a otro, conviene que **reciba un `modifier`** para que quien lo use pueda personalizarlo. Además, puedes **combinar** el `Modifier` recibido con el que defines dentro mediante **`then`**; el lugar donde se llama a `then` cambia el resultado:

```kotlin
// Text con un formato predefinido que acepta modificaciones
@Composable
fun HeaderTextMod(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .background(Color.LightGray)
            .border(width = 1.dp, color = Color.DarkGray)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .then(modifier) // se combina el modifier recibido con el definido internamente
    )
}

HeaderTextMod("¡Bienvenido!")
HeaderTextMod(
    text = "¡Bienvenido!",
    modifier = Modifier.background(Color.Cyan)
)
```

También puedes usar el `Modifier` recibido **solo si llega uno**, y en caso contrario uno interno por defecto:

```kotlin
@Composable
fun MyText(
    value: String,
    modifier: Modifier = Modifier
) {
    val defaultModifier = Modifier
        .background(Color.LightGray) // Fondo por defecto
        .padding(8.dp)               // Margen interno por defecto
        .size(100.dp)                // Tamaño por defecto

    Text(
        text = value,
        fontSize = 18.sp,
        modifier = if (modifier == Modifier) defaultModifier else modifier
    )
}
```

::: info-box Buenas prácticas
Los componentes propios permiten dividir el código para que esté más organizado. Deben:
- Ser **lo más pequeños posible**: como las funciones, cada uno con una única responsabilidad.
- Tener **nombres semánticos**: así se explican solos y cualquiera que lea el código sabrá qué hacen.
:::

## 1.6. Text y BasicText

`Text` y `BasicText` muestran una cadena de texto. La diferencia es que **`Text` aplica los principios de Material** y `BasicText` no. Su uso básico ya lo conoces:

```kotlin
Text(text = "Hello Rick!")
```

### Consultar la definición de un componente

Es fundamental conocer cómo funcionan los componentes. Para abrir su código fuente en Android Studio:

- Mantén <kbd>Ctrl</kbd> pulsado y haz **clic izquierdo** sobre el nombre, o
- Sitúa el cursor sobre el nombre y pulsa <kbd>Ctrl</kbd> + <kbd>B</kbd>.

Esto funciona con cualquier clase, función u objeto. Si dejas el ratón encima del nombre, aparece una ventana emergente con la misma información. Todas las funciones tienen documentación **KDoc** que explica sus parámetros:

```kotlin
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) { /* … */ }
```

Como con cualquier clase que no es tuya, debes saber qué parámetros tiene y qué tipo acepta cada uno: `text` es un `String` y es **obligatorio**; `modifier` es un `Modifier`; `fontWeight` es un `FontWeight`… Navegando por los parámetros con <kbd>Ctrl</kbd> + <kbd>B</kbd> descubres el tipo que aceptan. También puedes consultarlo en la documentación oficial.

**El único parámetro obligatorio es `text`**: el resto tienen valor por defecto. Al usar **parámetros con nombre**, como recomienda Kotlin, puedes omitir los que no necesites y cambiar su orden:

```kotlin
Text(
    text = "Hola Rick!",
    modifier = modifier.fillMaxSize(),
    color = Color.DarkGray,
    textAlign = TextAlign.Center,
    fontSize = 24.sp,
    lineHeight = 26.sp,
    maxLines = 2,
    textDecoration = TextDecoration.Underline
)
```

### Ejemplos de estilo

Con los parámetros configuras aspectos concretos como `color` o `fontSize`. Con **`style`** puedes indicar varias configuraciones de estilo a la vez:

```kotlin
Column {
    Text(text = "Hello Rick!", color = Color.Red)
    Text(text = "Hello Rick!", fontWeight = FontWeight.Bold)
    Text(text = "Hello Rick!", color = Color.Green, fontWeight = FontWeight.Bold)
    Text(
        text = "Hello Rick!",
        textDecoration = TextDecoration.Underline,
        fontSize = 20.sp
    )
    Text(
        text = "Hello Rick!",
        textDecoration = TextDecoration.combine(
            listOf(
                TextDecoration.Underline,
                TextDecoration.LineThrough
            )
        )
    )
    Text(
        text = "Hello Rick!",
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            color = Color.Blue
        )
    )
    Text(
        text = "Hello Rick!",
        style = MaterialTheme.typography.bodySmall.copy(color = Color.Magenta)
    )
}
```

## 1.7. Button

Un `Button` lanza una acción cuando el usuario interactúa con él. Tiene **dos parámetros obligatorios**, `onClick` y `content`, que son **funciones lambda**. Como `content` es el último parámetro, se puede escribir **fuera de los paréntesis**:

```kotlin
Button(onClick = {
    Log.i("Botón", "Se ha pulsado el botón")
}) {
    Text("Button")
}
```

```kotlin
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) { /* … */ }
```

- `onClick` y `content` son lambdas: su valor es un **bloque de instrucciones** entre llaves `{ }`.
- `content` acepta **`@Composable`**: dentro puedes escribir instrucciones Kotlin **y componentes** de Jetpack Compose.
- `content` es un **`RowScope`** (ámbito de fila): se comporta como una `Row` y los componentes que incluyas se colocan **en horizontal**.

```kotlin
Button(
    onClick = {
        Log.i("Mi info", "Se ha pulsado el botón")
    },
) {
    Icon(
        imageVector = Icons.Default.AccountBox,
        contentDescription = "Cuenta"
    )
    Spacer(modifier = Modifier.width(16.dp))
    Text(text = "Cuenta")
}
```

### ButtonDefaults

`Button` define algunos valores por defecto en la clase **`ButtonDefaults`** (`shape`, `buttonColors()`, `buttonElevation()`, `ContentPadding`…). Para modificarlos se usa esa misma clase:

```kotlin
Button(
    onClick = { /*TODO*/ },
    colors = ButtonDefaults.buttonColors(
        contentColor = Color.Cyan,
        containerColor = Color.DarkGray
    )
) {
    Text("Acepta")
}
```

### Variaciones

`Button` tiene **variaciones con estilo predefinido**. Internamente, todas son del tipo `Button`:

```kotlin
Button(onClick = { /* TODO */ }) { Text(text = "Button (Filled)") }

FilledTonalButton(onClick = { /* TODO */ }) { Text(text = "Button (Filled Tonal)") }

ElevatedButton(onClick = { /* TODO */ }) { Text(text = "Button (Elevated)") }

OutlinedButton(onClick = { /* TODO */ }) { Text(text = "Button (Outlined)") }

TextButton(onClick = { /* TODO */ }) { Text(text = "Button (Text)") }
```

| Variación | Aspecto |
|:---|:---|
| `Button` | Relleno con el color principal. Acción más importante. |
| `FilledTonalButton` | Relleno con un tono secundario más suave. |
| `ElevatedButton` | Fondo claro con sombra. |
| `OutlinedButton` | Solo el borde. |
| `TextButton` | Solo el texto, sin fondo ni borde. |

## 1.8. Spacer

`Spacer` deja un **espacio** entre otros componentes. Solo admite el parámetro `modifier`:

```kotlin
Text("Hola Rick")
Spacer(modifier = Modifier.height(30.dp))
Text("Hello there!")

Spacer(modifier = Modifier.width(15.dp))
Spacer(
    modifier = Modifier
        .width(15.dp)
        .height(40.dp)
)
```

Es recomendable crear un **componente propio** que extienda a `Spacer` para simplificar su uso:

```kotlin
@Composable
fun MySpacer(height: Int = 0, width: Int = 0, size: Int = 0) {
    val modifier = when {
        // Prioridad al tamaño cuadrado
        size > 0 -> Modifier.size(size.dp)
        // Altura y ancho específicos
        height > 0 && width > 0 -> Modifier.height(height.dp).width(width.dp)
        // Solo altura
        height > 0 -> Modifier.height(height.dp)
        // Solo ancho
        width > 0 -> Modifier.width(width.dp)
        // Sin modificadores
        else -> Modifier
    }
    Spacer(modifier = modifier)
}

MySpacer(height = 20)
MySpacer(width = 40)
MySpacer(height = 40, width = 200)
MySpacer(size = 30)
```

## 1.9. Imágenes en el proyecto

En una aplicación Android las imágenes se **importan previamente en el proyecto** o se **obtienen de internet**. Las que se importan deben estar en uno de estos formatos:

- **Vectoriales**: SVG y *VectorDrawable*.
- **Matriciales**: GIF, JPG, PNG y **WebP**.

### Imágenes matriciales

1. Abre el panel **Resource Manager** en la parte izquierda y pulsa el botón **+**.
2. Elige **Import Drawables** y selecciona la imagen (JPG, PNG o WebP).

   ![Resource Manager con el menú del botón +: Import Drawables, Image Asset y Vector Asset](/img/contenidos/up04/up04-1-resource-manager.webp){width=280px}

3. En la ventana de importación puedes elegir un **QUALIFIER**, que permite cargar imágenes de distintos tamaños según las características del dispositivo.
4. Pulsa **Import** y la imagen ya estará disponible.

### Imágenes vectoriales

Para añadir imágenes vectoriales, normalmente **iconos de Google**, elige **Vector Asset** en el mismo menú. Se abre una ventana para elegir el icono y configurar su nombre, tamaño, color y opacidad. Pulsa **Next** y después **Finish**.

![Asset Studio: configuración de un Vector Asset y confirmación de la ruta del icono](/img/contenidos/up04/up04-1-vector-asset.webp)

Una vez añadidas, las imágenes aparecen en **Resource Manager** y en la carpeta **`res/drawable`**. Si has elegido *qualifiers*, se organizan según ellos.

![Carpeta res/drawable con la imagen importada](/img/contenidos/up04/up04-1-carpeta-res.webp){width=280px}

### Convertir imágenes a WebP

Google recomienda usar imágenes **WebP** porque están más optimizadas: pueden **reducir el tamaño de la aplicación hasta un 80 %**. WebP es compatible desde la API 14 con pérdida de calidad y desde la API 18 sin pérdida, así que con las versiones de API del curso no hay problemas.

1. Haz clic derecho sobre la imagen en el proyecto → **Convert to WebP…**
2. Elige las opciones: **Lossy encoding** con la calidad que quieras o **Lossless encoding** para la máxima calidad.

   ![Ventana Converting Images to WebP](/img/contenidos/up04/up04-1-convertir-webp.webp)

3. La previsualización muestra la **calidad final**, el **tamaño final** y la **diferencia** con la original. Puedes ajustar la calidad antes de pulsar **Finish**.

::: tip-box ¿Cuánto se ahorra?
En el ejemplo de clase, un PNG de 421,3 KB convertido con un 30 % de calidad apenas pierde nitidez y pasa a ocupar **14,5 KB** (un 3,5 % del original). Una foto JPG de 7,9 MB se queda en unos **400 KB** (un 6,1 %).
:::

## 1.10. Image

El componente `Image` carga imágenes **importadas en el proyecto**. Tiene dos parámetros obligatorios:

- **`painter`**: el recurso que se va a cargar, normalmente con `painterResource(id = R.drawable.nombre)`.
- **`contentDescription`**: descripción de la imagen, que usan los lectores de pantalla (accesibilidad).

```kotlin
Image(
    painter = painterResource(id = R.drawable.rick),
    contentDescription = "Rick",
)

Image(
    painter = painterResource(id = R.drawable.rick),
    contentDescription = "Rick",
    colorFilter = ColorFilter.tint(
        color = Color.Red,
        blendMode = BlendMode.Color
    )
)
```

### Dar forma con clip

`Modifier.clip()` da forma a **cualquier componente**. Si además añades un borde, debes darle **la misma forma** que a `clip`:

```kotlin
Image(
    painter = painterResource(id = R.drawable.rick),
    contentDescription = "Rick",
    modifier = Modifier
        .clip(CircleShape)
        .border(
            width = 5.dp,
            color = Color.Green,
            shape = CircleShape
        )
)
```

Otras formas disponibles son `RectangleShape` y `RoundedCornerShape`, que admite un tamaño común (`RoundedCornerShape(16.dp)`) o uno por esquina (`topStart`, `topEnd`, `bottomEnd`, `bottomStart`).

### ContentScale

Si la imagen ocupa todo el espacio disponible, **`contentScale`** indica cómo debe rellenarlo:

```kotlin
Image(
    painter = painterResource(id = R.drawable.rick),
    contentDescription = "Rick",
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop
)
```

| Valor | Comportamiento |
|:---|:---|
| `ContentScale.Crop` | Escala hasta cubrir todo el contenedor y recorta lo que sobra. |
| `ContentScale.Fit` | Escala hasta que la imagen cabe entera, manteniendo la proporción. |
| `ContentScale.FillWidth` / `FillHeight` | Ajusta al ancho o al alto del contenedor. |
| `ContentScale.FillBounds` | Estira la imagen hasta ocupar todo el contenedor, sin mantener la proporción. |
| `ContentScale.Inside` | Como `Fit`, pero nunca amplía la imagen. |
| `ContentScale.None` | No aplica ninguna escala. |

## 1.11. Imágenes de internet

También puedes cargar imágenes alojadas en internet, por ejemplo las que devuelve una API.

### Permiso de acceso a internet

La aplicación necesita **permiso de acceso a internet**. Añade esta línea en `app → manifests → AndroidManifest.xml`:

```xml [AndroidManifest.xml]
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        …
```

::: warning-box Emulador sin conexión
El dispositivo virtual también debe estar **conectado a internet**. Compruébalo en los ajustes rápidos del emulador (Internet → *AndroidWifi*).
:::

### Librería Coil y AsyncImage

Las imágenes de internet se cargan con la librería **[Coil](https://coil-kt.github.io/coil/)** y su componente **`AsyncImage`**. Primero añade la dependencia:

::: tabs

== libs.versions.toml

```toml [gradle/libs.versions.toml]
[versions]
# …
coil = "3.3.0"

[libraries]
# …
coil-compose = { group = "io.coil-kt.coil3", name = "coil-compose", version.ref = "coil" }
coil-network-okhttp = { group = "io.coil-kt.coil3", name = "coil-network-okhttp", version.ref = "coil" }
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
dependencies {
    // …
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
```

:::

Tras cambiar estos archivos, pulsa **Sync Now** en el aviso que aparece en la parte superior derecha para sincronizar Gradle.

::: info-box Versión de Kotlin
Coil **3.3.0** requiere como mínimo **Kotlin 2.2.x**. Si tu proyecto usa una versión anterior, actualízala en el apartado `[versions]` de `libs.versions.toml` (por ejemplo, `kotlin = "2.2.21"`) o usa Coil 3.2.0.
:::

`AsyncImage` tiene dos parámetros obligatorios, **`model`** (la URL) y **`contentDescription`**, y también admite `contentScale`:

```kotlin
AsyncImage(
    model = "https://alextz78.github.io/portal.png",
    contentDescription = "Rick y Morty",
    modifier = Modifier.fillMaxWidth(),
    contentScale = ContentScale.Fit
)
```

::: warning-box Previsualizaciones sin internet
Las previsualizaciones (`@Preview`) **no tienen acceso a internet**: para ver las imágenes debes ejecutar la aplicación.
:::

Para personalizar la carga se usa un **`ImageRequest`**. Con él puedes aplicar transformaciones o hacer que la imagen aparezca poco a poco con **`crossfade`**:

::: tabs

== Recorte circular

```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://alextz78.github.io/goku.jpg")
        .transformations(CircleCropTransformation())
        .build(),
    contentDescription = "Goku",
    modifier = Modifier.fillMaxWidth(),
    contentScale = ContentScale.Fit
)
```

== Esquinas redondeadas

```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://alextz78.github.io/goku.jpg")
        .transformations(
            RoundedCornersTransformation(
                topLeft = 150f,
                topRight = 0f,
                bottomLeft = 0f,
                bottomRight = 500f
            )
        )
        .build(),
    contentDescription = "Goku",
    modifier = Modifier.height(400.dp),
    contentScale = ContentScale.Fit
)
```

== Con clip

```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://alextz78.github.io/goku.jpg")
        .build(),
    contentDescription = "Goku",
    modifier = Modifier
        .height(400.dp)
        .clip(RoundedCornerShape(50.dp)),
    contentScale = ContentScale.Fit
)
```

== Crossfade

```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://alextz78.github.io/goku.jpg")
        .crossfade(durationMillis = 2000)
        .build(),
    contentDescription = "Goku",
    modifier = Modifier.height(400.dp),
    contentScale = ContentScale.Crop
)
```

:::

::: info-box Transformaciones en Coil 3
En Coil 3, `CircleCropTransformation` y `RoundedCornersTransformation` están en el paquete `coil3.transform`. Si Android Studio no las encuentra, revisa las importaciones.
:::

## 1.12. Icon

`Icon` representa un **icono** en la aplicación. Es similar a `Image`, pero forma parte de **Material Design**:

- Solo admite imágenes **vectoriales** o **rasterizadas** (convertidas a WebP).
- Se muestra con **un solo color**.
- Su tamaño por defecto es **24 dp**, aunque admite 20, 24, 40 y 48 dp.

Lo más habitual es usar los **iconos vectoriales de Material Design**. Para tenerlos todos disponibles, añade estas dependencias y pulsa **Sync Now**:

::: tabs

== libs.versions.toml

```toml [gradle/libs.versions.toml]
[libraries]
# …
androidx-material-icons-core = { group = "androidx.compose.material", name = "material-icons-core" }
androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended" }
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
dependencies {
    // …
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
}
```

:::

Con la dependencia añadida tienes disponibles [todos los iconos de Material](https://fonts.google.com/icons) a través de la clase **`Icons`**. En versiones anteriores de Compose con Material 2, la librería *core* venía incluida por defecto.

```kotlin
Icon(
    imageVector = Icons.Rounded.AccountCircle,
    contentDescription = "Cuenta"
)
```

También puedes usar una imagen como icono, pero **pierde sus colores** salvo que indiques `tint = Color.Unspecified`:

```kotlin
Image(
    painter = painterResource(id = R.drawable.dragonball),
    contentDescription = "Dragon Ball Logo",
    modifier = Modifier.size(size = 128.dp)
)

Icon(
    painter = painterResource(id = R.drawable.dragonball),
    contentDescription = "Dragon Ball Logo",
    modifier = Modifier.size(size = 128.dp),
    tint = Color.Unspecified // sin esta línea se pinta de un solo color
)
```

### Variaciones y color

La clase `Icons` ofrece cada icono en varias **variaciones**:

| Variación | Aspecto |
|:---|:---|
| `Icons.Default` | Igual que `Filled`. |
| `Icons.Filled` | Icono relleno de un solo color. |
| `Icons.Outlined` | Solo los bordes. |
| `Icons.TwoTone` | Con dos tonos. |
| `Icons.Sharp` | Esquinas en ángulo. |
| `Icons.Rounded` | Esquinas redondeadas. |

Con el parámetro **`tint`** cambias el color del icono:

```kotlin
Icon(
    imageVector = Icons.Default.PlayArrow,
    contentDescription = "Play",
    tint = Color.Red
)
Icon(
    imageVector = Icons.Rounded.PlayArrow,
    contentDescription = "Play",
    tint = Color.Green
)
Icon(
    imageVector = Icons.TwoTone.PlayArrow,
    contentDescription = "Play",
    tint = Color.Blue
)
```

## 1.13. Badge y BadgedBox

**`Badge`** muestra información dinámica, como el número de mensajes pendientes. Solo debería contener un **icono o un texto corto**:

```kotlin
Badge {
    Text("3")
}

Badge(
    containerColor = Color.DarkGray,
    contentColor = Color.White
) {
    Text("New")
}
```

**`BadgedBox`** muestra esa información **sobre otro elemento**, normalmente un icono. Se usa mucho en las barras de navegación:

```kotlin
BadgedBox(
    badge = {
        Badge(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ) {
            Text(text = "472")
        }
    }
) {
    Icon(
        imageVector = Icons.Filled.Mail,
        contentDescription = "Correo",
        tint = Color.Red
    )
}
```

## 1.14. Divider

`HorizontalDivider` y `VerticalDivider` dibujan una **línea** para separar elementos, normalmente en columnas (`Column`) y filas (`Row`):

```kotlin
Column {
    Text("1")
    HorizontalDivider()
    Text("2")
    HorizontalDivider()
    Text("3")
}

Spacer(modifier = Modifier.height(8.dp))

Row(modifier = Modifier.height(50.dp)) {
    Text("1")
    VerticalDivider()
    Text("2")
    VerticalDivider()
    Text("3")
}
```

Los dos tienen los mismos parámetros: `modifier`, `thickness` (grosor) y `color`:

```kotlin
@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) = Canvas(modifier.fillMaxWidth().height(thickness)) { /* … */ }
```

## 1.15. Componentes experimentales

Algunos componentes de Jetpack Compose están en **fase experimental**, pero se pueden usar sin problema. En ese caso hay que indicarlo con la anotación **`@OptIn(ExperimentalMaterial3Api::class)`**. La ayuda contextual de Android Studio (<kbd>Alt</kbd> + <kbd>Enter</kbd>) te ofrece tres opciones:

| Opción | Efecto |
|:---|:---|
| *Opt in for 'ExperimentalMaterial3Api' on 'Componente'* | Anota solo el componente que contiene al componente experimental. |
| *Opt in … in containing file* | Anota todo el archivo actual. |
| *Propagate 'ExperimentalMaterial3Api' opt-in requirement* | Obliga a indicarlo también en el componente padre que use este componente. |

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Converter() {
    // … aquí se usa un componente experimental
}
```

::: tip-box Practica
Ya puedes hacer la [Actividad 1 — Perfil personal](../ejercicios/#actividad-1).
:::
