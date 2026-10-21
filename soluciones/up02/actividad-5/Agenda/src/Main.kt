/**
 * Proyecto **Agenda** — UP02 · Actividad 5.
 *
 * Agenda de contactos (nombre, apellidos, edad, teléfono y correo) gestionada con un menú.
 * El código se reparte en cuatro archivos:
 * - `Contact.kt`: modelo de datos (`data class`).
 * - `Input.kt`: lectura y validación de entradas (incluidas expresiones regulares).
 * - `Agenda.kt`: una función por cada opción del menú.
 * - `Main.kt`: menú y bucle principal.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/**
 * Opciones del menú. El texto de cada una se usa para pintarlo, así el menú y el `when`
 * no pueden desincronizarse.
 *
 * @property number número que escribe el usuario.
 * @property label texto que se muestra.
 * @property needsContacts true si la opción no se puede usar con la agenda vacía.
 */
enum class MenuOption(val number: Int, val label: String, val needsContacts: Boolean = false) {
    SHOW(1, "Mostrar agenda"),
    SAVE(2, "Guardar contacto"),
    SEARCH(3, "Buscar contacto", needsContacts = true),
    DELETE(4, "Borrar contacto", needsContacts = true),
    CLEAR(5, "Borrar agenda", needsContacts = true),
    EXIT(0, "Salir");

    companion object {
        /**
         * Busca la opción que corresponde a un número.
         *
         * @param number número introducido por el usuario.
         * @return la opción, o null si no existe.
         */
        fun fromNumber(number: Int?): MenuOption? = entries.firstOrNull { it.number == number }
    }
}

/** Punto de entrada: saluda al usuario y repite el menú hasta que elige salir. */
fun main() {
    val userName = readText("¿Cómo te llamas? ")
    println("¡Hola, $userName! Esta es tu agenda.")

    val agenda = mutableListOf<Contact>()

    while (true) {
        showMenu()
        print("Elige una opción: ")
        val option = MenuOption.fromNumber(readln().trim().toIntOrNull())

        when {
            option == null -> println("Opción no válida.")
            option.needsContacts && agenda.isEmpty() ->
                println("La agenda está vacía: no se puede ${option.label.lowercase()}.")
            else -> when (option) {
                MenuOption.SHOW -> showAgenda(agenda)
                MenuOption.SAVE -> saveContact(agenda)
                MenuOption.SEARCH -> searchContact(agenda)
                MenuOption.DELETE -> deleteContact(agenda)
                MenuOption.CLEAR -> clearAgenda(agenda)
                MenuOption.EXIT -> {
                    println("¡Hasta pronto, $userName!")
                    return
                }
            }
        }
    }
}

/** Muestra el menú a partir de las entradas de [MenuOption]. */
fun showMenu() {
    println()
    MenuOption.entries.forEach { println("${it.number}. ${it.label}") }
}
