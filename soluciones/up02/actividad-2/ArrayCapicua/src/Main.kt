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
