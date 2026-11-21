---
layout: doc
title: "Simulacro de examen · 1.ª evaluación · Solución"
description: "Solución del simulacro de la 1.ª evaluación de PMDM, con retroalimentación y rúbrica"
sidebar: true
outline: [2, 3]
aside: true
---

# Simulacro de examen · 1.ª evaluación · Solución

::: tip-box Cómo usar esta solución
Haz antes el [simulacro](./simulacro) en las mismas condiciones que el examen y corrígelo después con esta página. Cada pregunta de la parte A incluye por qué cada opción es correcta o incorrecta, y la parte B, una solución posible con la rúbrica de corrección.
:::

## Ponderación por resultado de aprendizaje

| RA · CE | Descripción | Parte A (preguntas) | Parte B (apartados) | Puntos |
|---|---|---|---|---|
| RA1 · a | Limitaciones de la ejecución en dispositivos móviles | 1 | — | 0,1 |
| RA1 · b | Tecnologías de desarrollo para dispositivos móviles | 6 | — | 0,6 |
| RA1 · d | Configuraciones que clasifican los dispositivos | 2 | — | 0,2 |
| RA1 · e | Perfiles que relacionan dispositivo y aplicación | 1 | B3 | 1,6 |
| RA2 · a | Estructura de clases necesaria para la aplicación | 19 | B1 | 3,4 |
| RA2 · b | Clases que modelan ventanas, controles y eventos | 1 | B2 | 2,1 |
| RA2 · g | Pruebas de interacción usuario-aplicación en emuladores | — | B4 | 1 |
| RA2 · i | Documentación de los procesos de desarrollo | — | B5 | 1 |
| | | | **Total** | **10** |

Los apartados prácticos también evidencian criterios secundarios: B2 → RA1 g (modificar una app existente); B3 → RA2 b; B4 → RA1 h; B5 → RA1 g.

## Parte A · Plantilla de respuestas

| Nº | Pregunta | Bloque | RA · CE | Respuesta |
|---|---|---|---|---|
| 1 | Aplicación nativa | Tipos de aplicaciones | RA1 · b | **d** |
| 2 | Desventaja de las multiplataforma | Tipos de aplicaciones | RA1 · b | **c** |
| 3 | Flutter | Tipos de aplicaciones | RA1 · b | **a** |
| 4 | Publicación costosa | Tipos de aplicaciones | RA1 · b | **d** |
| 5 | Capas de la arquitectura | Arquitectura: de Dalvik a ART | RA1 · b | **c** |
| 6 | Bytecode DEX | Arquitectura: de Dalvik a ART | RA1 · b | **d** |
| 7 | De Dalvik a ART | Arquitectura: de Dalvik a ART | RA1 · a | **a** |
| 8 | Concepto de fragmentación | Fragmentación | RA1 · d | **b** |
| 9 | Factores de la fragmentación | Fragmentación | RA1 · d | **c** |
| 10 | Distribución acumulativa | Fragmentación | RA1 · e | **a** |
| 11 | Tipos nulables | Null safety | RA2 · a | **c** |
| 12 | Operador Elvis | Null safety | RA2 · a | **a** |
| 13 | Operador !! | Null safety | RA2 · a | **b** |
| 14 | Smart cast | Null safety | RA2 · a | **c** |
| 15 | lateinit | Null safety | RA2 · a | **a** |
| 16 | toIntOrNull | Null safety | RA2 · a | **c** |
| 17 | Qué devuelve apply | Funciones de alcance | RA2 · a | **d** |
| 18 | let | Funciones de alcance | RA2 · a | **a** |
| 19 | also | Funciones de alcance | RA2 · a | **d** |
| 20 | with | Funciones de alcance | RA2 · a | **d** |
| 21 | Configurar un objeto | Funciones de alcance | RA2 · a | **b** |
| 22 | ?.let con null | Funciones de alcance | RA2 · a | **c** |
| 23 | Tipo de una lambda | Lambdas | RA2 · a | **c** |
| 24 | Lambda final | Lambdas | RA2 · b | **a** |
| 25 | Closures | Lambdas | RA2 · a | **b** |
| 26 | return@forEach | Lambdas | RA2 · a | **b** |
| 27 | Lambda con receptor | Lambdas | RA2 · a | **a** |
| 28 | Referencias a funciones | Lambdas | RA2 · a | **b** |
| 29 | val y colecciones | Particularidades de Kotlin | RA2 · a | **d** |
| 30 | copy() en data class | Particularidades de Kotlin | RA2 · a | **c** |

**Cálculo:** nota A = aciertos × 0,1 − errores × 0,033 (mínimo 0).

