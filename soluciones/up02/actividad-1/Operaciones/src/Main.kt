/**
 * Proyecto **Operaciones** — UP02 · Actividad 1.
 *
 * Pide dos números decimales y calcula su suma, resta, producto y división, evitando dividir
 * entre cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra las cuatro operaciones básicas. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")

    println("$a + $b = ${a + b}")
    println("$a - $b = ${a - b}")
    println("$a * $b = ${a * b}")
    if (b != 0.0) println("$a / $b = ${a / b}")
    else println("No se puede dividir entre cero.")
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
