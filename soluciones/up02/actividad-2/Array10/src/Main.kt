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
