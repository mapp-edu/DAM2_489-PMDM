/**
 * Proyecto **ArrayPersonalizado** — UP02 · Actividad 2.
 *
 * Pide un tamaño entre 3 y 8, crea un array de caracteres de ese tamaño, pide las letras para
 * rellenarlo y lo muestra.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada. */
fun main() {
    val size = readIntInRange("Introduce el tamaño del array (3-8): ", 3, 8)
    val letters = CharArray(size) { readLetter("Letra ${it + 1} de $size: ") }
    println("Array: ${letters.joinToString(" ")}")
}

/**
 * Pide una única letra, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return la letra introducida.
 */
fun readLetter(prompt: String): Char {
    while (true) {
        print(prompt)
        val input = readln().trim()
        if (input.length == 1 && input[0].isLetter()) return input[0]
        println("Error: introduce una sola letra.")
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
