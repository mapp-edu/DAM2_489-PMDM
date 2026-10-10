---
layout: doc
title: 3. Programación orientada a objetos
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 3. Programación orientada a objetos

Kotlin busca ser conciso, y donde más se nota es al escribir clases. En este apartado verás tanto la forma tradicional (al estilo de Java) como la forma recomendada en Kotlin.

- Las clases se crean con la palabra reservada **`class`**, como en Java.
- Aunque Kotlin permite definir varias clases en un mismo archivo `.kt`, **no es recomendable**: cada archivo debe contener una única clase y llamarse como ella.
- Todas las clases heredan de **`Any`** (en Java, `Object`), que define tres métodos que se pueden usar directamente o sobrescribir:
  - `equals`: indica si un objeto es igual a otro.
  - `hashCode`: devuelve el código *hash* del objeto.
  - `toString`: devuelve la representación del objeto como `String`.

## 3.1. Definir una clase

```kotlin
class Person {
}
```

Una clase sin cuerpo se puede definir incluso así, y aun así se pueden crear objetos de ella:

```kotlin
class Person

val morty = Person()
```

### Al estilo de Java

```kotlin
class Product {
    var name: String
        get() {
            return field
        }
        set(name: String) {
            field = name
        }
    var price: Double
        get() {
            return field
        }
        set(price: Double) {
            field = price
        }

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }
}
```

- La estructura es parecida a la de Java, pero los *getters* y *setters* se definen **justo después de cada propiedad**. Dentro de ellos, **`field`** representa el valor almacenado en la propiedad.
- El constructor se define con la palabra **`constructor`**.
- Su uso también es como en Java (sin `new`):

```kotlin
val ps5 = Product("PS5", 499.99)
ps5.name = "PlayStation 5"
println(ps5.name)
```

::: info-box Modificadores de visibilidad
Si no se indica lo contrario, clases, propiedades y métodos son **públicos** (`public`). Los modificadores disponibles son:

| Modificador | Visible desde |
|:---|:---|
| `public` | Cualquier parte (por defecto) |
| `private` | La propia clase (o el propio archivo, si se declara a nivel superior) |
| `protected` | La propia clase y sus subclases |
| `internal` | El mismo **módulo** (por ejemplo, el módulo `app` de un proyecto) |
:::

## 3.2. Ahorrar código paso a paso

**1. *Getters* de una sola expresión.** Se pueden escribir en una línea:

```kotlin
class Product {
    var name: String
        get() = field
        set(name: String) {
            field = name
        }
    var price: Double
        get() = field
        set(price: Double) {
            field = price
        }

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }
}
```

**2. *Getters* y *setters* implícitos.** Si no hacen nada especial, Kotlin los añade automáticamente:

```kotlin
class Product {
    var name: String
    var price: Double

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }
}
```

**3. Constructor primario.** Las propiedades se declaran en la cabecera de la clase y el constructor pasa a ser implícito:

```kotlin
class Product(var name: String, var price: Double)
```

::: accent-box success ✨ Una sola línea
Con esta línea tienes la clase con su **constructor**, sus ***getters*** y sus ***setters***. Si no necesitas hacer nada especial en ellos, esta sintaxis ahorra muchísimo código. A este constructor se le llama **constructor primario**.
:::

## 3.3. Constructores secundarios

Los constructores que se incluyen en el cuerpo de la clase se llaman **constructores secundarios**. Se declaran con `constructor` y se pueden **sobrecargar**. Si una propiedad no se inicializa en algún constructor, debe tener un **valor por defecto**:

```kotlin
class Product {
    var name: String
    var price: Double = -1.0

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }

    constructor(name: String) {
        this.name = name
    }
}

val game = Product("GTA VI", 70.0)
val controller = Product("Dual Sense")
```

Si se combinan el constructor primario y constructores secundarios, estos deben llamar al primario con **`this`**:

::: tabs

== Primario con todas las propiedades

```kotlin
class Product(var name: String, var price: Double) {
    constructor(name: String) : this(name, -1.0)
}
```

