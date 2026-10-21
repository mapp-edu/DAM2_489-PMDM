/**
 * Proyecto **CantidadA** — UP02 · Actividad 3.
 *
 * Pide una frase y muestra cuántas letras 'a' contiene (mayúsculas y acentuadas incluidas).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    print("Introduce una frase: ")
    val sentence = readln()
    val count = sentence.count { it.lowercaseChar() in "aá" }
    println("La frase contiene $count 'a'.")
}
