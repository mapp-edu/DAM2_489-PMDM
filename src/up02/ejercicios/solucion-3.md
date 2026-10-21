---
layout: doc
title: "Solución Actividad 3 — Cadenas"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 3 — Cadenas

Soluciones de referencia de la [Actividad 3](./#actividad-3) del boletín. Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.

::: tip-box Descarga
Proyectos de esta actividad: <a :href="withBase('/descargas/up02/soluciones-up02-actividad-3.zip')" download>soluciones-up02-actividad-3.zip</a>. Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre (*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.
:::

::: warning-box Ejecútalas en IntelliJ IDEA
Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con `ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.
:::

## Claves de la solución

- Las comparaciones ignoran mayúsculas (`equals(…, ignoreCase = true)`, `lowercase()`) y las vocales acentuadas cuentan como vocales.
- `PalabraE` y `Palabras` usan `Regex` (`\p{L}` para letras con tilde, `\s+` para varios espacios).
- `LetraNombre` pide la posición contando desde 1 (como la entiende el usuario) y resta 1 al indexar.
- `DatosPersonales` justifica los tipos: `String` para textos e `Int` para día, mes y año; usa `java.time` para validar la fecha y calcular la edad.
- `ListaNombresPlus` agrupa con `groupBy` para indicar qué nombres se repiten y cuántas veces.

## Proyectos

::: details Saludo
**Enunciado:** Programa que pida al usuario su nombre y apellidos en variables diferentes y salude al usuario con esos datos.

```kotlin
/**
 * Proyecto **Saludo** — UP02 · Actividad 3.
 *
 * Pide el nombre y los apellidos en variables diferentes y saluda al usuario.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val name = readText("Introduce tu nombre: ")
    val surnames = readText("Introduce tus apellidos: ")
    println("¡Hola, $name $surnames! Bienvenido/a.")
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details DosNombres
**Enunciado:** Programa que pida al usuario dos nombres e indique si son iguales o no. Si son diferentes mostrará la longitud de cada uno.

```kotlin
/**
 * Proyecto **DosNombres** — UP02 · Actividad 3.
 *
 * Pide dos nombres e indica si son iguales; si son diferentes, muestra la longitud de cada
 * uno. La comparación ignora mayúsculas y minúsculas.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val first = readText("Primer nombre: ")
    val second = readText("Segundo nombre: ")
    if (first.equals(second, ignoreCase = true)) {
        println("Los nombres son iguales.")
    } else {
        println("Los nombres son diferentes.")
        println("\"$first\" tiene ${first.length} caracteres.")
        println("\"$second\" tiene ${second.length} caracteres.")
    }
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details EmpiezaAcaba
**Enunciado:** Programa que pida al usuario una frase e indique si empiezan y acaban con la misma letra. Usa startsWith y endsWith.

```kotlin
/**
 * Proyecto **EmpiezaAcaba** — UP02 · Actividad 3.
 *
 * Pide una frase e indica si empieza y acaba con la misma letra, usando startsWith y endsWith.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val sentence = readText("Introduce una frase: ")
    val firstLetter = sentence.first().toString()
    // Se ignoran mayúsculas para que «Ana» empiece y acabe por la misma letra
    val sameLetter = sentence.startsWith(firstLetter, ignoreCase = true) &&
        sentence.endsWith(firstLetter, ignoreCase = true)
    println(
        if (sameLetter) "La frase empieza y acaba con la letra '$firstLetter'."
        else "La frase no empieza y acaba con la misma letra."
    )
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details LetraNombre
**Enunciado:** Programa que pida al usuario su nombre completo y lo almacene en una cadena. A continuación, mostrará la longitud del nombre y pedirá al usuario una posición (máximo la longitud) e indicará la letra que se encuentra en esa posición.

```kotlin
/**
 * Proyecto **LetraNombre** — UP02 · Actividad 3.
 *
 * Pide el nombre completo, muestra su longitud, pide una posición (como máximo la longitud) e
 * indica la letra que hay en ella. Las posiciones se cuentan desde 1, como las cuenta el
 * usuario.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val fullName = readText("Introduce tu nombre completo: ")
    println("Tu nombre tiene ${fullName.length} caracteres.")
    val position = readIntInRange("Introduce una posición (1-${fullName.length}): ", 1, fullName.length)
    // El usuario cuenta desde 1; los índices de String empiezan en 0
    println("En la posición $position está el carácter '${fullName[position - 1]}'.")
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
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

::: details PalabraE
**Enunciado:** Programa que pida al usuario una palabra que empiece por E y de longitud mínima 5. El programa acabará cuando el usuario introduzca una palabra válida.

```kotlin
/**
 * Proyecto **PalabraE** — UP02 · Actividad 3.
 *
 * Pide una palabra que empiece por E y tenga al menos 5 letras; termina cuando la palabra es
 * válida.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Expresión regular: empieza por e/E y tiene al menos 5 letras en total (sin espacios). */
val VALID_WORD = Regex("^[eEéÉ]\\p{L}{4,}$")

/** Punto de entrada. */
fun main() {
    while (true) {
        print("Introduce una palabra que empiece por E (mínimo 5 letras): ")
        val word = readln().trim()
        when {
            !word.startsWith("e", ignoreCase = true) -> println("No empieza por E.")
            word.length < 5 -> println("Tiene menos de 5 letras.")
            !VALID_WORD.matches(word) -> println("Debe ser una sola palabra formada por letras.")
            else -> {
                println("¡Correcto! \"$word\" es válida.")
                return
            }
        }
    }
}
```
:::

::: details Palabras
**Enunciado:** Programa que pida al usuario una frase e indique cuántas palabras tiene.

```kotlin
/**
 * Proyecto **Palabras** — UP02 · Actividad 3.
 *
 * Pide una frase e indica cuántas palabras tiene; separa por cualquier secuencia de espacios
 * para no contar palabras vacías.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln().trim()
    // \s+ agrupa varios espacios o tabuladores seguidos como un único separador
    val words = if (sentence.isEmpty()) 0 else sentence.split(Regex("\\s+")).size
    println("La frase tiene $words palabra(s).")
}
```
:::

::: details Digrafos
**Enunciado:** Programa que pida al usuario una frase e indique si aparece o no cada uno de los dígrafos: ch, rr, ll, gu o qu.

```kotlin
/**
 * Proyecto **Digrafos** — UP02 · Actividad 3.
 *
 * Pide una frase e indica si aparece o no cada uno de los dígrafos ch, rr, ll, gu y qu.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dígrafos que se buscan. */
val DIGRAPHS = listOf("ch", "rr", "ll", "gu", "qu")

/** Punto de entrada. */
fun main() {
    val sentence = readText("Introduce una frase: ").lowercase()
    for (digraph in DIGRAPHS) {
        println("'$digraph': ${if (digraph in sentence) "aparece" else "no aparece"}")
    }
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details DatosPersonales
**Enunciado:** Programa que pida al usuario los siguientes datos y los almacene en variables, presta atención al tipo de variable necesario para cada caso. Datos a pedir: nombre, apellidos, país de nacimiento, día de nacimiento, mes de nacimiento, año de nacimiento.

```kotlin
/**
 * Proyecto **DatosPersonales** — UP02 · Actividad 3.
 *
 * Pide nombre, apellidos, país, día, mes y año de nacimiento y los guarda en variables del
 * tipo adecuado: String para los textos e Int para los datos numéricos de la fecha, validando
 * que la fecha exista.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

import java.time.LocalDate
import java.time.Period

/** Punto de entrada. */
fun main() {
    val name: String = readText("Nombre: ")
    val surnames: String = readText("Apellidos: ")
    val country: String = readText("País de nacimiento: ")
    val currentYear = LocalDate.now().year
    val birthYear: Int = readIntInRange("Año de nacimiento: ", 1900, currentYear)
    val birthMonth: Int = readIntInRange("Mes de nacimiento (1-12): ", 1, 12)
    val maxDay = LocalDate.of(birthYear, birthMonth, 1).lengthOfMonth()
    val birthDay: Int = readIntInRange("Día de nacimiento (1-$maxDay): ", 1, maxDay)

    val birthDate = LocalDate.of(birthYear, birthMonth, birthDay)
    val age = Period.between(birthDate, LocalDate.now()).years

    println(
        """
        |--- Datos personales ---
        |Nombre:     $name $surnames
        |País:       $country
        |Nacimiento: $birthDay/$birthMonth/$birthYear ($age años)
        """.trimMargin()
    )
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
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

::: details Password
**Enunciado:** Programa que pida el nombre de usuario, también se pedirá la contraseña un máximo de tres veces, si la contraseña introducida coincide con "2D4m" se mostrará un mensaje de bienvenida. En caso de introducir una contraseña incorrecta se mostrará un mensaje indicándolo. Si se introducen 3 contraseñas incorrectas se mostrará un mensaje como el siguiente "Contacte con el administrador para reiniciar su contraseña".

```kotlin
/**
 * Proyecto **Password** — UP02 · Actividad 3.
 *
 * Pide el nombre de usuario y la contraseña un máximo de tres veces. Si coincide con "2D4m" da
 * la bienvenida; tras tres fallos pide contactar con el administrador.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Contraseña correcta. */
const val PASSWORD = "2D4m"

/** Número máximo de intentos. */
const val MAX_ATTEMPTS = 3

/** Punto de entrada. */
fun main() {
    val user = readText("Usuario: ")
    for (attempt in 1..MAX_ATTEMPTS) {
        print("Contraseña (intento $attempt de $MAX_ATTEMPTS): ")
        if (readln() == PASSWORD) {
            println("¡Bienvenido/a, $user!")
            return
        }
        println("Contraseña incorrecta.")
    }
    println("Contacte con el administrador para reiniciar su contraseña")
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details CantidadA
**Enunciado:** Programa que pida una frase y muestre cuántas 'a' contiene.

```kotlin
/**
 * Proyecto **CantidadA** — UP02 · Actividad 3.
 *
 * Pide una frase y muestra cuántas letras 'a' contiene (mayúsculas y acentuadas incluidas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln()
    val count = sentence.count { it.lowercaseChar() in "aá" }
    println("La frase contiene $count 'a'.")
}
```
:::

::: details SinVocales
**Enunciado:** Programa que pida una frase y la muestre sin vocales.

```kotlin
/**
 * Proyecto **SinVocales** — UP02 · Actividad 3.
 *
 * Pide una frase y la muestra sin vocales (mayúsculas, minúsculas y acentuadas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Expresión regular que reconoce cualquier vocal. */
val VOWELS = Regex("[aeiouáéíóúü]", RegexOption.IGNORE_CASE)

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    println(readln().replace(VOWELS, ""))
    // Alternativa sin Regex: sentence.filterNot { it.lowercaseChar() in "aeiouáéíóúü" }
}
```
:::

::: details CantidadVocales
**Enunciado:** Programa que pida una frase y muestre la cantidad de cada vocal y el total de vocales.

```kotlin
/**
 * Proyecto **CantidadVocales** — UP02 · Actividad 3.
 *
 * Pide una frase y muestra la cantidad de cada vocal y el total de vocales. Las vocales
 * acentuadas se cuentan con su vocal base.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln().lowercase()

    // Mapa ordenado vocal → contador
    val counts = linkedMapOf('a' to 0, 'e' to 0, 'i' to 0, 'o' to 0, 'u' to 0)
    for (char in sentence) {
        val base = normalizeVowel(char)
        if (base != null) counts[base] = counts.getValue(base) + 1
    }

    counts.forEach { (vowel, count) -> println("$vowel: $count") }
    println("Total de vocales: ${counts.values.sum()}")
}

/**
 * Devuelve la vocal base de un carácter (á → a, ü → u…) o null si no es vocal.
 *
 * @param char carácter en minúsculas.
 * @return la vocal sin tilde o null.
 */
fun normalizeVowel(char: Char): Char? = when (char) {
    'a', 'á' -> 'a'
    'e', 'é' -> 'e'
    'i', 'í' -> 'i'
    'o', 'ó' -> 'o'
    'u', 'ú', 'ü' -> 'u'
    else -> null
}
```
:::

::: details ListaNombres
**Enunciado:** Programa que pida 10 nombres al usuario y los almacene en un array de cadenas.

```kotlin
/**
 * Proyecto **ListaNombres** — UP02 · Actividad 3.
 *
 * Pide 10 nombres y los almacena en un array de cadenas.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de nombres. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val names = Array(SIZE) { readText("Nombre ${it + 1} de $SIZE: ") }
    println("Nombres: ${names.joinToString(", ")}")
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::

::: details ListaNombresPlus
**Enunciado:** Modifica el programa anterior para que compruebe si algún nombre se repite en el array.

```kotlin
/**
 * Proyecto **ListaNombresPlus** — UP02 · Actividad 3.
 *
 * Como ListaNombres, pero comprueba si algún nombre se repite en el array (sin distinguir
 * mayúsculas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de nombres. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val names = Array(SIZE) { readText("Nombre ${it + 1} de $SIZE: ") }
    println("Nombres: ${names.joinToString(", ")}")

    // Se agrupan por nombre en minúsculas y se quedan los grupos con más de un elemento
    val repeated = names.groupBy { it.lowercase() }.filterValues { it.size > 1 }
    if (repeated.isEmpty()) {
        println("No se repite ningún nombre.")
    } else {
        repeated.forEach { (_, group) -> println("\"${group.first()}\" se repite ${group.size} veces.") }
    }
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
```
:::
