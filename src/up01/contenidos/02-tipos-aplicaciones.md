---
layout: doc
title: 2. Tipos de aplicaciones
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 2. Tipos de aplicaciones

Según las herramientas con las que se desarrollan, distinguimos dos grandes tipos de aplicaciones móviles:

::: accent-box primary left 📖 Definiciones
**Aplicación nativa** (*native app*): se desarrolla con las herramientas que proporciona el fabricante de la plataforma.

**Aplicación multiplataforma** (*cross-platform app*, *hybrid app*): se desarrolla con herramientas multipropósito que generan la aplicación para varias plataformas.
:::

Como en cualquier otro mercado de software, el de los dispositivos móviles es muy diverso y cambia **cada mes**. Los desarrolladores se enfrentan al reto de **elegir para qué plataforma desarrollar** y no existe una fórmula mágica que diga cuál usar: hay que conocer las diferencias para tomar la decisión con criterio.

## 2.1. Aplicaciones nativas

::: tabs

== Ventajas

- **Se aprovecha todo el potencial del hardware.** Al usar la plataforma del fabricante, el acceso a las funcionalidades y a los sensores está más optimizado.
- **Mayor optimización de las pantallas.** La plataforma nativa da más libertad para adaptar la interfaz a la enorme variedad de tamaños y relaciones de aspecto.
- **Mayor rendimiento.** Al estar desarrolladas expresamente para una plataforma, son muy rápidas y funcionan mejor.

== Desventajas

- **Incompatibilidad entre plataformas.** Una aplicación nativa solo funciona en el sistema operativo para el que se desarrolló: habrá que desarrollarla tantas veces como plataformas se quieran cubrir.
- **Incompatibilidad de versiones.** Las plataformas empujan a desarrollar para las últimas versiones, por lo que dar soporte a todas las versiones a veces es todo un desafío.

:::

## 2.2. Aplicaciones multiplataforma

::: tabs

== Ventajas

- **Reutilización de código.** La aplicación se desarrolla una sola vez (y se compila varias veces).
- **Compatibilidad de plataformas.** Las herramientas abstraen los detalles de cada plataforma.
- **Curva de aprendizaje corta.** No hace falta conocer los entresijos de cada plataforma.
- **Rentabilidad.** No se requiere hardware ni software específico, así que la inversión es menor.
- **Misma interfaz y experiencia de usuario** en todas las plataformas.

== Desventajas

- **Peor rendimiento.** Las herramientas añaden capas extra para traducir las funcionalidades a cada plataforma.
- **Problemas de compatibilidad.** Una misma funcionalidad puede no comportarse igual en todas las plataformas.
- **Menor flexibilidad.** Los procedimientos genéricos a veces impiden un desarrollo concreto.
- **Publicación costosa.** Publicar en los canales oficiales de cada plataforma puede exigir requisitos adicionales (por ejemplo, un Mac para publicar en la App Store).

:::

## 2.3. Cuadro resumen

| | Ventajas | Desventajas |
|:---|:---|:---|
| **Nativas** | Se aprovecha todo el potencial del hardware · Mayor optimización de las pantallas · Mayor rendimiento | Incompatibilidad entre plataformas · Incompatibilidad de versiones |
| **Multiplataforma** | Reutilización de código · Compatibilidad de plataformas · Curva de aprendizaje corta · Rentabilidad · Misma interfaz y experiencia de usuario | Peor rendimiento · Problemas de compatibilidad · Menor flexibilidad · Publicación costosa |

## 2.4. Tecnologías de desarrollo

### Aplicaciones nativas

| Plataforma destino | IDE | Lenguaje |
|:---|:---|:---|
| Android | Android Studio | Java, Kotlin |
| Android | App Inventor | Lenguaje de bloques |
| Apple | Xcode | Objective-C, Swift |

### Aplicaciones multiplataforma

| Compañía | Plataforma destino | Herramienta / IDE | Lenguaje |
|:---|:---|:---|:---|
| Microsoft | Android, iOS y Windows | Xamarin / .NET MAUI | C# |
| Unity Technologies | Escritorio, móviles, TV | Unity | C# |
| Google | Android, iOS, escritorio, web | Flutter | Dart |
| JetBrains | Android, iOS, escritorio, web | Kotlin Multiplatform (Android Studio) | Kotlin y Compose Multiplatform |
| Código abierto | Android, iOS, Windows, web | Ionic | JavaScript / TypeScript |
| Meta (Facebook) | Android, iOS, escritorio, TV | React Native | JavaScript / TypeScript |
| Apache | Android, iOS | Apache Cordova | HTML5, CSS3 y JS |

::: info-box En este módulo
Trabajaremos el desarrollo **nativo para Android** con **Kotlin** y **Jetpack Compose** en Android Studio. Lo que aprendas de Compose te servirá también para dar el salto a Compose Multiplatform.
:::

::: warning-box Xamarin
Microsoft finalizó el soporte de Xamarin en mayo de 2024; su sucesor es **.NET MAUI**, que mantiene C# como lenguaje.
:::
