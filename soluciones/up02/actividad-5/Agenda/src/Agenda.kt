/**
 * Operaciones de la agenda — UP02 · Actividad 5.
 *
 * Cada función recibe la lista de contactos y realiza una única acción del menú.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/**
 * Opción 1: muestra todos los contactos o indica que la agenda está vacía.
 *
 * @param agenda lista de contactos.
 */
fun showAgenda(agenda: List<Contact>) {
    if (agenda.isEmpty()) {
        println("La agenda está vacía.")
        return
    }
    println("--- Agenda (${agenda.size} contacto/s) ---")
    printNumbered(agenda)
}

/**
 * Opción 2: pide un contacto y lo guarda si no existe ya otro con los mismos datos.
 *
 * @param agenda lista de contactos, que se modifica.
 */
fun saveContact(agenda: MutableList<Contact>) {
    val contact = readContact()
    // data class: «in» compara por contenido gracias al equals generado
    if (contact in agenda) {
        println("Ya existe un contacto con esos datos. No se ha guardado.")
    } else {
        agenda.add(contact)
        println("Contacto guardado.")
    }
}

/**
 * Opción 3: pide un nombre y muestra todos los contactos con ese nombre.
 *
 * @param agenda lista de contactos (no vacía).
 */
fun searchContact(agenda: List<Contact>) {
    val name = readText("Nombre a buscar: ")
    val found = findByName(agenda, name)
    if (found.isEmpty()) println("No hay ningún contacto llamado \"$name\".")
    else printNumbered(found)
}

/**
 * Opción 4: pide un nombre y borra el contacto. Si hay varios con ese nombre, los
 * muestra numerados y pregunta cuál eliminar.
 *
 * @param agenda lista de contactos (no vacía), que se modifica.
 */
fun deleteContact(agenda: MutableList<Contact>) {
    val name = readText("Nombre del contacto a borrar: ")
    val found = findByName(agenda, name)
    val toDelete: Contact = when (found.size) {
        0 -> {
            println("No hay ningún contacto llamado \"$name\".")
            return
        }
        1 -> found.first()
        else -> {
            println("Hay ${found.size} contactos con ese nombre:")
            printNumbered(found)
            found[readIntInRange("¿Cuál quieres eliminar? (1-${found.size}): ", 1, found.size) - 1]
        }
    }
    agenda.remove(toDelete)
    println("Contacto eliminado: ${toDelete.toDisplayString()}")
}

/**
 * Opción 5: elimina todos los contactos.
 *
 * @param agenda lista de contactos, que se vacía.
 */
fun clearAgenda(agenda: MutableList<Contact>) {
    agenda.clear()
    println("Se han eliminado todos los contactos.")
}

/**
 * Busca los contactos cuyo nombre coincide, sin distinguir mayúsculas.
 *
 * @param agenda lista de contactos.
 * @param name nombre buscado.
 * @return los contactos que coinciden (lista vacía si no hay ninguno).
 */
fun findByName(agenda: List<Contact>, name: String): List<Contact> =
    agenda.filter { it.name.equals(name.trim(), ignoreCase = true) }

/**
 * Muestra una lista de contactos numerada desde 1.
 *
 * @param contacts contactos que se muestran.
 */
fun printNumbered(contacts: List<Contact>) {
    contacts.forEachIndexed { i, contact -> println("${i + 1}. ${contact.toDisplayString()}") }
}
