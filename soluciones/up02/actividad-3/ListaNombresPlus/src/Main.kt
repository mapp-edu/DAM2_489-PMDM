/**
 * Proyecto **ListaNombresPlus** — UP02 · Actividad 3.
 *
 * Como ListaNombres, pero comprueba si algún nombre se repite en el array (sin distinguir
 * mayúsculas).
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

    // Se agrupan por nombre en minúsculas y se quedan los grupos con más de un elemento
    val repeated = names.groupBy { it.lowercase() }.filterValues { it.size > 1 }
    if (repeated.isEmpty()) {
        println("No se repite ningún nombre.")
    } else {
        repeated.forEach { (_, group) -> println("\"${group.first()}\" se repite ${group.size} veces.") }
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
