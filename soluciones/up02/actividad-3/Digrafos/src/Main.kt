/**
 * Proyecto **Digrafos** — UP02 · Actividad 3.
 *
 * Pide una frase e indica si aparece o no cada uno de los dígrafos ch, rr, ll, gu y qu.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Dígrafos que se buscan. */
val DIGRAPHS = listOf("ch", "rr", "ll", "gu", "qu")

/** Punto de entrada. */
fun main() {
    val sentence = readText("Introduce una frase: ").lowercase()
    for (digraph in DIGRAPHS) {
        println("'$digraph': ${if (digraph in sentence) "aparece" else "no aparece"}")
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
