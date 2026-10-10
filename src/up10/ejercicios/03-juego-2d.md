---
layout: doc
title: P3 · Juego de plataformas 2D
sidebar: true
outline: [2, 3]
aside: true
---

# 📋 Práctica 3 — Juego de plataformas 2D

**Tipo**: proyecto Unity + vídeo · **Peso**: 20 % · **Entrega**: semana 4

## Objetivos

- Establecer la lógica de un juego 2D sencillo.
- Crear los objetos y escenas del juego y distribuir los objetos en ellas.
- Configurar la física 2D, la animación y el sonido de los eventos.

Contenidos de referencia: [apartados 3](../contenidos/03-scripting-csharp) y [4](../contenidos/04-juegos-2d).

## Enunciado

Crea con la plantilla **Universal 2D** un proyecto llamado **Platformer2DTuNombre**: un juego de plataformas con **dos niveles**.

### Lógica del juego
Antes de empezar, escribe en `Assets/LOGICA.md`:
- Objetivo del juego, cómo se gana y cómo se pierde.
- Un **diagrama de estados** del juego (menú, jugando, pausa, nivel superado, fin de partida).
- Una tabla con los objetos del juego y sus propiedades (velocidad, vida, puntos, daño…).

### Escenas
| Escena | Contenido |
|:---|:---|
| **Menú** | Título, botón *Jugar* y mejor puntuación guardada con `PlayerPrefs`. En la parte inferior, tu nombre. |
| **Nivel 1** y **Nivel 2** | Construidos con **Tilemap** (fondo y suelo en *tilemaps* distintos) y con una meta que lleva al siguiente nivel. |
| **Fin de partida** | Puntuación, botón *Reintentar* y botón *Menú*. |

### Objetos obligatorios
- **Jugador** con `Rigidbody2D`, collider en forma de cápsula, movimiento horizontal y **salto solo desde el suelo**, controlado con el **Input System** (que funcione con teclado y con **botones en pantalla**).
- **Animaciones** del jugador con **Animator**: al menos *reposo*, *correr* y *saltar*, controladas por parámetros, y el sprite volteado según la dirección.
- **Coleccionables** (prefab trigger) que suman puntos.
- Al menos **un tipo de enemigo** (prefab) que patrulle entre dos puntos y quite vida al tocar al jugador.
- Un elemento con **física**: plataforma móvil, superficie resbaladiza (*Physics Material 2D*) o caja que se pueda empujar.
- **GameManager** con puntuación y vidas, y **HUD** con TextMeshPro adaptado a pantallas de móvil (*Canvas Scaler*).
- **Cámara** que siga al jugador con **Cinemachine** y no salga de los límites del nivel.

### Sonido
- **Música** de fondo con bucle.
- **Efectos** para al menos cuatro eventos: saltar, recoger, recibir daño y superar el nivel.

### Opcional
Fondo con **paralaje**, partículas al recoger monedas, pausa con `Time.timeScale`, un segundo tipo de enemigo.

## Entregable

Proyecto comprimido según las [normas comunes](./#normas-de-entrega-comunes) y **vídeo** en el que juegues los dos niveles y expliques: la jerarquía de una escena, el script del jugador, la configuración del Animator y la física.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA5.a** Establece la lógica del juego<br>*LOGICA.md y GameManager* | No hay documento o la lógica no se implementa | Objetivos, estados y objetos definidos e implementados | El diagrama de estados se corresponde con el código y se justifica | Lógica clara y equilibrada, con dificultad progresiva |
| **RA5.b** Crea los objetos y define sus características<br>*Jugador, enemigo, coleccionables, prefabs* | Faltan objetos obligatorios | Todos los objetos funcionan; prefabs y scripts propios | Componentes bien repartidos (dibujo separado de la lógica), valores configurables | Código limpio y comentado; objetos reutilizables |
| **RA5.c** Crea las escenas y distribuye los objetos<br>*Menú, dos niveles, fin de partida* | Faltan escenas o no se navega entre ellas | Las cuatro escenas funcionan y están en la lista de compilación | Jerarquía ordenada, *tilemaps* con colliders compuestos, *sorting layers* | Niveles bien diseñados y coherentes con la lógica |
| **RA5.e** Establece las propiedades físicas<br>*Rigidbody2D, colliders, materiales* | El jugador atraviesa el suelo o se queda atascado | Física correcta: salto desde el suelo, colisiones, elemento físico | Tipos de cuerpo, gravedad, fricción y capas elegidos y justificados | Control preciso y agradable |
| **RA5.f** Incorpora sonido a los eventos<br>*Música y efectos* | Sin sonido o solo música | Música y los cuatro efectos obligatorios | Uso correcto de `AudioSource`, `PlayOneShot` y volúmenes | Mezcla equilibrada (*Audio Mixer*), sonido que mejora la experiencia |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
