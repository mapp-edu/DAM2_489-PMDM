/**
 * Proyecto **Factorial** — UP02 · Actividad 1.
 *
 * Pide un número entero y muestra su factorial. Usa Long y limita la entrada a 20, el mayor
 * factorial que cabe en un Long.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Mayor número cuyo factorial cabe en un Long (20! ≈ 2,4·10¹⁸). */
const val MAX_FACTORIAL = 20

/** Punto de entrada: calcula el factorial de un número. */
fun main() {
    val n = readIntInRange("Introduce un número entre 0 y $MAX_FACTORIAL: ", 0, MAX_FACTORIAL)
    println("$n! = ${factorial(n)}")
}

/**
 * Calcula el factorial de forma iterativa.
 *
 * @param n número no negativo.
 * @return n! (0! = 1).
 */
fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) result *= i
    return result
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
