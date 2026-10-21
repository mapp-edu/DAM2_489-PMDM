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
