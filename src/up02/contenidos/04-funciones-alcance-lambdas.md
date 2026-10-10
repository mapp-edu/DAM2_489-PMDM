---
layout: doc
title: 4. Funciones de alcance y lambdas
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 4. Funciones de alcance y lambdas

## 4.1. Funciones de alcance

Kotlin ofrece las llamadas ***scope functions*** (funciones de alcance), que ejecutan un bloque de código **en el contexto del objeto que las llama**: dentro del bloque, ese objeto está disponible directamente. Hay cinco: `let`, `run`, `with`, `apply` y `also`.

| Función | Uso | Contexto | Devuelve |
|:---|:---|:---|:---|
| `let` | Ejecutar un bloque asegurando que el objeto no es `null` | El objeto, con `it` | El resultado de la última instrucción |
| `apply` | Configurar un objeto | El objeto, con `this` | El propio objeto |
| `run` | Configurar un objeto y operar con él | El objeto, con `this` | El resultado de la última instrucción |
| `run` (sin objeto) | Ejecutar instrucciones donde se necesita una expresión | — | El resultado de la última instrucción |
| `also` | Hacer acciones adicionales con el objeto | El objeto, con `it` | El propio objeto |
| `with` | Agrupar llamadas sobre un objeto | El objeto, con `this` | El resultado de la última instrucción |

Todas devuelven un valor, pero no es obligatorio guardarlo en una variable. Todas se usan con un bloque entre llaves (una **lambda**), y las **pistas del IDE** ayudan a entenderlas: indican el contexto (`it: Product`, `this: Product`) y marcan con `^let`, `^run`… el valor que se devuelve (aparece una vez por cada camino posible).

![Pistas del IDE en una función let: contexto y valor devuelto](/img/contenidos/up02/up02-4-p04-2.webp)

Para los ejemplos usaremos esta clase:

```kotlin
class Product(var name: String, var price: Double, var year: Int) {
    fun addPercentageToPrice(percentage: Double) {
        price += price * percentage / 100
    }
}
```

## 4.2. `let`

- **Contexto**: el objeto que la llama, accesible con `it`.
- **Devuelve**: el resultado de la última instrucción.

```kotlin
val product = Product("PS5", 495.50, 2021)
var result = product.let {
    println("Si el producto es antiguo se aplica un descuento")
    if (it.year < 2023) it.price * 0.85
    else it.price
}
```

Combinada con `?.`, `let` **asegura que el objeto no es `null`** antes de ejecutar el bloque: si lo es, el bloque no se ejecuta.

```kotlin
var product: Product? = Product("PS5", 495.50, 2021)
val number = (1..100).random()
if (number % 2 == 0) product = null

var result = product?.let {
    println("Si el producto es antiguo se aplica un descuento")
    if (it.year < 2023) it.price * 0.85
    else it.price
}
```

## 4.3. `run`

Tiene dos usos.

**1. Llamada desde un objeto**, como `let`, pero sin controlar si es `null`.

- **Contexto**: el objeto que la llama, accesible con `this` (no hace falta escribir `this.` para acceder a sus propiedades).
- **Devuelve**: el resultado de la última instrucción.

```kotlin
val product = Product("PS5", 495.50, 2021)
val result = product.run {
    // equivale a this.name += …
    name += " [OFERTA]"
    if (price < 1000.0) addPercentageToPrice(12.5)
    price
}
println("Producto: ${product.name} ${product.price}")
println(result)
```

```txt
Producto: PS5 [OFERTA] 557.4375
557.4375
```

**2. Llamada sin objeto.**

- **Contexto**: ninguno.
- **Devuelve**: el resultado de la última instrucción.

```kotlin
val name = "Rick Sanchez"
val containsR = run {
    var letterR = false
    for (letter in name) {
        if (letter == 'R') {
            letterR = true
            break
        }
    }
    letterR
}
```

## 4.4. `also`

