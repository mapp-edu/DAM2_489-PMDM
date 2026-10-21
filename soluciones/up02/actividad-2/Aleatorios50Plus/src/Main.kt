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
