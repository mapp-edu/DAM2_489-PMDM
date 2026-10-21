/**
 * Proyecto **ParImpar** — UP02 · Actividad 1.
 *
 * Pide un número entero e indica si es par o impar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: indica la paridad de un número. */
fun main() {
    val number = readInt("Introduce un número entero: ")
    // Se usa % 2 == 0 porque el resto de un negativo impar es -1, no 1
    println(if (number % 2 == 0) "$number es par." else "$number es impar.")
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
