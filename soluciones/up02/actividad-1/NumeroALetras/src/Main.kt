/**
 * Proyecto **NumeroALetras** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 5 y lo muestra en letras; si no es válido, muestra «Número
 * no válido».
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: convierte un número del 1 al 5 a letras. */
fun main() {
    val number = readInt("Introduce un número entre 1 y 5: ")
    val text = when (number) {
        1 -> "uno"
        2 -> "dos"
        3 -> "tres"
        4 -> "cuatro"
        5 -> "cinco"
        else -> "Número no válido"
    }
    println(text)
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
