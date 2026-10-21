/**
 * Proyecto **TablaMultiplicar** — UP02 · Actividad 1.
 *
 * Pide un número entero entre 1 y 10 y muestra su tabla de multiplicar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra la tabla de multiplicar de un número. */
fun main() {
    val number = readIntInRange("Introduce un número entre 1 y 10: ", 1, 10)
    println("Tabla del $number")
    for (i in 1..10) {
        println("$number x $i = ${number * i}")
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
