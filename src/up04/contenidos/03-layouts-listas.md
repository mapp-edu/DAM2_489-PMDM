---
layout: doc
title: 3. Layouts, listas y Scaffold
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 3. Layouts, listas y Scaffold

## 3.1. Layout

El **layout** es el diseño gráfico o la disposición de los elementos: la manera en la que se colocan dentro de una interfaz. Jetpack Compose ofrece componentes que agrupan a otros y ayudan a diseñar el layout de cada pantalla. Ya has usado algunos: `Scaffold`, `Column` y `Row`.

| | | |
|:---|:---|:---|
| `Surface` | `Card` | `Box` |
| `BoxWithConstraints` | `Column` | `Row` |
| `LazyRow` y `LazyColumn` | `LazyVerticalGrid` y `LazyHorizontalGrid` | `HorizontalPager` y `VerticalPager` |
| `LazyVerticalStaggeredGrid` y `LazyHorizontalStaggeredGrid` | `ConstraintLayout` | `Scaffold` |
| `TopAppBar` | `BottomAppBar` | `NavigationDrawer` |
| `NavigationRail` | `DropdownMenu` | `BottomSheet` y `SideSheet` |

## 3.2. Surface

`Surface` es un **contenedor con estilo predefinido** que sigue el tema por defecto de Material Design. Es el eje central de la filosofía de Material: de hecho, `Scaffold` usa internamente un `Surface`. Tiene parámetros para definir la **forma**, la **elevación**, el **borde** y los **colores** del contenido y del contenedor.

Por sí solo **no organiza los elementos**: los hijos se dibujan uno encima de otro. Dentro debes usar otro layout como `Column` o `Row`:

::: tabs

== Sin layout (se superponen)

```kotlin
Surface(
    modifier = Modifier.fillMaxWidth(),
    color = MaterialTheme.colorScheme.background
) {
    Text("¡Bienvenido!")
    Text("Rick Sanchez")
}
```

== Con Column

```kotlin
Surface(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(text = "¡Bienvenido!")
        Spacer(modifier = Modifier.height(4.dp))
        Surface(shadowElevation = 10.dp) {
            Text(
                text = "Rick Sanchez",
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "¡Comencemos!")
    }
}
```

Puedes usar `Surface` tantas veces como quieras para tener una superficie que se diferencie del resto de la interfaz (aquí, con sombra).

:::

## 3.3. Card

Las `Card` son `Surface` con un estilo predefinido (modificable con sus parámetros) que les da aspecto de **tarjeta** con borde, elevación y sombra. Hay tres variantes: **`Card`**, **`ElevatedCard`** y **`OutlinedCard`**. Dentro de una `Card` los elementos se organizan en **columna** (`ColumnScope`).

```kotlin
Card {
    Text(text = "Rick Sanchez", modifier = Modifier.padding(8.dp))
}

OutlinedCard {
    Text(text = "Rick Sanchez", modifier = Modifier.padding(8.dp))
}

ElevatedCard {
    Text(text = "Rick Sanchez", modifier = Modifier.padding(8.dp))
}
```

## 3.4. Box y BoxWithConstraints

`Box` coloca varios elementos **en el mismo lugar, uno encima de otro**. Un uso posible es mostrar un contenido u otro según se pulse un botón:

```kotlin
var visible by rememberSaveable { mutableStateOf(true) }
Box {
    if (visible) {
        Text(text = "Rick Sanchez")
    } else {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "Cuenta"
        )
    }
}
Button(
    onClick = {
        visible = !visible
    }
) {
    Text(text = "Cambio de contenido")
}
```

**`BoxWithConstraints`** es una versión de `Box` que transmite al contenido los **límites del componente** (`minWidth`, `maxWidth`, `minHeight`, `maxHeight`, `hasBoundedWidth`, `hasFixedHeight`…) para usarlos si hace falta, por ejemplo para adaptar la interfaz al espacio disponible:

```kotlin
BoxWithConstraints {
    // Se almacenan los límites: this.constraints, ya que dentro de la columna
    // this se refiere a la propia columna
    val boxConstraints = constraints
    Column {
        Text(text = "Ancho mínimo: ${boxConstraints.minWidth}")
        Text(text = "Ancho máximo: ${boxConstraints.maxWidth}")
        Text(text = "Alto mínimo: ${boxConstraints.minHeight}")
        Text(text = "Alto máximo: ${boxConstraints.maxHeight}")
    }
}
```

