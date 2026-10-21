/**
 * Lectura y validación de datos por teclado — UP02 · Actividad 5.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Teléfono español: 9 dígitos que empiezan por 6, 7, 8 o 9, con prefijo +34 opcional. */
val PHONE_REGEX = Regex("""^(\+34)?[6789]\d{8}$""")

/** Correo electrónico: usuario@dominio.extensión (validación sencilla, no exhaustiva). */
val EMAIL_REGEX = Regex("""^[\w.+-]+@[\w-]+(\.[\w-]+)*\.[a-zA-Z]{2,}$""")

/**
 * Muestra [prompt] y lee una cadena no vacía.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el texto introducido, sin espacios al principio ni al final.
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
 * Lee un entero entre [min] y [max] (incluidos), repitiendo mientras no sea válido.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param min valor mínimo admitido.
 * @param max valor máximo admitido.
 * @return el número introducido.
 */
fun readIntInRange(prompt: String, min: Int, max: Int): Int {
    while (true) {
        print(prompt)
        val number = readln().trim().toIntOrNull()
        if (number != null && number in min..max) return number
        println("Error: introduce un número entre $min y $max.")
    }
}

/**
 * Lee un texto que debe cumplir una expresión regular.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @param regex expresión regular que debe cumplir el texto completo.
 * @param errorMessage mensaje que se muestra si no la cumple.
 * @return el texto validado.
 */
fun readMatching(prompt: String, regex: Regex, errorMessage: String): String {
    while (true) {
        val text = readText(prompt).replace(" ", "")
        if (regex.matches(text)) return text
        println("Error: $errorMessage")
    }
}

/**
 * Pide al usuario los datos de un contacto, validando edad, teléfono y correo.
 *
 * @return el contacto creado con los datos introducidos.
 */
fun readContact(): Contact = Contact(
    name = readText("Nombre: ").capitalizeWords(),
    surnames = readText("Apellidos: ").capitalizeWords(),
    age = readIntInRange("Edad: ", 0, 120),
    phone = readMatching("Teléfono: ", PHONE_REGEX, "teléfono no válido (9 dígitos, p. ej. 612345678)."),
    email = readMatching("Correo electrónico: ", EMAIL_REGEX, "correo no válido (p. ej. nombre@dominio.com).")
        .lowercase(),
)

/**
 * Función de extensión que pone en mayúscula la primera letra de cada palabra, para que
 * «ana», «Ana» y «ANA» se guarden igual y no se cuelen duplicados por mayúsculas.
 *
 * @return la cadena con cada palabra capitalizada.
 */
fun String.capitalizeWords(): String =
    trim().split(Regex("\\s+")).joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { it.uppercase() }
    }
