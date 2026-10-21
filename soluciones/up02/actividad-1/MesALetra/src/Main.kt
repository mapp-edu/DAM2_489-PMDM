/**
 * Proyecto **MesALetra** — UP02 · Actividad 1.
 *
 * Pide día, mes y año de la fecha actual y la muestra con el formato «7 de diciembre de 2019»,
 * validando que la fecha exista (incluidos años bisiestos).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra una fecha con el mes en letras. */
fun main() {
    val year = readIntInRange("Introduce el año: ", 1, 9999)
    val month = readIntInRange("Introduce el mes (1-12): ", 1, 12)
    val day = readIntInRange("Introduce el día: ", 1, daysInMonth(month, year))
    println("$day de ${monthName(month)} de $year")
}

/**
 * Devuelve el nombre en castellano del mes indicado.
 *
 * @param month número de mes, entre 1 y 12.
 * @return nombre del mes en minúsculas.
 */
fun monthName(month: Int): String = when (month) {
    1 -> "enero"; 2 -> "febrero"; 3 -> "marzo"; 4 -> "abril"
    5 -> "mayo"; 6 -> "junio"; 7 -> "julio"; 8 -> "agosto"
    9 -> "septiembre"; 10 -> "octubre"; 11 -> "noviembre"; 12 -> "diciembre"
    else -> throw IllegalArgumentException("Mes no válido: $month")
}

/**
 * Calcula cuántos días tiene un mes en un año concreto.
 *
 * @param month número de mes, entre 1 y 12.
 * @param year año, necesario para saber si febrero tiene 28 o 29 días.
 * @return número de días del mes.
 */
fun daysInMonth(month: Int, year: Int): Int = when (month) {
    2 -> if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) 29 else 28
    4, 6, 9, 11 -> 30
    else -> 31
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
