/**
 * Proyecto **AleatoriosUnicos** — UP02 · Actividad 2.
 *
 * Rellena un array con 10 enteros aleatorios entre 1 y 50 sin que se repita ninguno.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE)
    var filled = 0
    while (filled < SIZE) {
        val candidate = (1..50).random()
        // Solo se añade si no está ya entre las posiciones rellenadas
        if (numbers.take(filled).none { it == candidate }) {
            numbers[filled] = candidate
            filled++
        }
    }
    println("Array sin repetidos: ${numbers.joinToString(" ")}")
    // Alternativa idiomática: (1..50).shuffled().take(SIZE).toIntArray()
}
