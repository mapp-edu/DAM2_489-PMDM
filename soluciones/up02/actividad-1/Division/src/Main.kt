/**
 * Proyecto **Division** — UP02 · Actividad 1.
 *
 * Pide dos números enteros y muestra la división del mayor entre el menor. Si el menor es cero
 * no se realiza la división.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: divide el mayor de dos números entre el menor. */
fun main() {
    val a = readInt("Introduce el primer número: ")
    val b = readInt("Introduce el segundo número: ")
    val greater = maxOf(a, b)
    val smaller = minOf(a, b)

    if (smaller == 0) {
        println("El menor es cero: no se puede realizar la división.")
    } else {
        // Se convierte a Double para no perder los decimales de la división entera
        println("$greater / $smaller = ${greater.toDouble() / smaller}")
    }
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
