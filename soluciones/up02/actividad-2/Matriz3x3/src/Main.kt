/**
 * Proyecto **Matriz3x3** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 3x3 con enteros aleatorios entre 0 y 9 y la muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 3

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (0..9).random() } }
    for (row in matrix) println(row.joinToString(" "))
}