## Parte A · Respuestas con retroalimentación

### UP01 · Tipos de aplicaciones

#### 1. Aplicación nativa <Badge type="tip" text="RA1 · b" />

¿Qué característica define a una aplicación **nativa** de Android?

**Respuesta correcta: d)** Se desarrolla con el SDK y el lenguaje de la plataforma (Kotlin o Java) y accede directamente a todas las APIs del sistema.

::: tip Retroalimentación
Nativa = SDK oficial + lenguaje de la plataforma + acceso completo a las APIs del sistema.
:::

- ❌ **a)** Eso describe una aplicación web progresiva (PWA).
- ❌ **b)** Eso describe un framework multiplataforma como Flutter.
- ❌ **c)** Eso es una aplicación híbrida (Cordova, Ionic + Capacitor).
- ✅ **d)** Correcto. Al usar el SDK oficial, tiene acceso completo al hardware y a las APIs, y el mejor rendimiento.

#### 2. Desventaja de las multiplataforma <Badge type="tip" text="RA1 · b" />

¿Cuál de estas es una **desventaja** de las aplicaciones **multiplataforma** frente a las nativas?

**Respuesta correcta: c)** Peor rendimiento, porque las herramientas añaden capas extra para traducir las funcionalidades a cada plataforma.

::: tip Retroalimentación
Multiplataforma: un código para varias plataformas, a cambio de capas extra y peor rendimiento.
:::

- ❌ **a)** Esa es una desventaja de las nativas (incompatibilidad entre plataformas).
- ❌ **b)** Al contrario: la reutilización de código es su principal ventaja.
- ✅ **c)** Correcto. Es la primera desventaja del cuadro resumen de los apuntes.
- ❌ **d)** Al contrario: tienen una curva de aprendizaje corta.

#### 3. Flutter <Badge type="tip" text="RA1 · b" />

Según la tabla de tecnologías de desarrollo, ¿qué herramienta multiplataforma de **Google** se programa en **Dart**?

**Respuesta correcta: a)** Flutter.

::: tip Retroalimentación
Flutter → Dart · Kotlin Multiplatform → Kotlin · React Native e Ionic → JavaScript/TypeScript · .NET MAUI y Unity → C#.
:::

- ✅ **a)** Correcto. Google · Android, iOS, escritorio y web · Dart.
- ❌ **b)** Es de JetBrains y se programa en Kotlin.
- ❌ **c)** Es de Meta y se programa en JavaScript o TypeScript.
- ❌ **d)** Es de Microsoft y se programa en C#.

#### 4. Publicación costosa <Badge type="tip" text="RA1 · b" />

Una de las desventajas de las aplicaciones multiplataforma es que su **publicación puede ser costosa**. ¿Por qué?

**Respuesta correcta: d)** Porque publicar en los canales oficiales puede exigir requisitos adicionales, como un ordenador con macOS y Xcode para la App Store.

::: tip Retroalimentación
Para publicar en la App Store se necesita un Mac con Xcode; en Google Play no hace falta hardware ni software específico.
:::

- ❌ **a)** Los apuntes no hablan de cuotas por plataforma.
- ❌ **b)** El código se escribe una vez; el coste está en los requisitos de publicación.
- ❌ **c)** Falso: Android instala cualquier .apk, se haya hecho con la herramienta que sea.
- ✅ **d)** Correcto. Es el ejemplo que dan los apuntes y la comparativa entre iOS y Android.

### UP01 · Arquitectura: de Dalvik a ART

#### 5. Capas de la arquitectura <Badge type="tip" text="RA1 · b" />

Ordena **de abajo arriba** las capas principales de la arquitectura de Android.

**Respuesta correcta: c)** Núcleo Linux → HAL → ART y bibliotecas nativas → Framework de APIs Java → Aplicaciones

::: tip Retroalimentación
De abajo arriba: núcleo Linux, HAL, ART + bibliotecas nativas (C/C++), framework de APIs Java y aplicaciones.
:::

- ❌ **a)** La base es el núcleo Linux; la HAL está por encima de él.
- ❌ **b)** El framework está por encima de ART y de la HAL, no por debajo.
- ✅ **c)** Correcto. Cada capa se apoya en la inferior: el núcleo gestiona el hardware y el framework ofrece las APIs a las apps.
- ❌ **d)** ART no es la base del sistema, y las aplicaciones siempre están en la capa superior.

#### 6. Bytecode DEX <Badge type="tip" text="RA1 · b" />

¿Qué formato de código ejecutan las máquinas virtuales **Dalvik y ART**?

