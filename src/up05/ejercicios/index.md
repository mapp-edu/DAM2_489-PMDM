---
layout: doc
title: Actividades UP05
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP05 — Navegación y temas

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

## Prácticas guiadas (no evaluables)

Estas prácticas consisten en **estudiar los proyectos de ejemplo** que se facilitan en el aula virtual: descárgalos, ejecútalos en el emulador y localiza en el código cada concepto de los apuntes.

### Actividad 1 — Estudio de la aplicación de navegación {#actividad-1}

Analiza la aplicación de ejemplo del [apartado 1](../contenidos/01-navegacion): identifica las rutas de `Routes.kt`, el `backStack`, cada `entry` del `NavDisplay` y las lambdas que recibe cada pantalla. Comprueba qué ocurre con la pila al pulsar *¡Comenzar!* y *Reiniciar estado de la aplicación*.

### Actividad 2 — Dragon Ball adaptativo {#actividad-2}

Estudia la versión con **diseño adaptativo** de la aplicación Dragon Ball: cómo obtiene la clase de tamaño de la ventana y cómo decide si muestra una columna (lista y detalle en pantallas distintas) o dos (maestro-detalle). Pruébala en vertical y en horizontal.

### Actividad 3 — Estudio de la Splash Screen {#actividad-3}

Estudia el proyecto de ejemplo que combina la **Splash Screen por defecto** (XML) y la **Splash Screen con Jetpack Compose**, junto con las **animaciones entre pantallas** del `NavDisplay`.

## Actividad 4 — Navegando {#actividad-4}

Crea un proyecto llamado **RickMortyCardsTuNombre**. Usando todo lo visto hasta ahora, incluida la navegación, la aplicación tendrá:

**Splash Screen** con Jetpack Compose: una **foto tuya** tipo carné (actual) en el centro y, debajo, tu **nombre y apellido**. Debe durar **3 segundos**.

**Onboarding** con 3 pantallas (no es necesario que usen `Scaffold`):

1. El **logo** de Rick y Morty y, debajo, la **foto del portal** con los personajes. En la parte inferior, un botón **Saltar** que lleve a la 3.ª pantalla y otro para pasar a la **siguiente**.
2. El texto *«Haz tus elecciones y pelea»* y las imágenes de las **3 cartas**. En la parte inferior, un botón para pasar a la **siguiente**.
3. Arriba, un botón para **volver a iniciar** el Onboarding. Un **campo de texto** para el nombre de usuario y un botón para pasar a la aplicación. Solo se podrá pulsar si se ha introducido un nombre de usuario (**solo letras**). No es necesario guardar el nombre ni pasarlo a la siguiente pantalla.

**Aplicación**: 2 pantallas con un `Scaffold` que tenga en la `TopBar` el **logo** de Rick y Morty.

- **Pantalla principal**: la imagen del **portal** y un botón *«Comenzar partida»* que navegue a la pantalla de juego.
- **Pantalla de juego**: en la `TopBar`, una **flecha** a la izquierda para volver a la principal. En el cuerpo, el texto *«Paz entre los mundos»* y la imagen con ese nombre.

::: warning-box Gestiona la pila
Al pulsar atrás **no deben volver a aparecer** ni la Splash Screen ni el Onboarding.
:::

### Imágenes de partida

| Archivo | Uso |
|:---|:---|
| [rymlogo.webp](/descargas/up05/rymlogo.webp) | Logo de Rick y Morty. |
| [rymportal.webp](/descargas/up05/rymportal.webp) | Portal con los personajes (Onboarding 1). |
| [card1.webp](/descargas/up05/card1.webp) · [card2.webp](/descargas/up05/card2.webp) · [card3.webp](/descargas/up05/card3.webp) | Las 3 cartas (Onboarding 2). |
| [portal.jpg](/descargas/up05/portal.jpg) | Portal de la pantalla principal. |
| [pazentremundos.webp](/descargas/up05/pazentremundos.webp) | Imagen de la pantalla de juego. |

## Actividad 5 — Coloreando {#actividad-5}

Modifica la aplicación **Navegando**:

1. Entra en [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/) y cambia los **4 colores base** (*Primary*, *Secondary*, *Tertiary* y *Neutral*) desde el menú lateral izquierdo.
2. **Exporta** la paleta para **Jetpack Compose**.
3. Añade al proyecto el código de los archivos exportados.
4. Modifica los componentes para que usen los colores del tema (`MaterialTheme.colorScheme.xxxxx`).

## Entrega

| Actividad | Qué se entrega |
|:---|:---|
| **Actividad 4** | Un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques las funcionalidades más importantes y la ejecución, y el **proyecto comprimido**: detén la aplicación, ejecuta **Build → Clean Project**, cierra Android Studio y comprime **la carpeta** del proyecto en un `.zip`. |
| **Actividad 5** | Un **vídeo** en el que se vea la aplicación con los colores nuevos. |

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.a** Genera la estructura de clases necesaria<br>*Paquetes `navigation` y `screens`, `Routes.kt`* | Navegación mezclada con las pantallas o sin rutas | Rutas centralizadas y pantallas que reciben lambdas | Explica por qué las pantallas no conocen la navegación y cómo se pasan los datos | Estructura limpia y reutilizable (Scaffold propio, componentes compartidos) |
| **RA2.b** Utiliza las clases de interfaz y navegación<br>*Splash, Onboarding, `NavDisplay`* | Faltan pantallas o la navegación no funciona | Todas las pantallas y la navegación funcionan | La pila se gestiona correctamente: atrás no vuelve a la Splash ni al Onboarding; *Saltar* y *Volver a iniciar* funcionan | Transiciones animadas y comportamiento coherente en toda la app |
| **RA2.g** Prueba la interacción en el emulador<br>*Validación y casos límite* | El nombre de usuario no se valida | El botón solo se activa con un nombre de solo letras | Se prueban y explican los casos límite (giro de pantalla, botón atrás del sistema) | Experiencia fluida, con avisos claros al usuario |
| **RA3.f** Crea y controla animaciones / temas<br>*Actividad 5* | No se aplica el tema exportado | El tema exportado se aplica y los componentes usan `MaterialTheme.colorScheme` | No quedan colores *hardcoded* y se justifica la elección de la paleta | Paleta coherente y accesible en modo claro y oscuro |
| **RA2.i** Documenta el desarrollo<br>*Vídeos y recursos* | Vídeo ausente o textos *hardcoded* | Vídeos dentro de los límites; recursos centralizados | Los vídeos justifican las decisiones técnicas | Vídeos claros y bien estructurados; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
