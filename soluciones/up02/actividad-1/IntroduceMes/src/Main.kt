/**
 * Proyecto **IntroduceMes** — UP02 · Actividad 1.
 *
 * Pide un número de mes hasta que esté entre 1 y 12 y lo muestra en letras.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Nombres de los meses; el índice 0 corresponde a enero. */
val MONTHS = listOf(
    "enero", "febrero", "marzo", "abril", "mayo", "junio",
    "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
)

/** Punto de entrada: pide un mes válido y lo muestra en letras. */
fun main() {
    var month: Int
    do {
        month = readInt("Introduce un número de mes (1-12): ")
        if (month !in 1..12) println("El mes $month no existe.")
    } while (month !in 1..12)
    println("El mes $month es ${MONTHS[month - 1]}.")
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
