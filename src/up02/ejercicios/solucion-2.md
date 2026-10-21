---
layout: doc
title: "Solución Actividad 2 — Colecciones"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 2 — Colecciones

Soluciones de referencia de la [Actividad 2](./#actividad-2) del boletín. Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.

::: tip-box Descarga
Proyectos de esta actividad: <a :href="withBase('/descargas/up02/soluciones-up02-actividad-2.zip')" download>soluciones-up02-actividad-2.zip</a>. Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre (*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.
:::

::: warning-box Ejecútalas en IntelliJ IDEA
Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con `ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.
:::

## Claves de la solución

- Se usan `IntArray`/`DoubleArray`/`CharArray` (lo que piden los enunciados) y `Array<IntArray>` para matrices.
- `ArrayPosicion`: `Random.nextDouble(from, until)` excluye el límite superior; se usa `Math.nextUp(1000.0)` para que el 1000 quede incluido.
- `AleatoriosUnicos` genera candidatos y descarta repetidos; en un comentario se da la alternativa `(1..50).shuffled().take(10)`.
- `OrdenarBurbuja` incluye la optimización de parar cuando una pasada no hace intercambios.
- `ArrayYMatriz` usa `contentEquals` y explica por qué `==` entre arrays compara referencias.
- `Matriz5x5` resuelve todo en `main` (como pide el enunciado) y `Matriz5x5Plus` lo reparte en funciones con una sola responsabilidad. Comparar las dos es un buen ejercicio de clase.

## Proyectos

::: details MayorMenor
**Enunciado:** Programa que pida 10 números enteros y los almacene en un array, a continuación, el programa indicará qué números son el mayor y el menor. Por último, mostrará el array por pantalla.

```kotlin
/**
 * Proyecto **MayorMenor** — UP02 · Actividad 2.
 *
 * Pide 10 números enteros, los guarda en un array, indica el mayor y el menor y, por último,
 * muestra el array.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { readInt("Número ${it + 1} de $SIZE: ") }

    // Recorrido manual (también valdría numbers.max() y numbers.min())
    var max = numbers[0]
    var min = numbers[0]
    for (n in numbers) {
        if (n > max) max = n
        if (n < min) min = n
    }
    println("Mayor: $max")
    println("Menor: $min")
    println("Array: ${numbers.contentToString()}")
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details ArrayCifras
**Enunciado:** Programa que pida 10 números enteros y los almacene en un array, a continuación, el programa dirá cuántos números hay de una cifra, cuántos hay de dos y cuántos hay de más de dos.

```kotlin
/**
 * Proyecto **ArrayCifras** — UP02 · Actividad 2.
 *
 * Pide 10 números enteros, los guarda en un array e indica cuántos tienen una cifra, dos
 * cifras o más de dos (se ignora el signo).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { readInt("Número ${it + 1} de $SIZE: ") }
    var oneDigit = 0
    var twoDigits = 0
    var moreDigits = 0
    for (n in numbers) {
        when (digitCount(n)) {
            1 -> oneDigit++
            2 -> twoDigits++
            else -> moreDigits++
        }
    }
    println("De una cifra: $oneDigit")
    println("De dos cifras: $twoDigits")
    println("De más de dos cifras: $moreDigits")
}

/**
 * Cuenta las cifras de un número entero, sin tener en cuenta el signo.
 *
 * @param n número del que se cuentan las cifras.
 * @return número de cifras (el 0 tiene una).
 */
fun digitCount(n: Int): Int = n.toLong().let { if (it < 0) -it else it }.toString().length

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details Array10
**Enunciado:** Programa que pida 10 números enteros y los almacene en un array, a continuación, los mostrará por pantalla en el orden en el que se han introducido, a continuación, dirá cuántos números pares e impares hay y, por último, mostrará todos los números en orden inverso al que se han introducido.

```kotlin
/**
 * Proyecto **Array10** — UP02 · Actividad 2.
 *
 * Pide 10 números enteros, los muestra en el orden de entrada, cuenta pares e impares y los
 * muestra en orden inverso.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { readInt("Número ${it + 1} de $SIZE: ") }

    println("Orden de entrada: ${numbers.joinToString(" ")}")

    val evens = numbers.count { it % 2 == 0 }
    println("Pares: $evens · Impares: ${SIZE - evens}")

    print("Orden inverso: ")
    for (i in numbers.indices.reversed()) print("${numbers[i]} ")
    println()
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details ArrayCapicua
**Enunciado:** Programa que pida 6 números enteros y los almacene en un array, a continuación, los mostrará por pantalla en el orden en el que se han introducido, e indicará si el array es capicúa.

```kotlin
/**
 * Proyecto **ArrayCapicua** — UP02 · Actividad 2.
 *
 * Pide 6 números enteros, los guarda en un array, los muestra e indica si el array es capicúa.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 6

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { readInt("Número ${it + 1} de $SIZE: ") }
    println("Array: ${numbers.joinToString(" ")}")
    println(if (isPalindrome(numbers)) "El array es capicúa." else "El array no es capicúa.")
}

/**
 * Comprueba si un array se lee igual de izquierda a derecha que de derecha a izquierda,
 * comparando cada posición i con su simétrica (size - 1 - i) hasta la mitad.
 *
 * @param array array que se comprueba.
 * @return true si es capicúa.
 */
fun isPalindrome(array: IntArray): Boolean {
    for (i in 0 until array.size / 2) {
        if (array[i] != array[array.size - 1 - i]) return false
    }
    return true
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details ArrayPosicion
**Enunciado:** Programa que rellene automáticamente un array con 100 decimales aleatorios entre 0 y 1000 incluidos. A continuación, se pedirá una posición al usuario y se mostrará por pantalla el elemento que se encuentra en dicha posición del array.

```kotlin
/**
 * Proyecto **ArrayPosicion** — UP02 · Actividad 2.
 *
 * Rellena un array con 100 decimales aleatorios entre 0 y 1000 (incluidos), pide una posición
 * y muestra el elemento que se encuentra en ella.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

import kotlin.random.Random

/** Tamaño del array. */
const val SIZE = 100

/** Punto de entrada. */
fun main() {
    // nextDouble(from, until) excluye el límite superior; con Math.nextUp se incluye el 1000
    val numbers = DoubleArray(SIZE) { Random.nextDouble(0.0, Math.nextUp(1000.0)) }
    val position = readIntInRange("Introduce una posición (0-${SIZE - 1}): ", 0, SIZE - 1)
    println("En la posición $position está el número ${"%.2f".format(numbers[position])}")
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::

::: details ArrayPersonalizado
**Enunciado:** Programa que pida un número entre 3 y 8 al usuario, a continuación, creará un array de caracteres del tamaño introducido y pedirá al usuario las letras para rellenarlo. Por último, mostrará el array por pantalla.

```kotlin
/**
 * Proyecto **ArrayPersonalizado** — UP02 · Actividad 2.
 *
 * Pide un tamaño entre 3 y 8, crea un array de caracteres de ese tamaño, pide las letras para
 * rellenarlo y lo muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val size = readIntInRange("Introduce el tamaño del array (3-8): ", 3, 8)
    val letters = CharArray(size) { readLetter("Letra ${it + 1} de $size: ") }
    println("Array: ${letters.joinToString(" ")}")
}

/**
 * Pide una única letra, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return la letra introducida.
 */
fun readLetter(prompt: String): Char {
    while (true) {
        print(prompt)
        val input = readln().trim()
        if (input.length == 1 && input[0].isLetter()) return input[0]
        println("Error: introduce una sola letra.")
    }
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::

::: details Aleatorios50
**Enunciado:** Programa que rellene automáticamente un array con 50 enteros aleatorios entre 1 y 20 incluidos. A continuación, se pedirá un número al usuario y se indicará si el número está en el array. Por último, se mostrará el array por pantalla.

```kotlin
/**
 * Proyecto **Aleatorios50** — UP02 · Actividad 2.
 *
 * Rellena un array con 50 enteros aleatorios entre 1 y 20, pide un número, indica si está en
 * el array y muestra el array.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 50

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { (1..20).random() }
    val target = readInt("Introduce el número que quieres buscar: ")
    println(if (target in numbers) "$target está en el array." else "$target no está en el array.")
    println("Array: ${numbers.joinToString(" ")}")
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details Aleatorios50Plus
**Enunciado:** Modificar el ejercicio anterior para que muestre cuántas veces aparece el número buscado.

```kotlin
/**
 * Proyecto **Aleatorios50Plus** — UP02 · Actividad 2.
 *
 * Como Aleatorios50, pero además indica cuántas veces aparece el número buscado.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 50

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { (1..20).random() }
    val target = readInt("Introduce el número que quieres buscar: ")
    val occurrences = numbers.count { it == target }
    if (occurrences > 0) println("$target está en el array y aparece $occurrences vez/veces.")
    else println("$target no está en el array.")
    println("Array: ${numbers.joinToString(" ")}")
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
```
:::

::: details AleatoriosUnicos
**Enunciado:** Programa que rellene automáticamente un array con 10 enteros aleatorios entre 1 y 50 incluidos, no se debe repetir ningún número.

```kotlin
/**
 * Proyecto **AleatoriosUnicos** — UP02 · Actividad 2.
 *
 * Rellena un array con 10 enteros aleatorios entre 1 y 50 sin que se repita ninguno.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE)
    var filled = 0
    while (filled < SIZE) {
        val candidate = (1..50).random()
        // Solo se añade si no está ya entre las posiciones rellenadas
        if (numbers.take(filled).none { it == candidate }) {
            numbers[filled] = candidate
            filled++
        }
    }
    println("Array sin repetidos: ${numbers.joinToString(" ")}")
    // Alternativa idiomática: (1..50).shuffled().take(SIZE).toIntArray()
}
```
:::

::: details ArrayPosiciones
**Enunciado:** Programa que rellene automáticamente un array con 100 números enteros aleatorios entre 0 y 1000. A continuación, pedirá dos posiciones al usuario y mostrará todos los números que hay en el array entre esas posiciones.

```kotlin
/**
 * Proyecto **ArrayPosiciones** — UP02 · Actividad 2.
 *
 * Rellena un array con 100 enteros aleatorios entre 0 y 1000, pide dos posiciones y muestra
 * los números comprendidos entre ellas (incluidas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 100

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { (0..1000).random() }
    val first = readIntInRange("Posición inicial (0-${SIZE - 1}): ", 0, SIZE - 1)
    val second = readIntInRange("Posición final (0-${SIZE - 1}): ", 0, SIZE - 1)
    for (i in minOf(first, second)..maxOf(first, second)) {
        println("[$i] = ${numbers[i]}")
    }
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::

::: details OrdenarBurbuja
**Enunciado:** Programa que rellene automáticamente un array con 10 números enteros aleatorios entre 0 y 100. A continuación, mostrará el array por pantalla, luego lo ordenará mediante el método de la burbuja (visto en el módulo de Programación) y finalmente volverá a mostrar el array por pantalla.

```kotlin
/**
 * Proyecto **OrdenarBurbuja** — UP02 · Actividad 2.
 *
 * Rellena un array con 10 enteros aleatorios entre 0 y 100, lo muestra, lo ordena con el
 * método de la burbuja y lo vuelve a mostrar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { (0..100).random() }
    println("Antes:   ${numbers.joinToString(" ")}")
    bubbleSort(numbers)
    println("Después: ${numbers.joinToString(" ")}")
}

/**
 * Ordena el array de menor a mayor con el método de la burbuja. En cada pasada el mayor
 * elemento pendiente «sube» hasta el final; si en una pasada no hay intercambios, el array
 * ya está ordenado y se termina antes.
 *
 * @param array array que se ordena (se modifica in situ).
 */
fun bubbleSort(array: IntArray) {
    for (pass in 0 until array.size - 1) {
        var swapped = false
        for (j in 0 until array.size - 1 - pass) {
            if (array[j] > array[j + 1]) {
                array[j] = array[j + 1].also { array[j + 1] = array[j] }
                swapped = true
            }
        }
        if (!swapped) break
    }
}
```
:::

::: details Matriz3x3
**Enunciado:** Programa que rellene automáticamente una matriz de 3x3 con enteros aleatorios entre 0 y 9 incluidos. A continuación, mostrará la matriz.

```kotlin
/**
 * Proyecto **Matriz3x3** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 3x3 con enteros aleatorios entre 0 y 9 y la muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 3

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (0..9).random() } }
    for (row in matrix) println(row.joinToString(" "))
}
```
:::

::: details MatrizTamanyos
**Enunciado:** Programa que pida un número entero entre 2 y 5 al usuario y cree una matriz de NxN siendo N el número introducido por el usuario. A continuación, pedirá números enteros al usuario para rellenar la matriz y por último se mostrará la matriz.

```kotlin
/**
 * Proyecto **MatrizTamanyos** — UP02 · Actividad 2.
 *
 * Pide N entre 2 y 5, crea una matriz NxN, pide los números para rellenarla y la muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val n = readIntInRange("Introduce el tamaño de la matriz (2-5): ", 2, 5)
    val matrix = Array(n) { row -> IntArray(n) { col -> readInt("Valor [$row][$col]: ") } }
    println("Matriz ${n}x$n:")
    for (row in matrix) println(row.joinToString("\t"))
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::

::: details Matriz4x4Aleatoria
**Enunciado:** Programa que rellene automáticamente una matriz de 4x4 con enteros aleatorios entre 0 y 9 incluidos. A continuación, mostrará la matriz y la media de cada una de las filas.

```kotlin
/**
 * Proyecto **Matriz4x4Aleatoria** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 4x4 con enteros aleatorios entre 0 y 9, la muestra junto con la media
 * de cada fila.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 4

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (0..9).random() } }
    for (row in matrix) {
        println("${row.joinToString(" ")}  → media: ${"%.2f".format(row.average())}")
    }
}
```
:::

::: details MatrizLoca
**Enunciado:** Programa que pida dos números al usuario (N y M) y que cree una matriz de NxM que rellenará automáticamente con números aleatorios entre 1 y 9. Al finalizar mostrará la matriz por pantalla.

```kotlin
/**
 * Proyecto **MatrizLoca** — UP02 · Actividad 2.
 *
 * Pide N y M, crea una matriz NxM con números aleatorios entre 1 y 9 y la muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val rows = readIntInRange("Número de filas (N): ", 1, 50)
    val cols = readIntInRange("Número de columnas (M): ", 1, 50)
    val matrix = Array(rows) { IntArray(cols) { (1..9).random() } }
    for (row in matrix) println(row.joinToString(" "))
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::

::: details Diagonal3x3
**Enunciado:** Programa que rellene una matriz de 3x3 con enteros aleatorios entre 1 y 100 incluidos. A continuación, mostrará la matriz, luego su diagonal y por último la traspuesta de la matriz.

```kotlin
/**
 * Proyecto **Diagonal3x3** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 3x3 con enteros aleatorios entre 1 y 100, la muestra, muestra su
 * diagonal principal y su traspuesta.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 3

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (1..100).random() } }
    println("Matriz:")
    printMatrix(matrix)

    println("Diagonal: ${(0 until SIZE).map { matrix[it][it] }.joinToString(" ")}")

    // La traspuesta intercambia filas por columnas: t[i][j] = m[j][i]
    val transposed = Array(SIZE) { i -> IntArray(SIZE) { j -> matrix[j][i] } }
    println("Traspuesta:")
    printMatrix(transposed)
}

/**
 * Muestra una matriz alineando las columnas con tabuladores.
 *
 * @param matrix matriz que se muestra.
 */
fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) println(row.joinToString("\t"))
}
```
:::

::: details ArrayYMatriz
**Enunciado:** Programa que rellene automáticamente un array con 3 números aleatorios entre 1 y 5, también rellenará automáticamente una matriz de 10x3 con números aleatorios entre 1 y 5. Por último, mostrará si alguna fila es igual al array.

```kotlin
/**
 * Proyecto **ArrayYMatriz** — UP02 · Actividad 2.
 *
 * Rellena un array de 3 números y una matriz de 10x3 con aleatorios entre 1 y 5, y muestra si
 * alguna fila de la matriz es igual al array.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de columnas (longitud del array). */
const val COLS = 3

/** Número de filas de la matriz. */
const val ROWS = 10

/** Punto de entrada. */
fun main() {
    val array = IntArray(COLS) { (1..5).random() }
    val matrix = Array(ROWS) { IntArray(COLS) { (1..5).random() } }

    println("Array: ${array.joinToString(" ")}")
    println("Matriz:")
    matrix.forEachIndexed { i, row -> println("Fila $i: ${row.joinToString(" ")}") }

    // contentEquals compara contenido; == entre arrays compara referencias
    val equalRows = matrix.indices.filter { matrix[it].contentEquals(array) }
    if (equalRows.isEmpty()) println("Ninguna fila es igual al array.")
    else println("Filas iguales al array: ${equalRows.joinToString(", ")}")
}
```
:::

::: details Matriz5x5
**Enunciado:** Programa con una matriz de 5x5 que muestre un menú y realice la acción elegida (ver detalle más abajo).

```kotlin
/**
 * Proyecto **Matriz5x5** — UP02 · Actividad 2.
 *
 * Usando una matriz de 5x5 muestra un menú (rellenar, mostrar, total fila, total columna,
 * buscar, media, traspuesta, salir) y realiza la acción elegida hasta que se elige salir. Esta
 * versión resuelve todo dentro de main, tal como pide el enunciado; la versión Plus lo
 * organiza en funciones.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de filas de la matriz. Cambiarlo no exige tocar nada más. */
const val ROWS = 5

/** Número de columnas de la matriz. */
const val COLS = 5

/** Valor mínimo de los aleatorios. */
const val MIN_VALUE = 1

/** Valor máximo de los aleatorios. */
const val MAX_VALUE = 50

/** Punto de entrada: bucle del menú con todo el código en main. */
fun main() {
    val matrix = Array(ROWS) { IntArray(COLS) }
    var filled = false
    var option: Int

    do {
        println(
            """
            |
            |1.- Rellenar matriz
            |2.- Mostrar
            |3.- Total fila
            |4.- Total columna
            |5.- Buscar número
            |6.- Media
            |7.- Traspuesta
            |0.- Salir
            """.trimMargin()
        )
        print("Elige una opción: ")
        option = readln().trim().toIntOrNull() ?: -1

        if (option in 2..7 && !filled) {
            println("La matriz está vacía. Rellénala primero (opción 1).")
            continue
        }

        when (option) {
            1 -> {
                for (i in 0 until ROWS) for (j in 0 until COLS) matrix[i][j] = (MIN_VALUE..MAX_VALUE).random()
                filled = true
                println("Matriz rellenada.")
            }
            2 -> for (row in matrix) println(row.joinToString("\t"))
            3 -> {
                var row: Int
                do {
                    print("Número de fila (0-${ROWS - 1}): ")
                    row = readln().trim().toIntOrNull() ?: -1
                } while (row !in 0 until ROWS)
                println("Total de la fila $row: ${matrix[row].sum()}")
            }
            4 -> {
                var col: Int
                do {
                    print("Número de columna (0-${COLS - 1}): ")
                    col = readln().trim().toIntOrNull() ?: -1
                } while (col !in 0 until COLS)
                var total = 0
                for (i in 0 until ROWS) total += matrix[i][col]
                println("Total de la columna $col: $total")
            }
            5 -> {
                print("Número a buscar: ")
                val target = readln().trim().toIntOrNull()
                if (target == null) println("Error: no es un número.")
                else if (matrix.any { target in it }) println("$target está en la matriz.")
                else println("$target no está en la matriz.")
            }
            6 -> {
                var sum = 0
                for (row in matrix) sum += row.sum()
                println("Media: ${"%.2f".format(sum.toDouble() / (ROWS * COLS))}")
            }
            7 -> for (j in 0 until COLS) {
                for (i in 0 until ROWS) print("${matrix[i][j]}\t")
                println()
            }
            0 -> println("¡Hasta pronto!")
            else -> println("Error: opción no válida.")
        }
    } while (option != 0)
}
```
:::

::: details Matriz5x5Plus
**Enunciado:** Modificar el ejercicio anterior para realizar las acciones desde funciones.

```kotlin
/**
 * Proyecto **Matriz5x5Plus** — UP02 · Actividad 2.
 *
 * Versión de Matriz5x5 en la que cada acción del menú se realiza desde una función con una
 * única responsabilidad.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de filas de la matriz. Cambiarlo no exige tocar nada más. */
const val ROWS = 5

/** Número de columnas de la matriz. */
const val COLS = 5

/** Valor mínimo de los aleatorios. */
const val MIN_VALUE = 1

/** Valor máximo de los aleatorios. */
const val MAX_VALUE = 50

/** Punto de entrada: bucle del menú que delega cada acción en una función. */
fun main() {
    val matrix = Array(ROWS) { IntArray(COLS) }
    var filled = false

    while (true) {
        showMenu()
        val option = readInt("Elige una opción: ")
        if (option in 2..7 && !filled) {
            println("La matriz está vacía. Rellénala primero (opción 1).")
            continue
        }
        when (option) {
            1 -> { fillRandom(matrix); filled = true; println("Matriz rellenada.") }
            2 -> printMatrix(matrix)
            3 -> {
                val row = readIntInRange("Número de fila (0-${ROWS - 1}): ", 0, ROWS - 1)
                println("Total de la fila $row: ${rowTotal(matrix, row)}")
            }
            4 -> {
                val col = readIntInRange("Número de columna (0-${COLS - 1}): ", 0, COLS - 1)
                println("Total de la columna $col: ${columnTotal(matrix, col)}")
            }
            5 -> {
                val target = readInt("Número a buscar: ")
                println(if (contains(matrix, target)) "$target está en la matriz." else "$target no está en la matriz.")
            }
            6 -> println("Media: ${"%.2f".format(average(matrix))}")
            7 -> printMatrix(transpose(matrix))
            0 -> { println("¡Hasta pronto!"); return }
            else -> println("Error: opción no válida.")
        }
    }
}

/** Muestra las opciones del menú. */
fun showMenu() {
    println(
        """
        |
        |1.- Rellenar matriz
        |2.- Mostrar
        |3.- Total fila
        |4.- Total columna
        |5.- Buscar número
        |6.- Media
        |7.- Traspuesta
        |0.- Salir
        """.trimMargin()
    )
}

/**
 * Rellena la matriz con números aleatorios entre [MIN_VALUE] y [MAX_VALUE].
 *
 * @param matrix matriz que se rellena (se modifica).
 */
fun fillRandom(matrix: Array<IntArray>) {
    for (row in matrix) for (j in row.indices) row[j] = (MIN_VALUE..MAX_VALUE).random()
}

/**
 * Muestra la matriz por pantalla.
 *
 * @param matrix matriz que se muestra.
 */
fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) println(row.joinToString("\t"))
}

