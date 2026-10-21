/**
 * Proyecto **MayorDeDos** — UP02 · Actividad 1.
 *
 * Pide dos números decimales e indica cuál es el mayor de ellos (o si son iguales).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: compara dos números decimales. */
fun main() {
    val first = readDouble("Introduce el primer número: ")
    val second = readDouble("Introduce el segundo número: ")
    val message = when {
        first > second -> "El mayor es $first"
        second > first -> "El mayor es $second"
        else -> "Los dos números son iguales ($first)"
    }
    println(message)
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
