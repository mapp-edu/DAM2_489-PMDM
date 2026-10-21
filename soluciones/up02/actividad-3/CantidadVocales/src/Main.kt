/**
 * Proyecto **CantidadVocales** — UP02 · Actividad 3.
 *
 * Pide una frase y muestra la cantidad de cada vocal y el total de vocales. Las vocales
 * acentuadas se cuentan con su vocal base.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln().lowercase()

    // Mapa ordenado vocal → contador
    val counts = linkedMapOf('a' to 0, 'e' to 0, 'i' to 0, 'o' to 0, 'u' to 0)
    for (char in sentence) {
        val base = normalizeVowel(char)
        if (base != null) counts[base] = counts.getValue(base) + 1
    }

    counts.forEach { (vowel, count) -> println("$vowel: $count") }
    println("Total de vocales: ${counts.values.sum()}")
}

/**
 * Devuelve la vocal base de un carácter (á → a, ü → u…) o null si no es vocal.
 *
 * @param char carácter en minúsculas.
 * @return la vocal sin tilde o null.
 */
fun normalizeVowel(char: Char): Char? = when (char) {
    'a', 'á' -> 'a'
    'e', 'é' -> 'e'
    'i', 'í' -> 'i'
    'o', 'ó' -> 'o'
    'u', 'ú', 'ü' -> 'u'
    else -> null
}
