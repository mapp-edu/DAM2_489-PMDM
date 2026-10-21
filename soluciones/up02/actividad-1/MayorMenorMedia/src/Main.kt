/**
 * Proyecto **MayorMenorMedia** — UP02 · Actividad 1.
 *
 * Pide números hasta que se introduce el cero y muestra el mayor, el menor y la media de los
 * introducidos (el cero no cuenta).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: calcula mayor, menor y media de una serie terminada en 0. */
fun main() {
    var count = 0
    var sum = 0.0
    var max = Double.NEGATIVE_INFINITY
    var min = Double.POSITIVE_INFINITY

    var number = readDouble("Introduce un número (0 para terminar): ")
    while (number != 0.0) {
        count++
        sum += number
        if (number > max) max = number
        if (number < min) min = number
        number = readDouble("Introduce un número (0 para terminar): ")
    }

    if (count == 0) {
        println("No se ha introducido ningún número.")
    } else {
        println("Mayor: $max")
        println("Menor: $min")
        println("Media: ${"%.2f".format(sum / count)}")
    }
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
