/**
 * Proyecto **ListaNombres** — UP02 · Actividad 3.
 *
 * Pide 10 nombres y los almacena en un array de cadenas.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Número de nombres. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val names = Array(SIZE) { readText("Nombre ${it + 1} de $SIZE: ") }
    println("Nombres: ${names.joinToString(", ")}")
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
