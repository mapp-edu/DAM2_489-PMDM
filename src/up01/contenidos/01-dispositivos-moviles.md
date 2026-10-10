---
layout: doc
title: 1. Dispositivos móviles
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Dispositivos móviles

## 1.1. Introducción

En apenas 25 años el mundo ha cambiado de una manera difícil de imaginar. Podría decirse que en la década de 1990 y a principios de los 2000 el mundo **se volvió digital**; hoy en día, **el mundo es móvil**.

::: accent-box info 📊 Un dato
En España el teléfono móvil superó al PC como principal dispositivo de acceso a internet en **2017**.
:::

## 1.2. Historia

Desde la invención de los ordenadores se han creado dispositivos cada vez más pequeños y manejables, pero con muchas más prestaciones.

![ENIAC (1946) frente a un portátil de 2022](/img/contenidos/up01/up01-p03-1.webp)
*ENIAC (1946) frente a un portátil de 2022*

En la telefonía móvil ha ocurrido exactamente lo mismo: del Motorola DynaTAC 8000X de 1984 a los teléfonos de bolsillo de principios de los 2000.

![Motorola DynaTAC 8000X (1984) y la evolución de los móviles Nokia entre 1987 y 2000](/img/contenidos/up01/up01-p04-1.webp)
*Motorola DynaTAC 8000X (1984) y móviles Nokia a lo largo del tiempo*

Llegó un momento en el que **ordenadores y teléfonos móviles se fusionaron**. Las PDA (asistentes personales digitales) y los primeros *smartphones* estaban orientados sobre todo al uso de oficina.

![PDA Palm V (1999), BlackBerry Pearl 8100 (2006) y la presentación del iPhone (2007)](/img/contenidos/up01/up01-p06-1.webp)
*PDA Palm V (1999), BlackBerry Pearl 8100 (2006) e iPhone (2007)*

### La llegada del iPhone

En **2007 Steve Jobs**, cofundador de **Apple**, presentó el **iPhone**. Ya existían *smartphones* antes, pero el iPhone fue una revolución en el mundo de la telefonía móvil.

![Primer iPhone (2007)](/img/contenidos/up01/up01-p07-1.webp)

::: details ¿Por qué el iPhone fue una revolución?
- **Pantalla táctil capacitiva**: no necesitaba lápiz, a diferencia de las pantallas resistivas.
- **Precio**: unos 500 €, menos de la mitad que una BlackBerry.
- **Tamaño de pantalla**: más de 3 pulgadas.
- **Teclado completo (digital)**: el del LG Prada era un panel numérico.
- **Sistema operativo** diseñado en exclusiva.
- **WiFi**.
- **iPod** integrado: reproductor de música.
- **4 GB** de almacenamiento.
- **Pantalla muy resistente**.
:::

Como ocurre con cualquier sistema informático (PC, consolas…), en ese momento comenzó una **guerra** por ver quién **dominaba** el mercado de los sistemas operativos móviles.

![BlackBerry, Apple y Android](/img/contenidos/up01/up01-p09-1.webp)

Hoy la batalla tiene dos supervivientes. Según [StatCounter](https://gs.statcounter.com/os-market-share/mobile/worldwide), en agosto de 2026 la cuota de mercado mundial de sistemas operativos móviles era de un **67,61 % para Android** y un **32,36 % para iOS**: entre los dos suman el **99,97 %**.

![Cuota de mercado de iOS y Android](/img/contenidos/up01/up01-p10-1.webp)

::: tip-box Para pensar
¿Quién ha ganado la batalla? ¿Qué consecuencias tiene para un desarrollador que el mercado esté repartido entre solo dos plataformas?
:::

## 1.3. Comparativa: iOS frente a Android

| iOS | Android |
|:---|:---|
| Ecosistema **cerrado**: la plataforma lo tiene todo bajo control. | Muy **personalizable**. |
| Más optimizado, al tener el hardware controlado. | Menos optimizado, debido a las diferencias de hardware entre dispositivos. |
| **Poca fragmentación**: la última versión se mantiene disponible durante mucho tiempo (iOS 15 llegó hasta el iPhone 6s, de 2015). | **Mucha fragmentación**: las versiones disponibles para un dispositivo dependen de muchos factores, como sus características o el fabricante. |
| Al ser un ecosistema cerrado, no hay terceros que añadan capas al sistema operativo. | Los fabricantes suelen incorporar su propia capa sobre el sistema operativo. |
| Solo disponible en dispositivos Apple. | Cualquier fabricante puede comercializar un dispositivo con Android. |
| La App Store está muy supervisada: es casi imposible instalar aplicaciones maliciosas. | Es muy fácil publicar en Google Play y también se pueden instalar aplicaciones descargadas de internet (`.apk`). |
| Para **publicar** en la App Store se necesita un ordenador con macOS y el IDE Xcode. | No se necesita hardware ni software específico para publicar en Google Play o distribuir el `.apk`. |

## 1.4. Limitaciones de los dispositivos móviles

Al desarrollar aplicaciones para dispositivos móviles hay que tener en cuenta varios **factores determinantes**:

- Desconexión.
- Seguridad.
- Memoria.
- Consumo de batería.
- Almacenamiento.

### Desconexión

Un dispositivo móvil puede quedarse desconectado en cualquier momento: porque se agota la batería, porque se pierde la señal de datos o, simplemente, porque el usuario decide apagar las conexiones.

Si la aplicación utiliza datos de un servidor, debe tenerlo en cuenta y ofrecer un mecanismo que, en la medida de lo posible, asegure que **no se pierden datos** por este motivo.

### Seguridad

- Al ser dispositivos pequeños, son susceptibles de ser **sustraídos**.
- En ocasiones se conectan a **redes poco seguras** (WiFi gratuitas).
- Existe la posibilidad de que se hayan instalado **aplicaciones maliciosas** con acceso a los sensores del dispositivo.

### Memoria

Una de las principales desventajas de los dispositivos móviles es la dificultad, o incluso la imposibilidad, de **ampliar el hardware**. La cantidad de RAM ha aumentado en los últimos años y hoy son habituales capacidades de 8 GB, e incluso de 12 y 16 GB, pero sigue siendo un recurso que hay que administrar con cuidado.

### Consumo de batería

La batería es uno de los **recursos más preciados** del dispositivo. Aunque su capacidad ha aumentado, también lo ha hecho el tamaño de las pantallas, que suele ser el componente que más energía consume.

::: warning-box Buena práctica
Una aplicación móvil debe **liberar los recursos** que no necesita (sensores, conexiones, procesos en segundo plano…) para reducir el consumo de batería.
:::

### Almacenamiento

Muchos dispositivos permiten añadir una tarjeta microSD, pero normalmente ese espacio solo se usa para contenido multimedia (fotos, vídeos, audios). Las aplicaciones se instalan en el almacenamiento interno, que es un recurso muy valioso: cada aplicación debería **controlar su tamaño** para dejar espacio a las demás. Aun así, el almacenamiento ha crecido mucho y ya hay dispositivos de hasta 1 TB.

::: accent-box teal 🎯 Idea clave
Desarrollar para móvil implica asumir que la conexión puede fallar, que el dispositivo puede perderse y que memoria, batería y almacenamiento son limitados. Estas limitaciones condicionan el diseño de cualquier aplicación.
:::
