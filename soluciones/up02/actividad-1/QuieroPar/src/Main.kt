/**
 * Proyecto **QuieroPar** — UP02 · Actividad 1.
 *
 * Pide números hasta que se introduzca uno par, avisando cada vez que no lo es.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: repite la petición hasta obtener un número par. */
fun main() {
    var number = readInt("Introduce un número par: ")
    while (number % 2 != 0) {
        println("$number no es par.")
        number = readInt("Introduce un número par: ")
    }
    println("¡Bien! $number es par.")
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
