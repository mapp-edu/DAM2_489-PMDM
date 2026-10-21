/**
 * Proyecto **MatrizTamanyos** — UP02 · Actividad 2.
 *
 * Pide N entre 2 y 5, crea una matriz NxN, pide los números para rellenarla y la muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val n = readIntInRange("Introduce el tamaño de la matriz (2-5): ", 2, 5)
    val matrix = Array(n) { row -> IntArray(n) { col -> readInt("Valor [$row][$col]: ") } }
    println("Matriz ${n}x$n:")
    for (row in matrix) println(row.joinToString("\t"))
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
