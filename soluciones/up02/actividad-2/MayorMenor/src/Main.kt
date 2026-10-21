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
