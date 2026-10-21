/**
 * Proyecto **ArrayCifras** — UP02 · Actividad 2.
 *
 * Pide 10 números enteros, los guarda en un array e indica cuántos tienen una cifra, dos
 * cifras o más de dos (se ignora el signo).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { readInt("Número ${it + 1} de $SIZE: ") }
    var oneDigit = 0
    var twoDigits = 0
    var moreDigits = 0
    for (n in numbers) {
        when (digitCount(n)) {
            1 -> oneDigit++
            2 -> twoDigits++
            else -> moreDigits++
        }
    }
    println("De una cifra: $oneDigit")
    println("De dos cifras: $twoDigits")
    println("De más de dos cifras: $moreDigits")
}

/**
 * Cuenta las cifras de un número entero, sin tener en cuenta el signo.
 *
 * @param n número del que se cuentan las cifras.
 * @return número de cifras (el 0 tiene una).
 */
fun digitCount(n: Int): Int = n.toLong().let { if (it < 0) -it else it }.toString().length

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