::: info-box Píxeles
`constraints` devuelve los valores en **píxeles**. Si los necesitas en `dp`, usa `maxWidth` y `maxHeight` directamente del ámbito `BoxWithConstraintsScope`.
:::

## 3.5. Column y Row

Para alinear el contenido dentro de `Column` y `Row` se usan:

- **Arrangement**: eje **principal** (columnas: vertical; filas: horizontal).
- **Alignment**: eje **secundario** (columnas: horizontal; filas: vertical).

::: tabs

== Column

```kotlin
Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    // …
}
```

== Row

```kotlin
Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    // …
}
```

:::

### Valores de Arrangement (eje principal)

| Valor | Efecto | Disponible en |
|:---|:---|:---|
| `Center` | Centrado. | Ambos |
| `SpaceEvenly` | Mismo espacio entre los elementos y en los extremos. | Ambos |
| `SpaceBetween` | Mismo espacio entre los elementos, sin espacio en los extremos. | Ambos |
| `SpaceAround` | Mismo espacio a cada lado de cada elemento: entre dos elementos hay el doble que en los extremos. | Ambos |
| `spacedBy(16.dp)` | Espacio fijo entre elementos. | Ambos |
| `Top` / `Bottom` | Arriba / abajo. | Solo `Column` |
| `Start` / `End` | Principio / final de la línea según el sentido de lectura. | Solo `Row` |

### Valores de Alignment (eje secundario)

| Valor | Efecto | Disponible en |
|:---|:---|:---|
| `Start` / `End` | Principio / final de la línea según el sentido de lectura. | Solo `Column` |
| `CenterHorizontally` | Centrado horizontal. | Solo `Column` |
| `Top` / `Bottom` | Arriba / abajo. | Solo `Row` |
| `CenterVertically` | Centrado vertical. | Solo `Row` |

### Peso

Con `Modifier.weight()` indicas el **peso** (proporción) que ocupa un componente dentro de otro. Aquí el primer texto ocupa la mitad de la columna y los otros dos, un cuarto cada uno:

```kotlin
Column(
    modifier = Modifier.size(width = 300.dp, height = 200.dp)
) {
    Text(
        text = "Bienvenidos!",
        modifier = Modifier
            .fillMaxWidth()
            .weight(2f)
            .background(Color.Red)
    )
    Text(
        text = "Rick Sanchez",
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Green)
    )
    Text(
        text = "Mort Smith",
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Cyan)
    )
}
```

## 3.6. FlowRow y FlowColumn

`FlowRow` y `FlowColumn` son como `Row` y `Column`, pero cuando los elementos **no caben** en una única fila o columna, **pasan a la siguiente**:

```kotlin
val textList = listOf(
    "Precio descendente",
    "Precio ascendente",
    "Puntuación 4+",
    "Ofertas",
    "Más vendidos"
)
FlowRow(
    horizontalArrangement = Arrangement.spacedBy(24.dp),
    modifier = Modifier.padding(8.dp),
    //maxItemsInEachRow = 2
) {
    textList.forEach { text ->
        Text(
            text = text,
            modifier = Modifier
                .padding(4.dp)
                .background(Color.LightGray)
                .padding(4.dp)
        )
    }
}
```

## 3.7. Scroll y componentes Lazy

Cuando hay demasiados componentes, algunos quedan fuera de la parte visible de la pantalla. Ya viste que `verticalScroll` y `horizontalScroll` permiten deslizar el contenido, pero **cargan todo el contenido en memoria** se vaya a ver o no, así que la aplicación no está optimizada.

Para eso están los componentes **Lazy**, que organizan elementos en fila, en columna o en cuadrícula con **dos ventajas** muy importantes:

- Si el contenido no cabe, **se puede hacer scroll sin añadir nada más**.
- **Solo se cargan los componentes visibles** (más el anterior y el siguiente), así que se optimiza el uso de memoria.

| Componentes | Organización |
|:---|:---|
| `LazyColumn` y `LazyRow` | En columna o en fila. |
| `LazyVerticalGrid` y `LazyHorizontalGrid` | En cuadrícula. |
| `LazyVerticalStaggeredGrid` y `LazyHorizontalStaggeredGrid` | En cuadrícula escalonada. |

