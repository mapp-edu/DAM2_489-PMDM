---
layout: doc
title: 1. Corrutinas y Side Effects
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Corrutinas y Side Effects

## 1.1. Introducción: el hilo principal

En Android, el **hilo principal** (*main thread* o *UI thread*) es el encargado de dibujar la interfaz de usuario y de gestionar todas las interacciones del usuario con la aplicación.

Esto implica que, si el hilo principal se pone a ejecutar una tarea pesada o asíncrona, **toda la interfaz se queda bloqueada** y el usuario no puede interactuar con el dispositivo hasta que la tarea termina.

- **Tareas pesadas**: las que tienen una duración larga.
- **Tareas asíncronas**: las que se podrían realizar en segundo plano (acceso a red, a una base de datos, a ficheros…).

## 1.2. Corrutinas

Kotlin dispone de las **corrutinas** para ejecutar acciones sin bloquear el hilo principal.

Las corrutinas son más eficientes que los hilos porque reparten su trabajo entre los hilos disponibles, ejecutando las instrucciones con distintas configuraciones (**contexto** + **dispatcher**). Además, el número de hilos de un sistema es limitado, pero la cantidad de corrutinas que se pueden lanzar es prácticamente ilimitada.

Así, cuando se quiere ejecutar un conjunto de instrucciones sin bloquear el hilo principal, se crea una corrutina y se le indica la configuración con la que debe ejecutarse; el sistema se encarga de gestionarla usando los hilos que considere necesarios.

