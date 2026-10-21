/**
 * Proyecto **EmpiezaAcaba** — UP02 · Actividad 3.
 *
 * Pide una frase e indica si empieza y acaba con la misma letra, usando startsWith y endsWith.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val sentence = readText("Introduce una frase: ")
    val firstLetter = sentence.first().toString()
    // Se ignoran mayúsculas para que «Ana» empiece y acabe por la misma letra
    val sameLetter = sentence.startsWith(firstLetter, ignoreCase = true) &&
        sentence.endsWith(firstLetter, ignoreCase = true)
    println(
        if (sameLetter) "La frase empieza y acaba con la letra '$firstLetter'."
        else "La frase no empieza y acaba con la misma letra."
    )
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
