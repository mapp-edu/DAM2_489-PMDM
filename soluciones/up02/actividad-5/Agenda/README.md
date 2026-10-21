# Agenda — UP02 · Actividad 5

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
