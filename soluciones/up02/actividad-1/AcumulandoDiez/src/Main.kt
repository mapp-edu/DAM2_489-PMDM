/**
 * Proyecto **AcumulandoDiez** — UP02 · Actividad 1.
 *
 * Pide 10 números y muestra la suma de todos ellos.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Cantidad de números que se piden. */
const val AMOUNT = 10

/** Punto de entrada: acumula la suma de diez números. */
fun main() {
    var sum = 0.0
    for (i in 1..AMOUNT) {
        sum += readDouble("Número $i de $AMOUNT: ")
    }
    println("La suma es $sum")
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