Permite hacer **acciones adicionales** («además», *also*) con el objeto.

- **Contexto**: el objeto que la llama, accesible con `it`.
- **Devuelve**: el propio objeto (automáticamente).

```kotlin
val product = Product("Nintendo Switch", 350.0, 2021).also {
    if (it.price < 1000.0) it.addPercentageToPrice(12.5)
}

product.also {
    it.name = it.name.uppercase()
}

println(product.also {
    println("---> $it")        // Se imprime el producto
    println("Precio nuevo")
    it.price *= 0.85           // Se cambia el precio del producto
})                             // y println vuelve a imprimir el producto
```

## 4.5. `apply`

Se usa para **configurar** un objeto (asignar valores a sus propiedades).

- **Contexto**: el objeto que la llama, accesible con `this`.
- **Devuelve**: el propio objeto (automáticamente).

```kotlin
val product = Product("Nintendo Switch", 300.0, 2021)
product.apply {
    // equivale a this.name = "Switch OLED"
    name = "Switch OLED"
    price = 349.90
}
```

## 4.6. `with`

Permite **agrupar acciones** sobre un objeto. A diferencia de las anteriores, el objeto se pasa como **argumento**.

- **Contexto**: el objeto que se pasa, accesible con `this`.
- **Devuelve**: el resultado de la última instrucción.

```kotlin
val product = Product("Nintendo Switch", 300.0, 2021)
with(product) {
    println("""$name
              | Precio anterior: $price €""".trimMargin())
    if (price < 1000.0) addPercentageToPrice(12.5)
    println(" Precio nuevo: $price €")
    println(this)
}

val newProduct = with(product) {
    if (price < 1000.0) addPercentageToPrice(12.5)
    this   // se devuelve el propio objeto
}
```

## 4.7. Funciones lambda

Normalmente una función se declara y recibe un nombre para poder usarla:

```kotlin
fun add(number1: Int, number2: Int): Int {
    return number1 + number2
}
```

Las **funciones lambda**, también llamadas **funciones anónimas** o **funciones flecha**, no se declaran con nombre: se usan como una **expresión**.

### Sintaxis

Se escriben entre llaves `{ }`, pueden tener parámetros o no, y deben tener un cuerpo. Si no tienen parámetros, se escriben directamente las instrucciones. **La última expresión es el valor de retorno** (el IDE lo marca con `^lambda`):

```kotlin
{ println("Desde una lambda") }

{
    var text = "Desde una lambda"
    text += "!"
    text   // valor devuelto
}

{
    val first10EvenInts: MutableList<Int> = mutableListOf()
    for (i in 0 until 10) {
        first10EvenInts.add(i * 2)
    }
    first10EvenInts   // valor devuelto
}
```

::: tip-box Activar las pistas de las lambdas
Si no ves las pistas en IntelliJ IDEA o Android Studio, actívalas en **File → Settings → Editor → Inlay Hints → Lambdas → Kotlin** (`Ctrl + Alt + S`).

![Configuración de las pistas de lambdas](/img/contenidos/up02/up02-4-p15-1.webp)
:::

Si tienen parámetros, se separan del cuerpo con **`->`** (de ahí el nombre de funciones flecha):

```kotlin
{ name: String -> "Hola $name! Te saludo desde una lambda" }

{ x: Int, y: Int -> x + y }

{ init: Int, final: Int ->
    val evenInterval: MutableList<Int> = mutableListOf()
    for (i in init until final) {
        if (i % 2 == 0) evenInterval.add(i)
    }
    evenInterval
}
```

En los ejemplos anteriores Kotlin deduce los tipos, pero también se puede indicar el **tipo de la lambda**: `(tipos de los parámetros) -> tipo de retorno`.

```kotlin
val greeting: (String) -> String = { name: String ->
    "Hola $name! Te saludo desde una lambda"
}

val evenInterval: (Int, Int) -> MutableList<Int> = { init: Int, final: Int ->
    val evenInterval: MutableList<Int> = mutableListOf()
    for (i in init until final) {
        if (i % 2 == 0) evenInterval.add(i)
    }
    evenInterval
}
```

