/**
 * Proyecto **OperacionesMenu** — UP02 · Actividad 1.
 *
 * Pide dos números decimales y muestra un menú (1-sumar, 2-restar, 3-multiplicar, 4-dividir,
 * 0-salir). Realiza la operación elegida evitando dividir entre cero y, al final, se despide.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: pide dos números, una opción del menú y realiza la operación. */
fun main() {
    val a = readDouble("Introduce el primer número: ")
    val b = readDouble("Introduce el segundo número: ")
    showMenu()
    when (readIntInRange("Elige una opción: ", 0, 4)) {
        1 -> println("$a + $b = ${a + b}")
        2 -> println("$a - $b = ${a - b}")
        3 -> println("$a * $b = ${a * b}")
        4 -> if (b == 0.0) println("No se puede dividir entre cero.") else println("$a / $b = ${a / b}")
        0 -> println("No se realiza ninguna operación.")
    }
    println("¡Hasta pronto!")
}

/** Muestra las opciones del menú. */
fun showMenu() {
    println(
        """
        |1 - Sumar
        |2 - Restar
        |3 - Multiplicar
        |4 - Dividir
        |0 - Salir
        """.trimMargin()
    )
}

/**
 * Muestra [prompt] y lee un número decimal, repitiendo la petición hasta que la entrada sea válida.
 * Admite tanto el punto como la coma como separador decimal.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número decimal introducido.
 */
fun readDouble(prompt: String): Double {
    while (true) {
        print(prompt)
        readln().trim().replace(',', '.').toDoubleOrNull()?.let { return it }
        println("Error: debes introducir un número.")
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