**Respuesta correcta: d)** Bytecode DEX (Dalvik Executable), generado a partir de las clases compiladas de Kotlin o Java.

::: tip Retroalimentación
Kotlin/Java → .class → .dex (dentro del APK) → ejecutado por ART.
:::

- ❌ **a)** Los .class se convierten a DEX al compilar; Android no ejecuta .class directamente.
- ❌ **b)** El código fuente se compila antes; el dispositivo no recibe código fuente.
- ❌ **c)** Las .so son bibliotecas nativas (NDK); el código Kotlin/Java se ejecuta como DEX.
- ✅ **d)** Correcto. Al compilar, las clases se transforman en archivos .dex, que son los que ejecuta el runtime.

#### 7. De Dalvik a ART <Badge type="tip" text="RA1 · a" />

¿Cuál fue el cambio principal al sustituir **Dalvik por ART**?

**Respuesta correcta: a)** ART añadió la compilación anticipada (AOT) a código máquina, lo que acelera el arranque y reduce el consumo de batería frente al JIT de Dalvik durante la ejecución.

::: tip Retroalimentación
Dalvik: JIT en tiempo de ejecución. ART: compilación AOT (hoy combinada con JIT y perfiles). Mejora el rendimiento y la batería, dos limitaciones clave de los móviles.
:::

- ✅ **a)** Correcto. Compilar antes de ejecutar evita trabajo repetido en cada uso: mejor rendimiento y menos batería, a cambio de más espacio.
- ❌ **b)** ART sigue partiendo del bytecode DEX.
- ❌ **c)** Al contrario: ART compila a código máquina para ser más rápido.
- ❌ **d)** ART solo ejecuta aplicaciones Android (DEX).

### UP01 · Fragmentación

#### 8. Concepto de fragmentación <Badge type="tip" text="RA1 · d" />

Se llama **fragmentación** al hecho de que…

**Respuesta correcta: b)** En un momento dado existan diferentes versiones del sistema instaladas en los dispositivos en uso.

::: tip Retroalimentación
Fragmentación = muchas versiones de Android conviviendo a la vez en los dispositivos.
:::

- ❌ **a)** Eso no es la fragmentación del sistema operativo.
- ✅ **b)** Correcto. Es la definición de los apuntes.
- ❌ **c)** Eso no tiene relación con las versiones del sistema.
- ❌ **d)** Eso es un problema de memoria, no de fragmentación.

#### 9. Factores de la fragmentación <Badge type="tip" text="RA1 · d" />

Según los apuntes, ¿de qué **dos factores** depende principalmente la fragmentación en Android?

**Respuesta correcta: c)** De la predisposición del fabricante a actualizar sus dispositivos y del hardware del dispositivo.

::: tip Retroalimentación
Fabricante (¿actualiza o no?) + hardware (¿puede con la nueva versión?).
:::

- ❌ **a)** No influyen en qué versión de Android tiene cada dispositivo.
- ❌ **b)** No influyen en la versión del sistema.
- ✅ **c)** Correcto. Los dos factores que señalan los apuntes.
- ❌ **d)** La App Store no tiene relación con Android.

#### 10. Distribución acumulativa <Badge type="tip" text="RA1 · e" />

Al crear un proyecto, Android Studio indica que con un **SDK mínimo API 28** la app funcionará en aproximadamente el **97 %** de los dispositivos. ¿Qué significa?

**Respuesta correcta: a)** Que alrededor del 97 % de los dispositivos tiene instalada una versión con nivel de API 28 o superior.

::: tip Retroalimentación
Distribución acumulativa: probabilidad de que un dispositivo tenga una versión igual o superior a la elegida.
:::

- ✅ **a)** Correcto. La distribución es acumulativa: versión igual o superior.
- ❌ **b)** La distribución es acumulativa: incluye todas las versiones superiores.
- ❌ **c)** Es justo al revés.
- ❌ **d)** El porcentaje habla de dispositivos, no de funcionalidades.

### UP02 · Null safety

#### 11. Tipos nulables <Badge type="tip" text="RA2 · a" />

Dadas estas declaraciones, ¿qué línea **compila**?

```kotlin
var a: String = "Hola"
val b: String? = "Hola"
```

**Respuesta correcta: c)** `val n = b?.length`

::: tip Retroalimentación
Un tipo `T` no admite null; `T?` sí, y el compilador obliga a tratar el caso null.
:::

