/**
 * Proyecto **Saludo** — UP02 · Actividad 3.
 *
 * Pide el nombre y los apellidos en variables diferentes y saluda al usuario.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val name = readText("Introduce tu nombre: ")
    val surnames = readText("Introduce tus apellidos: ")
    println("¡Hola, $name $surnames! Bienvenido/a.")
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
