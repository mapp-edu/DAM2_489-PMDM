---
layout: doc
title: "Simulacro de examen · 1.ª evaluación"
description: "Simulacro de la 1.ª evaluación de PMDM: test de teoría y práctica sobre el proyecto Explora"
sidebar: true
outline: [2, 3]
aside: true
---

# Simulacro de examen · 1.ª evaluación

**Módulo:** Programación Multimedia y Dispositivos Móviles · DAM2  
**Contenidos:** UP01 (tipos de aplicaciones; arquitectura de Dalvik a ART; fragmentación) · UP02 (Kotlin: null safety, funciones de alcance, lambdas) · UP03 y vídeos de la UP04 (proyecto Explora)  
**Duración:** 150 minutos

| Parte | Contenido | Puntos | Tiempo orientativo |
|---|---|---|---|
| A · Test (Aules) | 30 preguntas: 10 de la UP01 y 20 de la UP02 | 3 | 40 min |
| B · Práctica | Ampliación de tu proyecto Explora (5 apartados) | 7 | 110 min |
| | **Total** | **10** | **150 min** |

::: info-box Normas de la parte A
- Cada pregunta tiene **una sola respuesta correcta** y vale **0,1 puntos**.
- Cada respuesta incorrecta **resta 0,033 puntos**, un tercio del valor de la pregunta. Las preguntas en blanco no restan.
- La nota mínima de la parte A es 0.
:::

El examen se supera con una nota total de **5 o más**.

## Parte A · Test de teoría (3 puntos)

Esta parte se realiza en el cuestionario de Aules. Aquí tienes el mismo contenido para consultarlo.

### UP01 · Tipos de aplicaciones

#### 1. Aplicación nativa <Badge type="tip" text="RA1 · b" />

¿Qué característica define a una aplicación **nativa** de Android?

- **a)** Se ejecuta en el navegador y se instala desde una URL, sin pasar por la tienda.
- **b)** Comparte un único código para Android e iOS y dibuja la interfaz con un motor gráfico propio.
- **c)** Es una web empaquetada en un WebView que accede al hardware mediante plugins.
- **d)** Se desarrolla con el SDK y el lenguaje de la plataforma (Kotlin o Java) y accede directamente a todas las APIs del sistema.

#### 2. Aplicación híbrida <Badge type="tip" text="RA1 · b" />

Una aplicación hecha con HTML, CSS y JavaScript, empaquetada en un contenedor nativo que la muestra en un **WebView** y accede a la cámara mediante plugins (por ejemplo, Ionic + Capacitor), es una aplicación…

- **a)** Web progresiva (PWA).
- **b)** Híbrida.
- **c)** Nativa.
- **d)** Multiplataforma compilada, como Flutter o Kotlin Multiplatform.

#### 3. Motor de renderizado propio <Badge type="tip" text="RA1 · b" />

¿Qué tecnología multiplataforma **dibuja toda la interfaz con su propio motor de renderizado**, en lugar de usar los componentes visuales nativos de cada sistema?

- **a)** React Native.
- **b)** Flutter.
- **c)** Capacitor.
- **d)** Kotlin Multiplatform compartiendo solo la lógica de negocio.

#### 4. Limitaciones de una PWA <Badge type="tip" text="RA1 · b" />

¿Cuál es una limitación habitual de una **aplicación web progresiva (PWA)** frente a una aplicación nativa?

- **a)** El acceso al hardware y a las APIs del sistema depende de lo que permita el navegador, y suele ser más limitado.
- **b)** No puede funcionar sin conexión.
- **c)** Debe publicarse obligatoriamente en Google Play.
- **d)** Solo puede programarse en Kotlin.

### UP01 · Arquitectura: de Dalvik a ART

#### 5. Capas de la arquitectura <Badge type="tip" text="RA1 · b" />

Ordena **de abajo arriba** las capas principales de la arquitectura de Android.

- **a)** HAL → Núcleo Linux → Framework de APIs Java → ART → Aplicaciones
- **b)** Núcleo Linux → Framework de APIs Java → ART → HAL → Aplicaciones
- **c)** Núcleo Linux → HAL → ART y bibliotecas nativas → Framework de APIs Java → Aplicaciones
- **d)** ART → Núcleo Linux → HAL → Aplicaciones → Framework de APIs Java

#### 6. Bytecode DEX <Badge type="tip" text="RA1 · b" />

¿Qué formato de código ejecutan las máquinas virtuales **Dalvik y ART**?

- **a)** Archivos .class de la máquina virtual de Java, sin transformar.
- **b)** El código fuente Kotlin, que se interpreta línea a línea.
- **c)** Bibliotecas nativas .so escritas en C o C++.
- **d)** Bytecode DEX (Dalvik Executable), generado a partir de las clases compiladas de Kotlin o Java.