- ❌ **a)** `String` no admite null; haría falta `String?`.
- ❌ **b)** Sobre un tipo nulable solo se permiten `?.` o `!!.`.
- ✅ **c)** Correcto. La llamada segura devuelve un `Int?`: la longitud, o `null` si `b` fuera null.
- ❌ **d)** `b?.length` es de tipo `Int?`, no `Int`.

#### 12. Operador Elvis <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val nombre: String? = null
println(nombre?.length ?: -1)
```

**Respuesta correcta: a)** `-1`

::: tip Retroalimentación
`a ?: b` → si `a` no es null, `a`; si lo es, `b`.
:::

- ✅ **a)** Correcto. `nombre?.length` vale null, y el operador Elvis devuelve el valor de la derecha.
- ❌ **b)** Sin `?:` imprimiría null, pero Elvis lo sustituye por -1.
- ❌ **c)** El valor por defecto es el que escribimos a la derecha de `?:`, no 0.
- ❌ **d)** La llamada segura evita la excepción.

#### 13. Operador !! <Badge type="tip" text="RA2 · a" />

¿Qué hace el operador `!!` en `val n = texto!!.length`?

**Respuesta correcta: b)** Afirma que `texto` no es null; si lo es, lanza `NullPointerException` en tiempo de ejecución.

::: tip Retroalimentación
`!!` = «confía en mí, compilador». Si te equivocas, vuelve el NullPointerException.
:::

- ❌ **a)** Eso sería `texto?.length ?: 0`.
- ✅ **b)** Correcto. Por eso hay que evitarlo salvo cuando sea imposible que el valor sea null.
- ❌ **c)** `!!` no convierte nada: lanza una excepción.
- ❌ **d)** Eso se consigue con `texto?.let { … }`.

#### 14. Smart cast <Badge type="tip" text="RA2 · a" />

¿Por qué compila `t.length` en esta función, si `t` es de tipo `String?`?

```kotlin
fun longitud(t: String?): Int {
    if (t != null) return t.length
    return 0
}
```

**Respuesta correcta: c)** Por el smart cast: tras comprobar `t != null`, el compilador trata `t` como `String` en esa rama.

::: tip Retroalimentación
Smart cast: tras `!= null` (o `is Tipo`), el compilador convierte el tipo automáticamente.
:::

- ❌ **a)** Sobre un `String?` sin comprobar, `t.length` no compila.
- ❌ **b)** No se añade ningún `!!`: el tipo se deduce de la comprobación.
- ✅ **c)** Correcto. El compilador sigue la comprobación y convierte el tipo automáticamente.
- ❌ **d)** Sí compila, gracias al smart cast.

#### 15. lateinit <Badge type="tip" text="RA2 · a" />

Sobre `lateinit var binding: ActivityMainBinding`, ¿qué afirmación es **correcta**?

**Respuesta correcta: a)** Declara una propiedad no nula que se inicializa más tarde; si se usa antes de asignarla, lanza `UninitializedPropertyAccessException`.

::: tip Retroalimentación
`lateinit var` evita declarar como nulable algo que se crea más tarde (por ejemplo, en `onCreate`).
:::

- ✅ **a)** Correcto. Es la promesa de inicializarla antes de usarla, por ejemplo en `onCreate`.
- ❌ **b)** `lateinit` solo se permite con `var` y con tipos que no sean primitivos.
- ❌ **c)** La propiedad sigue siendo no nula: por eso no hace falta `?.` al usarla.
- ❌ **d)** Lanza una excepción, no devuelve null.

#### 16. toIntOrNull <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `edad`?

```kotlin
val edad = "veinte".toIntOrNull() ?: 18
```

**Respuesta correcta: c)** `18`

::: tip Retroalimentación
Para leer lo que escribe el usuario: `toIntOrNull()` + `?:`.
:::

- ❌ **a)** Lo sería sin el operador Elvis.
- ❌ **b)** Eso ocurriría con `toInt()`, no con `toIntOrNull()`.
- ✅ **c)** Correcto. `toIntOrNull` devuelve null si el texto no es un número, y Elvis aplica el valor por defecto.
- ❌ **d)** Kotlin no interpreta números escritos con letras.

### UP02 · Funciones de alcance

#### 17. Qué devuelve apply <Badge type="tip" text="RA2 · a" />

¿Qué devuelve `apply` y cómo se llama el objeto dentro de su bloque?

**Respuesta correcta: d)** Devuelve el propio objeto; dentro, el objeto es `this`.

::: tip Retroalimentación
`let`/`also` → it · `run`/`with`/`apply` → this · `apply`/`also` devuelven el objeto.
:::

- ❌ **a)** Eso es `let`.
- ❌ **b)** Eso es `also`.
- ❌ **c)** Eso es `run` o `with`.
- ✅ **d)** Correcto. Por eso se usa para configurar un objeto recién creado.

#### 18. let <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `r`?

```kotlin
val r = "Kotlin".let { it.length * 2 }
```

**Respuesta correcta: a)** `12`

::: tip Retroalimentación
`let` recibe el objeto como `it` y devuelve la última expresión del bloque.
:::

- ✅ **a)** Correcto. `let` devuelve el resultado de la lambda: 6 × 2.
- ❌ **b)** `let` no devuelve el objeto, sino el resultado de la lambda.
- ❌ **c)** Falta multiplicar por 2.
- ❌ **d)** La última expresión de la lambda es `it.length * 2`, que es un Int.

#### 19. also <Badge type="tip" text="RA2 · a" />

¿Qué contiene `lista`?

```kotlin
val lista = mutableListOf(3, 1, 2).also { it.sort() }
```

**Respuesta correcta: d)** `[1, 2, 3]`

::: tip Retroalimentación
`also` devuelve el objeto original y recibe el objeto como `it`.
:::

- ❌ **a)** `sort()` ordena la misma lista, y `also` la devuelve ya ordenada.
- ❌ **b)** `also` devuelve el objeto, no el resultado de la lambda.
- ❌ **c)** No se suma nada.
- ✅ **d)** Correcto. `also` devuelve el propio objeto, que `sort()` ha ordenado.

#### 20. with <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `t`?

```kotlin
val t = with(StringBuilder()) {
    append("A")
    append("B")
    toString()
}
```

**Respuesta correcta: d)** `"AB"`

::: tip Retroalimentación
`with(objeto) { … }`: el objeto es `this` y devuelve la última expresión.
:::

- ❌ **a)** Las dos llamadas a `append` actúan sobre el mismo StringBuilder.
- ❌ **b)** Se añaden ambas letras.
- ❌ **c)** `with` devuelve el resultado de la lambda.
- ✅ **d)** Correcto. Dentro de `with`, el objeto es `this`, y el resultado es la última expresión: `toString()`.

#### 21. Configurar un objeto <Badge type="tip" text="RA2 · a" />

Quieres **crear un objeto, dar valor a varias de sus propiedades y guardar el objeto configurado** en una variable, en una sola expresión. ¿Qué función de alcance es la más adecuada?

**Respuesta correcta: b)** `apply`

::: tip Retroalimentación
Configurar → `apply` · Acción extra → `also` · Valor nulable → `?.let` · Calcular → `run`/`with`.
:::

- ❌ **a)** `let` devuelve el resultado de la lambda, no el objeto.
- ✅ **b)** Correcto. Recibe el objeto como `this` y lo devuelve: `Perfil().apply { alias = "ana" }`.
- ❌ **c)** `run` devuelve el resultado de la lambda.
- ❌ **d)** `takeIf` sirve para quedarse con el objeto solo si cumple una condición.

#### 22. ?.let con null <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val texto: String? = null
texto?.let { println("Hola $it") }
```

