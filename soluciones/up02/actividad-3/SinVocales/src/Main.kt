/**
 * Proyecto **SinVocales** — UP02 · Actividad 3.
 *
 * Pide una frase y la muestra sin vocales (mayúsculas, minúsculas y acentuadas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Expresión regular que reconoce cualquier vocal. */
val VOWELS = Regex("[aeiouáéíóúü]", RegexOption.IGNORE_CASE)

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    println(readln().replace(VOWELS, ""))
    // Alternativa sin Regex: sentence.filterNot { it.lowercaseChar() in "aeiouáéíóúü" }
}