Parámetros comunes:

- `reverseLayout`: muestra los elementos en **orden inverso** (por defecto `false`).
- `userScrollEnabled`: indica si el scroll está activo (por defecto `true`).
- En las cuadrículas, `columns` (cuadrícula vertical) o `rows` (horizontal).
- `state`: estado del scroll. **Por defecto se crea con `rememberSaveable`**, así que el scroll se gestiona automáticamente.

El contenido se añade con dos bloques:

- **`item { }`**: añade **un** elemento.
- **`items(lista) { elemento -> }`**: recorre una lista y añade **cada elemento**.

## 3.8. LazyColumn y LazyRow

::: tabs

== LazyColumn

```kotlin
val dragonballCharacters = listOf("Son Goku", "Vegeta", "Piccolo", "Son Gohan",
    "Trunks", "Bulma", "Krilin", "Master Roshi", "Freezer", "Cell")

LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    item {
        OutlinedCard(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Personajes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    items(dragonballCharacters) { character ->
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = character,
                modifier = Modifier.height(80.dp).padding(8.dp)
            )
        }
    }
}
```

== LazyRow

```kotlin
LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    item {
        OutlinedCard(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
        ) {
            Text(
                text = "Personajes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
    items(dragonballCharacters) { character ->
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Text(
                text = character,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(80.dp)
                    .padding(8.dp)
            )
        }
    }
}
```

:::

### Sticky headers

Un comportamiento muy habitual son las cabeceras **«pegajosas»**, como en la aplicación de contactos: la letra se queda fija arriba mientras recorres sus nombres. Se usa el bloque **`stickyHeader`**, y los elementos se agrupan por algún criterio, por ejemplo la primera letra. Con `groupBy` obtienes un `Map` que puedes recorrer:

```kotlin
// dragonBallCharacters es un List<String>
val groupedCharacters: Map<Char, List<String>> = dragonBallCharacters.sorted().groupBy { it[0] }
```

```kotlin
LazyColumn {
    // Se crea un mapa a partir de la lista y la primera letra de cada elemento de la lista
    val groupedCharacters: Map<Char, List<String>> = dragonBallCharacters.sorted().groupBy { it[0] }
    Log.i("--->", groupedCharacters.toString())
    groupedCharacters.forEach { header, names ->
        stickyHeader {
            Text(
                text = "$header",
                fontSize = 30.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillParentMaxWidth()
            )
            HorizontalDivider(
                color = Color.Black,
                thickness = 1.dp
            )
        }
        items(names) { character ->
            Text(
                text = character,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 40.dp)
                    .padding(vertical = 8.dp)
            )
        }
    }
}
```

## 3.9. LazyVerticalGrid y LazyHorizontalGrid

Organizan los elementos en **cuadrícula** por columnas o por filas. **Todos los elementos ocupan el mismo espacio**: el del elemento más grande. El número de columnas (`columns`) o filas (`rows`) se indica con:

- `GridCells.Fixed(número)`: número fijo de columnas o filas.
- `GridCells.Adaptive(tamañoMínimo.dp)`: tantas como quepan con ese tamaño mínimo.

::: tabs

== LazyVerticalGrid

```kotlin
LazyVerticalGrid(
    columns = GridCells.Adaptive(150.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
) {
    item {
        OutlinedCard(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Personajes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        }
    }
    items(dragonballCharacters) { character ->
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = character, modifier = Modifier.height(80.dp).padding(8.dp))
        }
    }
}
```

== LazyHorizontalGrid

```kotlin
LazyHorizontalGrid(
    rows = GridCells.Fixed(3),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
) {
    item {
        OutlinedCard(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Personajes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        }
    }
    items(dragonballCharacters) { character ->
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = character, modifier = Modifier.height(80.dp).padding(8.dp))
        }
    }
}
```

:::

## 3.10. Cuadrículas escalonadas (Staggered)

`LazyVerticalStaggeredGrid` y `LazyHorizontalStaggeredGrid` son como las anteriores, pero **cada elemento ocupa el espacio de su contenido**, como en un muro de fotos.

| Componente | Parámetros más usados |
|:---|:---|
| `LazyVerticalStaggeredGrid` | `columns`, `verticalItemSpacing` (espacio vertical), `horizontalArrangement` (espacio horizontal). |
| `LazyHorizontalStaggeredGrid` | `rows`, `horizontalItemSpacing` (espacio horizontal), `verticalArrangement` (espacio vertical). |

