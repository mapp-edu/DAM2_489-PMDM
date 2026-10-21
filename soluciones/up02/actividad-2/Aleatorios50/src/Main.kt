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
