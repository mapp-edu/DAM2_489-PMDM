---
layout: doc
title: Actividades UP03
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP03 — Introducción a Android

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

## Prácticas guiadas

### Práctica A — Crear el proyecto ClickCounter {#guiada-a}

1. Crea en Android Studio un proyecto llamado **ClickCounterTuNombre** con la plantilla *Empty Activity*, el paquete `com.tunombretuapellido.clickcounter` y el SDK mínimo API 28.
2. Ejecútalo en el emulador y comprueba que aparece el mensaje *Hello Android!*.

### Práctica B — Dispositivo virtual del curso {#guiada-b}

1. Elimina el dispositivo virtual que Android Studio crea por defecto.
2. Crea un dispositivo virtual **Pixel 9a**.
3. Ejecuta el proyecto ClickCounterTuNombre y comprueba que funciona en el nuevo dispositivo.

## Actividad 0 — Contador de clics {#actividad-0}

Recrea la aplicación **ClickCounter** de los apuntes ([apartado 2](../contenidos/02-primera-app-compose)) y complétala con lo visto en el [apartado 3](../contenidos/03-recursos-idiomas): textos centralizados en `strings.xml`, dimensiones en `dimens.xml` y la aplicación en inglés y español.

## Actividad 1 — Estadísticas {#actividad-1}

Crea un proyecto llamado **StatisticsTuNombre** que permita llevar la cuenta de **personas, patinetes, bicicletas y coches** que pasan por una calle.

- Cada contador tendrá botones para **sumar uno**, **restar uno** y **reiniciar**.
- Arriba del todo se mostrará el título *Estadísticas TuNombre*.
- Se mostrará el **total**, un botón para **reiniciar todos** los contadores y el **porcentaje** de cada elemento contado.
- Abajo, un botón **Randomize** pondrá un número aleatorio de 0 a 10 en cada uno de los cuatro contadores.

::: tip-box Pistas
- Igual que `Column` organiza en columna, **`Row`** organiza los elementos en fila.
- Para cambiar el color de un botón usa `ButtonDefaults.buttonColors` con la clase `Color`, ya sea con un color predefinido o con su valor hexadecimal `0xAARRGGBB`. Puedes guardar un color en una variable para reutilizarlo:

```kotlin
val orange = Color(0xFFFF9800)

Button(
    onClick = { /* TODO */ },
    colors = ButtonDefaults.buttonColors(
        containerColor = orange,
        contentColor = Color.DarkGray,
    )
) {
    Text(text = "Botón")
}
```

Al escribir un color hexadecimal, Android Studio lo muestra junto al número de línea; si haces clic sobre él, se abre el selector de color.
:::

Ejemplo de interfaz. **Tu aplicación debe ser diferente en colores y en la organización de los componentes**:

![Ejemplo de interfaz de la aplicación Estadísticas](/img/contenidos/up03/act3-p02-3.webp)

## Actividad 2 — Dice Game {#actividad-2}

Crea un proyecto llamado **DiceGameTuNombre** con un pequeño juego de dados.

1. Crea en el proyecto un paquete llamado `model` y añade en él una clase **`Dice`** con un método estático (en un `companion object`) que devuelva una tirada aleatoria de un dado de 6 caras.
2. La aplicación estará en **3 idiomas**: inglés, español y valenciano (catalán).
3. Arriba se mostrará el **título del juego** (invéntatelo) y un breve texto que explique cómo se juega; abajo, **tu nombre**.

En la zona central transcurre el juego:

- El jugador empieza con **20 puntos**, que se muestran y actualizan durante la partida. Nunca puede tener puntos negativos y puede seguir jugando mientras tenga más de 0.
- Se muestra el número de la **última tirada**; la partida empieza siempre con una tirada ya hecha.
- Hay **2 botones con flechas** (arriba y abajo) y **6 botones con los números del 1 al 6**.
- Al pulsar un botón:
  - **Flecha**: gasta **1 punto** y apuesta a que la siguiente tirada será **mayor** (flecha arriba) o **menor** (flecha abajo) que la anterior. Si acierta, gana **3 puntos**.
  - **Número**: gasta **3 puntos** y apuesta a que la siguiente tirada será ese número. Si acierta, gana **15 puntos**.
- Un botón permitirá **volver a empezar** cuando el jugador se quede sin puntos.

Puedes añadir lo que consideres conveniente, como un texto de *Game Over* o desactivar los botones al quedarse sin puntos.

## Actividad 3 — Basket 3x3 {#actividad-3}

Crea un proyecto llamado **Basket3x3TuNombre** para llevar el marcador de un partido de baloncesto 3x3.

- La aplicación estará **bloqueada en horizontal** (*landscape*) y tendrá como **icono una pelota de baloncesto**.
- La pantalla estará dividida en dos mitades, una por equipo. Cada mitad mostrará:
  - El **marcador** actual.
  - Las **faltas de equipo**, hasta un máximo de 7, con un botón para añadir falta y otro para quitarla (por si se marca por error). Al llegar a **7 faltas** el equipo entra en **bonus** (esa falta y las siguientes se castigan con 2 tiros libres) y se debe indicar de alguna manera.
  - Una zona por **jugador** con su nombre (*Jugador 1* a *Jugador 4*) y dos botones: anotar **1 punto** y anotar **2 puntos**. Cada anotación se suma al marcador.
- Un botón **reseteará** todo el marcador.
- Si un equipo llega a **21 puntos**, gana el partido: se indicará de alguna manera y se deshabilitarán todos los botones salvo el de resetear.

::: details Opcional (después de entregar)
Implementa un sistema para **deshacer** las últimas acciones del marcador.
:::

## Entrega

Se entregan las **actividades 1, 2 y 3**. Para cada una:

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques las partes más importantes del código y muestres la ejecución en el emulador. Si grabas con el móvil, debe estar completamente quieto.
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA1.f** Analiza la estructura de la aplicación<br>*Vídeo* | No explica el código o la explicación es errónea | Identifica `MainActivity`, los componentes y los recursos usados | Explica el papel de cada parte (Activity, Scaffold, estado, recursos) | Relaciona la estructura con el ciclo de vida y la recomposición |
| **RA2.a** Genera la estructura de clases necesaria<br>*Paquete `model`, componentes* | Todo el código en un único componente o no compila | Componentes `@Composable` propios y clase `Dice` en `model` | Divide la interfaz en componentes reutilizables y justifica la división | Estructura limpia y coherente entre las tres aplicaciones |
| **RA2.b** Utiliza las clases de interfaz y controles<br>*Layouts, botones, textos* | Faltan elementos de la interfaz o se superponen | Interfaz completa con `Column`, `Row`, `Button`, `Text`… y estado con `rememberSaveable` | Interfaz clara, adaptada y diferente al ejemplo; el estado se conserva al girar | Interfaz cuidada, consistente y accesible (contraste, tamaños en `dp`/`sp`) |
| **RA2.g** Prueba la interacción en el emulador<br>*Funcionalidad* | Las reglas del juego o del marcador fallan | Todas las reglas funcionan en el emulador | Se controlan los casos límite (puntos negativos, 7 faltas, 21 puntos) | Experiencia fluida, con avisos claros al usuario |
| **RA2.i** Documenta el desarrollo<br>*Recursos y vídeo* | Textos *hardcoded* o vídeo ausente | Cadenas en `strings.xml`, multiidioma e icono; vídeo dentro de los límites | El vídeo justifica las decisiones técnicas | Vídeo claro y bien estructurado; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
