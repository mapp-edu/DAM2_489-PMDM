/**
 * Proyecto **DosNombres** — UP02 · Actividad 3.
 *
 * Pide dos nombres e indica si son iguales; si son diferentes, muestra la longitud de cada
 * uno. La comparación ignora mayúsculas y minúsculas.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val first = readText("Primer nombre: ")
    val second = readText("Segundo nombre: ")
    if (first.equals(second, ignoreCase = true)) {
        println("Los nombres son iguales.")
    } else {
        println("Los nombres son diferentes.")
        println("\"$first\" tiene ${first.length} caracteres.")
        println("\"$second\" tiene ${second.length} caracteres.")
    }
}

/**
 * Muestra [prompt] y lee una cadena no vacía (sin espacios al principio ni al final).
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido.
 */
fun readText(prompt: String): String {
    while (true) {
        print(prompt)
        val text = readln().trim()
        if (text.isNotEmpty()) return text
        println("Error: no puedes dejarlo en blanco.")
    }
}