**Respuesta correcta: c)** Nada.

::: tip Retroalimentación
`valor?.let { … }` es la forma idiomática de «haz esto solo si no es null».
:::

- ❌ **a)** El bloque no llega a ejecutarse.
- ❌ **b)** El bloque no se ejecuta.
- ✅ **c)** Correcto. Con la llamada segura, el bloque de `let` solo se ejecuta si `texto` no es null.
- ❌ **d)** `?.` evita la excepción.

### UP02 · Lambdas

#### 23. Tipo de una lambda <Badge type="tip" text="RA2 · a" />

¿Cuál es el tipo de esta lambda?

```kotlin
val sumar = { a: Int, b: Int -> a + b }
```

**Respuesta correcta: c)** `(Int, Int) -> Int`

::: tip Retroalimentación
Tipo función: `(tipos de entrada) -> tipo de salida`.
:::

- ❌ **a)** `sumar` guarda una función, no un número.
- ❌ **b)** Ese sería el tipo de una función que devuelve otra función.
- ✅ **c)** Correcto. Entre paréntesis, los tipos de los parámetros; tras la flecha, el tipo de retorno.
- ❌ **d)** La lambda recibe dos parámetros.

#### 24. Lambda final <Badge type="tip" text="RA2 · b" />

Dada `fun operar(a: Int, b: Int, op: (Int, Int) -> Int) = op(a, b)`, ¿qué ocurre con esta llamada?

```kotlin
val r = operar(2, 3) { x, y -> x * y }
```

**Respuesta correcta: a)** Compila y `r` vale 6: como el último parámetro es una función, la lambda puede ir fuera de los paréntesis.

