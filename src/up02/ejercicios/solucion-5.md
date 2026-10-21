---
layout: doc
title: "Solución Actividad 5 — Funciones: agenda"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 5 — Funciones: agenda

Soluciones de referencia de la [Actividad 5](./#actividad-5) del boletín. Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.

::: tip-box Descarga
Proyectos de esta actividad: <a :href="withBase('/descargas/up02/soluciones-up02-actividad-5.zip')" download>soluciones-up02-actividad-5.zip</a>. Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre (*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.
:::

::: warning-box Ejecútalas en IntelliJ IDEA
Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con `ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.
:::

Agenda de contactos por consola en Kotlin. Cada contacto guarda **nombre, apellidos, edad, teléfono y correo electrónico**.

## Organización del código

| Archivo | Contenido | Por qué |
|:---|:---|:---|
| `Contact.kt` | `data class Contact` | Agrupa los cinco datos en un solo tipo con nombres y tipos propios (`age: Int`), en lugar de un `Map<String, Any>` que obliga a convertir tipos. El `equals` generado permite detectar contactos duplicados con `contact in agenda`. |
| `Input.kt` | `readText`, `readIntInRange`, `readMatching`, `readContact` y la extensión `String.capitalizeWords()` | Toda la interacción y la validación están en un solo sitio. Las expresiones regulares `PHONE_REGEX` y `EMAIL_REGEX` validan teléfono y correo. Se normalizan los datos (mayúsculas en nombres, correo en minúsculas, sin espacios en el teléfono) para que «ana» y «Ana» no se guarden como dos contactos distintos. |
| `Agenda.kt` | Una función por opción: `showAgenda`, `saveContact`, `searchContact`, `deleteContact`, `clearAgenda`, más `findByName` y `printNumbered` | Cada función tiene una única responsabilidad y recibe la agenda como parámetro (no hay variables globales). Las funciones que solo leen reciben `List<Contact>` y las que modifican, `MutableList<Contact>`. |
| `Main.kt` | `enum class MenuOption` y el bucle principal | El menú se pinta a partir del `enum`, así que texto y `when` no se pueden desincronizar. La propiedad `needsContacts` aplica de forma centralizada la regla «las opciones 3, 4 y 5 no se realizan si la agenda está vacía». El `when` sobre el `enum` es exhaustivo: si se añade una opción y no se trata, no compila. |

### Colección elegida

La agenda es una `MutableList<Contact>`:

- Se necesita **orden** (para mostrar los contactos numerados y elegir cuál borrar).
- Se permiten **varios contactos con el mismo nombre** (opción 4), así que un `Map` con el nombre como clave no serviría.
- No se usa un `Set` porque los duplicados ya se controlan con `in` y el orden de inserción es útil al mostrarlos.

### Null safety y validación

- `MenuOption.fromNumber(readln().trim().toIntOrNull())` devuelve `null` si la opción no existe o no es un número, y el `when` lo trata como «Opción no válida». Nunca se lanza `NumberFormatException`.
- Ninguna entrada puede dejar el programa en un estado inválido: los textos no pueden estar vacíos, la edad debe estar entre 0 y 120 y el teléfono y el correo deben cumplir su expresión regular.

## Pruebas realizadas

| Caso | Entrada | Resultado esperado | ✔ |
|:---|:---|:---|:---:|
| Opción con agenda vacía | `3` | «La agenda está vacía: no se puede buscar contacto.» | ✔ |
| Opción inexistente | `9`, `x` | «Opción no válida.» y vuelve el menú | ✔ |
| Teléfono no válido | `123` | Error y se vuelve a pedir | ✔ |
| Correo no válido | `ana@x` | Error y se vuelve a pedir | ✔ |
| Contacto duplicado (con mayúsculas distintas) | `Ana García … ANA@mail.com` | «Ya existe un contacto con esos datos.» | ✔ |
| Teléfono con prefijo y espacios | `+34 712 345 678` | Se guarda como `+34712345678` | ✔ |
| Buscar con minúsculas | `ana` | Muestra los dos contactos «Ana» | ✔ |
| Borrar con nombre repetido | `ana` → `2` | Lista los dos y elimina el elegido | ✔ |
| Borrar agenda | `5` | Elimina todo; la opción 3 vuelve a estar bloqueada | ✔ |

## Ejecución de ejemplo

> En la entrega del alumnado, cada opción se acompaña de una **captura** de IntelliJ. Aquí se muestra la salida en texto.

```txt
¿Cómo te llamas? Profe
¡Hola, Profe! Esta es tu agenda.

1. Mostrar agenda
2. Guardar contacto
3. Buscar contacto
4. Borrar contacto
5. Borrar agenda
0. Salir
Elige una opción: 3
La agenda está vacía: no se puede buscar contacto.
…
Elige una opción: 2
Nombre: ana
Apellidos: garcía
Edad: 30
Teléfono: 123
Error: teléfono no válido (9 dígitos, p. ej. 612345678).
Teléfono: 612345678
Correo electrónico: ana@x
Error: correo no válido (p. ej. nombre@dominio.com).
Correo electrónico: ana@mail.com
Contacto guardado.
…
Elige una opción: 1
--- Agenda (2 contacto/s) ---
1. Ana García · 30 años · ☎ 612345678 · ✉ ana@mail.com
2. Ana Ruiz · 25 años · ☎ +34712345678 · ✉ ar@mail.es
…
Elige una opción: 4
Nombre del contacto a borrar: ana
Hay 2 contactos con ese nombre:
1. Ana García · 30 años · ☎ 612345678 · ✉ ana@mail.com
2. Ana Ruiz · 25 años · ☎ +34712345678 · ✉ ar@mail.es
¿Cuál quieres eliminar? (1-2): 2
Contacto eliminado: Ana Ruiz · 25 años · ☎ +34712345678 · ✉ ar@mail.es
…
Elige una opción: 5
Se han eliminado todos los contactos.
…
Elige una opción: 0
¡Hasta pronto, Profe!
```

## Posibles ampliaciones

- Persistir la agenda en un fichero (anticipa la persistencia de RA2.f).
- Editar un contacto usando `copy()` de la `data class`.
- Ordenar la agenda por apellidos con `sortedBy { it.surnames }`.

## Código

### `Main.kt`

```kotlin
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
```

### `Contact.kt`

```kotlin
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
```

### `Input.kt`

```kotlin
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
```

### `Agenda.kt`

```kotlin
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
```