El número de columnas o filas se indica con `StaggeredGridCells.Fixed(número)` o `StaggeredGridCells.Adaptive(tamañoMínimo.dp)`.

```kotlin
LazyVerticalStaggeredGrid(
    columns = StaggeredGridCells.Fixed(3),
    verticalItemSpacing = 8.dp,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
) {
    item {
        OutlinedCard(
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Personajes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        }
    }
    items(dragonballCharacters) { character ->
        val randomHeight = ((1..30).random() * 15).dp
        Card(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
                .height(randomHeight)
        ) {
            Text(text = character, modifier = Modifier.height(80.dp).padding(8.dp))
        }
    }
}
```

En la versión horizontal se usa `rows`, `horizontalItemSpacing`, `fillMaxHeight()` y `width(randomHeight)` en lugar de sus equivalentes verticales.

## 3.11. Gestionar el estado de los componentes Lazy

Android **gestiona automáticamente el estado del scroll**, como se ve en la definición de los componentes:

```kotlin
@Composable
fun LazyVerticalStaggeredGrid(
    columns: StaggeredGridCells,
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalItemSpacing: Dp = 0.dp,
    // …
)
```

Aun así, es interesante crear una **variable propia** para el estado y así saber, por ejemplo, en qué dirección se desplaza el usuario, cuánto ha desplazado o cuál es el índice del primer elemento visible.

En este ejemplo:

1. Se crea un estado propio para el scroll y se asigna al componente con `state`.
2. Se crea otro estado para mostrar u ocultar un botón. Como **depende del anterior**, se usa **`derivedStateOf`**: cambia cuando el primer elemento ya no se ve completo.
3. El botón usa una **corrutina** (las verás en la UP06) para volver al principio de la lista.

```kotlin
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize().padding(8.dp)
) {
    val dragonballCharacters = listOf("Son Goku", "Vegeta", "Piccolo", "Son Gohan", "Trunks",
        "Bulma", "Krilin", "Master Roshi", "Freezer", "Cell")

    val scrollState = rememberLazyStaggeredGridState()
    val showEndTextGrid by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }
    val coroutineGridScope = rememberCoroutineScope()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState,
        modifier = Modifier.weight(.5f)
    ) {
        item {
            OutlinedCard(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Personajes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
            }
        }

        items(dragonballCharacters) { character ->
            val randomHeight = ((2..30).random() * 15).dp
            Card(
                modifier = Modifier.background(Color.LightGray).padding(8.dp).fillMaxWidth().height(randomHeight)
            ) {
                Text(text = character, modifier = Modifier.height(80.dp).padding(8.dp))
            }
        }
    }
    if (showEndTextGrid) {
        Button(
            onClick = {
                coroutineGridScope.launch {
                    scrollState.animateScrollToItem(0, 0)
                }
            }
        ) {
            Text(text = "Volver arriba")
        }
    }
}
```

## 3.12. ListItem

Aunque en una columna puedes añadir cualquier componente, `ListItem` da un **estilo uniforme** a los elementos de una lista. Tiene cinco zonas:

| Zona | Uso habitual |
|:---|:---|
| `headlineContent` | Texto principal (obligatorio). |
| `overlineContent` | Texto pequeño encima del principal. |
| `supportingContent` | Texto de apoyo debajo del principal. |
| `leadingContent` | Al principio: normalmente un icono o una imagen. |
| `trailingContent` | Al final: un icono o un texto. |

Si hay `overlineContent`, el contenido de `leadingContent` y `trailingContent` se alinea arriba; si no, se centra verticalmente.

```kotlin
ListItem(
    headlineContent = { Text(text = "Headline text") },
    overlineContent = { Text(text = "Overline text") },
    supportingContent = { Text(text = "Supporting text") },
    leadingContent = {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorito"
        )
    },
    trailingContent = {
        Text(text = "Trailing")
    }
)
```

## 3.13. Scaffold

`Scaffold` es la **estructura por defecto** para crear interfaces complejas siguiendo Material. Incorpora huecos para los componentes habituales:

| Parámetro | Para qué sirve |
|:---|:---|
| `topBar` | Barra de navegación superior. |
| `bottomBar` | Barra de navegación inferior. |
| `snackbarHost` | Mensajes temporales (sustituyen a los antiguos `Toast`). |
| `floatingActionButton` | Botón flotante, normalmente abajo a la derecha. |
| `content` | Contenido principal. Es el **único obligatorio** y, como es la última lambda, se escribe fuera de los paréntesis. |

```kotlin
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
)
```

Un proyecto nuevo de Android Studio ya incluye un `Scaffold` en `MainActivity`. Su estructura completa:

```kotlin
Scaffold(
    topBar = {
    },
    bottomBar = {
    },
    snackbarHost = {
    },
    floatingActionButton = {
    },
    floatingActionButtonPosition = FabPosition.End
) {
    Column(
        modifier = Modifier
            .padding(it)
    ) {
    }
}
```

::: warning-box No olvides el padding
El contenido recibe unos `PaddingValues` con el espacio que ocupan las barras. Si no los aplicas con `Modifier.padding(it)`, el contenido quedará **tapado** por la `topBar` y la `bottomBar`.
:::

`topBar`, `bottomBar`, `snackbarHost` y `floatingActionButton` son de tipo `@Composable`, así que admiten **cualquier componente**. Jetpack Compose ofrece componentes **específicos** para ellos (y se recomienda usarlos), que también se pueden usar sin `Scaffold`.

### TopAppBar

`TopAppBar` crea una barra superior con un **título** (`title`, único obligatorio), un icono de navegación a la izquierda (`navigationIcon`) e iconos de acción a la derecha (`actions`):

```kotlin
topBar = {
    TopAppBar(
        title = { Text(text = "Título Top Bar") },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú"
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Cuenta"
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Configuración"
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Color.Cyan,
            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Yellow
        )
    )
},
```

Hay tres versiones más:

| Versión | Diferencia |
|:---|:---|
| `CenterAlignedTopAppBar` | El título aparece centrado. |
| `MediumTopAppBar` | El título se sitúa una línea más abajo. |
| `LargeTopAppBar` | Como la mediana, pero con el título más grande. |

### DropdownMenu en la TopAppBar

Es habitual que un icono de la `TopAppBar` (en `navigationIcon` o en `actions`) abra un **menú desplegable**. Se crea con `DropdownMenu` y `DropdownMenuItem`:

```kotlin
navigationIcon = {
    var expanded by rememberSaveable { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menú"
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Editar") },
            onClick = { /* TODO */ },
            leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar") }
        )
        DropdownMenuItem(
            text = { Text("Configuración") },
            onClick = { /* TODO */ },
            leadingIcon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Configuración") }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text("Envía comentarios") },
            onClick = { /* TODO */ },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
            trailingIcon = { Icon(imageVector = Icons.Default.PrivacyTip, contentDescription = "Ayuda") }
        )
    }
},
```

### FloatingActionButton

Los **FAB** representan la **acción más importante** de una pantalla. Tienen un estilo predeterminado y flotan sobre el resto de elementos. Se usan sobre todo con `Scaffold`, pero también de forma independiente.

| Componente | Uso |
|:---|:---|
| `FloatingActionButton` | Solo un icono. |
| `SmallFloatingActionButton` | Icono, para pantallas pequeñas. |
| `LargeFloatingActionButton` | Icono, para pantallas grandes. |
| `ExtendedFloatingActionButton` | Icono y texto, cuando el FAB necesita algo más de información. |

En un `Scaffold` se sitúa en la parte inferior del contenido; con `floatingActionButtonPosition` indicas si aparece en el centro o al final (según el sentido de lectura):

```kotlin
floatingActionButton = {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir"
        )
    }
},
floatingActionButtonPosition = FabPosition.End
```

### BottomBar

Una barra inferior con iconos que normalmente **cambia el contenido** del `Scaffold`. Hay tres posibilidades:

- **`BottomAppBar` con iconos o texto**: se alinean a la izquierda. Si usas `NavigationBarItem`, se reparten en el espacio disponible.
- **`BottomAppBar` con iconos y FAB**: tiene una sobrecarga con los parámetros `actions` y `floatingActionButton`. Si ya has configurado el FAB en el `Scaffold`, **no** uses esta versión.
- **`NavigationBar`**: barra con **tres, cuatro o cinco** iconos, cada uno un `NavigationBarItem`.

