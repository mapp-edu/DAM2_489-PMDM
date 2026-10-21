/**
 * Proyecto **NumerosRango** — UP02 · Actividad 1.
 *
 * Pide dos números enteros y muestra los números comprendidos entre ambos, incluidos, sea cual
 * sea el orden en que se introduzcan.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra todos los enteros entre dos valores. */
fun main() {
    val a = readInt("Introduce el primer número: ")
    val b = readInt("Introduce el segundo número: ")
    // Se ordenan los extremos para que el rango no quede vacío si a > b
    for (n in minOf(a, b)..maxOf(a, b)) print("$n ")
    println()
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
