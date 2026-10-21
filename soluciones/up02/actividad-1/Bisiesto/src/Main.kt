/**
 * Proyecto **Bisiesto** — UP02 · Actividad 1.
 *
 * Pide un año e indica si es bisiesto: divisible entre 4 y no entre 100, o divisible entre
 * 400.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: indica si un año es bisiesto. */
fun main() {
    val year = readIntInRange("Introduce un año: ", 1, 9999)
    println(if (isLeapYear(year)) "$year es bisiesto." else "$year no es bisiesto.")
}

/**
 * Indica si un año es bisiesto según el calendario gregoriano.
 *
 * @param year año que se comprueba.
 * @return true si el año es bisiesto.
 */
fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0

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
