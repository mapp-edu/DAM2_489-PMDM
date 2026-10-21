/**
 * Proyecto **Intercambio** — UP02 · Actividad 1.
 *
 * Pide dos números enteros, intercambia el valor de las variables y las muestra. Se muestra la
 * versión clásica con variable auxiliar y la idiomática de Kotlin con also.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: intercambia el valor de dos variables. */
fun main() {
    var a = readInt("Introduce el valor de a: ")
    var b = readInt("Introduce el valor de b: ")
    println("Antes:   a = $a, b = $b")

    // Versión clásica con variable auxiliar
    val aux = a
    a = b
    b = aux
    println("Después: a = $a, b = $b")

    // Versión idiomática en Kotlin (deshace el cambio para comprobarlo):
    // also se ejecuta después de evaluar b, por eso «a = b» recibe el valor antiguo de b
    a = b.also { b = a }
    println("De nuevo con also: a = $a, b = $b")
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
