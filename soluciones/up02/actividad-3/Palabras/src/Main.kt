/**
 * Proyecto **Palabras** — UP02 · Actividad 3.
 *
 * Pide una frase e indica cuántas palabras tiene; separa por cualquier secuencia de espacios
 * para no contar palabras vacías.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln().trim()
    // \s+ agrupa varios espacios o tabuladores seguidos como un único separador
    val words = if (sentence.isEmpty()) 0 else sentence.split(Regex("\\s+")).size
    println("La frase tiene $words palabra(s).")
}
