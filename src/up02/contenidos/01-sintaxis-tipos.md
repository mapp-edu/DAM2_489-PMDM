---
layout: doc
title: 1. Sintaxis, tipos de datos y null safety
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Sintaxis, tipos de datos y null safety

::: warning-box Antes de empezar
En primer curso estudiaste el módulo de **Programación**, así que en los ejemplos se darán por conocidos conceptos como el control de flujo (`if`, `for`…), las funciones, la programación orientada a objetos, el acceso a propiedades, las llamadas a métodos o las excepciones, incluso antes de ver cómo se escriben en Kotlin.
:::

::: danger-box Normas para todo el curso
- Los nombres de archivos, variables, funciones, métodos, clases… se escriben **en inglés**.
- Cada archivo empieza con un comentario **KDoc** (equivalente a JavaDoc) con, al menos, las etiquetas `@author` y `@version`.
- Cada clase y cada método llevan también su comentario KDoc.
- El código debe explicarse por sí mismo; si hay algoritmos o elementos complejos, se comentan.
:::

## 1.1. Introducción

Al principio, **Java** era el lenguaje de programación de Android. En **2017** Google declaró **Kotlin** lenguaje oficial para Android, al mismo nivel que Java, y en 2019 lo convirtió en el lenguaje **preferente** (*Kotlin-first*). Kotlin tiene soporte oficial de Google y viene integrado en Android Studio.

Algunas características de Kotlin:

- Lenguaje **multiplataforma y multipropósito**: con él se pueden desarrollar aplicaciones Android, de escritorio, web, de consola…
- Se compila para la **JVM**, por lo que es totalmente compatible con Java y sus librerías (se puede llamar a Java desde Kotlin y viceversa).
- Es **orientado a objetos**, aunque no obliga a que todo esté dentro de clases.
- Es **conciso**: evita código innecesario (hasta un 40 % menos que Java).
- Es ***null safety***: gestiona los nulos de forma segura y evita los errores `NullPointerException`.
- De forma informal, se podría decir que es «Java al estilo de Python», con las ventajas de ambos.

En esta unidad estudiamos los fundamentos y la sintaxis de Kotlin creando *scripts* con **IntelliJ IDEA** (necesitas un JDK instalado). Más adelante usaremos Kotlin dentro de Android Studio.