::: tabs

== BottomAppBar

```kotlin
bottomBar = {
    BottomAppBar {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Cake, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Face4, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Handyman, contentDescription = null)
        }
    }
},
```

== BottomAppBar con FAB

```kotlin
bottomBar = {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Cake, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face4, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Handyman, contentDescription = null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    )
},
```

== NavigationBar

```kotlin
bottomBar = {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Contacts, contentDescription = null) },
            label = { Text("Contactos") },
            selected = true,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Cake, contentDescription = null) },
            label = { Text("Cumpleaños") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.HelpOutline, contentDescription = null) },
            label = { Text("Ayuda") },
            selected = false,
            onClick = { /* TODO */ }
        )
    }
},
```

Quien desarrolla la aplicación debe implementar cómo se sabe **qué botón está seleccionado** (normalmente con un estado).

:::

### Snackbar

Los `Snackbar` son **mensajes temporales** en la parte inferior de la pantalla. Necesitan un **estado**:

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
```

En el `Scaffold` se configura en `snackbarHost`, donde también puedes darle estilo:

::: tabs

== Por defecto

```kotlin
snackbarHost = {
    SnackbarHost(hostState = snackbarHostState)
},
```

== Con estilo

```kotlin
snackbarHost = {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                snackbarData = it,
                containerColor = Color.LightGray,
                contentColor = Color.Blue
            )
        }
    )
},
```

:::

Para mostrarlo se necesita una **corrutina** (UP06). Desde cualquier lugar de la interfaz donde pueda ir un bloque de instrucciones puedes lanzar el `Snackbar` con el mensaje que quieras:

```kotlin
val coroutineScope = rememberCoroutineScope()

Column(
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    for (i in 1..5) {
        Card(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Objeto $i",
                        duration = SnackbarDuration.Long
                    )
                }
            },
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "Objeto $i",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
```

## 3.14. ConstraintLayout

Los layouts anteriores colocan los elementos automáticamente. **`ConstraintLayout`** obliga a **indicar de forma expresa cómo se unen** unos elementos con otros. Esas restricciones se mantienen aunque cambie la orientación o el tamaño de pantalla.

Es uno de los layouts más usados con las **vistas XML** antiguas, así que conviene conocerlo para **migrar de XML a Compose**, y además da mucha libertad para diseñar interfaces.

### Dependencias

::: tabs

== libs.versions.toml

```toml [gradle/libs.versions.toml]
[versions]
# …
constraintlayout = "2.2.0"
constraintlayoutcompose = "1.1.0"

