---
layout: doc
title: 2. Control de flujo, colecciones y funciones
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 2. Control de flujo, colecciones y funciones

## 2.1. Control de flujo

Las instrucciones de control de flujo alteran el orden normal de ejecución del programa. En Kotlin disponemos de:

- `if`, `if`–`else` e `if`–`else if`–`else`
- `when` (similar a `switch` de Java)
- `for`, `while` y `do`–`while`
- `repeat`

::: info-box Guía de estilo para las llaves
- **No** se ponen llaves en las ramas de `when` ni en las expresiones `if` que no tengan más de una rama `else` y quepan en **una sola línea**.
- **Sí** se ponen en cualquier rama de `if`, `for`, `when`, `do` y en los `while`, aunque el cuerpo esté vacío o tenga una sola instrucción, cuando no cabe en una línea.
:::

## 2.2. `if`, `if`–`else` e `if`–`else if`–`else`

Si el `if` cabe en una sola línea, es preferible escribirlo así, sin llaves:

::: tabs

== Una línea (preferible)

```kotlin
if (number == 0) println("El número es cero")

if (number1 > number2) higherNumber = number1 else higherNumber = number2
```

== Con llaves

```kotlin
if (number == 0) {
    println("El número es cero")
}

if (number1 > number2) {
    higherNumber = number1
} else {
    higherNumber = number2
}
```

:::

```kotlin
if (number1 > number2) {
    println("El mayor es: $number1")
} else if (number1 < number2) {
    println("El mayor es: $number2")
} else {
    println("Los dos números son: $number1")
}
```

## 2.3. `if` como expresión

En Kotlin, `if` es una **expresión**: devuelve un valor que se puede asignar. Así, el ejemplo anterior:

```kotlin
if (number1 > number2) higherNumber = number1 else higherNumber = number2
```

se puede escribir de esta forma, en la que **la rama `else` es obligatoria**:

```kotlin
higherNumber = if (number1 > number2) number1 else number2
```

También se pueden usar llaves para ejecutar varias instrucciones; en ese caso, **la última instrucción de cada bloque es el valor que se asigna**:

```kotlin
var highNumber = if (number1 > number2) {
    // instrucciones
    number1
} else {
    // instrucciones
    number2
}
println("El mayor es $highNumber")
```

