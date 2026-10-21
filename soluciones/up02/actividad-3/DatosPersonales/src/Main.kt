/**
 * Proyecto **DatosPersonales** — UP02 · Actividad 3.
 *
 * Pide nombre, apellidos, país, día, mes y año de nacimiento y los guarda en variables del
 * tipo adecuado: String para los textos e Int para los datos numéricos de la fecha, validando
 * que la fecha exista.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

import java.time.LocalDate
import java.time.Period

/** Punto de entrada. */
fun main() {
    val name: String = readText("Nombre: ")
    val surnames: String = readText("Apellidos: ")
    val country: String = readText("País de nacimiento: ")
    val currentYear = LocalDate.now().year
    val birthYear: Int = readIntInRange("Año de nacimiento: ", 1900, currentYear)
    val birthMonth: Int = readIntInRange("Mes de nacimiento (1-12): ", 1, 12)
    val maxDay = LocalDate.of(birthYear, birthMonth, 1).lengthOfMonth()
    val birthDay: Int = readIntInRange("Día de nacimiento (1-$maxDay): ", 1, maxDay)

    val birthDate = LocalDate.of(birthYear, birthMonth, birthDay)
    val age = Period.between(birthDate, LocalDate.now()).years

    println(
        """
        |--- Datos personales ---
        |Nombre:     $name $surnames
        |País:       $country
        |Nacimiento: $birthDay/$birthMonth/$birthYear ($age años)
        """.trimMargin()
    )
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