[libraries]
# …
androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
androidx-constraintlayout-compose = { group = "androidx.constraintlayout", name = "constraintlayout-compose", version.ref = "constraintlayoutcompose" }
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
dependencies {
    // …
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.constraintlayout.compose)
}
```

:::

Tras sincronizar Gradle ya puedes usar `ConstraintLayout` y añadir en él los componentes y sus restricciones.

### Referencias

Para indicar cómo se unen los componentes, cada uno necesita una **referencia**. Se pueden crear de una en una o varias a la vez, y se asignan con el modificador **`constrainAs`**:

```kotlin
ConstraintLayout(
    modifier = Modifier.fillMaxSize()
) {
    val titleRef = createRef()
    val (iconRef, greetingRef, friendsTitleRef, friendsListRef) = createRefs()

    Text(
        text = "Constraint Layout",
        fontSize = 20.sp,
        modifier = Modifier.constrainAs(titleRef) {
            // Añadir restricciones
        }
    )
    // …
}
```

### Puntos de unión

Los elementos se unen entre sí y también al propio contenedor, cuya referencia es **`parent`**. Los puntos de unión son:

| Eje | Puntos |
|:---|:---|
| Vertical | `top`, `baseline` (línea base del texto), `bottom` |
| Horizontal | `start`, `end` (dependen del sentido de lectura del idioma) |

Las uniones se escriben con `linkTo`:

```kotlin
ConstraintLayout(modifier = Modifier.fillMaxSize()) {
    val titleRef = createRef()
    val (iconRef, greetingRef, friendsTitleRef, friendsListRef) = createRefs()

    Text(
        text = "Constraint Layout",
        fontSize = 40.sp,
        modifier = Modifier.constrainAs(titleRef) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    )
    Icon(
        imageVector = Icons.TwoTone.Android,
        contentDescription = "Android",
        modifier = Modifier.size(120.dp).constrainAs(iconRef) {
            top.linkTo(titleRef.bottom)
            start.linkTo(greetingRef.end)
            end.linkTo(parent.end)
        }
    )
    Text(
        text = "Hola Rick!",
        modifier = Modifier.constrainAs(greetingRef) {
            top.linkTo(iconRef.top)
            bottom.linkTo(iconRef.bottom)
            start.linkTo(parent.start)
            end.linkTo(iconRef.start)
        }
    )
    Text(
        text = "Amigos:",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.constrainAs(friendsTitleRef) {
            top.linkTo(iconRef.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    )
    Column(
        modifier = Modifier.padding(16.dp).constrainAs(friendsListRef) {
            top.linkTo(friendsTitleRef.bottom)
        }
    ) {
        listOf("Hombre pájaro", "Gearhead", "Squanchy", "Unidad",
            "Dr. Xenon Bloom", "Presidente Obama", "Jaguar").forEach {
            ListItem(
                headlineContent = { Text(it) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Destino: $it",
                        modifier = Modifier.size(50.dp)
                    )
                }
            )
            HorizontalDivider()
        }
    }
}
```

### Guías

Dentro de `ConstraintLayout` puedes crear **guías** a las que unir los componentes. Se indican con un **porcentaje** (`Float` de 0 a 1) o directamente en **dp**:

```kotlin
val topGuide = createGuidelineFromTop(0.05f)
val startGuide = createGuidelineFromStart(0.1f)
val endGuide = createGuidelineFromEnd(0.1f)
val friendsGuide = createGuidelineFromTop(0.4f)

Text(
    text = "Constraint Layout",
    modifier = Modifier
        .background(Color.LightGray)
        .constrainAs(titleRef) {
            top.linkTo(topGuide)
            start.linkTo(startGuide)
            end.linkTo(endGuide)
        }
)
```

### Barreras

Una **barrera** agrupa dos o más componentes y sirve de guía para que otros se unan a ella. Por ejemplo, una barrera al final de dos cajas se desplaza según la que sea más ancha:

```kotlin
ConstraintLayout(modifier = Modifier.fillMaxSize()) {
    val (lightBox, darkBox, blackBox) = createRefs()
    val barrier = createEndBarrier(lightBox, darkBox)

    Box(
        modifier = Modifier.background(Color.LightGray)
            .size(100.dp)
            .constrainAs(lightBox) {
                top.linkTo(parent.top, 20.dp)
            }
    )
    Box(
        modifier = Modifier.background(Color.DarkGray)
            .size(50.dp) // pruébalo también con 120.dp
            .constrainAs(darkBox) {
                top.linkTo(lightBox.bottom)
                start.linkTo(lightBox.start, 20.dp)
            }
    )
    Box(
        modifier = Modifier.background(Color.Black)
            .size(50.dp)
            .constrainAs(blackBox) {
                top.linkTo(parent.top, 90.dp)
                start.linkTo(barrier)
            }
    )
}
```

Si la caja oscura mide 50 dp, la negra se coloca tras la caja clara; si mide 120 dp, la barrera se mueve y la negra se coloca tras la oscura.

## 3.15. Componentes modales: ModalNavigationDrawer

Los componentes **modales** aparecen **sobre la pantalla**: `ModalNavigationDrawer`, `ModalBottomSheet`, `ModalSideSheet`… El más conocido es `ModalNavigationDrawer`, el **menú hamburguesa**, que al pulsarlo abre un menú lateral.

::: info-box Pantallas grandes
En pantallas grandes o en horizontal este menú debería estar **siempre visible**, porque sobra espacio. En esos casos se sustituye por un `NavigationDrawer` permanente o un `NavigationRail`.
:::

El siguiente ejemplo, para una pantalla pequeña, usa conceptos que verás más adelante (corrutinas y navegación). Los datos de los botones están en una **`enum class`**, que debe declararse **fuera** de cualquier componente `@Composable`:

```kotlin
enum class Options(val title: String, val icon: ImageVector) {
    INIT("Inicio", Icons.Default.Home),
    EDIT("Editar", Icons.Default.Edit),
    SETTINGS("Configuración", Icons.Default.Settings),
    HELP("Ayuda", Icons.AutoMirrored.Filled.Help),
}
```

```kotlin
val options = listOf(Options.INIT, Options.EDIT, Options.SETTINGS, Options.HELP)
val drawerState = rememberDrawerState(DrawerValue.Closed)
var selectedOption by rememberSaveable { mutableStateOf(options[0].title) }
val scope = rememberCoroutineScope()

Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = selectedOption) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (drawerState.isOpen) scope.launch { drawerState.close() }
                        else scope.launch { drawerState.open() }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)
        )
    }
) { innerPadding ->
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                options.forEach { option ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(option.icon, contentDescription = option.title)
                        },
                        label = {
                            Text(text = option.title)
                        },
                        selected = option.title == selectedOption,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedOption = option.title
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        modifier = Modifier.padding(innerPadding)
    ) {
        // Cargar contenido según la opción elegida: selectedOption
    }
}
```

## 3.16. HorizontalPager y VerticalPager

`HorizontalPager` y `VerticalPager` crean **varias páginas** por las que se navega **deslizando el dedo**:

```kotlin
val colors = listOf(Color.LightGray, Color.Magenta, Color.Cyan, Color.Yellow, Color.White)
val pagerState = rememberPagerState(
    pageCount = {
        colors.size
    }
)

