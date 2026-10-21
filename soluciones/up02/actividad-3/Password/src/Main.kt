/**
 * Proyecto **Password** — UP02 · Actividad 3.
 *
 * Pide el nombre de usuario y la contraseña un máximo de tres veces. Si coincide con "2D4m" da
 * la bienvenida; tras tres fallos pide contactar con el administrador.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Contraseña correcta. */
const val PASSWORD = "2D4m"

/** Número máximo de intentos. */
const val MAX_ATTEMPTS = 3

/** Punto de entrada. */
fun main() {
    val user = readText("Usuario: ")
    for (attempt in 1..MAX_ATTEMPTS) {
        print("Contraseña (intento $attempt de $MAX_ATTEMPTS): ")
        if (readln() == PASSWORD) {
            println("¡Bienvenido/a, $user!")
            return
        }
        println("Contraseña incorrecta.")
    }
    println("Contacte con el administrador para reiniciar su contraseña")
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
