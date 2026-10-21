/**
 * Proyecto **Diagonal3x3** — UP02 · Actividad 2.
 *
 * Rellena una matriz de 3x3 con enteros aleatorios entre 1 y 100, la muestra, muestra su
 * diagonal principal y su traspuesta.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dimensión de la matriz. */
const val SIZE = 3

/** Punto de entrada. */
fun main() {
    val matrix = Array(SIZE) { IntArray(SIZE) { (1..100).random() } }
    println("Matriz:")
    printMatrix(matrix)

    println("Diagonal: ${(0 until SIZE).map { matrix[it][it] }.joinToString(" ")}")

    // La traspuesta intercambia filas por columnas: t[i][j] = m[j][i]
    val transposed = Array(SIZE) { i -> IntArray(SIZE) { j -> matrix[j][i] } }
    println("Traspuesta:")
    printMatrix(transposed)
}

/**
 * Muestra una matriz alineando las columnas con tabuladores.
 *
 * @param matrix matriz que se muestra.
 */
fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) println(row.joinToString("\t"))
}
