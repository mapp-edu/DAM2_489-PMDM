/**
 * Modelo de datos de la agenda — UP02 · Actividad 5.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/**
 * Contacto de la agenda. Al ser una `data class`, Kotlin genera `equals`, `hashCode`,
 * `toString` y `copy`, de modo que dos contactos con los mismos datos son iguales y
 * detectar duplicados es tan sencillo como usar `in`.
 *
 * @property name nombre del contacto.
 * @property surnames apellidos del contacto.
 * @property age edad en años.
 * @property phone teléfono (9 dígitos, con prefijo internacional opcional).
 * @property email correo electrónico.
 */
data class Contact(
    val name: String,
    val surnames: String,
    val age: Int,
    val phone: String,
    val email: String,
) {
    /**
     * Devuelve el contacto en formato legible de una línea.
     *
     * @return texto con todos los datos del contacto.
     */
    fun toDisplayString(): String = "$name $surnames · $age años · ☎ $phone · ✉ $email"
}
