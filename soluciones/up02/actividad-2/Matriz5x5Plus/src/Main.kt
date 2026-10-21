/**
 * Proyecto **Matriz5x5Plus** — UP02 · Actividad 2.
 *
 * Versión de Matriz5x5 en la que cada acción del menú se realiza desde una función con una
 * única responsabilidad.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de filas de la matriz. Cambiarlo no exige tocar nada más. */
const val ROWS = 5

/** Número de columnas de la matriz. */
const val COLS = 5

/** Valor mínimo de los aleatorios. */
const val MIN_VALUE = 1

/** Valor máximo de los aleatorios. */
const val MAX_VALUE = 50

/** Punto de entrada: bucle del menú que delega cada acción en una función. */
fun main() {
    val matrix = Array(ROWS) { IntArray(COLS) }
    var filled = false

    while (true) {
        showMenu()
        val option = readInt("Elige una opción: ")
        if (option in 2..7 && !filled) {
            println("La matriz está vacía. Rellénala primero (opción 1).")
            continue
        }
        when (option) {
            1 -> { fillRandom(matrix); filled = true; println("Matriz rellenada.") }
            2 -> printMatrix(matrix)
            3 -> {
                val row = readIntInRange("Número de fila (0-${ROWS - 1}): ", 0, ROWS - 1)
                println("Total de la fila $row: ${rowTotal(matrix, row)}")
            }
            4 -> {
                val col = readIntInRange("Número de columna (0-${COLS - 1}): ", 0, COLS - 1)
                println("Total de la columna $col: ${columnTotal(matrix, col)}")
            }
            5 -> {
                val target = readInt("Número a buscar: ")
                println(if (contains(matrix, target)) "$target está en la matriz." else "$target no está en la matriz.")
            }
            6 -> println("Media: ${"%.2f".format(average(matrix))}")
            7 -> printMatrix(transpose(matrix))
            0 -> { println("¡Hasta pronto!"); return }
            else -> println("Error: opción no válida.")
        }
    }
}

/** Muestra las opciones del menú. */
fun showMenu() {
    println(
        """
        |
        |1.- Rellenar matriz
        |2.- Mostrar
        |3.- Total fila
        |4.- Total columna
        |5.- Buscar número
        |6.- Media
        |7.- Traspuesta
        |0.- Salir
        """.trimMargin()
    )
}

/**
 * Rellena la matriz con números aleatorios entre [MIN_VALUE] y [MAX_VALUE].
 *
 * @param matrix matriz que se rellena (se modifica).
 */
fun fillRandom(matrix: Array<IntArray>) {
    for (row in matrix) for (j in row.indices) row[j] = (MIN_VALUE..MAX_VALUE).random()
}

/**
 * Muestra la matriz por pantalla.
 *
 * @param matrix matriz que se muestra.
 */
fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) println(row.joinToString("\t"))
}

/**
 * Suma los elementos de una fila.
 *
 * @param matrix matriz de trabajo.
 * @param row índice de la fila.
 * @return la suma de la fila.
 */
fun rowTotal(matrix: Array<IntArray>, row: Int): Int = matrix[row].sum()

/**
 * Suma los elementos de una columna.
 *
 * @param matrix matriz de trabajo.
 * @param col índice de la columna.
 * @return la suma de la columna.
 */
fun columnTotal(matrix: Array<IntArray>, col: Int): Int = matrix.sumOf { it[col] }

/**
 * Indica si un número está en la matriz.
 *
 * @param matrix matriz de trabajo.
 * @param target número buscado.
 * @return true si aparece al menos una vez.
 */
fun contains(matrix: Array<IntArray>, target: Int): Boolean = matrix.any { target in it }

/**
 * Calcula la media de todos los elementos.
 *
 * @param matrix matriz de trabajo.
 * @return la media aritmética.
 */
fun average(matrix: Array<IntArray>): Double = matrix.flatMap { it.asList() }.average()

/**
 * Devuelve la traspuesta de la matriz (funciona también con matrices no cuadradas).
 *
 * @param matrix matriz original, que no se modifica.
 * @return una matriz nueva con filas y columnas intercambiadas.
 */
fun transpose(matrix: Array<IntArray>): Array<IntArray> =
    Array(matrix[0].size) { i -> IntArray(matrix.size) { j -> matrix[j][i] } }

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

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
