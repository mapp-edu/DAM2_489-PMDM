# Solucionario UP02 — Fundamentos de Kotlin

Soluciones de referencia del [boletín de actividades de la UP02](../../src/up02/ejercicios/index.md). Están fuera de `src/`, así que VitePress no las publica en la web del curso, pero sí son visibles en el repositorio.

## Estructura

```txt
soluciones/up02/
├── actividad-1/   30 proyectos (variables, condicionales y bucles)
├── actividad-2/   19 proyectos (arrays y matrices)
├── actividad-3/   14 proyectos (cadenas)
├── actividad-4/   Productos (colección de Map)
└── actividad-5/   Agenda (data class, enum, funciones, Regex) + README modelo
```

Cada proyecto contiene solo `src/` con sus `.kt`. Para abrirlo en IntelliJ IDEA: **New Project → Kotlin → Build system: IntelliJ** con el nombre del proyecto y sustituye su `src/` por el de la solución. También se puede compilar desde la terminal:

```bash
kotlinc actividad-5/Agenda/src/*.kt -include-runtime -d agenda.jar && java -jar agenda.jar
```

Los 65 proyectos se han compilado con Kotlin 2.1 sin errores ni avisos y se han probado con entradas válidas y no válidas.

## Criterios comunes a todas las soluciones

| Aspecto | Cómo se resuelve | Criterio |
|:---|:---|:---|
| Normas del curso | Identificadores en inglés, KDoc de archivo con `@author` y `@version`, KDoc en cada función, clase y constante | RA2.i |
| Validación de entradas | `readln().toIntOrNull()` / `toDoubleOrNull()` dentro de funciones `readInt`, `readDouble`, `readIntInRange` y `readText`, que repiten la petición en vez de lanzar excepción. Los decimales aceptan coma o punto | RA2.g |
| Constantes | Tamaños, rangos y valores mágicos en `const val` (si se cambia el tamaño de la matriz de `Matriz5x5`, no hay que tocar nada más) | RA2.a |
| Kotlin idiomático | `if`/`when` como expresión, rangos (`in`, `downTo`, `step`, `until`), plantillas de cadena, `also` para intercambios, `IntArray(n) { … }` para inicializar | RA2.a |

Cada proyecto incluye sus propias funciones de lectura (copiadas) porque los proyectos tienen que ser independientes; en las unidades siguientes se puede proponer agruparlas en un módulo común.

## Notas por actividad

### Actividad 1 — Control de flujo

- `Intercambio` muestra la versión clásica con variable auxiliar y la idiomática `a = b.also { b = a }`.
- `TresAscendente` ordena con tres comparaciones e intercambios (sin colecciones), que es lo que se busca practicar.
- `MesALetra` valida que el día exista en el mes (incluido febrero en bisiestos), igual que `Bisiesto`.
- `Primos` prueba divisores solo hasta √n.
- `Factorial` usa `Long` y limita la entrada a 20 (21! desborda `Long`); se puede comentar `BigInteger` como ampliación.
- `MayorMenorMedia` controla el caso de que el primer número sea 0 (sin división entre cero).
- `NumerosRango` funciona sea cual sea el orden de los dos números.

### Actividad 2 — Colecciones

- Se usan `IntArray`/`DoubleArray`/`CharArray` (lo que piden los enunciados) y `Array<IntArray>` para matrices.
- `ArrayPosicion`: `Random.nextDouble(from, until)` excluye el límite superior; se usa `Math.nextUp(1000.0)` para que el 1000 quede incluido.
- `AleatoriosUnicos` genera candidatos y descarta repetidos; en un comentario se da la alternativa `(1..50).shuffled().take(10)`.
- `OrdenarBurbuja` incluye la optimización de parar cuando una pasada no hace intercambios.
- `ArrayYMatriz` usa `contentEquals` y explica por qué `==` entre arrays compara referencias.
- `Matriz5x5` resuelve todo en `main` (como pide el enunciado) y `Matriz5x5Plus` lo reparte en funciones con una sola responsabilidad. Comparar las dos es un buen ejercicio de clase.

### Actividad 3 — Cadenas

- Las comparaciones ignoran mayúsculas (`equals(…, ignoreCase = true)`, `lowercase()`) y las vocales acentuadas cuentan como vocales.
- `PalabraE` y `Palabras` usan `Regex` (`\p{L}` para letras con tilde, `\s+` para varios espacios).
- `LetraNombre` pide la posición contando desde 1 (como la entiende el usuario) y resta 1 al indexar.
- `DatosPersonales` justifica los tipos: `String` para textos e `Int` para día, mes y año; usa `java.time` para validar la fecha y calcular la edad.
- `ListaNombresPlus` agrupa con `groupBy` para indicar qué nombres se repiten y cuántas veces.

### Actividad 4 — Map

- La colección es `MutableList<Map<String, Any>>`; las claves están en constantes para evitar errores tipográficos.
- El más barato se obtiene con `minByOrNull` y se trata el posible `null` con `?.let … ?: "ninguno"`.
- Al guardar los valores como `Any` hace falta convertir (`as Double`, `as Int`). Es un buen momento para explicar que una `data class` sería más segura, que es justo lo que se hace en la Actividad 5.

### Actividad 5 — Agenda

Ver [`actividad-5/Agenda/README.md`](actividad-5/Agenda/README.md): es el documento modelo que se pide entregar, con la organización del código, la justificación y una ejecución de cada opción.
