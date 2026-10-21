/**
 * Proyecto **RangoNumerico** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 20 e indica si el número introducido es válido o no.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Rango de valores válidos. */
val VALID_RANGE = 1..20

/** Punto de entrada: comprueba si el número está dentro del rango válido. */
fun main() {
    val number = readInt("Introduce un número entre ${VALID_RANGE.first} y ${VALID_RANGE.last}: ")
    if (number in VALID_RANGE) println("$number es un número válido.")
    else println("$number NO es válido: está fuera del rango $VALID_RANGE.")
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