::: tip Retroalimentación
Lambda final: si el último parámetro es una función, la lambda va fuera de los paréntesis.
:::

- ✅ **a)** Correcto. Es la sintaxis de lambda final, la misma que usa Compose para el contenido de un `Button`.
- ❌ **b)** Kotlin permite sacar la lambda fuera si es el último argumento.
- ❌ **c)** La operación que se pasa es la multiplicación.
- ❌ **d)** La lambda declara dos parámetros, x e y.

#### 25. Closures <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
var c = 0
val inc = { c++ }
inc()
inc()
println(c)
```

**Respuesta correcta: b)** `2`

::: tip Retroalimentación
Closure: la lambda puede leer y modificar las variables del ámbito donde se creó.
:::

- ❌ **a)** La lambda modifica la variable original, no una copia.
- ✅ **b)** Correcto. La lambda captura la variable `c` y la modifica en cada llamada (closure).
- ❌ **c)** Se llama dos veces.
- ❌ **d)** En Kotlin sí puede: es una closure.

#### 26. return@forEach <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
listOf(1, 2, 3, 4).forEach {
    if (it % 2 == 0) return@forEach
    print(it)
}
```

**Respuesta correcta: b)** `13`

::: tip Retroalimentación
`return@etiqueta` sale de la lambda; un `return` a secas saldría de la función que la contiene.
:::

- ❌ **a)** `return@forEach` no detiene el recorrido entero.
- ✅ **b)** Correcto. `return@forEach` termina solo esa vuelta, como un `continue`.
- ❌ **c)** Los pares se saltan antes del `print`.
- ❌ **d)** Se saltan los pares, no los impares.

#### 27. Lambda con receptor <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val saludo: String.() -> String = { "Hola, $this" }
println("Ana".saludo())
```

**Respuesta correcta: a)** `Hola, Ana`

::: tip Retroalimentación
`T.() -> R`: dentro, `this` es el objeto. Es la base de `apply` y de Compose.
:::

- ✅ **a)** Correcto. En una lambda con receptor, el objeto ("Ana") es `this` dentro del bloque.
- ❌ **b)** `$this` es una plantilla de cadena que se sustituye por el receptor.
- ❌ **c)** Una lambda con receptor se puede llamar sobre el objeto, como una función de extensión.
- ❌ **d)** El receptor es "Ana", no null.

#### 28. Referencias a funciones <Badge type="tip" text="RA2 · a" />

Con `fun esPar(n: Int) = n % 2 == 0`, ¿qué devuelve esta expresión?

```kotlin
listOf(1, 2, 3, 4).filter(::esPar)
```

**Respuesta correcta: b)** `[2, 4]`

::: tip Retroalimentación
`::funcion` permite pasar una función con nombre donde se espera una lambda.
:::

- ❌ **a)** `filter` se queda con los elementos que cumplen la condición.
- ✅ **b)** Correcto. `::esPar` es una referencia a la función, equivalente a `{ esPar(it) }`.
- ❌ **c)** `filter` devuelve una lista.
- ❌ **d)** Una referencia a función también es válida.

### UP02 · Particularidades de Kotlin

#### 29. val y colecciones <Badge type="tip" text="RA2 · a" />

¿Qué ocurre con este código?

```kotlin
val lista = mutableListOf(1)
lista.add(2)
```

**Respuesta correcta: d)** Compila y la lista contiene `[1, 2]`: `val` impide reasignar la variable, no modificar el contenido.

::: tip Retroalimentación
`val`/`var`: ¿puedo reasignar? · `List`/`MutableList`: ¿puedo cambiar el contenido?
:::

- ❌ **a)** `val` no impide llamar a `add` sobre una lista mutable.
- ❌ **b)** `mutableListOf` crea una lista modificable.
- ❌ **c)** `add` modifica la lista.
- ✅ **d)** Correcto. La mutabilidad de la colección es independiente de `val` o `var`.

#### 30. copy() en data class <Badge type="tip" text="RA2 · a" />

Con `data class Lugar(val nombre: String, val visitado: Boolean = false)`, ¿qué afirmación es correcta?

```kotlin
val a = Lugar("Faro")
val b = a.copy(visitado = true)
```

**Respuesta correcta: c)** `b` es un objeto nuevo con `visitado = true`, y `a` no cambia; por eso `a == b` es false.

::: tip Retroalimentación
Datos inmutables + `copy()`: el patrón que usamos en `PantallaLugares` para marcar un lugar.
:::

- ❌ **a)** `copy` siempre crea un objeto nuevo.
- ❌ **b)** Las propiedades son `val`: `a` no se puede modificar.
- ✅ **c)** Correcto. `copy` crea una copia y solo cambia lo que se indica. Así se actualiza el estado en Compose.
- ❌ **d)** En una data class, `==` compara el contenido, y `visitado` es distinto.

## Parte B · Solución de la práctica

La solución parte del [proyecto de referencia de la fase 1](../../proyecto/ejercicios/fase-1-solucion) (`es.dam.explora`). Cada alumno lo habrá resuelto sobre su propio modelo: lo que se evalúa es el concepto, no que el código coincida.

### B1 · Modelo de datos (1,5 puntos) <Badge type="tip" text="RA2 · a" />

```kotlin [data/Modelo.kt]
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import es.dam.explora.R