::: info-box Recursos oficiales
- [Documentación oficial de Kotlin](https://kotlinlang.org/docs/home.html).
- [Kotlin by example](https://play.kotlinlang.org/byExample/overview): plataforma para aprender Kotlin con ejemplos ejecutables.
:::

## 1.2. Ejecutar código Kotlin

### Configurar IntelliJ IDEA

La interfaz actual de IntelliJ IDEA esconde la barra de menús tras un icono conocido como «menú hamburguesa».

![IntelliJ IDEA con la nueva interfaz](/img/contenidos/up02/up02-1-p07-1.webp)

Si quieres tener siempre visible la barra de menús, ve a **File → Settings → Appearance & Behavior → Appearance** y en **Main menu** elige **Show above Main Toolbar**.

![Configuración para mostrar la barra de menús](/img/contenidos/up02/up02-1-p08-1.webp)

IntelliJ IDEA dispone de *plugins* que mejoran la productividad. Por ejemplo, **Key Promoter X** muestra, cada vez que usas el ratón para una acción, el atajo de teclado equivalente.

![Instalación del plugin Key Promoter X](/img/contenidos/up02/up02-1-p09-1.webp)

### Crear un proyecto

Con un JDK instalado, crea un proyecto nuevo con estas opciones:

- **Language:** Kotlin
- **Build system:** IntelliJ
- **JDK:** el que tengas instalado
- **Add sample code:** marcado

![Asistente New Project de IntelliJ IDEA](/img/contenidos/up02/up02-1-p10-1.webp)

### Ejecutar un programa

Hay varias formas de ejecutar un programa: el botón **Run** de la barra superior, el icono ▷ junto a la función `main` o el menú **Run → Run 'Main.kt'** (`Mayús + F10`).

![Formas de ejecutar un programa en IntelliJ IDEA](/img/contenidos/up02/up02-1-p11-1.webp)

Al ejecutar se lanza la función `main` **del archivo activo**. Si hay varios archivos con una función `main`, solo se ejecuta la del archivo activo; si el archivo activo no tiene función `main`, el botón **Run** aparece desactivado.

![El botón Run solo está activo si el archivo tiene función main](/img/contenidos/up02/up02-1-p12-1.webp)

El código de ejemplo incluye una función `main` con contenido y un punto de ruptura. **Lo primero en cada proyecto es vaciar `main` y eliminar ese punto de ruptura.**

![Proyecto con la función main vacía](/img/contenidos/up02/up02-1-p13-1.webp)

::: details El botón Run aparece en gris aunque haya función main
A veces, aun teniendo la función `main`, el botón **Run** aparece desactivado (en gris). Para solucionarlo:

1. Haz clic derecho sobre la carpeta del proyecto.
2. Selecciona **Open Module Settings** (`F4`).
3. Ve a la pestaña **Dependencies**.
4. En **Module SDK** cambia el SDK seleccionado.

![Open Module Settings](/img/contenidos/up02/up02-1-p14-2.webp)

![Cambio del Module SDK en la pestaña Dependencies](/img/contenidos/up02/up02-1-p14-3.webp)
:::

## 1.3. Guía de estilo

En cualquier lenguaje es importante que el código sea legible, y por eso las plataformas oficiales publican guías de estilo. Revisa las [convenciones de código de Kotlin](https://kotlinlang.org/docs/coding-conventions.html).

::: tip-box Formatear el código
Usa **Code → Reformat Code** (`Ctrl + Alt + L`) para que el IDE formatee el código según la guía de estilo (siempre que no hayas cambiado la configuración del IDE).
:::

## 1.4. Hola mundo en Kotlin

Los archivos de Kotlin tienen la extensión `.kt`. A diferencia de Java, no todo tiene que estar dentro de clases: la función `main` puede estar directamente en el archivo.

```kotlin [Greeting.kt]
// Programa en Kotlin

fun main() {
    println("Hello there!")
}
```

- `main` puede recibir argumentos (`fun main(args: Array<String>)`), pero no es obligatorio indicarlo.
- `println` está disponible directamente, sin `System.out`.
- `readln()` lee como cadena lo que se escribe por teclado en la consola.
- El `;` al final de cada instrucción es opcional y, por convención, **no se usa**.
- Los comentarios de línea y de bloque son como en Java: `//` y `/* */`. Los comentarios KDoc, como los de JavaDoc: `/** */`.

## 1.5. Paquetes e importaciones

Como en Java, la declaración del paquete y las importaciones van al principio del archivo:

```kotlin
package com.alextorres.hellothere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

## 1.6. Variables

Las variables son «cajas» que almacenan valores que pueden cambiar durante la ejecución. Kotlin tiene **tipado estático**: una vez que una variable tiene un tipo, ese tipo no cambia (como en Java).

Las variables se declaran con la palabra reservada **`var`**, y el tipo se indica con dos puntos detrás del nombre. Al declararla hay que hacer **obligatoriamente** una de estas tres cosas:

```kotlin
var name: String                    // 1. Solo se indica el tipo
var name = "Rick Sanchez"           // 2. Solo se asigna el valor: Kotlin deduce el tipo
var name: String = "Rick Sanchez"   // 3. Se indica el tipo y se asigna el valor
```

## 1.7. Tipos de datos

### Numéricos

| Tipo | Tamaño | Ejemplo de literal |
|:---|:---:|:---|
| `Byte` | 8 bits | `12` |
| `Short` | 16 bits | `1200` |
| `Int` | 32 bits | `154`, `0x9A` (hexadecimal), `0b10011010` (binario) |
| `Long` | 64 bits | `154L` |
| `Float` | 32 bits | `154.8f` |
| `Double` | 64 bits | `154.8` |

::: tabs

== Indicando el tipo

```kotlin
var quantity: Int = 154
var bigNumber: Long = 154L
var hexNumber: Int = 0x9A
var binNumber: Int = 0b10011010
var decimal: Float = 154.8f
var bigDecimal: Double = 154.8
```

== Kotlin deduce el tipo

```kotlin
var quantity = 154
var bigNumber = 154L
var hexNumber = 0x9A
var binNumber = 0b10011010
var decimal = 154.8f
var bigDecimal = 154.8
```

:::

Kotlin permite usar el **guion bajo** en los literales numéricos para hacerlos más legibles:

```kotlin
var number = 2_000_000
var dniNumber = 44_294_051
var creditCard = 6135_8442_0103_5610L
```

### Caracteres

Se declaran con el tipo **`Char`**. Kotlin usa la codificación UTF-16, así que los literales se escriben entre comillas simples con el carácter o con su código Unicode. **No se pueden tratar como números.**

```kotlin
var letter = 'N'
letter = '7'
```

Secuencias de escape disponibles:

| Secuencia | Significado | Secuencia | Significado |
|:---:|:---|:---:|:---|
| `\t` | Tabulación | `\"` | Comilla doble |
| `\b` | Retroceso | `\\` | Barra invertida |
| `\r` | Retorno de carro | `\$` | Símbolo de dólar |
| `\n` | Salto de línea | `\uXXXX` | Carácter Unicode (4 dígitos hexadecimales) |
| `\'` | Apóstrofo | | |

### Booleanos

Se declaran con el tipo **`Boolean`** y sus valores son `true` y `false`.

```kotlin
var isActive = true
var hasDiscount = false
```

### Cadenas

Se declaran con el tipo **`String`** y los literales van entre comillas dobles.

```kotlin
var name = "Rick Sanchez"
```

#### *Raw strings*

Con **triples comillas dobles** la cadena admite saltos de línea. Son las *raw strings* (cadenas en crudo): cuentan todos los caracteres que hay entre las comillas, incluidos los espacios de la sangría.

```kotlin
var brave = """A veces no soy yo
               Busco un disfraz mejor
               Bailando hasta el apagón
               Disculpad mi osadía"""
```

```txt
A veces no soy yo
               Busco un disfraz mejor
               Bailando hasta el apagón
               Disculpad mi osadía
```

Con `trimMargin()` se eliminan los espacios del principio de cada línea hasta el delimitador de margen, que por defecto es `|` (se puede cambiar, por ejemplo, con `trimMargin(">")`):

```kotlin
var brave = """|A veces no soy yo
               |Busco un disfraz mejor
               |Bailando hasta el apagón
               |Disculpad mi osadía""".trimMargin()
```

```txt
A veces no soy yo
Busco un disfraz mejor
Bailando hasta el apagón
Disculpad mi osadía
```

#### *String templates*

Las plantillas de cadena permiten insertar variables dentro de un `String` con el símbolo `$` seguido del nombre. Si la variable se envuelve entre llaves `${ }`, se puede operar con ella:

```kotlin
println("El descuento es un $discount%")

println("Producto: ${product.name}")

println("""Libreta de cuadros $notebookPrice €
          |Bolígrafo azul $penPrice €
          |      Total: ${notebookPrice + penPrice} €
          """.trimMargin())
```

::: danger-box ¡Importante!
Usa **siempre** *string templates* y *raw strings*. Evita:

- Concatenar cadenas con `+`: ~~`println("Producto: " + product.name)`~~
- Encadenar varias instrucciones `println` seguidas para escribir un texto de varias líneas.
:::

### Arrays

Los *arrays* son estructuras de **longitud fija** que almacenan varios valores del mismo tipo (números, caracteres, cadenas, booleanos, objetos…).

```kotlin
var numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
var names = arrayOf("Rick", "Morty", "Summer")   // Kotlin deduce Array<String>
```

Se accede a los elementos con corchetes `[ ]`:

```kotlin
numbers[2] = 123
println(numbers[0])
println("Hay ${names.size} nombres")
```

También se puede usar el constructor de la clase `Array`, indicando el tamaño y una **función lambda** que rellena cada elemento:

```kotlin
var array = Array(10) { }            // 10 elementos vacíos (tipo Unit)
var zeros = Array(5) { 0 }           // 5 enteros, todos 0
var empties = Array(5) { "" }        // 5 cadenas vacías
var series = Array(100) { it }       // 100 enteros: 0, 1, 2… 99
var reverse = Array(50) { 50 - it }  // 50 enteros: 50, 49, 48… 1
```

El código entre llaves es una **lambda** (función anónima). Dentro de ella está disponible la variable **`it`**, que representa la posición del elemento, desde 0 hasta el tamaño menos 1.

::: tip-box Pistas del IDE
IntelliJ IDEA muestra por defecto pistas en el código (*inlay hints*), como `size:` o `it: Int`. Se configuran en **File → Settings → Editor → Inlay Hints**.
:::

#### Arrays multidimensionales

Son *arrays* en los que cada elemento es, a su vez, un *array*. Los más habituales son las **matrices**, que se pueden representar como una tabla.

```kotlin
// Matriz 3x4 de enteros ya inicializados
val matrix = arrayOf(
    arrayOf(5, 3, 2, 0),
    arrayOf(4, 9, 4, 5),
    arrayOf(8, 1, 7, 6)
)

// Matriz de 3x3 enteros inicializados a 0
var matrixOfInts = Array(3) { Array(3) { 0 } }

// Matriz de 6x5 cadenas inicializadas a ""
var matrixOfStrings = Array(6) { Array(5) { "" } }
```

Se accede a cada elemento con el índice de la fila y el de la columna:

```kotlin
matrix[1][3] = 8   // fila 1, columna 3: el 5 pasa a ser un 8
```

### `Any` y `Unit`

Todos los tipos son clases y, en la jerarquía de Kotlin, todos heredan de **`Any`** (en Java sería `Object`). Una variable de tipo `Any` admite cualquier valor:

```kotlin
var variable: Any = 6
variable = "Rick Sanchez"
variable = arrayOf(1, 2, 3)
```

Para usar una variable, esta debe tener un valor. El tipo **`Unit`** representa la ausencia de valor (en Java sería `void`):

```kotlin
val variable1: Any
// Error: la variable no se ha inicializado
println("valor de variable: $variable1")

val variable2: Any = Unit
// Funciona correctamente
println("valor de variable: $variable2")
```

### Ámbito de las variables

El ámbito (*scope*) es la parte del código en la que se puede usar una variable. Como norma general, una variable se puede usar dentro de todo el **bloque `{ }` en el que se declara**, incluidos los bloques anidados.

::: danger-box Variables globales
Una variable declarada fuera de cualquier bloque `{ }` se puede usar en todos los archivos del proyecto. **No uses esta posibilidad bajo ningún concepto.**
:::

## 1.8. Conversión de tipos

Para convertir un valor a otro tipo se usa un **método de la clase del tipo de origen**. Por ejemplo, para convertir un `Double` en `Int` se llama a `toInt()` sobre la variable decimal:

```kotlin
var decimalNumber: Double = 12.34
var intNumber: Int
intNumber = decimalNumber.toInt()
```

Los métodos de conversión empiezan por `to`: `toDouble()`, `toFloat()`, `toLong()`, `toInt()`, `toShort()`, `toByte()`, `toString()`…

Lo que se lee por teclado es **siempre una cadena**: si se pide un número hay que convertirlo, y la conversión puede lanzar una excepción.

```kotlin
var number = readln().toInt()
```

## 1.9. Constantes

Con la palabra reservada **`val`** se declaran variables de **solo lectura**: una vez asignado un valor, no puede cambiar.

```kotlin
val age: Int = 23
age = 24   // ❌ Error: val no se puede reasignar
```

El valor se puede asignar en el código o en tiempo de ejecución, por ejemplo tras pedir un dato al usuario:

```kotlin
val age: Int
age = readln().toInt()
```

Los *arrays* y, en general, los **objetos** declarados con `val` sí permiten cambiar su contenido: al modificar sus elementos o propiedades no se cambia la instancia almacenada. Lo que no se puede es asignar otra instancia:

```kotlin
val numbers = arrayOf(1, 2, 3, 4, 0)
// Se puede cambiar el contenido de los elementos
numbers[4] = 5

// Al declararse con val no se puede asignar otro objeto
numbers = arrayOf(1, 2)   // ❌ Error
```

### `const val`

Para **valores conocidos en tiempo de compilación** se antepone `const` a `val`. Las constantes `const val` se declaran en un archivo fuera de cualquier función o dentro de un objeto *singleton* (una clase con una única instancia), nunca dentro de una función:

::: tabs

== ❌ Dentro de una función

```kotlin
fun main() {
    const val PI = 3.1416   // Error
    println(PI)
}
```

== ✅ En el archivo

```kotlin
const val PI = 3.1416

fun main() {
    println(PI)
}
```

== ✅ En un objeto

```kotlin
object Constants {
    const val PI = 3.1416
}

fun main() {
    println(Constants.PI)
}
```

:::

## 1.10. Operadores

Son similares a los de Java:

| Tipo de operación | Operadores |
|:---|:---|
| Signo | `+` `-` |
| Asignación | `=` `+=` `-=` `*=` `/=` `%=` |
| Aritméticos | `+` `-` `*` `/` `%` |
| Incremento y decremento | `++` `--` (prefijos o sufijos) |
| Relacionales | `==` `!=` `<` `>` `<=` `>=` |
| Lógicos | `&&` `\|\|` `!` |
| Sobre bits | `and` `or` `xor` `inv()` `shl` (desplaza a la izquierda) `shr` (desplaza a la derecha) `ushr` |

::: tip-box Práctica
Realiza la [Actividad 1 — Variables, constantes y control de flujo](../ejercicios/#actividad-1).
:::

## 1.11. Null safety

El error `NullPointerException` se produce al acceder a un objeto que no se ha inicializado. Kotlin admite el valor `null`, pero **por defecto no permite asignarlo** a las variables, para evitar estos errores:

```kotlin
var name: String = null   // ❌ Error de compilación
```

Para que una variable pueda almacenar `null` hay que añadir **`?`** detrás del tipo:

```kotlin
var name: String = "Rick Sanchez"
name = null                // ❌ Error

var nullableName: String? = "Morty Smith"
nullableName = null        // ✅ Correcto
```

Kotlin impide realizar acciones que puedan fallar si la variable puede ser `null`:

```kotlin
var name: String = "Rick Sanchez"
println(name.length)

var nullableName: String? = "Morty Smith"
// La siguiente instrucción no se acepta, ya que la variable podría ser null
println(nullableName.length)   // ❌
```

Como en Java, se puede comprobar antes de acceder:

```kotlin
var nullableName: String? = "Morty Smith"
if (nullableName != null) {
    println("Hola $nullableName!")
} else {
    println("Hola invitado!")
}
```

### Operador de llamada segura `?.`

El operador **`?.`** hace la comprobación anterior de forma más compacta: si la variable es `null`, la expresión completa devuelve `null` en lugar de fallar.

```kotlin
var nullableName: String? = "Morty Smith"
println("${nullableName?.length}")
```

Permite encadenar varias comprobaciones:

```kotlin
val city: String? = user?.address?.city
```

## 1.12. Operador Elvis `?:`

El operador **Elvis** `?:` proporciona un **valor por defecto** cuando la expresión de su izquierda es `null`:

```kotlin
var nullableName: String? = "Morty Smith"
println(nullableName ?: "desconocido")   // Morty Smith
nullableName = null
println(nullableName ?: "desconocido")   // desconocido
```

Su uso más habitual es obtener un valor no nulo cuando una propiedad o un método devuelven `null`:

```kotlin
val upperName = name?.uppercase() ?: "Desconocido"
val city = user?.address?.city ?: "Desconocida"
```

También sirve para **lanzar una excepción**:

```kotlin
var nullableName: String? = "Morty Smith"
val name = nullableName ?: throw IllegalArgumentException("El nombre es nulo")
```

Si tras el operador Elvis necesitas ejecutar varias instrucciones, inclúyelas en un bloque **`run { }`** (se explicará más adelante). La **última instrucción** del bloque es el valor que se asigna cuando la variable es `null`:

```kotlin
val a = b ?: run {
    val valueWhenBIsNull = c.notNullValue()
    storeValue(valueWhenBIsNull)
    valueWhenBIsNull   // Valor por defecto del operador Elvis
}
```

## 1.13. El operador `!!`

Si estás **completamente seguro** de que una variable anulable no es `null`, el operador **`!!`** evita la comprobación de Kotlin. Si resulta que sí era `null`, se lanza `NullPointerException`.

```kotlin
var nullableName: String? = "Morty Smith"
println(nullableName!!.uppercase())
```

::: warning-box Evita `!!`
Este operador suele aparecer en código migrado de Java a Kotlin. La filosofía de Kotlin es **contraria** a su uso.
:::

## 1.14. Expresiones regulares

Es muy habitual comprobar que los datos que introduce el usuario cumplen ciertas reglas. Lo más básico es comprobar si ha escrito algo, con métodos como `isNotBlank()`, `isNotEmpty()`, `isNullOrBlank()` o `isNullOrEmpty()`:

![Métodos de comprobación de cadenas sugeridos por el IDE](/img/contenidos/up02/up02-1-p50-1.webp)

Cuando eso no basta, se usan **expresiones regulares**: patrones de caracteres que permiten comprobar si una cadena se ajusta a ellos. Por ejemplo, que un nombre no tenga números, que tenga una longitud concreta, que siga un orden (un DNI: 8 números y 1 letra) o que sea un correo electrónico. Existen en casi todos los lenguajes, así que merece la pena dominarlas.

En Kotlin se crean con la clase **`Regex`**, a la que se pueden pasar modificadores:

```kotlin
val checkDNI = Regex("expresión_regular")
val check = Regex("expresión_regular", RegexOption.IGNORE_CASE)
```

### Guía rápida

| Agrupación | Ejemplo | Significado |
|:---:|:---:|:---|
| `[ ]` | `[abc]` | Cualquiera de los caracteres indicados |
| `[^ ]` | `[^abc]` | Cualquier carácter salvo los indicados |
| `[ - ]` | `[0-9]` | Cualquier carácter del rango |
| `[^ - ]` | `[^A-B]` | Cualquier carácter fuera del rango |
| `( \| )` | `(x\|y)` | Una de las alternativas |

| Inicio y fin | Ejemplo | Significado |
|:---:|:---:|:---|
| `^` | `^hola` | Empieza por «hola» |
| `$` | `hola$` | Acaba en «hola» |
| `^ $` | `^hola$` | Es exactamente «hola» |

| Cantidad | Ejemplo | Significado |
|:---:|:---:|:---|
| `{n}` | `a{3}` | Exactamente 3 «a» seguidas |
| `{n,}` | `a{3,}` | 3 o más «a» seguidas |
| `{n,m}` | `a{3,5}` | Entre 3 y 5 «a» seguidas |
| `*` | `a*` | 0 o más «a» (como `a{0,}`) |
| `+` | `a+` | 1 o más «a» (como `a{1,}`) |
| `?` | `a?` | 0 o 1 «a» (como `a{0,1}`) |

| Clases | Significado |
|:---:|:---|
| `\s` | Un espacio en blanco |
| `\S` | Cualquier carácter que no sea espacio en blanco |
| `\w` | Un carácter de palabra: letra, dígito o `_` |
| `\W` | Cualquier carácter que no sea de palabra (símbolos) |
| `\d` | Un dígito, como `[0-9]` |
| `\D` | Cualquier carácter que no sea un dígito, como `[^0-9]` |

::: warning-box Barras invertidas en Kotlin
Dentro de una cadena normal de Kotlin la barra invertida se escapa: `\d` se escribe `"\\d"`. Con una *raw string* no hace falta: `"""\d"""`.
:::

Ejemplos:

```kotlin
"[a-z]{4}"                         // contiene 4 minúsculas seguidas
"[a-zA-Z0-9]{8}"                   // contiene 8 letras y/o números seguidos
"^\\d{7,8}[A-Za-z]$"               // DNI: 7 u 8 dígitos seguidos de 1 letra
"^[a-z_.]+@[a-z]+\\.[a-z]{2,3}$"   // patrón sencillo para un correo electrónico
```

### Comprobar una expresión regular

Se pueden usar métodos de `String` o de `Regex`:

```kotlin
val name = "Rick"
// Empieza con mayúscula y le siguen al menos dos minúsculas
val checkName = Regex("^[A-Z][a-z]{2,}$")
// Comprobación con String
println(name.contains(checkName))
// Comprobación con Regex
println(checkName.matches(name))
```

`Regex` tiene muchos más métodos: `replace`, `find`, `findAll`…

::: tip-box Herramientas
- Chuleta de expresiones regulares: <https://quickref.me/regex>
- Probar expresiones regulares en línea: <https://regex101.com/>

![Prueba de una expresión regular en regex101](/img/contenidos/up02/up02-1-p55-1.webp)
:::

## 1.15. Excepciones

Las excepciones se capturan con bloques `try`–`catch`, igual que en Java:

```kotlin
try {
    var number: Int = readln().toInt()
} catch (e: Exception) {
    println("Error: ${e.message}")
}
```

Y también se pueden lanzar con `throw`:

```kotlin
val number: Int
try {
    println("Introduce un número entre 1 y 100:")
    number = readln().toInt()
    if (number < 1 || number > 100) throw Exception("Número fuera de rango")
} catch (e: NumberFormatException) {
    println("Error: No se ha introducido un número")
} catch (e: Exception) {
    println("Error: ${e.message}")
}
```
