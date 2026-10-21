/**
 * Proyecto **Matriz4x4Aleatoria** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 4x4 con enteros aleatorios entre 0 y 9, la muestra junto con la media
 * de cada fila.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 4

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (0..9).random() } }
    for (row in matrix) {
        println("${row.joinToString(" ")}  → media: ${"%.2f".format(row.average())}")
    }
}
