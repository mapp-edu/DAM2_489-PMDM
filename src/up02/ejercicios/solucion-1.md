---
layout: doc
title: "Solución Actividad 1 — Variables, constantes y control de flujo"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 1 — Variables, constantes y control de flujo

Soluciones de referencia de la [Actividad 1](./#actividad-1) del boletín. Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.

::: tip-box Descarga
Proyectos de esta actividad: <a :href="withBase('/descargas/up02/soluciones-up02-actividad-1.zip')" download>soluciones-up02-actividad-1.zip</a>. Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre (*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.
:::

::: warning-box Ejecútalas en IntelliJ IDEA
Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con `ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.
:::

## Criterios comunes

| Aspecto | Cómo se resuelve | Criterio |
|:---|:---|:---|
| Normas del curso | Identificadores en inglés, KDoc de archivo con `@author` y `@version`, KDoc en cada función, clase y constante | RA2.i |
| Validación de entradas | `readln().toIntOrNull()` / `toDoubleOrNull()` dentro de funciones `readInt`, `readDouble`, `readIntInRange` y `readText`, que repiten la petición en vez de lanzar excepción. Los decimales aceptan coma o punto | RA2.g |
| Constantes | Tamaños, rangos y valores mágicos en `const val` (si se cambia el tamaño de la matriz de `Matriz5x5`, no hay que tocar nada más) | RA2.a |
| Kotlin idiomático | `if`/`when` como expresión, rangos (`in`, `downTo`, `step`, `until`), plantillas de cadena, `also` para intercambios, `IntArray(n) { … }` para inicializar | RA2.a |

Cada proyecto incluye sus propias funciones de lectura (copiadas) porque los proyectos tienen que ser independientes; en las unidades siguientes se puede proponer agruparlas en un módulo común.

## Claves de la solución

- `Intercambio` muestra la versión clásica con variable auxiliar y la idiomática `a = b.also { b = a }`.
- `TresAscendente` ordena con tres comparaciones e intercambios (sin colecciones), que es lo que se busca practicar.
- `MesALetra` valida que el día exista en el mes (incluido febrero en bisiestos), igual que `Bisiesto`.
- `Primos` prueba divisores solo hasta √n.
- `Factorial` usa `Long` y limita la entrada a 20 (21! desborda `Long`); se puede comentar `BigInteger` como ampliación.
- `MayorMenorMedia` controla el caso de que el primer número sea 0 (sin división entre cero).
- `NumerosRango` funciona sea cual sea el orden de los dos números.

## Proyectos

::: details Votar
**Enunciado:** Programa que pida la edad al usuario y que le indique si puede votar o no.

```kotlin
/**
 * Proyecto **Votar** — UP02 · Actividad 1.
 *
 * Pide la edad al usuario e indica si puede votar o no (mayoría de edad: 18 años).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Edad mínima para poder votar. */
const val VOTING_AGE = 18

/** Punto de entrada: pide la edad y muestra si la persona puede votar. */
fun main() {
    val age = readIntInRange("Introduce tu edad: ", 0, 130)
    if (age >= VOTING_AGE) println("Puedes votar.")
    else println("Todavía no puedes votar: te faltan ${VOTING_AGE - age} año(s).")
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

::: details MayorDeDos
**Enunciado:** Programa que pida dos números decimales al usuario e indique cuál es el mayor de ellos.

```kotlin
/**
 * Proyecto **MayorDeDos** — UP02 · Actividad 1.
 *
 * Pide dos números decimales e indica cuál es el mayor de ellos (o si son iguales).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: compara dos números decimales. */
fun main() {
    val first = readDouble("Introduce el primer número: ")
    val second = readDouble("Introduce el segundo número: ")
    val message = when {
        first > second -> "El mayor es $first"
        second > first -> "El mayor es $second"
        else -> "Los dos números son iguales ($first)"
    }
    println(message)
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details RangoNumerico
**Enunciado:** Programa que pida al usuario un número entero entre 1 y 20 y que indique si se ha introducido un número dentro de los válidos o no.

```kotlin
/**
 * Proyecto **RangoNumerico** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 20 e indica si el número introducido es válido o no.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Rango de valores válidos. */
val VALID_RANGE = 1..20

/** Punto de entrada: comprueba si el número está dentro del rango válido. */
fun main() {
    val number = readInt("Introduce un número entre ${VALID_RANGE.first} y ${VALID_RANGE.last}: ")
    if (number in VALID_RANGE) println("$number es un número válido.")
    else println("$number NO es válido: está fuera del rango $VALID_RANGE.")
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

::: details Intercambio
**Enunciado:** Programa que pida al usuario dos números enteros, a continuación, intercambiará el valor de las variables y, por último, mostrará el valor de las variables.

```kotlin
/**
 * Proyecto **Intercambio** — UP02 · Actividad 1.
 *
 * Pide dos números enteros, intercambia el valor de las variables y las muestra. Se muestra la
 * versión clásica con variable auxiliar y la idiomática de Kotlin con also.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: intercambia el valor de dos variables. */
fun main() {
    var a = readInt("Introduce el valor de a: ")
    var b = readInt("Introduce el valor de b: ")
    println("Antes:   a = $a, b = $b")

    // Versión clásica con variable auxiliar
    val aux = a
    a = b
    b = aux
    println("Después: a = $a, b = $b")

    // Versión idiomática en Kotlin (deshace el cambio para comprobarlo):
    // also se ejecuta después de evaluar b, por eso «a = b» recibe el valor antiguo de b
    a = b.also { b = a }
    println("De nuevo con also: a = $a, b = $b")
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

::: details GrandeDeTres
**Enunciado:** Programa que pida tres números decimales al usuario y los muestre cuál es el más grande.

```kotlin
/**
 * Proyecto **GrandeDeTres** — UP02 · Actividad 1.
 *
 * Pide tres números decimales y muestra cuál es el más grande, usando únicamente
 * condicionales.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra el mayor de tres números. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")
    val c = readDouble("Introduce el tercer número: ")

    // if es una expresión en Kotlin: su resultado se asigna directamente
    val greatest = if (a >= b && a >= c) a else if (b >= c) b else c
    println("El número más grande es $greatest")
    // Alternativa con la librería estándar: maxOf(a, b, c)
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details TresAscendente
**Enunciado:** Programa que pida tres números decimales al usuario y los muestre en orden ascendente.

```kotlin
/**
 * Proyecto **TresAscendente** — UP02 · Actividad 1.
 *
 * Pide tres números decimales y los muestra en orden ascendente, ordenándolos con intercambios
 * (sin usar colecciones).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: ordena tres números de menor a mayor. */
fun main() {
    var a = readDouble("Introduce el primer número: ")
    var b = readDouble("Introduce el segundo número: ")
    var c = readDouble("Introduce el tercer número: ")

    // Tres comparaciones bastan para ordenar tres valores
    if (a > b) a = b.also { b = a }
    if (b > c) b = c.also { c = b }
    if (a > b) a = b.also { b = a }

    println("Orden ascendente: $a, $b, $c")
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details Operaciones
**Enunciado:** Programa que pida dos números decimales al usuario y que calcule la suma, resta, producto y división de dichos números. Se debe tener cuidado de no dividir entre cero.

```kotlin
/**
 * Proyecto **Operaciones** — UP02 · Actividad 1.
 *
 * Pide dos números decimales y calcula su suma, resta, producto y división, evitando dividir
 * entre cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra las cuatro operaciones básicas. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")

    println("$a + $b = ${a + b}")
    println("$a - $b = ${a - b}")
    println("$a * $b = ${a * b}")
    if (b != 0.0) println("$a / $b = ${a / b}")
    else println("No se puede dividir entre cero.")
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details NumeroALetras
**Enunciado:** Programa que pida un número entero entre 1 y 5 al usuario y muestre por pantalla dicho número en letras, si se introduce un número no válido se mostrará el mensaje “Número no válido”.

```kotlin
/**
 * Proyecto **NumeroALetras** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 5 y lo muestra en letras; si no es válido, muestra «Número
 * no válido».
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: convierte un número del 1 al 5 a letras. */
fun main() {
    val number = readInt("Introduce un número entre 1 y 5: ")
    val text = when (number) {
        1 -> "uno"
        2 -> "dos"
        3 -> "tres"
        4 -> "cuatro"
        5 -> "cinco"
        else -> "Número no válido"
    }
    println(text)
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

::: details OperacionesMenu
**Enunciado:** Programa que pida dos números decimales al usuario, a continuación, se mostrará un menú con siguientes opciones: 1-sumar 2-restar 3-multiplicar 4-dividir 0-salir Se debe pedir al usuario que introduzca una opción y se realizará la operación indicada. Se debe tener cuidado de no dividir entre cero. En caso de introducir la opción cero en el menú no se realizará ninguna operación. Por último, el programa se despedirá del usuario.

```kotlin
/**
 * Proyecto **OperacionesMenu** — UP02 · Actividad 1.
 *
 * Pide dos números decimales y muestra un menú (1-sumar, 2-restar, 3-multiplicar, 4-dividir,
 * 0-salir). Realiza la operación elegida evitando dividir entre cero y, al final, se despide.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: pide dos números, una opción del menú y realiza la operación. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")
    showMenu()
    when (readIntInRange("Elige una opción: ", 0, 4)) {
        1 -> println("$a + $b = ${a + b}")
        2 -> println("$a - $b = ${a - b}")
        3 -> println("$a * $b = ${a * b}")
        4 -> if (b == 0.0) println("No se puede dividir entre cero.") else println("$a / $b = ${a / b}")
        0 -> println("No se realiza ninguna operación.")
    }
    println("¡Hasta pronto!")
}

/** Muestra las opciones del menú. */
fun showMenu() {
    println(
        """
        |1 - Sumar
        |2 - Restar
        |3 - Multiplicar
        |4 - Dividir
        |0 - Salir
        """.trimMargin()
    )
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
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

::: details ParImpar
**Enunciado:** Programa que pida un número entero e indique si es par o impar.

```kotlin
/**
 * Proyecto **ParImpar** — UP02 · Actividad 1.
 *
 * Pide un número entero e indica si es par o impar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: indica la paridad de un número. */
fun main() {
    val number = readInt("Introduce un número entero: ")
    // Se usa % 2 == 0 porque el resto de un negativo impar es -1, no 1
    println(if (number % 2 == 0) "$number es par." else "$number es impar.")
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

::: details MesALetra
**Enunciado:** Programa que pida al usuario la fecha actual pidiendo el número de día, el número de mes y el número de año. El programa mostrará un mensaje como el siguiente: "7 de diciembre de 2019"

```kotlin
/**
 * Proyecto **MesALetra** — UP02 · Actividad 1.
 *
 * Pide día, mes y año de la fecha actual y la muestra con el formato «7 de diciembre de 2019»,
 * validando que la fecha exista (incluidos años bisiestos).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra una fecha con el mes en letras. */
fun main() {
    val year = readIntInRange("Introduce el año: ", 1, 9999)
    val month = readIntInRange("Introduce el mes (1-12): ", 1, 12)
    val day = readIntInRange("Introduce el día: ", 1, daysInMonth(month, year))
    println("$day de ${monthName(month)} de $year")
}

/**
 * Devuelve el nombre en castellano del mes indicado.
 *
 * @param month número de mes, entre 1 y 12.
 * @return nombre del mes en minúsculas.
 */
fun monthName(month: Int): String = when (month) {
    1 -> "enero"; 2 -> "febrero"; 3 -> "marzo"; 4 -> "abril"
    5 -> "mayo"; 6 -> "junio"; 7 -> "julio"; 8 -> "agosto"
    9 -> "septiembre"; 10 -> "octubre"; 11 -> "noviembre"; 12 -> "diciembre"
    else -> throw IllegalArgumentException("Mes no válido: $month")
}

/**
 * Calcula cuántos días tiene un mes en un año concreto.
 *
 * @param month número de mes, entre 1 y 12.
 * @param year año, necesario para saber si febrero tiene 28 o 29 días.
 * @return número de días del mes.
 */
fun daysInMonth(month: Int, year: Int): Int = when (month) {
    2 -> if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) 29 else 28
    4, 6, 9, 11 -> 30
    else -> 31
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

::: details VocalConsonante
**Enunciado:** Programa que pida al usuario una letra y diga si es una vocal o una consonante. Se debe realizar con la instrucción `when`.

```kotlin
/**
 * Proyecto **VocalConsonante** — UP02 · Actividad 1.
 *
 * Pide una letra e indica si es vocal o consonante usando la instrucción when. Tiene en cuenta
 * mayúsculas, vocales acentuadas y la ñ.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: clasifica una letra como vocal o consonante. */
fun main() {
    print("Introduce una letra: ")
    val input = readln().trim()
    if (input.length != 1) {
        println("Error: debes introducir una única letra.")
        return
    }
    val letter = input.lowercase()[0]
    val result = when {
        !letter.isLetter() -> "'$input' no es una letra."
        letter in "aeiouáéíóúü" -> "'$input' es una vocal."
        else -> "'$input' es una consonante."
    }
    println(result)
}
```
:::

::: details Division
**Enunciado:** Programa que pida dos números enteros al usuario y muestre la división del mayor entre el menor. Si el menor es cero no debe realizarse la división.

```kotlin
/**
 * Proyecto **Division** — UP02 · Actividad 1.
 *
 * Pide dos números enteros y muestra la división del mayor entre el menor. Si el menor es cero
 * no se realiza la división.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: divide el mayor de dos números entre el menor. */
fun main() {
    val a = readInt("Introduce el primer número: ")
    val b = readInt("Introduce el segundo número: ")
    val greater = maxOf(a, b)
    val smaller = minOf(a, b)

    if (smaller == 0) {
        println("El menor es cero: no se puede realizar la división.")
    } else {
        // Se convierte a Double para no perder los decimales de la división entera
        println("$greater / $smaller = ${greater.toDouble() / smaller}")
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
```
:::

::: details Bisiesto
**Enunciado:** Programa que pida un año al usuario e indique si ese año es bisiesto o no. Un año bisiesto es aquel que es divisible por 4 es bisiesto y no debe ser divisible entre 100. También es bisiesto si un año es divisible entre 100 y además es divisible entre 400.

```kotlin
/**
 * Proyecto **Bisiesto** — UP02 · Actividad 1.
 *
 * Pide un año e indica si es bisiesto: divisible entre 4 y no entre 100, o divisible entre
 * 400.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: indica si un año es bisiesto. */
fun main() {
    val year = readIntInRange("Introduce un año: ", 1, 9999)
    println(if (isLeapYear(year)) "$year es bisiesto." else "$year no es bisiesto.")
}

/**
 * Indica si un año es bisiesto según el calendario gregoriano.
 *
 * @param year año que se comprueba.
 * @return true si el año es bisiesto.
 */
fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0

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

::: details QuieroPar
**Enunciado:** Programa que pida un número hasta que dicho número sea par. Si el número no es par se indicará antes de volver a pedir otro número. Finalmente se mostrará un mensaje indicando que el número introducido es par.

```kotlin
/**
 * Proyecto **QuieroPar** — UP02 · Actividad 1.
 *
 * Pide números hasta que se introduzca uno par, avisando cada vez que no lo es.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: repite la petición hasta obtener un número par. */
fun main() {
    var number = readInt("Introduce un número par: ")
    while (number % 2 != 0) {
        println("$number no es par.")
        number = readInt("Introduce un número par: ")
    }
    println("¡Bien! $number es par.")
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

::: details IntroduceMes
**Enunciado:** Programa que pida al usuario un número de mes, si el número no está entre 1 y 12 incluidos se volverá a pedir. Finalmente, con el número introducido se mostrará el mes en letras.

```kotlin
/**
 * Proyecto **IntroduceMes** — UP02 · Actividad 1.
 *
 * Pide un número de mes hasta que esté entre 1 y 12 y lo muestra en letras.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Nombres de los meses; el índice 0 corresponde a enero. */
val MONTHS = listOf(
    "enero", "febrero", "marzo", "abril", "mayo", "junio",
    "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
)

/** Punto de entrada: pide un mes válido y lo muestra en letras. */
fun main() {
    var month: Int
    do {
        month = readInt("Introduce un número de mes (1-12): ")
        if (month !in 1..12) println("El mes $month no existe.")
    } while (month !in 1..12)
    println("El mes $month es ${MONTHS[month - 1]}.")
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

::: details AcumulandoDiez
**Enunciado:** Programa que pida 10 números al usuario y finalmente muestre la suma de todos los números introducidos.

```kotlin
/**
 * Proyecto **AcumulandoDiez** — UP02 · Actividad 1.
 *
 * Pide 10 números y muestra la suma de todos ellos.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Cantidad de números que se piden. */
const val AMOUNT = 10

/** Punto de entrada: acumula la suma de diez números. */
fun main() {
    var sum = 0.0
    for (i in 1..AMOUNT) {
        sum += readDouble("Número $i de $AMOUNT: ")
    }
    println("La suma es $sum")
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details Primos
**Enunciado:** Programa que pida números e indique si son primos o no, el programa parará cuando se introduzca el número cero.

```kotlin
/**
 * Proyecto **Primos** — UP02 · Actividad 1.
 *
 * Pide números e indica si son primos; el programa termina al introducir el cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: comprueba números hasta que se introduce el 0. */
fun main() {
    var number = readInt("Introduce un número (0 para salir): ")
    while (number != 0) {
        println(if (isPrime(number)) "$number es primo." else "$number no es primo.")
        number = readInt("Introduce un número (0 para salir): ")
    }
    println("Fin del programa.")
}

/**
 * Indica si un número es primo. Solo se prueban divisores hasta la raíz cuadrada,
 * porque si n = a·b, uno de los dos factores es como mucho √n.
 *
 * @param n número que se comprueba.
 * @return true si n es primo (los números menores que 2 no lo son).
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    var divisor = 2
    while (divisor.toLong() * divisor <= n) {
        if (n % divisor == 0) return false
        divisor++
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

::: details DiezCuadrados
**Enunciado:** Programa que muestre los cuadrados de los primeros diez números naturales.

```kotlin
/**
 * Proyecto **DiezCuadrados** — UP02 · Actividad 1.
 *
 * Muestra los cuadrados de los diez primeros números naturales.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra n² para n de 1 a 10. */
fun main() {
    for (n in 1..10) {
        println("$n² = ${n * n}")
    }
}
```
:::

::: details TablaMultiplicar
**Enunciado:** Programa que pida un número entero entre 1 y 10 y mostrará la tabla de multiplicar de dicho número.

```kotlin
/**
 * Proyecto **TablaMultiplicar** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 10 y muestra su tabla de multiplicar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra la tabla de multiplicar de un número. */
fun main() {
    val number = readIntInRange("Introduce un número entre 1 y 10: ", 1, 10)
    println("Tabla del $number")
    for (i in 1..10) {
        println("$number x $i = ${number * i}")
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

::: details AcumulandoN
**Enunciado:** Programa que pida un número al usuario (N), a continuación, pedirá N números y finalmente mostrará la suma de esos N números.

```kotlin
/**
 * Proyecto **AcumulandoN** — UP02 · Actividad 1.
 *
 * Pide un número N, a continuación pide N números y muestra su suma.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: suma N números introducidos por el usuario. */
fun main() {
    val n = readIntInRange("¿Cuántos números vas a introducir? ", 1, 1000)
    var sum = 0.0
    repeat(n) { i ->
        sum += readDouble("Número ${i + 1} de $n: ")
    }
    println("La suma de los $n números es $sum")
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

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details CienAscendente
**Enunciado:** Programa que muestre los primeros 100 números naturales en orden ascendente.

```kotlin
/**
 * Proyecto **CienAscendente** — UP02 · Actividad 1.
 *
 * Muestra los 100 primeros números naturales en orden ascendente.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra del 1 al 100. */
fun main() {
    for (n in 1..100) print("$n ")
    println()
}
```
:::

::: details CienDescendente
**Enunciado:** Programa que muestre los primeros 100 números naturales en orden descendente.

```kotlin
/**
 * Proyecto **CienDescendente** — UP02 · Actividad 1.
 *
 * Muestra los 100 primeros números naturales en orden descendente.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra del 100 al 1. */
fun main() {
    for (n in 100 downTo 1) print("$n ")
    println()
}
```
:::

::: details DeTresEnTres
**Enunciado:** Programa que muestre números de tres en tres desde el uno hasta el 100.

```kotlin
/**
 * Proyecto **DeTresEnTres** — UP02 · Actividad 1.
 *
 * Muestra los números de tres en tres desde el 1 hasta el 100.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra 1, 4, 7… hasta 100. */
fun main() {
    for (n in 1..100 step 3) print("$n ")
    println()
}
```
:::

::: details Factorial
**Enunciado:** Programa que pida un número entero al usuario y calcule y muestre su factorial.

```kotlin
/**
 * Proyecto **Factorial** — UP02 · Actividad 1.
 *
 * Pide un número entero y muestra su factorial. Usa Long y limita la entrada a 20, el mayor
 * factorial que cabe en un Long.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Mayor número cuyo factorial cabe en un Long (20! ≈ 2,4·10¹⁸). */
const val MAX_FACTORIAL = 20

/** Punto de entrada: calcula el factorial de un número. */
fun main() {
    val n = readIntInRange("Introduce un número entre 0 y $MAX_FACTORIAL: ", 0, MAX_FACTORIAL)
    println("$n! = ${factorial(n)}")
}

/**
 * Calcula el factorial de forma iterativa.
 *
 * @param n número no negativo.
 * @return n! (0! = 1).
 */
fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) result *= i
    return result
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

::: details TablasMultiplicar
**Enunciado:** Programa que muestre las tablas de multiplicar de los números 1 a 10 incluidos.

```kotlin
/**
 * Proyecto **TablasMultiplicar** — UP02 · Actividad 1.
 *
 * Muestra las tablas de multiplicar del 1 al 10.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra las diez tablas de multiplicar. */
fun main() {
    for (table in 1..10) {
        println("--- Tabla del $table ---")
        for (i in 1..10) {
            println("$table x $i = ${table * i}")
        }
    }
}
```
:::

::: details MayorMenorMedia
**Enunciado:** Programa que pida números hasta que se introduzca el cero. Al final el programa indicará el mayor y el menor de los introducidos y la media de todos los números.

```kotlin
/**
 * Proyecto **MayorMenorMedia** — UP02 · Actividad 1.
 *
 * Pide números hasta que se introduce el cero y muestra el mayor, el menor y la media de los
 * introducidos (el cero no cuenta).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: calcula mayor, menor y media de una serie terminada en 0. */
fun main() {
    var count = 0
    var sum = 0.0
    var max = Double.NEGATIVE_INFINITY
    var min = Double.POSITIVE_INFINITY

    var number = readDouble("Introduce un número (0 para terminar): ")
    while (number != 0.0) {
        count++
        sum += number
        if (number > max) max = number
        if (number < min) min = number
        number = readDouble("Introduce un número (0 para terminar): ")
    }

    if (count == 0) {
        println("No se ha introducido ningún número.")
    } else {
        println("Mayor: $max")
        println("Menor: $min")
        println("Media: ${"%.2f".format(sum / count)}")
    }
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details MayoresCero
**Enunciado:** Programa que pida 10 números al usuario y finalmente muestre cuántos son mayores que cero.

```kotlin
/**
 * Proyecto **MayoresCero** — UP02 · Actividad 1.
 *
 * Pide 10 números y muestra cuántos son mayores que cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: cuenta los positivos entre diez números. */
fun main() {
    var positives = 0
    for (i in 1..10) {
        if (readDouble("Número $i de 10: ") > 0) positives++
    }
    println("Has introducido $positives número(s) mayor(es) que cero.")
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
```
:::

::: details NumerosRango
**Enunciado:** Programa que pida dos números enteros y muestre los números que hay entre esos dos introducidos, incluidos esos dos números.

```kotlin
/**
 * Proyecto **NumerosRango** — UP02 · Actividad 1.
 *
 * Pide dos números enteros y muestra los números comprendidos entre ambos, incluidos, sea cual
 * sea el orden en que se introduzcan.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra todos los enteros entre dos valores. */
fun main() {
    val a = readInt("Introduce el primer número: ")
    val b = readInt("Introduce el segundo número: ")
    // Se ordenan los extremos para que el rango no quede vacío si a > b
    for (n in minOf(a, b)..maxOf(a, b)) print("$n ")
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

::: details SumaParesImpares
**Enunciado:** Programa que recorra los 100 primeros números naturales y que muestre la suma de los que sean pares por un lado y la suma de los que sean impares por otro

```kotlin
/**
 * Proyecto **SumaParesImpares** — UP02 · Actividad 1.
 *
 * Recorre los 100 primeros números naturales y muestra, por separado, la suma de los pares y
 * la de los impares.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: suma pares e impares del 1 al 100. */
fun main() {
    var evenSum = 0
    var oddSum = 0
    for (n in 1..100) {
        if (n % 2 == 0) evenSum += n else oddSum += n
    }
    println("Suma de los pares: $evenSum")
    println("Suma de los impares: $oddSum")
}
```
:::