#### 7. De Dalvik a ART <Badge type="tip" text="RA1 · a" />

¿Cuál fue el cambio principal al sustituir **Dalvik por ART**?

- **a)** ART añadió la compilación anticipada (AOT) a código máquina, lo que acelera el arranque y reduce el consumo de batería frente al JIT de Dalvik durante la ejecución.
- **b)** ART eliminó el bytecode DEX y ejecuta directamente el código fuente.
- **c)** ART interpreta todo el código, por lo que ocupa menos memoria pero es más lento.
- **d)** ART permite ejecutar aplicaciones de iOS en Android.

### UP01 · Fragmentación

#### 8. Concepto de fragmentación <Badge type="tip" text="RA1 · d" />

En Android, la **fragmentación** se refiere a…

- **a)** La división del código de una app en varios archivos .dex.
- **b)** La fragmentación de la memoria RAM cuando hay muchas apps abiertas.
- **c)** El reparto de una pantalla en varios componentes Fragment.
- **d)** La gran variedad de fabricantes, tamaños y densidades de pantalla, hardware y capas de personalización, que obliga a adaptar y probar las apps en dispositivos muy distintos.

#### 9. Project Treble <Badge type="tip" text="RA1 · d" />

¿Qué objetivo tiene **Project Treble**?

- **a)** Actualizar componentes del sistema a través de Google Play, sin pasar por el fabricante.
- **b)** Separar la parte del sistema que implementa cada fabricante (vendor) del framework de Android mediante una interfaz estable, para que las actualizaciones lleguen antes.
- **c)** Ofrecer bibliotecas con APIs compatibles en muchos dispositivos, como AndroidX.
- **d)** Unificar las capas de personalización de todos los fabricantes en una sola.

#### 10. Combatir la fragmentación <Badge type="tip" text="RA1 · e" />

¿Qué práctica ayuda a que una app funcione bien **a pesar de la fragmentación**?

- **a)** Usar dp y sp, recursos con calificadores (idioma, modo oscuro, tamaño), bibliotecas Jetpack y probar en varios perfiles de emulador.
- **b)** Fijar todos los tamaños en píxeles para que la interfaz mida lo mismo en cualquier pantalla.
- **c)** Diseñar y probar solo en el modelo de móvil más vendido.
- **d)** Bloquear la rotación, el modo oscuro y el tamaño de fuente del sistema.

### UP02 · Null safety

#### 11. Tipos nulables <Badge type="tip" text="RA2 · a" />

Dadas estas declaraciones, ¿qué línea **compila**?

```kotlin
var a: String = "Hola"
val b: String? = "Hola"
```

- **a)** `a = null`
- **b)** `val n: Int = b.length`
- **c)** `val n = b?.length`
- **d)** `val n: Int = b?.length`

#### 12. Operador Elvis <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val nombre: String? = null
println(nombre?.length ?: -1)
```

- **a)** `-1`
- **b)** `null`
- **c)** `0`
- **d)** Lanza `NullPointerException`.

#### 13. Operador !! <Badge type="tip" text="RA2 · a" />

¿Qué hace el operador `!!` en `val n = texto!!.length`?

- **a)** Devuelve 0 si `texto` es null.
- **b)** Afirma que `texto` no es null; si lo es, lanza `NullPointerException` en tiempo de ejecución.
- **c)** Convierte `texto` en una cadena vacía si es null.
- **d)** Hace que la línea solo se ejecute si `texto` no es null.

#### 14. Smart cast <Badge type="tip" text="RA2 · a" />

¿Por qué compila `t.length` en esta función, si `t` es de tipo `String?`?

```kotlin
fun longitud(t: String?): Int {
    if (t != null) return t.length
    return 0
}
```

- **a)** Porque `length` funciona igual con tipos nulables.
- **b)** Porque el compilador añade `!!` automáticamente.
- **c)** Por el smart cast: tras comprobar `t != null`, el compilador trata `t` como `String` en esa rama.
- **d)** No compila: habría que escribir `t?.length`.

#### 15. lateinit <Badge type="tip" text="RA2 · a" />

Sobre `lateinit var binding: ActivityMainBinding`, ¿qué afirmación es **correcta**?

- **a)** Declara una propiedad no nula que se inicializa más tarde; si se usa antes de asignarla, lanza `UninitializedPropertyAccessException`.
- **b)** También se puede usar con `val` y con tipos como `Int`.
- **c)** Hace que la propiedad sea nulable.
- **d)** Si se usa sin inicializar, devuelve null.

#### 16. toIntOrNull <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `edad`?

```kotlin
val edad = "veinte".toIntOrNull() ?: 18
```

- **a)** `null`
- **b)** Lanza `NumberFormatException`.
- **c)** `18`
- **d)** `20`

### UP02 · Funciones de alcance

#### 17. Qué devuelve apply <Badge type="tip" text="RA2 · a" />

¿Qué devuelve `apply` y cómo se llama el objeto dentro de su bloque?

- **a)** Devuelve el resultado de la lambda; el objeto es `it`.
- **b)** Devuelve el propio objeto; el objeto es `it`.
- **c)** Devuelve el resultado de la lambda; el objeto es `this`.
- **d)** Devuelve el propio objeto; dentro, el objeto es `this`.

#### 18. let <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `r`?

```kotlin
val r = "Kotlin".let { it.length * 2 }
```

- **a)** `12`
- **b)** `"Kotlin"`
- **c)** `6`
- **d)** `Unit`

#### 19. also <Badge type="tip" text="RA2 · a" />

¿Qué contiene `lista`?

```kotlin
val lista = mutableListOf(3, 1, 2).also { it.sort() }
```

- **a)** `[3, 1, 2]`
- **b)** `kotlin.Unit`
- **c)** `6`
- **d)** `[1, 2, 3]`

#### 20. with <Badge type="tip" text="RA2 · a" />

¿Qué valor tiene `t`?

```kotlin
val t = with(StringBuilder()) {
    append("A")
    append("B")
    toString()
}
```

- **a)** `"B"`
- **b)** `"A"`
- **c)** No tiene valor: `with` no devuelve nada.
- **d)** `"AB"`

#### 21. Configurar un objeto <Badge type="tip" text="RA2 · a" />

Quieres **crear un objeto, dar valor a varias de sus propiedades y guardar el objeto configurado** en una variable, en una sola expresión. ¿Qué función de alcance es la más adecuada?

- **a)** `let`
- **b)** `apply`
- **c)** `run`
- **d)** `takeIf`

#### 22. ?.let con null <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val texto: String? = null
texto?.let { println("Hola $it") }
```