::: tip-box Más información
- [Corrutinas en Kotlin](https://kotlinlang.org/docs/coroutines-overview.html)
- [Corrutinas de Kotlin en Android](https://developer.android.com/kotlin/coroutines)
:::

## 1.3. Funciones de suspensión

Las **funciones de suspensión** (`suspend fun`) son aquellas en las que alguna de sus instrucciones *suspende* la ejecución de la propia función hasta que dicha instrucción termina (por ejemplo, `delay()`), pero **sin bloquear el hilo** en el que se ejecutan.

Estas funciones siempre deben ejecutarse **dentro de una corrutina** o dentro del **ámbito de una corrutina** (*coroutine scope*).

```kotlin
@Composable
fun SplashScreen(
    navigateToOnboarding: () -> Unit,
) {
    LaunchedEffect(key1 = true) {    // this: CoroutineScope
        delay(timeMillis = 2000)      // función de suspensión
        navigateToOnboarding()
    }
}
```

Android Studio señala en el margen del editor, con un icono de flecha discontinua, las líneas en las que se llama a una función de suspensión, es decir, las instrucciones que pueden suspender la ejecución de la función.

## 1.4. Side Effects (efectos secundarios)

Como ya se ha estudiado, Jetpack Compose **recompone** la interfaz de usuario cada vez que se produce un cambio de estado. En ocasiones la aplicación recompone más o menos veces de las necesarias, o al recomponer se ejecuta código que no debería ejecutarse. A todas estas situaciones no deseadas se las llama **Side Effects** (efectos secundarios).

Todos los componentes de la aplicación deberían evitar los efectos secundarios, pero hay ocasiones en las que **se necesitan**: por ejemplo, para eventos únicos como mostrar una notificación o navegar a otra pantalla cuando un estado cumple una condición.

### Ejemplo: un Toast que aparece cuando no debe

El siguiente formulario de inicio de sesión produce un efecto secundario:

```kotlin
var user by rememberSaveable { mutableStateOf("") }
var password by rememberSaveable { mutableStateOf("") }
var errorMessage by rememberSaveable { mutableStateOf("") }
val context = LocalContext.current

Text(
    text = "Iniciar sesión",
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
)
TextField(
    label = { Text(text = "Usuario") },
    value = user,
    onValueChange = { user = it },
    modifier = Modifier.fillMaxWidth().padding(8.dp)
)
TextField(
    label = { Text(text = "Contraseña") },
    value = password,
    onValueChange = { password = it },
    modifier = Modifier.fillMaxWidth().padding(8.dp)
)
Button(
    onClick = {
        errorMessage = if (user == "Rick" && password == "Sanchez") {
            "Usuario logueado!"
        } else {
            "Error"
        }
    },
    modifier = Modifier.fillMaxWidth()
) {
    Text(text = "Iniciar sesión")
}

if (errorMessage.isNotEmpty()) {
    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
}
```

Al pulsar el botón se cambia el mensaje que se muestra al usuario. Pero, como a partir de ese momento `errorMessage` ya no está vacío, **cada vez que se escriba en cualquiera de los `TextField`** (lo que provoca una recomposición) se volverá a mostrar el `Toast`.

## 1.5. Effect Handlers

Para resolver estos problemas se crearon los **Effect Handlers** (manejadores de efectos), que permiten ejecutar estas acciones en un entorno controlado. Además, los Effect Handlers facilitan el uso de corrutinas dentro de Jetpack Compose.

Los Effect Handlers disponibles son:

| Effect Handler | Uso principal |
|---|---|
| `LaunchedEffect` | Ejecutar una corrutina ligada a la composición, que se relanza cuando cambian sus claves. |
| `rememberCoroutineScope` | Obtener un ámbito de corrutina para lanzar corrutinas fuera de un `@Composable` (p. ej., en un `onClick`). |
| `derivedStateOf` | Crear un estado derivado de otros que solo notifica cuando su valor cambia. |
| `SideEffect` | Publicar el estado de Compose a código no gestionado por Compose tras cada recomposición correcta. |
| `rememberUpdatedState` | Referenciar el último valor de un parámetro dentro de un efecto de larga duración sin reiniciarlo. |
| `DisposableEffect` | Efectos que necesitan limpieza (`onDispose`) al salir de la composición o cambiar sus claves. |
| `produceState` | Convertir una fuente de datos externa (no Compose) en un estado de Compose. |
| `snapshotFlow` | Convertir estados de Compose en un `Flow`. |

En esta unidad se trabajan los tres primeros.

## 1.6. LaunchedEffect

Un bloque `LaunchedEffect` **se ejecuta siempre la primera vez que se compone el componente** en el que está incluido. **Después solo se vuelve a ejecutar si cambia alguno de los parámetros (claves) que recibe.**

`LaunchedEffect` admite hasta tres parámetros clave (`key1`, `key2`, `key3`) o un listado de claves (`vararg keys`).

```kotlin
@Composable
fun SplashScreen(
    navigateToOnboarding: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        delay(timeMillis = 2000)
        navigateToOnboarding()
    }
}
```

En este ejemplo, **el bloque solo se ejecutará una vez** (al componerse por primera vez el componente) **aunque `SplashScreen` se recomponga**, ya que la clave que recibe siempre tiene el mismo valor (`true`).

### LaunchedEffect es una corrutina

Un bloque `LaunchedEffect` es una corrutina, por lo que **permite ejecutar funciones de suspensión dentro de un componente `@Composable`** (como el `delay()` del ejemplo anterior).

- Si el bloque se está ejecutando y alguna de sus claves cambia, la corrutina en curso **se cancela y vuelve a empezar**.
- La corrutina se cancela cuando el `LaunchedEffect` **abandona la composición** (deja de estar en pantalla).

### Aplicación al ejemplo del login

Se envuelve el `Toast` en un `LaunchedEffect` cuya clave es `errorMessage`:

```kotlin
Button(
    onClick = {
        errorMessage = if (user == "Rick" && password == "Sanchez") {
            "Usuario logueado!"
        } else {
            "Error"
        }
    },
    modifier = Modifier.fillMaxWidth()
) {
    Text(text = "Iniciar sesión")
}
LaunchedEffect(key1 = errorMessage) {
    if (errorMessage.isNotEmpty()) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
```

Ahora el bloque se ejecuta cada vez que cambia el estado `errorMessage`, y no en cada recomposición.

Pero esta primera aproximación **no funciona del todo bien**: el mensaje solo cambia cuando pasa de `"Usuario logueado!"` a `"Error"` o viceversa. Si se produce un error y, tras modificar los datos, se vuelve a producir el mismo error, el valor de `errorMessage` no cambia y **no se vuelve a mostrar el mensaje**.

### Solución completa

Para solucionarlo basta con **vaciar `errorMessage` después de mostrar el `Toast`**:

```kotlin{39}
var user by rememberSaveable { mutableStateOf("") }
var password by rememberSaveable { mutableStateOf("") }
var errorMessage by rememberSaveable { mutableStateOf("") }
val context = LocalContext.current

Text(
    text = "Iniciar sesión",
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
)
TextField(
    label = { Text(text = "Usuario") },
    value = user,
    onValueChange = { user = it },
    modifier = Modifier.fillMaxWidth().padding(8.dp)
)
TextField(
    label = { Text(text = "Contraseña") },
    value = password,
    onValueChange = { password = it },
    modifier = Modifier.fillMaxWidth().padding(8.dp)
)
Button(
    onClick = {
        errorMessage = if (user == "Rick" && password == "Sanchez") {
            "Usuario logueado!"
        } else {
            "Error"
        }
    },
    modifier = Modifier.fillMaxWidth()
) {
    Text(text = "Iniciar sesión")
}
LaunchedEffect(key1 = errorMessage) {
    if (errorMessage.isNotEmpty()) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
    errorMessage = ""
}
```

Así, cada pulsación del botón provoca un cambio real del estado (de `""` a `"Error"` o a `"Usuario logueado!"`) y el `Toast` se muestra siempre que corresponde.

## 1.7. rememberCoroutineScope

`rememberCoroutineScope` permite obtener un **ámbito seguro** (*scope*) en el que ejecutar una corrutina. Se utiliza cuando se necesita **ejecutar una función de suspensión fuera del ámbito de un componente `@Composable`**, por ejemplo **dentro de un `onClick`**.

En ese caso no se puede usar `LaunchedEffect`, porque `onClick` no es un contexto `@Composable`. El siguiente código **no compila**:

```kotlin
Button(
    onClick = {
        errorMessage = if (user == "Rick" && password == "Sanchez") {
            "Usuario logueado!"
        } else {
            "Error"
        }
        // ❌ Error: LaunchedEffect solo puede llamarse desde un @Composable
        LaunchedEffect(key1 = errorMessage) {
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            errorMessage = ""
        }
    },
    modifier = Modifier.fillMaxWidth()
) {
    Text(text = "Iniciar sesión")
}
```

Lo que se necesita aquí es el **ámbito de una corrutina**. Primero se obtiene con `rememberCoroutineScope()` y después, para ejecutar la corrutina, se usa la función **`launch`**:

```kotlin
val coroutineScope = rememberCoroutineScope()
Button(
    onClick = {
        errorMessage = if (user == "Rick" && password == "Sanchez") {
            "Usuario logueado!"
        } else {
            "Error"
        }
        coroutineScope.launch {    // this: CoroutineScope
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    },
    modifier = Modifier.fillMaxWidth()
) {
    Text(text = "Iniciar sesión")
}
```

Este Effect Handler ya se ha utilizado anteriormente para **mover el scroll automáticamente** desde fuera de un `@Composable`. Mover el scroll es una función de suspensión, y al hacerlo al pulsar un icono o un botón se necesita el ámbito de la corrutina:

```kotlin
Icon(
    imageVector = Icons.Default.FirstPage,
    contentDescription = "Primero",
    modifier = Modifier.clickable {
        if (pagerState.currentPage != 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(page = 0)
            }
        }
    }
)
```

### Dispatchers: en qué hilo se ejecuta la corrutina

El ámbito que devuelve `rememberCoroutineScope` está ligado al **hilo principal**, así que, si no se indica nada, la corrutina lanzada con `launch` se ejecuta en él. Esto puede ser problemático si dentro se realizan tareas pesadas, ya que se podría bloquear la interfaz de usuario.

La función `launch` permite indicar en qué **contexto** (*dispatcher*) se quiere ejecutar la corrutina:

| Dispatcher | Uso |
|---|---|
| `Dispatchers.Main` | Hilo principal (interfaz de usuario). |
| `Dispatchers.IO` | Tareas de entrada/salida: red, base de datos, ficheros… |
| `Dispatchers.Default` | Conjunto de hilos compartido optimizado para tareas de cálculo intensivo (CPU). |
| `Dispatchers.Unconfined` | No confina la corrutina a ningún hilo concreto (no aconsejado). |

::: info-box Si no se indica dispatcher
Sin argumento, `launch` **hereda el contexto del ámbito** desde el que se lanza. Con `rememberCoroutineScope` y con `viewModelScope` ese contexto es `Dispatchers.Main`.
:::

**Criterio práctico:**

- Si las acciones de `launch` están **relacionadas con la interfaz de usuario**, lo más conveniente es no indicar nada o indicar `Dispatchers.Main`:

```kotlin
coroutineScope.launch {
    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
}
```

- Si las acciones están destinadas a **obtener datos de fuentes externas o del almacenamiento**, o no están relacionadas con la interfaz, lo más conveniente es indicar `Dispatchers.IO`:

```kotlin
coroutineScope.launch(Dispatchers.IO) {
    // Instrucciones de la corrutina
}
```

## 1.8. derivedStateOf

El manejador `derivedStateOf` permite crear un **estado que depende de uno o varios estados** más. De esta manera se **evitan recomposiciones innecesarias**: se podría decir que `derivedStateOf` no provoca recomposiciones hasta que el valor del estado derivado es **diferente al valor anterior**.

### El problema

```kotlin
fun isUsernameValid(username: String): Boolean {
    Log.i("-->", "En función")
    return username.isNotBlank()
}

var username by rememberSaveable { mutableStateOf("") }
val buttonEnabled = isUsernameValid(username)

TextField(
    value = username,
    onValueChange = { username = it }
)
Button(
    enabled = buttonEnabled,
    onClick = { /*TODO*/ }
) {
    Text(text = "Acceder")
}
```

Cada vez que cambia el estado `username` se ejecuta `isUsernameValid`, que recalcula `buttonEnabled`, y el botón **se recompone con cada letra** que se introduce. En el Logcat se aprecia cómo se llama a la función (`En función`) con cada pulsación de tecla.

¿Tiene sentido que se recomponga el botón si `buttonEnabled` es `true` y, al introducir una letra más, sigue siendo `true`?

### La solución

Al indicar que `buttonEnabled` es un **estado derivado** de `username`, se evita recomponer la vista tantas veces:

```kotlin{6-10}
fun isUsernameValid(username: String): Boolean {
    return username.isNotBlank()
}

var username by rememberSaveable { mutableStateOf("") }
val buttonEnabled by remember {
    derivedStateOf {
        isUsernameValid(username)
    }
}

TextField(
    value = username,
    onValueChange = { username = it }
)
Button(
    enabled = buttonEnabled,
    onClick = { /*TODO*/ }
) {
    Text(text = "Acceder")
}
```

Ahora el botón **solo se recompone cuando `buttonEnabled` cambia de valor** (pasa de `true` a `false` o viceversa). Si `buttonEnabled` vale `true` y al introducir una letra sigue siendo `true`, la vista no se recompone.

::: tip-box Cuándo usarlo
`derivedStateOf` compensa cuando el estado de entrada cambia **mucho más a menudo** que el resultado que se necesita (texto que se escribe → botón activo/inactivo, posición del scroll → mostrar/ocultar un botón "volver arriba"…). Si ambos cambian a la vez, no aporta nada.
:::