Si se indica el tipo y la lambda tiene **un solo parámetro**, se puede omitir y usar **`it`**:

```kotlin
val greeting: (String) -> String = {
    "Hola $it! Te saludo desde una lambda"
}
```

### Lambdas asignadas a variables

Como son expresiones, las lambdas deben asignarse a algo. Si se asignan a una variable, se usan como una función normal (esto no aporta nada nuevo):

```kotlin
val greeting = { name: String -> "Hola $name! Te saludo desde una lambda" }
println(greeting("Rick"))

val add: (Int, Int) -> Int = { number1: Int, number2: Int -> number1 + number2 }
println(add(3, 48))

val evenInterval = { init: Int, final: Int ->
    val evenInterval: MutableList<Int> = mutableListOf()
    for (i in init until final) {
        if (i % 2 == 0) evenInterval.add(i)
    }
    evenInterval
}
println(evenInterval(0, 25))
```

### Lambdas como parámetros

Lo que sí aporta una funcionalidad nueva es **pasar lambdas como parámetros** a otras funciones (funciones de orden superior). Es una práctica muy extendida en lenguajes y *frameworks* modernos y, como verás, **el desarrollo en Android con Jetpack Compose se basa en gran medida en ella**. Permite:

- Crear funciones ***callback***.
- Devolver respuestas distintas desde una función.
- Dejar que quien usa la función introduzca su propia lógica.

La sintaxis del parámetro es:

```kotlin
fun nameOfFun(parameter1: Type, lambdaName: (Type1, Type2) -> ReturnType) { }
// Sin parámetros
fun nameOfFun(parameter1: Type, lambdaName: () -> ReturnType) { }
// Sin valor de retorno
fun nameOfFun(parameter1: Type, lambdaName: (Type1, Type2) -> Unit) { }
// Sin parámetros ni valor de retorno
fun nameOfFun(parameter1: Type, lambdaName: () -> Unit) { }
```

#### Funciones *callback*

Un *callback* asegura que un conjunto de instrucciones se ejecuta **después** de otra acción concreta. Es muy típico con ejecución asíncrona, hilos, peticiones de red… Estas funciones ejecutan la lambda al final de su cuerpo:

```kotlin
fun doLogin(username: String, pass: String, lambda: (String, String) -> Unit) {
    // Instrucciones que conectan a la BBDD y realizan el login
    Thread.sleep(5000)
    lambda(username, pass)
}

fun doLoginGuest(lambda: () -> Unit) {
    // Instrucciones que conectan a la BBDD y realizan el login de invitado
    Thread.sleep(5000)
    lambda()
}
```

```kotlin
doLogin("Rick", "c-137", { username, pass -> println("Login: $username - $pass") })
```

Si la lambda es el **último parámetro**, se puede escribir **fuera de los paréntesis** (*trailing lambda*):

```kotlin
doLogin("Morty", "Sm1th") { username, pass ->
    println("Login: $username - $pass")
}
```

Y si es el **único parámetro**, se pueden quitar los paréntesis:

```kotlin
doLoginGuest {
    println("Login invitado")
}
```

#### Devolver respuestas distintas

Una función puede recibir **varias lambdas** y ejecutar una u otra según el caso. Compara una función tradicional con una que recibe un *callback* de éxito y otro de error:

::: tabs

== Tradicional

```kotlin
fun calculate(
    number1: Double,
    number2: Double,
    operation: Char,
): Double {
    when (operation) {
        '+' -> return number1 + number2
        '-' -> return number1 - number2
        '*' -> return number1 * number2
        '/' -> return number1 / number2
        else -> return 0.0
    }
}
```

== Con lambdas