== Primario con parte de las propiedades

```kotlin
class Product(var name: String) {
    var price: Double = -1.0

    constructor(name: String, price: Double) : this(name) {
        this.price = price
    }
}
```

:::

También puede **no haber constructores**; en ese caso las propiedades deben tener un valor asignado:

```kotlin
class Product {
    var name: String = ""
    var price: Double = -1.0
}

val game = Product()
game.name = "GTA VI"
println("Se vende ${game.name}")
```

## 3.4. Bloque `init`

Si usas el constructor primario pero necesitas hacer algo con las propiedades al crear el objeto, utiliza un bloque **`init`**:

```kotlin
class Product(var name: String, var price: Double) {
    init {
        name = name.uppercase()
    }
}

val product = Product("Nintendo Switch", 350.0)
println("Se vende ${product.name}")
// Mostrará: Se vende NINTENDO SWITCH
```

## 3.5. Funciones miembro (métodos)

```kotlin
class Product(var name: String, var price: Double) {
    fun finalPrice(percentage: Double) = price - price * percentage / 100
}
```

La misma función en su forma extendida:

```kotlin
class Product(var name: String, var price: Double) {
    fun finalPrice(percentage: Double): Double {
        return price - price * percentage / 100
    }
}
```

## 3.6. Miembros estáticos: `companion object`

Para definir propiedades y funciones **estáticas** se usa un **`companion object`** dentro de la clase. Todo lo que se declare en él es estático y se usa directamente con el nombre de la clase:

```kotlin
class Product(var name: String, var price: Double) {
    init {
        quantityOfProducts++
    }

    companion object {
        var quantityOfProducts = 0

        fun resetQuantity() {
            quantityOfProducts = 0
        }
    }
}

val switch = Product("Nintendo Switch OLED", 295.5)
val ps5 = Product("PlayStation 5", 450.0)

println(Product.quantityOfProducts)   // 2
Product.resetQuantity()
println(Product.quantityOfProducts)   // 0
```

## 3.7. Sobrescribir funciones heredadas

Si se sobrescribe una función heredada, hay que añadir **`override`**:

```kotlin
class Product(var name: String, var price: Double) {
    override fun toString(): String {
        return "$name: $price €"
    }
}
```

Se puede combinar todo lo anterior: constructor primario, bloque `init`, constructor secundario, *getters* y *setters* implícitos y sobrescritura de funciones heredadas:

```kotlin
class Product(var name: String) {
    var price: Double = -1.0

    init {
        name = name.uppercase()
    }

    constructor(name: String, price: Double) : this(name) {
        this.price = price
    }

    override fun toString(): String {
        return "$name: $price €"
    }
}
```

## 3.8. Visibilidad y encapsulación

Para cambiar la visibilidad de una propiedad basta con indicarlo delante de su declaración:

```kotlin
class Product(private var name: String) {
    private var price: Double = -1.0
    // …
}
```

::: warning-box Getters y setters implícitos
Los *getters* y *setters* implícitos tienen **la misma visibilidad que la propiedad**. Si la propiedad es `private`, desde fuera de la clase no se puede ni leer ni modificar:

```kotlin
val product = Product("Test")
product.name = "PS5"     // ❌ name es privada
product.price = 495.5    // ❌ price es privada
```
:::

Una solución es crear métodos públicos de acceso, aunque va **en contra de la filosofía de Kotlin** de ahorrar código:

```kotlin
class Product(private var name: String) {
    private var price: Double = -1.0

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
    // …
}
```

La forma idiomática es dejar la propiedad pública y hacer **privado solo el `set`**: se puede leer desde fuera, pero solo se puede modificar desde dentro de la clase (con una función, si hace falta):

```kotlin
class Product(name: String, price: Double) {
    var name: String = name
        private set
    var price: Double = price
        private set

    override fun toString(): String {
        return "$name: $price €"
    }
}
```

## 3.9. Uso de los objetos

Una vez declarada la clase, se usa como en Java: se accede a propiedades y funciones con la **notación de punto**:

```kotlin
val product = Product("PS5", 495.50)
println("${product.name}: ${product.finalPrice(10.0)} €")
```

## 3.10. `enum class`

Las `enum class` definen un **conjunto de constantes relacionadas**. Son una forma segura de trabajar con un conjunto cerrado de valores:

```kotlin
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}
```

```kotlin
val direction = Direction.EAST
println(direction)                    // EAST
println(direction.name)               // EAST
println(direction.ordinal)            // 3 (posición)

val color = Color.RED
println(color)                        // RED
println(color.name)                   // RED
println(color.rgb)                    // 16711680 (en decimal)
println(color.rgb.toHexString())      // 00ff0000 (en hexadecimal)
println(color.ordinal)                // 0 (posición)
```

## 3.11. `data class`

Las `data class` sirven para **modelar datos**: su finalidad es almacenarlos. Son clases muy sencillas que solo contienen propiedades y se usan mucho para intercambiar información con otras clases o con API externas. Se declaran con **`data`**:

```kotlin
data class Manga(val name: String, val type: String, val volumes: Int)
```

Kotlin les añade automáticamente:

- `equals()`: compara dos objetos por el valor de sus propiedades.
- `hashCode()`: código *hash* (se usa en la comparación anterior).
- `copy()`: hace una copia del objeto.
- `toString()`: genera una cadena legible con los datos del objeto.
- `component1()`, `component2()`…: acceso a cada propiedad en su orden de declaración.

```kotlin
val dragonBall = Manga("Dragon Ball", "Shonen", 42)
val deathNote = Manga("Death Note", "Shonen", 13)
val naruto = Manga("Naruto", "Shonen", 72)

println(dragonBall)
println(deathNote)
if (dragonBall == naruto) println("Son el mismo manga.")
else println("Son mangas diferentes.")
```

```txt
Manga(name=Dragon Ball, type=Shonen, volumes=42)
Manga(name=Death Note, type=Shonen, volumes=13)
Son mangas diferentes.
```

::: info-box Solo el constructor primario
Las propiedades que **no** están en el constructor primario no se benefician de estas funciones: por ejemplo, `toString()` solo muestra las del constructor primario.
:::

Con **`copy`** se copia el objeto y, a la vez, se pueden cambiar algunas propiedades:

```kotlin
val db = dragonBall.copy()
val dbz = dragonBall.copy(name = "DBZ")
```

### Desestructuración

Los objetos de una `data class` se pueden **desestructurar** para extraer sus propiedades a variables. Si no quieres alguno de los valores, pon `_` en su lugar; si los que sobran están al final, basta con no ponerlos:

```kotlin
val dragonBall = Manga("Dragon Ball", "Shonen", 42)
val (name, type, volumes) = dragonBall
println("Manga: $name ($type)")
println("Tomos: $volumes")

val (name) = dragonBall
val (name, type) = dragonBall
val (name, _, volumes) = dragonBall
```

```txt
Manga: Dragon Ball (Shonen)
Tomos: 42
```

::: tip-box `val` en las data class
Aunque se pueden declarar con `var`, lo recomendable es usar **`val`** en las propiedades de una `data class` y crear copias modificadas con `copy`. Lo agradecerás cuando trabajes con el estado de la interfaz en Jetpack Compose.
:::

## 3.12. Funciones de extensión

Kotlin permite **extender la funcionalidad de clases existentes**, del sistema o propias, **sin herencia**, aunque la clase sea final. Las funciones de extensión:

- Se definen fuera de la clase, así que solo se pueden usar en el **ámbito** en el que se definen.
- Se comportan como un método más de la clase y pueden usar sus propiedades y métodos **públicos** (`this` es el objeto sobre el que se llaman).
- Se definen como cualquier función, pero precedidas del nombre de la clase que extienden.

```kotlin
// Se añade una función a la clase Int
fun Int.isOdd(): Boolean {
    return this % 2 != 0
}

// Igual, en una línea
fun Int.isOdd() = this % 2 != 0
```

Con clases propias:

```kotlin
class Product(var name: String, var price: Double)

fun Product.sale(salePrice: Double): Double {
    return if (price > salePrice) salePrice else price
}
```

```kotlin
class Student(
    var name: String,
    var surname1: String,
    var surname2: String = "",
    var nia: Int,
    var email: String,
)

fun Student.completeName() = "$name $surname1 $surname2"
fun Student.nameWithMail() = "$name $surname1 ($email)"

fun main() {
    val student1 = Student(
        name = "Rick",
        surname1 = "Sanchez",
        nia = 123456,
        email = "rick@mail.com",
    )

    println("""|${student1.completeName()}
               |${student1.nameWithMail()}
               """.trimMargin())
}
```

Un uso típico es con **colecciones**, para aplicar una acción a todos sus elementos:

```kotlin
fun main() {
    val numbers = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(numbers.addition(2))   // [3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
}

fun MutableList<Int>.addition(quantity: Int): MutableList<Int> {
    val numbers = mutableListOf<Int>()
    for (i in this) numbers.add(i + quantity)
    return numbers
}
```

::: warning-box Extensión frente a miembro
- Una función de extensión **no puede sobrescribir** una función miembro: si tienen el mismo nombre y parámetros, **siempre se ejecuta la función miembro**. El IDE lo avisa con *Extension is shadowed by a member*.
- Sí puede **sobrecargarla** (mismo nombre, parámetros distintos).

![Aviso del IDE: la extensión queda oculta por una función miembro](/img/contenidos/up02/up02-3-p35-1.webp)
:::

## 3.13. Objetos *singleton*

Con **`object`** se crea un *singleton*: un objeto que es la **única instancia** de su clase.

```kotlin
object Author {
    var name = "Álex Torres"
    var company = "2 DAM"
    var date = Date()

    override fun toString(): String {
        return """$name ($company)
                 |$date""".trimMargin()
    }
}
```

Si se declaran a nivel superior en el archivo, se les pueden añadir funciones de extensión como a cualquier otra clase.

::: tip-box Práctica
En la [Actividad 5 — Funciones: agenda](../ejercicios/#actividad-5), modela los contactos con una `data class`.
:::

## 3.14. Herencia

En Kotlin **todas las clases son finales por defecto**: no se puede heredar de ellas si no se indica explícitamente con **`open`**.

```kotlin
open class Product(var name: String, var price: Double)

class Monitor(name: String, price: Double) : Product(name, price)
```

Con este código tienes:

- La clase `Product` con su constructor, *getters* y *setters*.
- La clase `Monitor`, cuyo constructor llama al de la clase madre y que hereda sus *getters* y *setters*.

Se pueden añadir propiedades y métodos nuevos:

```kotlin
open class Product(var name: String, var price: Double)

class Monitor(name: String, price: Double) : Product(name, price) {
    var size: Int = 0

    constructor(name: String, price: Double, size: Int) : this(name, price) {
        this.size = size
    }

    override fun toString(): String {
        return "$name ($size\"): $price €"
    }
}
```

El constructor primario de la clase hija **llama al constructor de la clase madre**, y el constructor secundario llama, mediante `this`, al constructor primario.

::: info-box `open` también en los miembros
Para que una subclase pueda sobrescribir una función o propiedad de la clase madre, ese miembro también debe declararse `open` (salvo los heredados de `Any`, como `toString`, que ya lo son).
:::

## 3.15. Clases abstractas

Se declaran con **`abstract`** y **no se pueden instanciar**. Sus miembros abstractos deben implementarse en las subclases con `override`:

```kotlin
abstract class Product(var name: String, var trademark: String) {
    abstract var price: Double
    abstract fun powerOn()
    abstract fun powerOff()

    fun information() {
        println("$name ($trademark): $price €")
    }
}

class Monitor(
    name: String,
    trademark: String,
    var color: String,
    override var price: Double,
) : Product(name, trademark) {
    override fun powerOn() {
        println("Encendido")
    }

    override fun powerOff() {
        println("Apagado")
    }
}
```