::: tip-box Práctica
Realiza la [Actividad 1 — Variables, constantes y control de flujo](../ejercicios/#actividad-1).
:::

## 2.4. Rangos y progresiones

Los rangos y las progresiones son series de valores:

```kotlin
0.rangeTo(10)      // rango de 0 a 10, incluido
0..10              // rango de 0 a 10, incluido

0.rangeUntil(10)   // rango de 0 a 9
0 until 10         // rango de 0 a 9
0..<10             // rango de 0 a 9

'a'..'z'           // rango de la «a» a la «z», incluida

10.downTo(0)       // progresión de 10 a 0, incluido, de 1 en 1
10 downTo 0        // progresión de 10 a 0, incluido, de 1 en 1

0..10 step 2       // progresión de 0 a 10, incluido, de 2 en 2
```

Todos los rangos funcionan como progresiones de paso 1; con `step` se indica otro paso. Los rangos también sirven para obtener **valores aleatorios**:

```kotlin
(1..100).random()
('a'..'z').random()
```

::: info-box Pistas del IDE
Con las pistas activadas, IntelliJ IDEA muestra los rangos como `0 ≤ .. ≤ 10` o `0 ≤ until < 10` para dejar claro qué extremos se incluyen.

![Rangos con las pistas del IDE activadas](/img/contenidos/up02/up02-2-p08-2.webp)
:::

## 2.5. `when`

Es una condición con varias ramas, similar al `switch` de Java:

```kotlin
when (number) {
    1 -> println("La variable es 1")
    2 -> println("La variable es 2")
    else -> println("La variable no es ni 1 ni 2")
}
```

Si alguna rama tiene varias instrucciones, se ponen llaves en **todas** las ramas:

```kotlin
when (number) {
    1 -> {
        // instrucciones
    }
    2 -> {
        // instrucciones
    }
}
```

Se pueden **agrupar valores** y usar **expresiones**:

```kotlin
when (position) {
    1, 2, 3 -> println("En el podio")
    else -> println("Sigue intentándolo")
}

var number: Int = 2
var position: String = "5"
when (number) {
    position.toInt() -> println("coincide el valor numérico")
    else -> println("no coincide el valor numérico")
}
```

Con `in` y `!in` se comprueban **rangos y colecciones**, y con `is` y `!is`, **tipos**:

```kotlin
when (position) {
    in 1..3 -> println("En el podio")
    in 4..10 -> println("Diploma")
    !in 11..20 -> println("Tienes que entrenar mucho más")
    else -> println("Ánimo para la próxima")
}

val numbers = listOf(1, 2, 3, 4, 5)
when (position) {
    in numbers -> println("Top 5")
    else -> println("Sigue intentándolo")
}

val variable: Any = "Rick"
when (variable) {
    is String -> println("La variable es una cadena")
    !is Int -> println("La variable no es ni una cadena ni un entero")
    else -> println("La variable es un entero")
}
```

En algunos casos la rama `else` es **obligatoria**, por ejemplo si se evalúa un booleano y no se contemplan todas las opciones:

::: tabs

== ❌ No compila

```kotlin
// No compila: no se evalúan todas las posibilidades
when (number1 > number2) {
    true -> println("$number1 es mayor que $number2")
}
```

== ✅ true / false

```kotlin
when (number1 > number2) {
    true -> println("$number1 es mayor que $number2")
    false -> println("$number1 no es mayor que $number2")
}
```

== ✅ true / else

```kotlin
when (number1 > number2) {
    true -> println("$number1 es mayor que $number2")
    else -> println("$number1 no es mayor que $number2")
}
```

:::

Sin variable a evaluar, `when` sustituye a las cadenas de `if`–`else`:

```kotlin
when {
    number1 > number2 -> print("El mayor es: $number1")
    number1 < number2 -> print("El mayor es: $number2")
    else -> print("Son iguales")
}
```

### `when` como expresión

Como `if`, `when` puede devolver un valor. En ese caso la rama `else` es obligatoria, **salvo que el compilador pueda comprobar que se cubren todas las opciones**:

```kotlin
// Se necesita el else para cubrir todas las posibilidades
val result: String = when (number) {
    1 -> "La variable es 1"
    2 -> "La variable es 2"
    else -> "La variable no es ni 1 ni 2"
}

// No se necesita el else: ya están todas las posibilidades
var result: String = when (number1 > number2) {
    true -> "$number1 es mayor que $number2"
    false -> "$number1 no es mayor que $number2"
}
```

## 2.6. `for`

Los bucles `for` de Kotlin recorren **rangos, progresiones y colecciones** con la palabra `in`:

- **Rangos**: series ascendentes o descendentes de `Int`, `Long` o `Char`.
- **Progresiones**: como un rango, pero con un paso entre elementos.
- **Colecciones**: conjuntos de elementos, como los *arrays*.

```kotlin
// Si el cuerpo ocupa una sola línea, mejor sin bloque
for (i in 1..3) print("$i ")        // 1, 2, 3

for (i in 0 until 5) {               // 0, 1, 2, 3, 4
}
for (i in 10 downTo 0) {             // 10, 9, 8… 1, 0
}
for (i in 1..14 step 3) {            // 1, 4, 7, 10, 13
}
for (i in 10 until 25 step 2) {      // 10, 12, 14… 22, 24
}
for (i in 10 downTo 0 step 2) {      // 10, 8, 6, 4, 2, 0
}
```

También funciona con **rangos de caracteres**:

```kotlin
for (letter in 'c'..'j') { }              // c d e f g h i j
for (letter in 'A'..'Z') { }              // A B C … X Y Z
for (letter in '!'..'~') { }              // de U+0021 a U+007E: ! " # $ % … { | } ~
for (letter in 'c'..'j' step 3) { }       // c f i
for (letter in 'z' downTo 'm' step 2) { } // z x v t r p n
```

`for` recorre cualquier objeto que proporcione un **iterador**: cadenas, listas, *arrays*, colecciones…

```kotlin
for (letter: Char in "Rick Sanchez") { }

var letters = listOf('a', 'b', 'c', 'd')
for (letter: Char in letters) { }
for (letter in letters) { }   // se deduce el tipo de dato

val matrix = arrayOf(
    arrayOf(5, 3, 2, 0),
    arrayOf(4, 9, 4, 5),
    arrayOf(8, 1, 7, 6)
)
for (row in matrix) {
    for (column in row) {
        print("$column ")
    }
    println()
}
```

Si necesitas la **posición** del elemento, hay dos formas de obtenerla:

```kotlin
var myArray = ('a'..'j').toList()

for (i in myArray.indices) {
    println("$i.- ${myArray[i]}")
}

for ((index, value) in myArray.withIndex()) {
    println("$index.- $value")
}
```

Si necesitas **modificar** el elemento, usa la primera opción (`indices`).

## 2.7. `while` y `do`–`while`

- `while` comprueba la condición **antes** de ejecutar el cuerpo, así que puede no ejecutarse nunca.
- `do`–`while` la comprueba **después**: el cuerpo se ejecuta al menos una vez.

```kotlin
while (quantity < 100) {
    // instrucciones
    quantity++
}

do {
    // instrucciones
} while (quantity-- > 0)
```

## 2.8. `repeat`

`repeat` repite el cuerpo el número de veces indicado (internamente usa un `for`). Dentro está disponible la variable `it` con el número de repetición, empezando en cero; puedes darle un nombre más descriptivo:

```kotlin
repeat(100) {
    println("${it + 1}.- Los 'Billetes Bart' no son de curso legal.")
}

repeat(100) { index ->
    println("${index + 1}.- Los 'Billetes Bart' no son de curso legal.")
}
```

## 2.9. `break`, `continue` y `return`

Funcionan como en Java:

- `return` finaliza el bucle **y la función** en la que se encuentra.
- `break` finaliza el bucle en el que se encuentra.
- `continue` finaliza la **iteración actual** del bucle.

En Kotlin las instrucciones se pueden **etiquetar** con `nombreEtiqueta@`. Así, un `break@etiqueta` finaliza el bucle etiquetado, aunque haya bucles anidados:

```kotlin
firstFor@ for (i in 1..10) {
    for (j in 1..5) {
        if (i % 2 == 0 && j == 2) break@firstFor
        println("$i - $j")
    }
}
```

## 2.10. Colecciones

Las colecciones almacenan un número **variable** de elementos (cero o más), normalmente del mismo tipo. Pueden almacenar elementos de distinto tipo, pero entonces hay que tener especial cuidado al usarlas. También pueden contener `null`.

| Colección | Características |
|:---|:---|
| **Array** | Se accede por índice. Admite repetidos. Dos *arrays* **no** son iguales aunque tengan los mismos elementos en el mismo orden. |
| **List** | Colección **ordenada**. Se accede por índice. Admite repetidos. Dos listas son iguales si tienen los mismos elementos en el mismo orden. |
| **Set** | Colección **sin orden** y **sin repetidos**. Dos conjuntos son iguales si tienen los mismos elementos, sin importar el orden. |
| **Map** | También llamado diccionario: pares **clave-valor** con claves únicas. Dos mapas son iguales si tienen los mismos pares, sin importar el orden. |

`List`, `Set` y `Map` tienen dos versiones:

- **Inmutable** (solo lectura): no se pueden añadir, eliminar ni modificar elementos.
- **Mutable**: se pueden añadir, eliminar y modificar elementos.

::: info-box ¿`val` o `var`?
Una colección mutable o inmutable se puede declarar con `val` o con `var`: lo que permite o no modificarla es su tipo, no la palabra con la que se declara. `val` solo impide asignarle **otra** colección.
:::

## 2.11. `List`

::: tabs

== List

```kotlin
// Se indica el tipo de los elementos
val numbers: List<String> = listOf("uno", "dos", "tres", "cuatro", "cinco")
// Se deduce el tipo: todos son iguales → String
val numbers = listOf("uno", "dos", "tres", "cuatro", "cinco")
// Se indica el tipo
val list: List<Any> = listOf("Rick", 70, "c-137", 'H')
// Se deduce el tipo. Distintos tipos → Any
val list = listOf("Rick", 70, "c-137", 'H')
```

== MutableList

```kotlin
// Se indica el tipo, pero no se añaden datos
val numbers: MutableList<Int> = mutableListOf()
// Se indica el tipo de los elementos
val numbers: MutableList<Int> = mutableListOf(1, 2, 3, 4)
// Se deduce el tipo: todos son iguales → Int
val numbers = mutableListOf(1, 2, 3, 4)
// Se indica el tipo
var list: MutableList<Any> = mutableListOf(1, 'a', "qwerty", true)
// Se deduce el tipo. Distintos tipos → Any
var list = mutableListOf(1, 'a', "qwerty", true)
```

:::

Algunas operaciones (las que modifican solo están disponibles en `MutableList`):

```kotlin
val list = listOf("uno", "dos", "tres", "cuatro", "cinco")
list.size                  // 5
list[2]                    // "tres"
list.indexOf("cuatro")     // 3
list.lastIndexOf("dos")    // 1
list.subList(1, 3)         // ["dos", "tres"]

val mutableList = mutableListOf(1, 2, 6, 3, 4, 5)
mutableList.add(6)         // añade el 6 al final
mutableList.add(2, 100)    // añade el 100 en la posición 2 y desplaza el resto
mutableList.removeAt(4)    // elimina el elemento de la posición 4
mutableList[1] = 99        // cambia el valor de la posición 1
mutableList.contains(3)    // true o false
mutableList.sort()         // ordena ascendentemente
mutableList.sortDescending() // ordena descendentemente
mutableList.max()          // elemento mayor
mutableList.min()          // elemento menor
mutableList.isEmpty()      // true si está vacía
mutableList.isNotEmpty()   // true si tiene elementos
mutableList.shuffle()      // desordena aleatoriamente la lista
mutableList.shuffled()     // devuelve una lista nueva desordenada
mutableList.first()        // primer elemento
mutableList.last()         // último elemento
mutableList.count()        // número de elementos
mutableList.reverse()      // invierte la lista
mutableList.reversed()     // devuelve una lista nueva invertida
```

::: danger-box Error por ser inmutable
```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
numbers[0] = 27   // ❌
numbers.add(3)    // ❌
```
:::

## 2.12. `Set`

::: tabs

== Set

```kotlin
val names: Set<String> = setOf("Ana", "Jorge", "Lucía")
val names = setOf("Ana", "Jorge", "Lucía")
val set: Set<Any> = setOf("Valencia", 2022, "ES", 'V')
val set = setOf("Valencia", 2022, "ES", 'V')
```

== MutableSet

```kotlin
val names: MutableSet<String> = mutableSetOf("Ana", "Jorge", "Lucía")
val names = mutableSetOf("Ana", "Jorge", "Lucía")
val set: MutableSet<Any> = mutableSetOf("Valencia", 2022, "ES", 'V')
val set = mutableSetOf("Valencia", 2022, "ES", 'V')
```

:::

```kotlin
val names = mutableSetOf("Rick", "Morty", "Summer")
names.add("Beth")       // añade un elemento nuevo
names.add("Morty")      // no lo añade: ya existe
names.add("morty")      // lo añade: "morty" != "Morty"
names.remove("Summer")  // elimina el elemento si lo encuentra (devuelve true/false)
names.contains("Rick")

val numbers = mutableSetOf(2, 54, 17, 83)
numbers.min(); numbers.max(); numbers.isEmpty(); numbers.isNotEmpty()
numbers.first(); numbers.last(); numbers.reversed(); numbers.shuffled(); numbers.count()

val set1 = setOf(1, 6, 3, 12, 16, 7)
val set2 = setOf(24, 7, 1, 9, 4, 14)
// Elementos de los dos conjuntos, sin repeticiones
val joinedGroup = set1 union set2
// Elementos comunes a los dos conjuntos
val commonElements = set1 intersect set2
// Elementos de set1 que no están en set2
val differentElements = set1 subtract set2
```

## 2.13. `Map`

Los pares clave-valor se crean con `to`:

::: tabs

== Map

```kotlin
val person: Map<String, String> = mapOf(
    "name" to "Rick Sanchez",
    "age" to "70",
    "alive" to "vivo",
    "dimension" to "c-137",
)

// Se deduce el tipo de la clave y del valor
val person = mapOf(
    "name" to "Rick Sanchez",
    "age" to "70",
)

// Map en el que los valores pueden ser de cualquier tipo
val person: Map<String, Any> = mapOf(
    "name" to "Rick Sanchez",
    "age" to 70,
    "alive" to true,
)
```

== MutableMap

```kotlin
val person: MutableMap<String, String> = mutableMapOf(
    "name" to "Rick Sanchez",
    "age" to "70",
    "alive" to "vivo",
    "dimension" to "c-137",
)

val person: MutableMap<String, Any> = mutableMapOf(
    "name" to "Rick Sanchez",
    "age" to 70,
    "alive" to true,
    "dimension" to "c-137",
)
```

:::

```kotlin
val person = mutableMapOf(
    "name" to "Rick Sanchez",
    "age" to 70,
    "alive" to true,
)
person["dimension"] = "c-137"   // añade (o cambia) el par clave-valor (equivale a put)
person.remove("age")            // elimina y devuelve el valor de esa clave
person.keys                     // conjunto de claves
person.values                   // colección de valores
person.size                     // número de pares clave-valor
person.clear()                  // vacía el mapa

// Recorrer un Map o MutableMap
for ((key, value) in person) {
    println("$key -> $value")
}
```

## 2.14. Operaciones sobre colecciones

Algunas operaciones devuelven un booleano que indica si se han realizado, como `add` y `remove`. Consulta la [documentación oficial de colecciones](https://kotlinlang.org/docs/collections-overview.html) para ver todas las operaciones de `List`, `Set` y `Map`.

Otras operaciones **recorren** la colección y devuelven una **colección nueva** con el resultado. Reciben una **lambda** (entre llaves, como al crear *arrays*), dentro de la cual `it` es el elemento actual:

- `forEach` recorre todos los elementos y permite hacer algo con cada uno.
- `filter` devuelve los elementos que cumplen una condición.
- `map` devuelve el resultado de aplicar una transformación a cada elemento.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)
numbers.forEach {
    print("${it * -1} ")
}

// Con Set se hace de la misma manera
val list = listOf("Uno", "Dos", "Tres", "Cuatro", "Cinco")
val filteredList = list.filter { it.length > 3 }   // [Tres, Cuatro, Cinco]
val mappedList = list.map { it.uppercase() }       // [UNO, DOS, TRES, CUATRO, CINCO]

val map = mapOf(1 to "Uno", 2 to "Dos", 3 to "Tres", 4 to "Cuatro", 5 to "Cinco")
val filteredMap = map.filter { it.value.length > 3 }  // {3=Tres, 4=Cuatro, 5=Cinco}
val mappedMap = map.map { it.value.length > 3 }       // [false, false, true, true, true]
```

::: tip-box Práctica
Realiza la [Actividad 2 — Colecciones](../ejercicios/#actividad-2) y la [Actividad 4 — Map](../ejercicios/#actividad-4).
:::

## 2.15. Funciones

### Funciones del sistema

Kotlin incorpora muchas funciones listas para usar; ya has visto algunas, como `println`, `toInt`, `toString` o `random`. Para los *arrays* tienes, entre otras, `min()`, `max()`, `isEmpty()`, `indexOf()`, `sort()`, `sorted()`, `reverse()` o `reversedArray()`. Todas están en la [documentación de la biblioteca estándar](https://kotlinlang.org/api/core/kotlin-stdlib/).

### Declarar funciones

Las funciones se declaran con la palabra **`fun`**:

```kotlin
fun isEven(number: Int): Boolean {
    return number % 2 == 0
}

isEven(16)
```

Una función puede no recibir parámetros ni devolver nada. Si no se indica tipo de retorno, devuelve **`Unit`** (equivalente a `void`), y `: Unit` se puede omitir:

```kotlin
fun greetingPerson(name: String) {
    println("¡Hola $name!")
}

fun greeting() {
    println("¡Hola!")
}

fun greeting(): Unit {   // equivalente a la anterior
    println("¡Hola!")
}
```

Los parámetros se separan por comas y se admite la **coma final** (*trailing comma*). Lo habitual en Kotlin es escribir entonces cada parámetro en una línea:

```kotlin
fun areEqual(
    num1: Int,
    num2: Int,
): Boolean {
    return num1 == num2
}

areEqual(26, 123)
```

Los parámetros son **inmutables**, como si se declararan con `val`. Para modificarlos hay que crear una copia:

::: tabs

== ❌ Error

```kotlin
fun addOne(number: Int): Int {
    number++   // Error: no se puede cambiar el valor
    return number
}
```

== ✅ Correcto

```kotlin
fun addOne(number: Int): Int {
    val newNumber = number + 1
    return newNumber
}

var myNumber = (0..100).random()
myNumber = addOne(myNumber)
```

:::

### Funciones de una sola expresión

Si la función devuelve un valor y se puede escribir en una línea, se quitan las llaves y se escribe `=` antes del cuerpo:

```kotlin
fun areEqual(num1: Int, num2: Int) = num1 == num2

fun greeting(name: String) = println("¡Hola $name!")
fun addition(num1: Int, num2: Int): Int = num1 + num2
fun isEven(num: Int): Boolean = num % 2 == 0

// Si no se indica el tipo de retorno, Kotlin lo deduce
fun inRange(num: Int, min: Int, max: Int) = num in min..max
fun inRange(num: Int, range: IntRange) = num in range
```

### Argumentos con nombre

En la llamada se puede indicar el **nombre del parámetro** y así cambiar su orden. Es la forma más habitual en Kotlin:

```kotlin
fun greeting(name: String, surname: String) = "Hola $name $surname"

greeting("Rick", "Sanchez")
greeting(name = "Rick", "Sanchez")
greeting("Rick", surname = "Sanchez")
greeting(name = "Rick", surname = "Sanchez")
greeting(surname = "Sanchez", name = "Rick")
```

### Valores por defecto

Los parámetros pueden tener un **valor por defecto**, y así se pueden omitir en la llamada:

```kotlin
fun finalPrice(
    price: Double,
    vat: Int = 21,
    discount: Int = 0,
): Double {
    var finalPrice: Double = price
    if (discount != 0) finalPrice -= price * discount / 100
    return finalPrice + finalPrice * vat / 100
}

finalPrice(355.0, 10, 5)
finalPrice(355.0, 10)                // descuento → valor por defecto
finalPrice(355.0, discount = 20)     // IVA → valor por defecto
```

Combinando valores por defecto y argumentos con nombre se pueden omitir parámetros **intermedios**, como en la última llamada.

Si se mezclan argumentos con y sin nombre, los que llevan nombre deben ir **al final**, salvo que estén en su posición:

```kotlin
greeting("Ana", "López", "Sanz")
greeting(name = "Ana", "López", "Sanz")
greeting("Ana", surname2 = "Sanz", surname1 = "López")
greeting(surname2 = "Sanz", name = "Ana", surname1 = "López")

greeting("López", surname2 = "Sanz", name = "Ana")   // ❌ "López" ocupa el lugar de name
```

### Número variable de parámetros: `vararg`

Con **`vararg`** se declara un número variable de parámetros. Suele ser el último; si no lo es, los siguientes deben pasarse con su nombre. Con el operador **spread** `*` se pasa un *array* (o una colección convertida a *array*) completo:

```kotlin
fun max(vararg numbers: Int): Int {
    var max: Int = numbers[0]
    for (number in numbers) {
        if (number > max) max = number
    }
    return max
}

max(5, 32, 14, 6, 57, 7, 26, 12)
val numbers = intArrayOf(5, 32, 14, 6, 57, 7, 26, 12)
max(*numbers)
max(3, 67, 23, *numbers)
max(3, 67, 23, *numbers, 42, 17, 92)
val otherNumbers: Array<Int> = arrayOf(34, 24, 15)
max(3, 67, 23, *numbers, 42, 17, 92, *otherNumbers.toIntArray())
```

### Sobrecarga

Kotlin permite **sobrecargar** funciones aunque no estén dentro de una clase:

```kotlin
fun addition() = println("No puedo sumar sin valores")
fun addition(num1: Int, num2: Int) = println("$num1 + $num2 = ${num1 + num2}")
fun addition(num1: Double, num2: Double) = println("$num1 + $num2 = ${num1 + num2}")
```

La función `max` anterior ya existe en la biblioteca estándar, así que al crearla se sobrecarga: el IDE ofrece tanto la propia como las del sistema.

![Sugerencias del IDE con la función max propia y las del sistema](/img/contenidos/up02/up02-2-p46-2.webp)

## 2.16. Ámbito de las funciones

Igual que con las variables, el ámbito de una función indica dónde se puede usar. Hay cuatro tipos:

### Nivel superior (*top level*)

Se definen fuera de cualquier bloque o clase. Se suelen usar como **biblioteca de funciones**: un archivo reúne varias funciones de nivel superior que se pueden usar en todo ese archivo y en los que lo importen.

```kotlin
fun main() {
    greeting()
}

fun greeting() = println("Hola Rick!")
```

### Locales

Se definen **dentro de otra función** y se pueden usar en todo el bloque de esa función (incluso desde otras funciones locales de la misma función). Deben definirse **antes** de llamarlas y pueden usar las variables locales de la función que las contiene.

```kotlin
fun main() {
    fun greeting() = println("Hola Rick!")

    fun welcome() {
        greeting()
        println("Bienvenido")
    }

    greeting()
}

fun test() {
    greeting()   // ❌ greeting solo existe dentro de main
}
```

### Miembro

Son las funciones definidas dentro de clases u objetos: lo que en programación orientada a objetos llamamos **métodos**. Las veremos en el siguiente apartado.

### De extensión

Kotlin permite **añadir funciones a clases existentes**. Solo existen en el ámbito en el que se definen:

```kotlin
fun main() {
    val number = (0..100).random()
    if (number.isOdd()) println("$number es impar")
    else println("$number es par")
}

// Se añade una función a la clase Int
fun Int.isOdd(): Boolean {
    return this % 2 != 0
}
```

### Ámbito y sobrecarga

Cuando hay varias funciones con el mismo nombre y los mismos parámetros, Kotlin elige según su **prioridad de resolución**:

1. Las funciones **locales** tienen prioridad sobre todas las demás: una función local con los mismos parámetros **sustituye** a la del sistema.
2. Después van las **importaciones explícitas** (por ejemplo, `import kotlin.math.max`, que el IDE suele añadir automáticamente): si existe, se usará la del sistema y se **ignorará** tu función de nivel superior.
3. Por último, las funciones de nivel superior del mismo paquete y las importaciones por defecto.

Puedes comprobarlo con las pistas del IDE: los nombres de los parámetros que muestra (`a:` y `b:` o `num1:` y `num2:`) indican qué función se está llamando.

::: tabs

== Nivel superior

```kotlin
import kotlin.math.max

fun max(num1: Int, num2: Int): Int {
    return when {
        num1 > num2 -> num1
        num2 > num1 -> num2
        else -> num1
    }
}

fun main() {
    println(max(3, 5))   // pistas a: y b: → kotlin.math.max
}
```

== Local

```kotlin
fun main() {
    fun max(num1: Int, num2: Int): Int {
        return when {
            num1 > num2 -> num1
            num2 > num1 -> num2
            else -> num1
        }
    }

    println(max(3, 5))   // pistas num1: y num2: → función local
}
```

:::

::: tip-box Práctica
Realiza la [Actividad 3 — Cadenas](../ejercicios/#actividad-3) y la [Actividad 5 — Funciones: agenda](../ejercicios/#actividad-5).
:::