```kotlin
fun calculate(
    number1: Double,
    number2: Double,
    operation: Char,
    correct: (result: Double) -> Unit,
    error: (error: String) -> Unit,
) {
    if (number1 < 0) {
        error("El primer número es menor que cero.")
    } else if (number2 < 0) {
        error("El segundo número es menor que cero.")
    } else {
        when (operation) {
            '+' -> correct(number1 + number2)
            '-' -> correct(number1 - number2)
            '*' -> correct(number1 * number2)
            '/' -> {
                if (number2 == 0.0) error("No se puede dividir entre cero.")
                else correct(number1 / number2)
            }
            else -> error("Operación no válida")
        }
    }
}
```

:::

Uso:

```kotlin
var result = ""
calculate(
    number1 = 9.0,
    number2 = 6.0,
    operation = '-',
    correct = {
        result = it.toString()
    },
    error = {
        result = it
    },
)
println("El resultado es: $result")
```

```txt
El resultado es: 3.0
```

::: warning-box Se pueden ejecutar varias lambdas
La función decide cuántas veces llama a cada lambda. Si, por ejemplo, el `else` de `calculate` empezara con `error("Sin error")` y `correct(0.0)` antes del `when`:

- Con las lambdas anteriores, que **guardan** el resultado en una variable, solo tendría efecto la **última** llamada: la salida seguiría siendo `El resultado es: 3.0`.
- Con lambdas que **muestran** el resultado, se verían todas las llamadas:

```kotlin
calculate(
    number1 = 9.0,
    number2 = 6.0,
    operation = '-',
    correct = { println("El resultado es: $it") },
    error = { println("Error: $it") },
)
```

```txt
Error: Sin error
El resultado es: 0.0
El resultado es: 3.0
```
:::

Para que una lambda sea un **parámetro opcional**, se le da un valor por defecto vacío con `= {}`:

```kotlin
fun calculate(
    number1: Double,
    number2: Double,
    operation: Char,
    correct: (result: Double) -> Unit,
    error: (error: String) -> Unit = {},
) { /* … */ }

// Con error: se muestra «Error: Operación no válida»
calculate(9.0, 6.0, 'o',
    correct = { println("El resultado es: $it") },
    error = { println("Error: $it") },
)

// Sin error: no se muestra nada
calculate(9.0, 6.0, 'o',
    correct = { println("El resultado es: $it") },
)
```

#### Introducir lógica propia

Si `calculate` estuviera en una librería externa, quien la usara podría incluir en cada lambda todas las instrucciones que quisiera:

```kotlin
calculate(
    number1 = 9.0,
    number2 = 6.0,
    operation = '-',
    correct = {
        /* Aquí se pueden introducir todas las instrucciones que se desee */
    },
    error = {
        /* Aquí se pueden introducir todas las instrucciones que se desee */
    },
)
```

### Ya las habías usado

Ya has usado lambdas como **último parámetro** de otras funciones:

```kotlin
// Al crear arrays
var reverse = Array(50) { 50 - it }

// En forEach, filter, map…
val numbers = listOf(1, 2, 3, 4, 5, 6)
numbers.forEach {
    print("${it * -1} ")
}

// En las funciones de alcance
product.apply {
    name = "Switch OLED"
    price = 349.90
}
```

::: accent-box teal 🎯 Lambdas en Jetpack Compose
Las lambdas son fundamentales en el desarrollo de aplicaciones Android con **Jetpack Compose**: los componentes reciben su contenido y sus eventos como lambdas. Fíjate en este adelanto:

```kotlin
MyMoviesApp {
    Scaffold(topBar = { MainAppBar(mediaItem.title) }) { padding ->
        Row(
            modifier = Modifier.padding(padding)
        ) {
            MainContent(
                onNavigate = {
                    currentItem = it.id
                },
                modifier = Modifier.weight(1f)
            )
            DetailContent(
                mediaItem = mediaItem,
                modifier = Modifier.weight(2f)
            )
        }
    }
}
```
:::