HorizontalPager(
    state = pagerState
) { page ->
    Text(
        text = "Página $page",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .background(colors[page])
            .fillMaxSize()
    )
}
```

Modificando el estado del *pager* también puedes **navegar con botones**. `animateScrollToPage` hace que el cambio sea animado (`scrollToPage` lo hace sin animación). Incluso puedes desactivar los gestos y manejar las páginas solo con botones.

```kotlin
enum class PaginationButtons(val icon: ImageVector, val description: String) {
    First(Icons.Default.FirstPage, "Primero"),
    Previous(Icons.AutoMirrored.Filled.NavigateBefore, "Anterior"),
    Next(Icons.AutoMirrored.Filled.NavigateNext, "Siguiente"),
    Last(Icons.AutoMirrored.Filled.LastPage, "Último"),
}
```

```kotlin
val colors = listOf(Color.LightGray, Color.Magenta, Color.Cyan, Color.Yellow, Color.White)
val characters = listOf("Rick Sanchez", "Morty Smith", "Summer Smith", "Hombrepájaro", "Unidad")
val pagerState = rememberPagerState(pageCount = { characters.size })
val coroutineScope = rememberCoroutineScope()
val positions = mapOf(
    PaginationButtons.First to 0,
    PaginationButtons.Previous to (pagerState.currentPage - 1).coerceAtLeast(0),
    PaginationButtons.Next to (pagerState.currentPage + 1).coerceAtMost(characters.size - 1),
    PaginationButtons.Last to characters.size - 1
)
HorizontalPager(
    state = pagerState,
    modifier = Modifier.weight(9.5f)
) { page -> // Cargar contenido según la página en la que se esté: page
    Text(
        text = characters[page],
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(20.dp)
            .background(colors[page])
            .fillMaxSize()
    )
}

Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) {
    positions.forEach { (paginationButton, page) ->
        IconButton(
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(page)
                    //pagerState.scrollToPage(page)
                }
            }
        ) {
            Icon(
                imageVector = paginationButton.icon,
                contentDescription = paginationButton.description
            )
        }
    }
}
```

## 3.17. TooltipBox

Los **tooltips** son pequeños mensajes que aparecen al hacer una **pulsación larga** sobre un elemento. Para que un componente tenga tooltip, se envuelve en un **`TooltipBox`**:

```kotlin
var isLiked by rememberSaveable { mutableStateOf(false) }
TooltipBox(
    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
    tooltip = {
        PlainTooltip {
            Text(text = if (isLiked) "Quitar de favoritos" else "Añadir a favoritos")
        }
    },
    state = rememberTooltipState()
) {
    IconButton(onClick = { isLiked = !isLiked }) {
        Icon(
            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "botón favoritos",
            tint = if (isLiked) Color.Red else Color.LightGray
        )
    }
}
```

También existe **`RichTooltip`**, que permite que el mensaje emergente contenga **acciones**.

::: tip-box Practica
Ya puedes hacer la [Actividad 4 — Dragon Ball](../ejercicios/#actividad-4).
:::