- **a)** `Hola null`
- **b)** `Hola `
- **c)** Nada.
- **d)** Lanza `NullPointerException`.

### UP02 · Lambdas

#### 23. Tipo de una lambda <Badge type="tip" text="RA2 · a" />

¿Cuál es el tipo de esta lambda?

```kotlin
val sumar = { a: Int, b: Int -> a + b }
```

- **a)** `Int`
- **b)** `(Int) -> (Int) -> Int`
- **c)** `(Int, Int) -> Int`
- **d)** `() -> Int`

#### 24. Lambda final <Badge type="tip" text="RA2 · b" />

Dada `fun operar(a: Int, b: Int, op: (Int, Int) -> Int) = op(a, b)`, ¿qué ocurre con esta llamada?

```kotlin
val r = operar(2, 3) { x, y -> x * y }
```

- **a)** Compila y `r` vale 6: como el último parámetro es una función, la lambda puede ir fuera de los paréntesis.
- **b)** No compila: la lambda tiene que ir dentro de los paréntesis.
- **c)** Compila y `r` vale 5.
- **d)** Compila, pero `op` solo recibe el primer parámetro.

#### 25. Closures <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
var c = 0
val inc = { c++ }
inc()
inc()
println(c)
```

- **a)** `0`
- **b)** `2`
- **c)** `1`
- **d)** No compila: una lambda no puede modificar variables externas.

#### 26. return@forEach <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
listOf(1, 2, 3, 4).forEach {
    if (it % 2 == 0) return@forEach
    print(it)
}
```

- **a)** `1`
- **b)** `13`
- **c)** `1234`
- **d)** `24`

#### 27. Lambda con receptor <Badge type="tip" text="RA2 · a" />

¿Qué imprime este código?

```kotlin
val saludo: String.() -> String = { "Hola, $this" }
println("Ana".saludo())
```

- **a)** `Hola, Ana`
- **b)** `Hola, this`
- **c)** No compila: habría que llamarla como `saludo("Ana")`.
- **d)** `Hola, null`

#### 28. Referencias a funciones <Badge type="tip" text="RA2 · a" />

Con `fun esPar(n: Int) = n % 2 == 0`, ¿qué devuelve esta expresión?

```kotlin
listOf(1, 2, 3, 4).filter(::esPar)
```

- **a)** `[1, 3]`
- **b)** `[2, 4]`
- **c)** `true`
- **d)** No compila: `filter` necesita una lambda entre llaves.

### UP02 · Particularidades de Kotlin

#### 29. val y colecciones <Badge type="tip" text="RA2 · a" />

¿Qué ocurre con este código?

```kotlin
val lista = mutableListOf(1)
lista.add(2)
```

- **a)** No compila porque `lista` es `val`.
- **b)** Compila, pero lanza una excepción al añadir.
- **c)** Compila, pero la lista sigue conteniendo `[1]`.
- **d)** Compila y la lista contiene `[1, 2]`: `val` impide reasignar la variable, no modificar el contenido.

