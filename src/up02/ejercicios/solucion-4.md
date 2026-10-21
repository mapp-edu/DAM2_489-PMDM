---
layout: doc
title: "Solución Actividad 4 — Map"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 4 — Map

Soluciones de referencia de la [Actividad 4](./#actividad-4) del boletín. Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.

::: tip-box Descarga
Proyectos de esta actividad: <a :href="withBase('/descargas/up02/soluciones-up02-actividad-4.zip')" download>soluciones-up02-actividad-4.zip</a>. Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre (*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.
:::

::: warning-box Ejecútalas en IntelliJ IDEA
Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con `ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.
:::

## Claves de la solución

- La colección es `MutableList<Map<String, Any>>`; las claves están en constantes para evitar errores tipográficos.
- El más barato se obtiene con `minByOrNull` y se trata el posible `null` con `?.let … ?: "ninguno"`.
- Al guardar los valores como `Any` hace falta convertir (`as Double`, `as Int`). Es un buen momento para explicar que una `data class` sería más segura, que es justo lo que se hace en la Actividad 5.

## Proyectos

::: details Productos
**Enunciado:** Colección de productos en la que cada elemento es un `Map` (nombre, precio y stock).

```kotlin
/**
 * Proyecto **Productos** — UP02 · Actividad 4.
 *
 * Almacena productos en una colección en la que cada elemento es un Map con las claves name,
 * price y stock. Pide productos hasta que el usuario decide finalizar y, al terminar, muestra
 * todos los productos, el más barato y los que tienen stock menor que 3.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Clave del nombre del producto. */
const val NAME = "name"

/** Clave del precio del producto. */
const val PRICE = "price"

/** Clave del stock del producto. */
const val STOCK = "stock"

/** Stock por debajo del cual un producto se considera escaso. */
const val LOW_STOCK = 3

/** Punto de entrada. */
fun main() {
    // Cada producto es un Map inmutable; la colección que los contiene sí es mutable
    val products = mutableListOf<Map<String, Any>>()

    do {
        products.add(readProduct())
    } while (askContinue())

    println("\n--- Productos (${products.size}) ---")
    products.forEach { println("  ${format(it)}") }

    // minByOrNull devuelve null si la lista está vacía (null safety)
    val cheapest = products.minByOrNull { it[PRICE] as Double }
    println("\nProducto más barato: ${cheapest?.let { format(it) } ?: "ninguno"}")

    val lowStock = products.filter { (it[STOCK] as Int) < LOW_STOCK }
    println("\nProductos con stock menor que $LOW_STOCK:")
    if (lowStock.isEmpty()) println("  Ninguno.") else lowStock.forEach { println("  ${format(it)}") }
}

/**
 * Pide los datos de un producto y los devuelve como Map.
 *
 * @return un Map con las claves [NAME], [PRICE] y [STOCK].
 */
fun readProduct(): Map<String, Any> {
    println("\nNuevo producto")
    val name = readText("  Nombre: ")
    var price: Double
    do {
        price = readDouble("  Precio (€): ")
        if (price < 0) println("  Error: el precio no puede ser negativo.")
    } while (price < 0)
    var stock: Int
    do {
        stock = readInt("  Stock: ")
        if (stock < 0) println("  Error: el stock no puede ser negativo.")
    } while (stock < 0)
    return mapOf(NAME to name, PRICE to price, STOCK to stock)
}

/**
 * Pregunta si se quiere añadir otro producto.
 *
 * @return true si el usuario responde «s», false si responde «n».
 */
fun askContinue(): Boolean {
    while (true) {
        print("¿Quieres añadir otro producto? (s/n): ")
        when (readln().trim().lowercase()) {
            "s", "si", "sí" -> return true
            "n", "no" -> return false
            else -> println("Responde «s» o «n».")
        }
    }
}

/**
 * Da formato a un producto para mostrarlo en una línea.
 *
 * @param product producto en forma de Map.
 * @return texto con nombre, precio y stock.
 */
fun format(product: Map<String, Any>): String =
    "${product[NAME]} · ${"%.2f".format(product[PRICE] as Double)} € · stock: ${product[STOCK]}"

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
```
:::
