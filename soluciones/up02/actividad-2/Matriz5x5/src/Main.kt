/**
 * Proyecto **Matriz5x5** — UP02 · Actividad 2.
 *
 * Usando una matriz de 5x5 muestra un menú (rellenar, mostrar, total fila, total columna,
 * buscar, media, traspuesta, salir) y realiza la acción elegida hasta que se elige salir. Esta
 * versión resuelve todo dentro de main, tal como pide el enunciado; la versión Plus lo
 * organiza en funciones.
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

/** Punto de entrada: bucle del menú con todo el código en main. */
fun main() {
    val matrix = Array(ROWS) { IntArray(COLS) }
    var filled = false
    var option: Int

    do {
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
        print("Elige una opción: ")
        option = readln().trim().toIntOrNull() ?: -1

        if (option in 2..7 && !filled) {
            println("La matriz está vacía. Rellénala primero (opción 1).")
            continue
        }

        when (option) {
            1 -> {
                for (i in 0 until ROWS) for (j in 0 until COLS) matrix[i][j] = (MIN_VALUE..MAX_VALUE).random()
                filled = true
                println("Matriz rellenada.")
            }
            2 -> for (row in matrix) println(row.joinToString("\t"))
            3 -> {
                var row: Int
                do {
                    print("Número de fila (0-${ROWS - 1}): ")
                    row = readln().trim().toIntOrNull() ?: -1
                } while (row !in 0 until ROWS)
                println("Total de la fila $row: ${matrix[row].sum()}")
            }
            4 -> {
                var col: Int
                do {
                    print("Número de columna (0-${COLS - 1}): ")
                    col = readln().trim().toIntOrNull() ?: -1
                } while (col !in 0 until COLS)
                var total = 0
                for (i in 0 until ROWS) total += matrix[i][col]
                println("Total de la columna $col: $total")
            }
            5 -> {
                print("Número a buscar: ")
                val target = readln().trim().toIntOrNull()
                if (target == null) println("Error: no es un número.")
                else if (matrix.any { target in it }) println("$target está en la matriz.")
                else println("$target no está en la matriz.")
            }
            6 -> {
                var sum = 0
                for (row in matrix) sum += row.sum()
                println("Media: ${"%.2f".format(sum.toDouble() / (ROWS * COLS))}")
            }
            7 -> for (j in 0 until COLS) {
                for (i in 0 until ROWS) print("${matrix[i][j]}\t")
                println()
            }
            0 -> println("¡Hasta pronto!")
            else -> println("Error: opción no válida.")
        }
    } while (option != 0)
}
