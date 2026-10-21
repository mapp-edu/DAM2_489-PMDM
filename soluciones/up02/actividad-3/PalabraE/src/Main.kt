/**
 * Proyecto **PalabraE** — UP02 · Actividad 3.
 *
 * Pide una palabra que empiece por E y tenga al menos 5 letras; termina cuando la palabra es
 * válida.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Expresión regular: empieza por e/E y tiene al menos 5 letras en total (sin espacios). */
val VALID_WORD = Regex("^[eEéÉ]\\p{L}{4,}$")

/** Punto de entrada. */
fun main() {
    while (true) {
        print("Introduce una palabra que empiece por E (mínimo 5 letras): ")
        val word = readln().trim()
        when {
            !word.startsWith("e", ignoreCase = true) -> println("No empieza por E.")
            word.length < 5 -> println("Tiene menos de 5 letras.")
            !VALID_WORD.matches(word) -> println("Debe ser una sola palabra formada por letras.")
            else -> {
                println("¡Correcto! \"$word\" es válida.")
                return
            }
        }
    }
}
