---
layout: doc
title: 4. Animaciones
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 4. Animaciones

## 4.1. APIs de animación

Jetpack Compose dispone de una serie de [APIs para crear animaciones](https://developer.android.com/develop/ui/compose/animation/introduction) en los componentes de la interfaz. En la documentación hay un [diagrama para elegir la animación](https://developer.android.com/develop/ui/compose/animation/choose-api) que necesitas en cada caso. Aquí verás ejemplos de uso de las más habituales.

| API | Para qué sirve |
|:---|:---|
| `animate*AsState` | Animar un **valor** entre un estado inicial y uno final. |
| `AnimatedVisibility` | **Mostrar y ocultar** componentes. |
| `AnimatedContent` | Animar el cambio de **todo el contenido** de un componente. |
| `Crossfade` | Cambiar un contenido por otro **fundiéndolos**. |
| `Modifier.animateContentSize` | Animar el cambio de **tamaño** de un componente. |

## 4.2. animate*AsState

Crea una animación **entre dos valores**: Android se encarga de animar desde el valor inicial hasta el final. El `*` se sustituye por el tipo: `Int`, `Float`, `Color`, `Dp`, `Size`, `Offset`, `Rect`, `IntOffset` o `IntSize`.

::: tabs

== Color

```kotlin
var animateColor by rememberSaveable { mutableStateOf(false) }
val backgroundColor by animateColorAsState(if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF))
Button(onClick = { animateColor = !animateColor }) {
    Text(text = "Cambiar color")
}
Text(
    text = "Hola Rick!",
    fontWeight = FontWeight.Bold,
    modifier = Modifier.background(backgroundColor).padding(20.dp)
)
```

== Opacidad (Float)

```kotlin
var animateAlpha by rememberSaveable { mutableStateOf(true) }
val alpha: Float by animateFloatAsState(if (animateAlpha) 1f else 0.3f)
Button(onClick = { animateAlpha = !animateAlpha }) {
    Text(text = "Cambiar opacidad")
}
Box(
    modifier = Modifier
        .graphicsLayer(alpha = alpha)
        .fillMaxWidth()
        .background(Color(0xFFFFA020))
) {
    Text(
        text = "Hola Rick!",
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(20.dp)
    )
}
```

:::

## 4.3. AnimatedVisibility

Permite **ocultar y mostrar** componentes con una animación:

```kotlin
var visible by rememberSaveable { mutableStateOf(false) }
Button(onClick = {
    visible = !visible
}) {
    Text(text = if (visible) "Ocultar" else "Mostrar")
}
AnimatedVisibility(visible = visible) {
    Text(
        text = "Hola Rick!",
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(Color(0xFFFFA020))
            .padding(20.dp)
            .fillMaxWidth()
    )
}
```

En su definición se ve que la animación de **entrada** por defecto es **aparecer y expandir** (`fadeIn() + expandVertically()`) y la de **salida**, **desvanecer y contraer** (`fadeOut() + shrinkVertically()`):

```kotlin
@Composable
fun ColumnScope.AnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandVertically(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    label: String = "AnimatedVisibility",
    content: @Composable AnimatedVisibilityScope.() -> Unit
)
```

Con los parámetros **`enter`** y **`exit`** puedes configurarlas, combinando con `+` las siguientes:

| Entrada (`enter`) | Salida (`exit`) |
|:---|:---|
| `fadeIn` | `fadeOut` |
| `slideIn` | `slideOut` |
| `slideInHorizontally` | `slideOutHorizontally` |
| `slideInVertically` | `slideOutVertically` |
| `scaleIn` | `scaleOut` |
| `expandIn` | `shrinkOut` |
| `expandHorizontally` | `shrinkHorizontally` |
| `expandVertically` | `shrinkVertically` |

En la documentación puedes [ver un ejemplo de cada animación](https://developer.android.com/develop/ui/compose/animation/composables-modifiers#enter-exit-transition).

## 4.4. AnimatedContent

Anima **todo el contenido** de un componente cuando cambia su estado. Por defecto la animación es **aparecer y escalar**. En el ejemplo, el segundo `AnimatedContent` la cambia por un **desplazamiento vertical**, hacia arriba o hacia abajo según suba o baje la cuenta:

```kotlin
var times by rememberSaveable { mutableStateOf(0) }
Row {
    Button(onClick = { times++ }) {
        Text("+1")
    }
    Spacer(modifier = Modifier.width(10.dp))
    Button(onClick = { times-- }) {
        Text("-1")
    }
}

AnimatedContent(targetState = times, label = "") {
    Text(
        text = "Cuenta: $it",
        modifier = Modifier.padding(10.dp)
    )
}

AnimatedContent(
    targetState = times,
    // Personalizando la animación
    transitionSpec = {
        // targetState: nuevo valor de la variable de estado
        // initialState: valor anterior de la variable de estado
        if (targetState > initialState) {
            (slideInVertically { height -> height } + fadeIn())
                .togetherWith(slideOutVertically { height -> -height } + fadeOut())
        } else {
            (slideInVertically { height -> -height } + fadeIn())
                .togetherWith(slideOutVertically { height -> height } + fadeOut())
        }
    },
    label = ""
) { targetCount ->
    Text(
        text = "$targetCount",
        modifier = Modifier.padding(10.dp)
    )
}
```

## 4.5. Crossfade

Convierte el contenido de un componente en otro, **mezclándolos** durante la animación:

::: tabs

== Al pulsar el contenido

```kotlin
var visibleContent by rememberSaveable { mutableStateOf("Manga") }
Crossfade(
    targetState = visibleContent,
    label = "",
    modifier = Modifier.clickable {
        visibleContent = if (visibleContent == "Manga") "Anime" else "Manga"
    }
) { screen ->
    when (screen) {
        "Manga" -> Text("Mostrar Manga")
        "Anime" -> Text("Mostrar Anime")
    }
}
```

== Con botones

```kotlin
val sections = arrayOf("Anime", "Manga", "Otros")
var visibleContent by rememberSaveable { mutableStateOf(sections[0]) }
Row {
    for (section in sections) {
        Button(onClick = {
            visibleContent = section
        }) {
            Text(text = section)
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}
Crossfade(
    targetState = visibleContent,
    label = "",
) { screen ->
    when (screen) {
        "Manga" -> Text("Mostrar Manga")
        "Anime" -> Text("Mostrar Anime")
        "Otros" -> Text("Mostrar otros")
    }
}
```

:::

## 4.6. Modifier.animateContentSize

Android **anima el componente cuando cambia su tamaño** porque cambia su contenido:

```kotlin
var expanded by rememberSaveable { mutableStateOf(false) }
Column(
    modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Son goku")
        TextButton(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Menos." else "Leer más...")
        }
    }
    if (expanded) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Es el protagonista de la serie de manga y anime Dragon Ball." +
                " Fue creado por Akira Toriyama en 1984. Al comienzo de la" +
                " historia, Goku aparece como un niño que practica las artes" +
                " marciales y que posee una cola de mono y una fuerza sobrehumana," +
                " pero más adelante se revela que es un extraterrestre de la raza" +
                " ficticia saiyajin, y que su nombre original es Kakarotto",
            textAlign = TextAlign.Justify
        )
    }
}
```

::: info-box ¿Por qué no un raw string?
No se usa un *raw string* (`"""…"""`) porque se quiere que el texto se ajuste automáticamente; simplemente se ha partido en líneas con `+` para que se lea mejor.
:::

## 4.7. Personalizar las animaciones

Las animaciones tienen una configuración por defecto, pero se pueden personalizar con el parámetro **`animationSpec`**, disponible en:

- `animate*AsState`, `Crossfade` y `Modifier.animateContentSize`.
- `fadeX`, `slideX`, `scaleX`, `expandX` y `shrinkX` en los parámetros `enter` y `exit` de `AnimatedVisibility`.
- `fadeX`, `slideX`, `scaleX`, `expandX` y `shrinkX` en el parámetro `transitionSpec` de `AnimatedContent`.

Tienes todos los detalles en la documentación: [Personalizar animaciones](https://developer.android.com/develop/ui/compose/animation/customize?hl=es-419). Algunos ejemplos básicos:

::: tabs

== spring (física)

Animación basada en físicas: un **[muelle](https://developer.android.com/develop/ui/compose/animation/customize#spring)**.

```kotlin
var animateColor by rememberSaveable { mutableStateOf(false) }
val backgroundColor by animateColorAsState(
    targetValue = if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF),
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMedium
    ),
    label = ""
)
```

- `dampingRatio`: el **rebote** al llegar al valor destino.
- `stiffness`: la **velocidad** a la que se mueve hacia el valor destino.

== tween (duración)

Animación basada en **duración**: *tween* (de *between*).

```kotlin
var animateColor by rememberSaveable { mutableStateOf(false) }
val backgroundColor by animateColorAsState(
    targetValue = if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF),
    animationSpec = tween(
        durationMillis = 1000,
        easing = LinearEasing
    ),
    label = ""
)
```

- [`easing`](https://developer.android.com/develop/ui/compose/animation/customize#easing): **cómo** se pasa de un valor a otro (lineal, acelerando, frenando…).
- `delayMillis`: espera antes de empezar la animación.

== repeatable

Repetir una animación **un número de veces**:

```kotlin
val backgroundColor by animateColorAsState(
    targetValue = if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF),
    animationSpec = repeatable(
        iterations = 4,
        animation = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ),
        repeatMode = RepeatMode.Reverse
    ),
    label = ""
)
```

== infiniteRepeatable

Repetir una animación **indefinidamente**:

```kotlin
val backgroundColor by animateColorAsState(
    targetValue = if (animateColor) Color(0xFFFFA020) else Color(0xFF40C0FF),
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ),
        repeatMode = RepeatMode.Reverse
    ),
    label = ""
)
```

:::

## 4.8. Animaciones JSON con Lottie

La librería **[Lottie](https://airbnb.io/lottie/#/android-compose)** permite cargar en Android animaciones definidas en **JSON**. Son muy útiles, por ejemplo, para indicar que se está haciendo una tarea en segundo plano.

### Obtener la animación

1. En **[LottieFiles](https://lottiefiles.com/)** hay muchas animaciones. Para descargarlas debes **registrarte** y guardar la animación en tu **espacio de trabajo** (*Save*).
2. Desde el espacio de trabajo, descarga la versión **Lottie JSON**.
3. Cambia el nombre del archivo para que esté **en minúsculas, sin espacios ni símbolos**.
4. Añádelo al proyecto en la carpeta **`res/raw`** (créala si no existe).

### Dependencia

::: tabs

== libs.versions.toml

```toml [gradle/libs.versions.toml]
[versions]
# …
lottieCompose = "6.7.1"

[libraries]
# …
lottie-compose = { group = "com.airbnb.android", name = "lottie-compose", version.ref = "lottieCompose" }
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
dependencies {
    // …
    implementation(libs.lottie.compose)
}
```

== settings.gradle.kts

```kotlin [settings.gradle.kts]
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

:::

::: info-box ¿Hace falta el repositorio de snapshots?
Las versiones estables de Lottie están en `mavenCentral()`, así que el repositorio de *snapshots* solo es necesario si usas una versión de desarrollo.
:::

### Mostrar la animación

Se necesita un estado con **`rememberLottieComposition`**, que carga la animación desde `res/raw` (`R.raw`), y un componente **`LottieAnimation`** que la reciba:

```kotlin
val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rainbowcat))

Box(
    modifier = Modifier
        .fillMaxWidth(),
    contentAlignment = Alignment.Center
) {
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(300.dp)
    )
}
```

En la definición de `LottieAnimation` están todos sus parámetros. Entre otros:

| Parámetro | Para qué sirve |
|:---|:---|
| `isPlaying` | Si la animación se está reproduciendo. |
| `speed` | Velocidad de reproducción. |
| `iterations` | Veces que se reproduce (`LottieConstants.IterateForever` para infinitas). |
| `restartOnPlay`, `reverseOnRepeat`, `contentScale`, `alignment`… | Otras opciones de reproducción y presentación. |

::: tip-box Practica
Con lo visto en esta página puedes darle vida a la Splash Screen y al Onboarding de la [Actividad 4 — Navegando](../ejercicios/#actividad-4).
:::