enum class Categoria(@StringRes val nombre: Int) {
    MONUMENTO(R.string.categoria_monumento),
    NATURALEZA(R.string.categoria_naturaleza),
    COSTA(R.string.categoria_costa)
}

data class Lugar(
    val id: Int,
    val nombre: String,
    val ciudad: String,
    @DrawableRes val imagen: Int,
    val categoria: Categoria,          // nuevo
    val retos: List<Reto>,
    val visitado: Boolean = false
) {
    val puntos: Int
        get() = retos.sumOf { it.puntos }
}

/** Suma de los puntos de los lugares visitados. */
fun List<Lugar>.puntosVisitados(): Int =
    filter { it.visitado }.sumOf { it.puntos }
```

```kotlin [data/DatosEjemplo.kt (fragmento)]
Lugar(
    id = 3,
    nombre = "Faro de Cullera",
    ciudad = "Cullera",
    imagen = R.drawable.lugar_faro,
    categoria = Categoria.COSTA,       // nuevo, en todos los lugares
    retos = listOf(/* … */)
)
```

**Comprobación** (con los datos de referencia): si se marcan la Lonja (40 puntos) y el Faro (40 puntos), `lugares.puntosVisitados()` devuelve 80.

### B2 · Interfaz (2 puntos) <Badge type="tip" text="RA2 · b" />

```kotlin [ui/TarjetaLugar.kt (dentro de la Column de textos)]
Text(
    text = stringResource(lugar.categoria.nombre),
    style = MaterialTheme.typography.labelMedium,
    color = MaterialTheme.colorScheme.secondary
)
```

```kotlin [ui/PantallaLugares.kt]
private const val TAG = "Explora"

// … dentro de PantallaLugares(), junto a los demás valores calculados:
val visitados = lugares.count { it.visitado }
val puntos = lugares.puntosVisitados()

// … dentro de la Column, debajo del contador de visitados:
Text(
    text = pluralStringResource(R.plurals.puntos_conseguidos, puntos, puntos),
    style = MaterialTheme.typography.labelLarge
)
Button(
    onClick = {
        Log.d(TAG, "Desmarcar todos: $visitados lugares desmarcados")
        for (i in lugares.indices) {
            lugares[i] = lugares[i].copy(visitado = false)
        }
    },
    enabled = visitados > 0
) {
    Text(stringResource(R.string.desmarcar_todos))
}
```

Claves que debe respetar la solución:
- **Puntos calculados.** Los puntos se calculan a partir del estado en cada recomposición; no son un estado aparte que haya que sincronizar.
- **Estado en la pantalla.** El botón modifica la lista de estado de la pantalla. `TarjetaLugar` no cambia de firma ni guarda estado.
- **`copy()`.** Se sustituye cada lugar por una copia; no se modifica el objeto, porque sus propiedades son `val`.

### B3 · Recursos e idiomas (1,5 puntos) <Badge type="tip" text="RA1 · e" />

::: code-group
```xml [values/strings.xml]
<string name="categoria_monumento">Monumento</string>
<string name="categoria_naturaleza">Naturaleza</string>
<string name="categoria_costa">Costa</string>
<string name="desmarcar_todos">Desmarcar todos</string>
<plurals name="puntos_conseguidos">
    <item quantity="one">%d punto conseguido</item>
    <item quantity="other">%d puntos conseguidos</item>
</plurals>
```

```xml [values-en/strings.xml]
<string name="categoria_monumento">Monument</string>
<string name="categoria_naturaleza">Nature</string>
<string name="categoria_costa">Coast</string>
<string name="desmarcar_todos">Uncheck all</string>
<plurals name="puntos_conseguidos">
    <item quantity="one">%d point earned</item>
    <item quantity="other">%d points earned</item>
