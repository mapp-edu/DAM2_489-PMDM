---
layout: doc
title: 4. Entorno de desarrollo
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 4. Entorno de desarrollo

## 4.1. Integrated Development Environment (IDE)

Para desarrollar aplicaciones nativas para Android han existido tres opciones principales:

- **App Inventor.** Entorno basado en un **lenguaje de bloques** similar a Scratch. Ofrece un nivel de abstracción muy alto.
- **Eclipse.** Primer IDE oficial para Android (con el *plugin* ADT), hoy abandonado.
- **Android Studio.** IDE oficial de Google, basado en IntelliJ IDEA, que reemplazó a Eclipse. Permite programar en Java y [Kotlin](https://kotlinlang.org/) y da acceso total a los recursos del dispositivo. **Es el que usaremos en el curso.**

Durante el curso necesitarás:

- El IDE **Android Studio**, que incluye el SDK de Android con las API y bibliotecas.
- El **controlador USB** del dispositivo real en el que harás las pruebas.
- Un **emulador** Android (el integrado en Android Studio u otro externo).

## 4.2. Android Studio

Consulta los [requisitos de Android Studio](https://developer.android.com/studio/install?hl=es-419) para Windows, macOS y Linux antes de instalarlo: la RAM, el espacio en disco y la virtualización por hardware son los puntos que más problemas suelen dar.

![Android Studio con la ventana «Acerca de» abierta](/img/contenidos/up01/up01-p42-1.webp)

Se puede desarrollar para Android desde cualquier sistema operativo. Windows suele ser el que menos problemas da al reconocer un **dispositivo real por USB** mediante **ADB** (*Android Debug Bridge*), imprescindible para probar en dispositivos reales. Si las pruebas en el dispositivo real fallan, siempre se puede recurrir a un emulador.

::: tip-box Práctica
Realiza la [Actividad 1 — Preparando el entorno de programación y pruebas](../ejercicios/01-instalacion-android-studio) y la [Actividad 2 — Hello there!](../ejercicios/02-hello-there).
:::

## 4.3. Emulador de Android

Android Studio incorpora un emulador en el que ejecutar las aplicaciones que estamos desarrollando. Permite crear **dispositivos virtuales** o **AVD** (*Android Virtual Device*) con las características que necesitemos:

- **Hardware**: memoria, tamaño y resolución de pantalla, densidad de píxeles…
- **Software**: versión de Android o nivel de API.

También permite elegir entre una lista de perfiles de dispositivos ya definidos e importar otros nuevos.

::: tip-box Práctica
Realiza la [Actividad 3 — Usando el emulador](../ejercicios/03-emulador).
:::

## 4.4. Probar en dispositivos reales

El emulador es una buena herramienta, pero la mejor opción es **probar las aplicaciones en dispositivos reales**, y a ser posible en varios con características distintas: versión de Android, tamaño de pantalla, cantidad de RAM…

Lo recomendable es combinar ambas cosas: probar en el emulador con distintos dispositivos virtuales y en distintos dispositivos reales.

Para probar en un dispositivo real hay que instalar sus **controladores** en el ordenador. Se pueden usar:

- Los que facilita el fabricante del dispositivo.
- Los genéricos de Google (*Google USB Driver*).
- Los universales ADB (*Android Debug Bridge*).

::: warning-box Modo desarrollador
Para depurar desde Android Studio es imprescindible activar las **opciones de desarrollador** y la **depuración USB** en el dispositivo real.
:::

::: tip-box Práctica
Realiza la [Actividad 4 — Probando en dispositivos reales](../ejercicios/04-dispositivo-real).
:::

## 4.5. Emuladores de terceros

Además del emulador de Android Studio existen emuladores de otras empresas. Algunos nacieron para ejecutar aplicaciones Android, sobre todo juegos, en sistemas operativos de escritorio.

### BlueStacks

Es el caso de [BlueStacks](https://www.bluestacks.com/es/index.html).

![Web de descarga e instalación de BlueStacks](/img/contenidos/up01/up01-p50-1.webp)

![Proceso de instalación de BlueStacks](/img/contenidos/up01/up01-p50-2.webp)

- No es necesario iniciar sesión en Google Play.
- Si hay problemas de ejecución, hay que cambiar el modo de gráficos a DirectX.
- Una vez cargado BlueStacks, basta con **arrastrar el archivo `.apk`** a la ventana principal.

![Instalación de un .apk en BlueStacks arrastrándolo a la ventana](/img/contenidos/up01/up01-p51-1.webp)

Al arrastrar el `.apk` se crea también un acceso directo en el escritorio, y la aplicación ya se puede ejecutar en el emulador.

![Aplicación Hello There ejecutándose en BlueStacks](/img/contenidos/up01/up01-p52-1.webp)

### Genymotion

Otro emulador muy conocido es [Genymotion](https://www.genymotion.com/). Sus desarrolladores afirman que es más rápido y eficiente que el emulador oficial. Puedes consultar este [manual de instalación y uso](https://desarrollador-android.com/desarrollo/herramientas/herramientas-de-ayuda/genymotion-emulador/).
