/**
 * Proyecto **LetraNombre** — UP02 · Actividad 3.
 *
 * Pide el nombre completo, muestra su longitud, pide una posición (como máximo la longitud) e
 * indica la letra que hay en ella. Las posiciones se cuentan desde 1, como las cuenta el
 * usuario.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val fullName = readText("Introduce tu nombre completo: ")
    println("Tu nombre tiene ${fullName.length} caracteres.")
    val position = readIntInRange("Introduce una posición (1-${fullName.length}): ", 1, fullName.length)
    // El usuario cuenta desde 1; los índices de String empiezan en 0
    println("En la posición $position está el carácter '${fullName[position - 1]}'.")
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

/**
 * Lee un entero comprendido entre [min] y [max] (ambos incluidos), repitiendo la petición
 * mientras la entrada no sea un número o esté fuera del rango.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido, dentro del rango.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        val number = readInt(prompt)
        if (number in min..max) return number
        println("Error: el número debe estar entre $min y $max.")
    }
}