</plurals>
```

```xml [values-ca/strings.xml]
<string name="categoria_monumento">Monument</string>
<string name="categoria_naturaleza">Natura</string>
<string name="categoria_costa">Costa</string>
<string name="desmarcar_todos">Desmarca-ho tot</string>
<plurals name="puntos_conseguidos">
    <item quantity="one">%d punt aconseguit</item>
    <item quantity="other">%d punts aconseguits</item>
</plurals>
```
:::

Errores habituales que bajan de nivel:
- Textos escritos directamente en el código.
- Traducciones que faltan, lo que el Translations Editor marca en rojo.
- Resolver el plural con un `if` en lugar de con `plurals`.

### B4 · Pruebas y depuración (1 punto) <Badge type="tip" text="RA2 · g" />

```kotlin [ui/TarjetaLugar.kt]
@Preview(name = "Categorías", showBackground = true)
@Composable
private fun TarjetasCategoriasPreview() {
    ExploraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TarjetaLugar(lugar = lugaresEjemplo[0], onVisitadoChange = {})
            TarjetaLugar(lugar = lugaresEjemplo[2].copy(visitado = true), onVisitadoChange = {})
        }
    }
}
```

Lo que debe verse en Logcat, filtrando con `package:mine tag:Explora`:

```
D  Desmarcar todos: 2 lugares desmarcados
```

Las capturas deben mostrar los puntos actualizados, otro idioma o el modo oscuro, y el mensaje de Logcat.

### B5 · Documentación (1 punto) <Badge type="tip" text="RA2 · i" />

Respuestas esperadas en `CAMBIOS.md`:

1. **Archivos modificados o creados:**
   - `Modelo.kt`: `Categoria`, el campo nuevo y `puntosVisitados`.
   - `DatosEjemplo.kt`: categoría de cada lugar.
   - `TarjetaLugar.kt`: texto de la categoría y preview.
   - `PantallaLugares.kt`: puntos, botón y log.
   - Los tres `strings.xml`.
2. **Dónde vive el estado.** El estado vive en `PantallaLugares` por la elevación del estado. Hay un único lugar donde cambia y la pantalla lo pasa hacia abajo. La tarjeta solo muestra el lugar y avisa con `onVisitadoChange`. Así es reutilizable, fácil de previsualizar y no se desincroniza con el contador ni con los puntos.
3. **Al girar la pantalla.** Los puntos vuelven a 0, porque se calculan a partir de la lista de lugares y esa lista se guarda con `remember`. Girar destruye y recrea la Activity, así que la lista vuelve a los datos de ejemplo, sin marcar. Se resolverá con un `ViewModel`.

### Cómo se califica la parte práctica

Cada apartado se valora con la escala 0-4 del módulo y se convierte de forma proporcional a su peso: **nota del apartado = peso × nivel / 4**. Por ejemplo, un nivel 3 en B2 (2 puntos) vale 1,5 puntos.

| Nivel | Significado general |
|---|---|
| 0 | No entregado o sin relación con lo solicitado. |
| 1 | Incompleto o incorrecto: no compila, o faltan elementos esenciales. |
| 2 | Correcto y completo, pero sin justificar las decisiones. |
| 3 | Correcto, completo y justificado (en `CAMBIOS.md` o con comentarios en el código). |
| 4 | Además, coherente con el resto del proyecto (nombres, estilo, tema, idiomas) y bien comunicado. |

El nivel 2 es el mínimo para considerar superado un apartado.


| Apartado | Nivel 2 (mínimo) | Nivel 3 | Nivel 4 |
|---|---|---|---|
| B1 · Modelo | `enum` con texto, campo en `Lugar` asignado en todos los datos y función de extensión correcta. | Explica por qué usa un `enum` y una función de extensión. | Nombres y estilo coherentes con el resto del modelo; usa `@StringRes`. |
| B2 · Interfaz | Categoría visible, puntos que se actualizan y botón que desmarca todo con `copy()`. | Botón desactivado sin marcados; estado elevado y justificado. | Diseño coherente con el tema (estilos de `MaterialTheme`) y sin estado duplicado. |
| B3 · Recursos | Todos los textos nuevos en recursos y en todos los idiomas. | `plurals` correcto y categorías traducidas. | Ningún aviso de Lint por textos fijos; traducciones naturales. |
| B4 · Pruebas | Log y preview funcionan; al menos dos capturas. | Las tres capturas, que muestran lo que se pide. | Pruebas adicionales comentadas (fuente grande, tablet, rotación). |
| B5 · Documentación | Lista de cambios. | Responde de forma razonada a las preguntas 2 y 3. | Clara, bien organizada y con capturas integradas. |

