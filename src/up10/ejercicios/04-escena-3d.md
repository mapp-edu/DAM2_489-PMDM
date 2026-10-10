---
layout: doc
title: P4 · Escena 3D
sidebar: true
outline: [2, 3]
aside: true
---

# 📋 Práctica 4 — Escena 3D: materiales, física, luz y sonido

**Tipo**: proyecto Unity + vídeo · **Peso**: 20 % · **Entrega**: semana 5

## Objetivos

- Crear una escena 3D y distribuir sus objetos.
- Crear materiales que determinen el aspecto final de las superficies.
- Establecer propiedades físicas, utilizar cámaras, configurar la iluminación e incorporar sonido.

Contenido de referencia: [apartado 5](../contenidos/05-juegos-3d).

## Enunciado

Crea con la plantilla **Universal 3D** un proyecto llamado **Physics3DTuNombre**: un **minijuego 3D de habilidad** en el que el jugador controla una **bola** que rueda por un circuito con obstáculos hasta una meta, recogiendo objetos por el camino (al estilo de los clásicos juegos de laberinto con bola).

### Escena
- Circuito construido con **primitivas** o **ProBuilder**, organizado en la jerarquía con contenedores (`Entorno`, `Obstaculos`, `Recogibles`, `Camaras`, `Luces`).
- **Prefabs** para obstáculos y recogibles.
- Un **menú** sencillo o un **HUD** con tiempo y objetos recogidos, y un mensaje al llegar a la meta o al caer al vacío (reinicio).

### Materiales y texturas
Crea al menos **cinco materiales** con el shader **URP/Lit** que se diferencien claramente:
1. Un **metal** pulido (*Metallic* y *Smoothness* altos).
2. Un material **rugoso** con textura y **Normal Map** (piedra, madera…).
3. Un suelo con textura repetida mediante **Tiling**.
4. Un material **emisivo** (meta o recogibles).
5. Un material **transparente** (cristal o agua).

Usa texturas propias o con licencia CC0 y cítalas.

### Física
- La bola se mueve aplicando **fuerzas** a su `Rigidbody` en `FixedUpdate`, según la entrada del **Input System** (teclado y joystick en pantalla). **Opcional**: control con el **acelerómetro** del móvil.
- Al menos **tres superficies** con distinto **Physics Material**: hielo (fricción muy baja), goma (fricción alta) y rebotadora.
- Al menos **dos obstáculos físicos**: objetos dinámicos con masas distintas que la bola pueda derribar, una puerta con `HingeJoint`, un péndulo, una rampa…
- Detección de **triggers** para recogibles, meta y caída.

### Cámaras e iluminación
- **Cámara de tercera persona** con **Cinemachine** que siga a la bola, y una **segunda cámara** (vista general o de la meta) que tome el control con una transición al terminar el nivel (prioridades).
- Iluminación con una luz **Directional** y al menos **dos luces Point o Spot** de distinto color.
- Objetos del entorno marcados como **Static** e iluminación **horneada** (*Generate Lighting*).
- Un **Volume** de posprocesado con algún efecto (*Bloom* para los materiales emisivos).

### Sonido
- Música o sonido ambiente en 2D.
- Sonido **3D** (*Spatial Blend* = 1) en algún objeto del escenario (fuente, máquina) que se oiga más fuerte al acercarse.
- Efectos para: recoger, golpear obstáculos (con volumen proporcional a la fuerza del choque) y llegar a la meta.

## Entregable

Proyecto comprimido según las [normas comunes](./#normas-de-entrega-comunes) y **vídeo** en el que recorras el circuito y expliques: los cinco materiales, los materiales físicos y el script de movimiento, la configuración de cámaras y luces, y el sonido 3D.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA5.b/c** Crea los objetos y la escena<br>*Circuito, prefabs, jerarquía* | Escena incompleta o desordenada | Circuito jugable con prefabs y jerarquía organizada | Escala realista y distribución pensada para la jugabilidad | Escena cuidada y coherente visualmente |
| **RA5.d** Crea materiales para las superficies<br>*Cinco materiales* | Menos de cinco o sin diferencias apreciables | Los cinco materiales pedidos funcionan | Se explica el papel de cada propiedad y mapa de textura | Materiales coherentes con un estilo visual; texturas citadas |
| **RA5.e** Establece las propiedades físicas<br>*Rigidbody, Physics Materials, obstáculos* | La bola no responde bien o atraviesa objetos | Movimiento por fuerzas, tres superficies y dos obstáculos físicos | Masas, fricciones, *ForceMode* y detección continua justificados | Física estable y divertida |
| **RA5.g** Utiliza cámaras y configura la iluminación<br>*Cinemachine, luces, horneado* | Cámara fija o iluminación por defecto | Dos cámaras con transición, luces y horneado | Se justifican los tipos de luz, el modo de iluminación y la configuración de cámara | Ambientación conseguida con un uso razonable del posprocesado |
| **RA5.f** Incorpora sonido a los eventos<br>*Ambiente, sonido 3D, efectos* | Sin sonido 3D o faltan efectos | Ambiente, sonido 3D y los efectos pedidos | Configuración de atenuación y volumen proporcional al impacto explicada | Mezcla equilibrada que refuerza la experiencia |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