/**
 * Suma los elementos de una fila.
 *
 * @param matrix matriz de trabajo.
 * @param row índice de la fila.
 * @return la suma de la fila.
 */
fun rowTotal(matrix: Array<IntArray>, row: Int): Int = matrix[row].sum()

/**
 * Suma los elementos de una columna.
 *
 * @param matrix matriz de trabajo.
 * @param col índice de la columna.
 * @return la suma de la columna.
 */
fun columnTotal(matrix: Array<IntArray>, col: Int): Int = matrix.sumOf { it[col] }

/**
 * Indica si un número está en la matriz.
 *
 * @param matrix matriz de trabajo.
 * @param target número buscado.
 * @return true si aparece al menos una vez.
 */
fun contains(matrix: Array<IntArray>, target: Int): Boolean = matrix.any { target in it }

/**
 * Calcula la media de todos los elementos.
 *
 * @param matrix matriz de trabajo.
 * @return la media aritmética.
 */
fun average(matrix: Array<IntArray>): Double = matrix.flatMap { it.asList() }.average()

/**
 * Devuelve la traspuesta de la matriz (funciona también con matrices no cuadradas).
 *
 * @param matrix matriz original, que no se modifica.
 * @return una matriz nueva con filas y columnas intercambiadas.
 */
fun transpose(matrix: Array<IntArray>): Array<IntArray> =
    Array(matrix[0].size) { i -> IntArray(matrix.size) { j -> matrix[j][i] } }

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
```
:::
