/**
 * Proyecto **VocalConsonante** — UP02 · Actividad 1.
 *
 * Pide una letra e indica si es vocal o consonante usando la instrucción when. Tiene en cuenta
 * mayúsculas, vocales acentuadas y la ñ.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: clasifica una letra como vocal o consonante. */
fun main() {
    print("Introduce una letra: ")
    val input = readln().trim()
    if (input.length != 1) {
        println("Error: debes introducir una única letra.")
        return
    }
    val letter = input.lowercase()[0]
    val result = when {
        !letter.isLetter() -> "'$input' no es una letra."
        letter in "aeiouáéíóúü" -> "'$input' es una vocal."
        else -> "'$input' es una consonante."
    }
    println(result)
}
