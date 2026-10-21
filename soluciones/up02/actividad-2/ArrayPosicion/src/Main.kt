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