#### 30. copy() en data class <Badge type="tip" text="RA2 · a" />

Con `data class Lugar(val nombre: String, val visitado: Boolean = false)`, ¿qué afirmación es correcta?

```kotlin
val a = Lugar("Faro")
val b = a.copy(visitado = true)
```

- **a)** `a` y `b` son el mismo objeto.
- **b)** `copy` modifica `a` y lo devuelve.
- **c)** `b` es un objeto nuevo con `visitado = true`, y `a` no cambia; por eso `a == b` es false.
- **d)** `a == b` es true porque son de la misma clase.

## Parte B · Práctica sobre el proyecto Explora (7 puntos)

Vas a añadir a tu app **categorías de lugares** y un **marcador de puntos conseguidos**.

### Condiciones de la parte práctica

- Trabajas sobre **tu propio proyecto Explora**, tal como lo entregaste. Antes de empezar, haz una copia de la carpeta del proyecto.
- Los nombres de este enunciado son los del vídeo 2 de la UP03 (`Lugar`, `PantallaLugares`, `TarjetaLugar`, `lugaresEjemplo`). Si en tu proyecto se llaman de otra forma, usa los tuyos. Si a tu modelo le falta algún dato que se pide (por ejemplo, los puntos), usa otro dato numérico equivalente y explícalo en `CAMBIOS.md`.
- Puedes consultar la documentación oficial de Android y Kotlin. No puedes usar asistentes de IA ni comunicarte con otras personas.
- El proyecto debe **compilar y ejecutarse**. Si una parte no funciona, déjala comentada y explícalo en `CAMBIOS.md`: se valora el resto.

### Entrega

Sube a la tarea de Aules un único archivo `.zip` llamado `Apellido_Nombre_Explora.zip` con:

1. La carpeta del proyecto. Antes, haz *Build › Clean Project* para que pese menos.
2. Un archivo `CAMBIOS.md` en la raíz del proyecto, con lo que pide el apartado B5.
3. Una carpeta `capturas/` con las capturas que se piden.


### B1 · Modelo de datos (1,5 puntos) <Badge type="tip" text="RA2 · a" />

1. Crea una `enum class Categoria` con al menos tres valores adecuados a tu temática (por ejemplo, `MONUMENTO`, `NATURALEZA` y `COSTA`). Cada valor debe tener una propiedad con el **identificador del texto** que lo nombra (`@StringRes val nombre: Int`).
2. Añade la categoría a tu clase `Lugar` y asígnala a todos los lugares de ejemplo.
3. Escribe una **función de extensión** `fun List<Lugar>.puntosVisitados(): Int` que devuelva la suma de los puntos de los lugares visitados.

### B2 · Interfaz (2 puntos) <Badge type="tip" text="RA2 · b" />

1. Muestra la **categoría** en cada tarjeta, como texto o con un icono vectorial y su texto.
2. Debajo del contador de visitados, muestra **«N puntos conseguidos»** usando la función de B1. Debe actualizarse al marcar o desmarcar un lugar.
3. Añade un botón **«Desmarcar todos»** que ponga todos los lugares como no visitados. Debe estar desactivado si no hay ninguno marcado. Respeta la elevación del estado: la tarjeta sigue sin guardar estado y los cambios se hacen con `copy()`.

### B3 · Recursos e idiomas (1,5 puntos) <Badge type="tip" text="RA1 · e" />

1. Todos los textos nuevos deben estar en `strings.xml`, traducidos a **todos los idiomas** que ya tenga tu app (como mínimo, dos).
2. «N puntos conseguidos» debe usar un recurso **`plurals`**: «1 punto conseguido», «2 puntos conseguidos».
3. Los nombres de las categorías también deben estar traducidos.

### B4 · Pruebas y depuración (1 punto) <Badge type="tip" text="RA2 · g" />

1. Al pulsar «Desmarcar todos», escribe en Logcat con `Log.d` y la etiqueta de tu app **cuántos lugares se han desmarcado**.
2. Añade una **preview** que muestre dos tarjetas de categorías distintas, una marcada y otra sin marcar.
3. Guarda en `capturas/` estas tres capturas:
   - La app en el emulador con algún lugar marcado y los puntos visibles.
   - La misma pantalla en otro idioma o en modo oscuro.
   - Logcat filtrado mostrando el mensaje del punto 1.

### B5 · Documentación (1 punto) <Badge type="tip" text="RA2 · i" />

En `CAMBIOS.md`, explica brevemente:

1. Qué archivos has modificado o creado, y para qué.
2. Por qué el estado de los lugares vive en `PantallaLugares` y no en `TarjetaLugar`.
3. Qué ocurre con los puntos conseguidos al girar la pantalla, y por qué.

