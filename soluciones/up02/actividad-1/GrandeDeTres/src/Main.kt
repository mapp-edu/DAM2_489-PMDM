/**
 * Proyecto **GrandeDeTres** — UP02 · Actividad 1.
 *
 * Pide tres números decimales y muestra cuál es el más grande, usando únicamente
 * condicionales.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra el mayor de tres números. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")
    val c = readDouble("Introduce el tercer número: ")

    // if es una expresión en Kotlin: su resultado se asigna directamente
    val greatest = if (a >= b && a >= c) a else if (b >= c) b else c
    println("El número más grande es $greatest")
    // Alternativa con la librería estándar: maxOf(a, b, c)
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
