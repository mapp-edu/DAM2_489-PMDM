---
layout: doc
title: "Codelab: tu primera app Android"
sidebar: true
outline: [2, 3]
aside: true
---

# Cómo crear tu primera app para Android
::: info-box Acerca de este codelab
Adaptación al castellano del codelab oficial de Google *Cómo crear tu primera app para Android* (Google Developers Training team, actualizado el 30 de marzo de 2026). [Ver el original](https://developer.android.com/codelabs/basic-android-kotlin-compose-first-app?hl=es-419).
:::
## 1. Antes de comenzar
Instala [Android Studio](https://developer.android.com/studio?hl=es-419) en tu ordenador si aún no lo hiciste. Comprueba que tu computadora cumpla con los requisitos del sistema necesarios para ejecutar Android Studio (que se encuentra en la parte inferior de la página de descarga). Si necesitas instrucciones más detalladas sobre el proceso de configuración, consulta el codelab [Cómo descargar e instalar Android Studio](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-2?hl=es-419).

En este codelab, crearás tu primera app para Android con una plantilla de proyecto que ofrece Android Studio. Puedes usar Kotlin y Jetpack Compose para personalizar tu app. Ten en cuenta que Android Studio se actualiza y, a veces, la IU cambia, por lo que no hay problema si tu Android Studio se ve un poco diferente al de las capturas de pantalla de este codelab.

### Requisitos previos

- Conocimientos básicos de Kotlin

### Requisitos

- La versión más reciente de Android Studio

### Qué aprenderás

- [x] Cómo crear una app para Android con Android Studio
- [x] Cómo ejecutar apps con la herramienta de vista previa en Android Studio
- [x] Cómo actualizar texto con Kotlin
- [x] Cómo actualizar una interfaz de usuario (IU) con Jetpack Compose
- [x] Cómo obtener una vista previa de tu app con la herramienta de vista previa en Jetpack Compose

### Qué compilarás

- Una app que te permite personalizar una presentación

Así se verá la app cuando completes este codelab (excepto que se personalizará con tu nombre):

![Resultado final](/img/ejercicios/13957184d295b16f_856.png)

### Requisitos

- Un ordenador que tenga Android Studio instalado

## 2. Cómo crear un proyecto con la plantilla

En este codelab, crearás una app para Android con la plantilla de proyecto **Empty Activity**, que proporciona Android Studio.

Para crear un proyecto en Android Studio, haz lo siguiente:

1. Haz doble clic en el ícono de Android Studio para iniciarlo.
![Icono Android Studio](/img/ejercicios/4853d32c0c91ae24_856.png)

2. En el diálogo **Welcome to Android Studio**, haz clic en la opción **New Project**.
![New Project](/img/ejercicios/687a108b17ba9b20_856.png)

Se abrirá la ventana **New Project** con una lista de plantillas que ofrece Android Studio.
![Plantillas](/img/ejercicios/2411487f7f8d88c0_856.png)

En Android Studio, una plantilla de proyecto es un proyecto de Android que proporciona el plano para un tipo de app determinado. Las plantillas crean la estructura del proyecto y los archivos necesarios de modo que Android Studio compile tu proyecto. La plantilla que elijas te brindará un código de partida para que puedas avanzar más rápido.

3. Asegúrate de que la pestaña **Phone and Tablet** esté seleccionada.
4. Haz clic en la plantilla **Empty Activity** para armar tu proyecto con ella. La plantilla **Empty Activity** es la que permite crear un proyecto simple que puedes usar para compilar una app de Compose. Tiene una sola pantalla y muestra el texto `Hello Android!`.
5. Haz clic en **Siguiente**. Se abrirá el diálogo **New Project**. Este tiene algunos campos para configurar el proyecto.
6. Configura tu proyecto como se indica a continuación:

El campo **Name** se usa a fin de ingresar el nombre de tu proyecto. Para este tipo de codelab ingresa "Greeting Card".

Deja el campo **Package name** tal como está. Así se organizarán los archivos en la estructura de archivos. En este caso, el nombre del paquete será `com.example.greetingcard`.

Deja el campo **Save location** tal como está. Este contiene la ubicación en la que se guardan todos los archivos relacionados con tu proyecto. Toma nota de esa ubicación para encontrar tus archivos.

Selecciona **API 24: Android 7.0 (Nougat)** del menú en el campo **Minimum SDK**. [Minimum SDK](https://developer.android.com/guide/topics/manifest/uses-sdk-element?hl=es-419) indica la versión mínima de Android en la que se puede ejecutar tu app.
![Campos](/img/ejercicios/4b0786fccfc883fe_856.png)

7. Haz clic en **Finish**. Ese proceso puede tardar un poco, así que aprovecha para disfrutar de un rico té. Mientras se configura Android Studio, un mensaje y una barra de progreso indicarán si aún se está configurando tu proyecto. Es posible que se vea de este modo:
![En esta imagen, se muestra una barra de progreso que gira y el texto indica](/img/ejercicios/68405342ab13c821_856.png) 

Un mensaje similar a este te informará cuando se cree la configuración del proyecto.
![En esta imagen, se muestra un mensaje de sincronización de Gradle que indica](/img/ejercicios/3558f61a535db5d1_856.png) 

8. Es posible que veas el panel **What's New** que contenga actualizaciones sobre funciones nuevas en Android Studio. Ciérralo por ahora.
![What's New](/img/ejercicios/c4b2b7748563ebb7_856.png)

9. En la esquina superior derecha de Android Studio, haz clic en **Split**. Esto te permitirá ver tanto el código como el diseño. También puedes hacer clic en **Code** para ver solo el código, o bien en **Design** para ver solo el diseño.
![Split](/img/ejercicios/b17a701425679ff1_856.png)

Después de presionar **Split**, deberías ver tres áreas:
![Workarea](/img/ejercicios/c24906abb54b261a_856.png)

- En la vista **Project** (1), se muestran los archivos y las carpetas de tu proyecto.
- En la vista **Code** (2), puedes editar el código.
- En la vista **Design** (3), se muestra una vista previa de cómo se ve tu app.

En la vista **Design**, podrías ver un panel en blanco con este texto:
![El texto en este panel indica](/img/ejercicios/38c4a624cae42640_856.png)

10. Haz clic en **Build & Refresh**. Es posible que la compilación tarde un poco en completarse, pero cuando finalice, la vista previa mostrará un cuadro de texto que indicará **"Hello Android!"**. La actividad Empty Compose contiene todo el código necesario para crear esta app.
![GreetingPreview](/img/ejercicios/a86077be9d06a909_856.png)

## 3. Busca archivos del proyecto
En esta sección, explorarás Android Studio a medida que te familiarizas con la estructura de archivos.

1. En Android Studio, consulta la pestaña **Project**. En la pestaña **Project**, se muestran los archivos y las carpetas de tu proyecto. Cuando estabas configurando tu proyecto, el nombre de paquete era **com.example.tarjetafelicitacion**. Puedes ver ese paquete aquí en la pestaña **Project**. En esencia, un paquete es una carpeta en la que se encuentra el código. Android Studio organiza el proyecto en una estructura de directorios compuesta por un conjunto de paquetes.

2. Si es necesario, selecciona Android en el menú desplegable de la pestaña Project.
<!-- TODO imagen pendiente: 52051aa2a3038b89_856.png («Android Project») -->

Esta es la organización y vista estándar de los archivos que usas. Resulta útil cuando escribes código para tu proyecto, ya que puedes acceder con facilidad a los archivos en los que trabajarás en tu app. Sin embargo, si accedes a los archivos desde un navegador, como Finder o Windows Explorer, la organización de la jerarquía de archivos es muy distinta.

3. Selecciona **Project Source Files** en el menú desplegable. Ahora puedes navegar entre los archivos del mismo modo que lo harías con cualquier navegador.
<!-- TODO imagen pendiente: 84dc993206449d28_856.png («Project Source Files») -->

Selecciona **Android** nuevamente para volver a la vista anterior. En este curso, usarás la vista de **Android**. Si la estructura de archivos se ve extraña, asegúrate de que sigues en la vista de **Android**.

## 4. Cómo actualizar el texto
Ahora que conoces Android Studio, es hora de comenzar a crear tu tarjeta de presentación.

Mira la vista **Code** del archivo `MainActivity.kt`. Observa que hay algunas funciones generadas automáticamente en este código, en particular, las funciones `onCreate()` y `setContent()`.

::: info Nota

Recuerda que una función es un segmento de un programa que realiza una tarea específica.

:::

```kotlin
	class MainActivity : ComponentActivity() {
		override fun onCreate(savedInstanceState: Bundle?) {
			super.onCreate(savedInstanceState)
			setContent {
				GreetingCardTheme {
					// A surface container using the 'background' color from the theme
					Surface(
						modifier = Modifier.fillMaxSize(),
						color = MaterialTheme.colorScheme.background
					) {
						Greeting("Android")
					}
				}
			}
		}
	}
```

La función `onCreate()` es el punto de entrada a esta app para Android y llama a otras funciones para compilar la interfaz de usuario. En los programas de Kotlin, la función `main()` es el punto de entrada o de partida de la ejecución. En las apps para Android, la función `onCreate()` realiza esa tarea.

La función `setContent()` dentro de la función `onCreate()` se usa para definir el diseño a través de funciones de componibilidad. Se puede llamar a todas las funciones marcadas con la anotación `@Composable` desde la función `setContent()` o desde otras de componibilidad. La anotación le indica al compilador de Kotlin que Jetpack Compose usa esta función para generar la IU.

::: info Nota

El compilador toma el código Kotlin que escribiste, lo analiza línea por línea y lo traduce a algo que la computadora puede comprender. Este proceso se denomina compilación de código.

:::

A continuación, observa la función `Greeting()`. La función `Greeting()` es una que corresponde a la componibilidad. Observa la anotación `@Composable` que se encuentra sobre ella. Esta función de componibilidad toma alguna entrada y genera lo que se muestra en la pantalla.

```kotlin
	@Composable
	fun Greeting(name: String, modifier: Modifier = Modifier) {
		Text(
			text = "Hello $name!",
			modifier = modifier
		)
	}
```

Ya aprendiste sobre las funciones (si necesitas un repaso, consulta el codelab [Cómo crear y usar funciones en Kotlin](https://developer.android.com/codelabs/basic-android-kotlin-compose-functions?hl=es-419)), pero existen algunas diferencias con las funciones de componibilidad.
![Funciones de componibilidad](/img/ejercicios/178c1b8d480aefe2_856.png)

- 1: Antes de la función, se agrega la anotación `@Composable`.
- 2:Los nombres de las funciones `@Composable` llevan mayúscula.
- 3:Las funciones `@Composable` no pueden mostrar nada.

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
```

Por el momento, la función `Greeting()` toma un nombre y muestra el saludo `Hello` dirigido a esa persona.

1. Actualiza la función `Greeting()` para presentarte en lugar de mostrar "Hello":

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hi, my name is $name!",
        modifier = modifier
    )
}
```

2. Android debería actualizar la vista previa automáticamente.
![Actualización](/img/ejercicios/178c1b8d480aefe2_856.png)

¡Excelente! Cambiaste el texto, pero te presenta como Android, un nombre que probablemente no sea el tuyo. A continuación, personalizarás la presentación con tu nombre.

La increíble función `GreetingPreview()` te permite ver el aspecto de la función de componibilidad sin tener que compilar la app por completo. Para habilitar la vista previa de una función de componibilidad, debes agregar una anotación `@Composable` y `@Preview`. La anotación `@Preview` le indica a Android Studio que esta función de componibilidad debe mostrarse en la vista de diseño de este archivo.

Como puedes ver, la anotación `@Preview` toma un parámetro llamado `showBackground`. Si estableces `showBackground` como **verdadero**, se agregará un fondo a la vista previa del elemento componible.

Como Android Studio, usa un tema claro de forma predeterminada para el editor, puede resultar difícil ver la diferencia entre `showBackground = true` y `showBackground = false`. Sin embargo, este es un ejemplo de cómo luce la diferencia. Observa el fondo blanco en la imagen cuando se establece como true.
![showBackground = true](/img/ejercicios/239d7fabe065588_856.png)

![showBackground = false](/img/ejercicios/3ce153efce1dadd3_856.png)

3. Actualiza la función `GreetingPreview()` con tu nombre. Luego, vuelve a compilar la app y revisa tu tarjeta de presentación personalizada.

```kotlin
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Meghan")
    }
}
```
![Resultado final](/img/ejercicios/9703ef244f8ee16c_856.png)

## 5. Cómo cambiar el color del fondo

Ahora tienes el texto de presentación, pero es un poco aburrido. En esta sección, aprenderás a cambiar el color de fondo.

Si deseas establecer un color de fondo diferente para la presentación, deberás envolver el texto en una `Surface`. Una `Surface` es un contenedor que representa una sección de la IU en la que puedes modificar el aspecto, como el borde o el color de fondo.

1. Para rodear el texto con una `Surface`, destaca la línea de texto, presiona (`Alt+Enter` en Windows o `Option+Enter` en Mac) y, luego, selecciona **Surround with widget**.
![Surround with widget](/img/ejercicios/220f9c42667a9595_856.png)

2. Selecciona **Surround with Container**.
![Surround with container](/img/ejercicios/f5258fe61ffc94fb_856.png)

El contenedor predeterminado que te dará es `Box`, pero puedes cambiarlo por otro tipo de contenedor. Más adelante en este curso, aprenderás sobre el diseño `Box`.
![Box](/img/ejercicios/85e695291c03dcfc_856.png)

3. Borra `Box` y escribe `Surface()` en su lugar.

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface() {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier
        )
    }
}
```

4. Al contenedor `Surface` agrega un parámetro `color` y establécelo como `Color`.

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color) {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier
        )
    }
}
```

5. Cuando escribes `Color`, puedes notar que la palabra estará en color rojo, lo que significa que Android Studio no puede resolverlo. Para resolver este problema, desplázate hasta la parte superior del archivo, donde aparece la opción de importar, y presiona los tres botones.
![import](/img/ejercicios/80b6219d24f04365_856.png)

6. Agrega esta sentencia al final de la lista de importaciones.

```kotlin
import androidx.compose.ui.graphics.Color
```

La lista completa de importaciones se verá de la siguiente manera:

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingcard.ui.theme.GreetingCardTheme
import androidx.compose.ui.graphics.Color
```

En tu código, la práctica recomendada es mantener las importaciones en orden alfabético y quitar las importaciones sin uso. Para ello, presiona **Help** en la barra de herramientas superior, escribe **Optimize Imports** y haz clic en **Optimize Imports**.
![Optimize Imports](/img/ejercicios/92241239038c774a_856.png)

Puedes abrir **Optimize Imports** directamente desde el menú: **Code > Optimize Imports**. Usar la opción de búsqueda de Help te ayudará a encontrar un elemento de menú si no recuerdas su ubicación.

Ahora, la lista completa de importaciones se verá de la siguiente manera:

```kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingcard.ui.theme.GreetingCardTheme
```

8. Observa que la palabra Color que escribiste en los paréntesis de Surface cambió de roja a subrayada en rojo. Para solucionar ese problema, agrega un punto después de la palabra. Verás una ventana emergente con diferentes opciones de colores.
Esta es una de las funciones geniales de Android Studio; es ingeniosa y te ayudará cuando sea posible. En este caso, reconoce que quieres especificar un color, así que te sugerirá diferentes colores.
![Colors](/img/ejercicios/3a709cb72da0f83d_856.png)

9. Elige un color para la plataforma. Este codelab usa **Cyan**, pero puedes elegir el que prefieras.

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   Surface(color = Color.Cyan) {
       Text(
           text = "Hi, my name is $name!",
           modifier = modifier
       )
   }
}
```

10. Observa la vista previa actualizada.
![Vista actualizada](/img/ejercicios/217a09ca55b503f8_856.png)

## 6. Cómo agregar padding
Ahora tu texto tiene un color de fondo; a continuación, agregarás un poco de espacio (padding) alrededor del texto.

Se usa un `Modifier` para aumentar o decorar un elemento componible. Un modificador que puedes usar es el modificador `padding`, que agrega espacio alrededor del elemento (en este caso, alrededor del texto). Esto se logra con la función `Modifier.padding()`.

Cada elemento componible debe tener un parámetro opcional del tipo `Modifier`. Debe ser el primer parámetro opcional.

1. Agrega padding a `modifier` con un tamaño de `24.dp`.

::: info Nota

Obtendrás más información sobre los píxeles independientes de la densidad (dp) en el siguiente artículo [Diseño: Material Design 3](https://m3.material.io/foundations/layout/understanding-layout/spacing#abccd6ce-1092-4ad0-9351-de75aeae0edf).

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}
```

2. Agrega estas importaciones a la sección de sentencias import.

Asegúrate de usar **Optimize Imports** para organizar alfabéticamente las importaciones nuevas.

```kotlin
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
```

![Felicitaciones](/img/ejercicios/2c09be85535277e9_856.png)

¡Felicitaciones! Compilaste tu primera app para Android en Compose. Este logro es bastante importante. Tómate un tiempo para experimentar con diferentes colores y texto, y personalízalos a tu gusto.

## 7. Cómo revisar el código de solución

### Fragmento de código para revisión

```kotlin
package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) {
        Text(
            text = "Hi, my name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Meghan")
    }
}
```

## 8. Conclusión

Aprendiste a usar Android Studio y creaste tu primera app para Android con Compose. ¡Bien hecho!

### Resumen

- Para crear un proyecto nuevo, abre Android Studio, haz clic en **New Project > Empty Activity > Next**, introduce un nombre para tu proyecto y, luego, establece sus parámetros de configuración.
- Si deseas saber cómo se verá tu app, usa el panel **Preview**.
- Las funciones de componibilidad son como las funciones normales, pero tienen algunas diferencias: los nombres de las funciones llevan mayúscula, debes agregar la anotación `@Composable` antes de la función y las funciones `@Composable` no pueden mostrar nada.
- Se usa un `Modifier` para aumentar o decorar el elemento componible.
