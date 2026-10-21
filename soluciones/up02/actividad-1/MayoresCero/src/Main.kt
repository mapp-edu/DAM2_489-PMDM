/**
 * Proyecto **MayoresCero** — UP02 · Actividad 1.
 *
 * Pide 10 números y muestra cuántos son mayores que cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: cuenta los positivos entre diez números. */
fun main() {
    var positives = 0
    for (i in 1..10) {
        if (readDouble("Número $i de 10: ") > 0) positives++
    }
    println("Has introducido $positives número(s) mayor(es) que cero.")
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
