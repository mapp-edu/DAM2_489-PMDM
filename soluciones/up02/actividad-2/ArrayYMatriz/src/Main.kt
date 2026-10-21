/**
 * Proyecto **ArrayYMatriz** — UP02 · Actividad 2.
 *
 * Rellena un array de 3 números y una matriz de 10x3 con aleatorios entre 1 y 5, y muestra si
 * alguna fila de la matriz es igual al array.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de columnas (longitud del array). */
const val COLS = 3

/** Número de filas de la matriz. */
const val ROWS = 10

/** Punto de entrada. */
fun main() {
    val array = IntArray(COLS) { (1..5).random() }
    val matrix = Array(ROWS) { IntArray(COLS) { (1..5).random() } }

    println("Array: ${array.joinToString(" ")}")
    println("Matriz:")
    matrix.forEachIndexed { i, row -> println("Fila $i: ${row.joinToString(" ")}") }

    // contentEquals compara contenido; == entre arrays compara referencias
    val equalRows = matrix.indices.filter { matrix[it].contentEquals(array) }
    if (equalRows.isEmpty()) println("Ninguna fila es igual al array.")
    else println("Filas iguales al array: ${equalRows.joinToString(", ")}")
}
