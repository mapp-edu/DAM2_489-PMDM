/**
 * Proyecto **Votar** — UP02 · Actividad 1.
 *
 * Pide la edad al usuario e indica si puede votar o no (mayoría de edad: 18 años).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Edad mínima para poder votar. */
const val VOTING_AGE = 18

/** Punto de entrada: pide la edad y muestra si la persona puede votar. */
fun main() {
    val age = readIntInRange("Introduce tu edad: ", 0, 130)
    if (age >= VOTING_AGE) println("Puedes votar.")
    else println("Todavía no puedes votar: te faltan ${VOTING_AGE - age} año(s).")
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
