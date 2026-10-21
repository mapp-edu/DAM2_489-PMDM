/**
 * Proyecto **TresAscendente** — UP02 · Actividad 1.
 *
 * Pide tres números decimales y los muestra en orden ascendente, ordenándolos con intercambios
 * (sin usar colecciones).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: ordena tres números de menor a mayor. */
fun main() {
    var a = readDouble("Introduce el primer número: ")
    var b = readDouble("Introduce el segundo número: ")
    var c = readDouble("Introduce el tercer número: ")

    // Tres comparaciones bastan para ordenar tres valores
    if (a > b) a = b.also { b = a }
    if (b > c) b = c.also { c = b }
    if (a > b) a = b.also { b = a }

    println("Orden ascendente: $a, $b, $c")
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
    }
}
