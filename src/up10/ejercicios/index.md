---
layout: doc
title: Actividades UP10
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP10 — Videojuegos 2D y 3D con Unity

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad. Los **assets de terceros** están permitidos solo si su licencia lo permite y se citan en los créditos.
:::

La unidad se evalúa con **cinco instrumentos** que, en conjunto, cubren **todos los criterios de evaluación de los RA4 y RA5**.

| Instrumento | Tipo | Peso | Semana de entrega |
|:---|:---|:---:|:---:|
| [Práctica 1 — Análisis de motores y de un juego](./01-analisis-motores) | Informe | 15 % | 2 |
| [Práctica 2 — Modificar un juego existente](./02-modificar-juego) | Proyecto Unity + vídeo | 10 % | 3 |
| [Práctica 3 — Juego de plataformas 2D](./03-juego-2d) | Proyecto Unity + vídeo | 20 % | 4 |
| [Práctica 4 — Escena 3D: física, luz y sonido](./04-escena-3d) | Proyecto Unity + vídeo | 20 % | 5 |
| [Proyecto final — Juego para Android](./05-proyecto-final) | GDD + proyecto + APK + defensa | 35 % | 6 |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->

::: warning-box Mínimo por instrumento
Como en el resto del módulo, **cada instrumento** debe alcanzar al menos el **nivel 2 (5 puntos)**. La nota de la unidad es la media ponderada de los cinco.
:::

## Relación entre criterios e instrumentos

### RA4 · Selecciona y prueba motores de juegos analizando la arquitectura de juegos 2D y 3D

| CE | Descripción | P1 | P2 | P3 | P4 | PF |
|:---:|:---|:---:|:---:|:---:|:---:|:---:|
| a | Se han identificado los elementos que componen la arquitectura de un juego 2D y 3D. | ✅ | | | | |
| b | Se han analizado los componentes de un motor de juegos. | ✅ | | | | |
| c | Se han analizado entornos de desarrollo de juegos. | ✅ | | | | |
| d | Se han analizado diferentes motores de juegos, sus características y funcionalidades. | ✅ | | | | |
| e | Se han identificado los bloques funcionales de un juego existente. | ✅ | ✅ | | | |
| f | Se ha reconocido la representación lógica y espacial de una escena gráfica sobre un juego existente. | ✅ | ✅ | | | |

### RA5 · Desarrolla juegos 2D y 3D sencillos utilizando motores de juegos

| CE | Descripción | P1 | P2 | P3 | P4 | PF |
|:---:|:---|:---:|:---:|:---:|:---:|:---:|
| a | Se ha establecido la lógica de un nuevo juego. | | | ✅ | | ✅ |
| b | Se han creado los objetos necesarios para el juego y definido sus características. | | ✅ | ✅ | ✅ | |
| c | Se han creado las escenas del juego y distribuido los objetos en las mismas. | | | ✅ | ✅ | |
| d | Se han creado materiales para determinar las propiedades finales de la superficie de un objeto. | | | | ✅ | |
| e | Se han establecido las propiedades físicas de los objetos. | | ✅ | ✅ | ✅ | |
| f | Se ha incorporado sonido a los diferentes eventos del juego. | | | ✅ | ✅ | |
| g | Se han utilizado cámaras y configurado la iluminación. | | | | ✅ | |
| h | Se han desarrollado e implantado juegos para dispositivos móviles. | | | | | ✅ |
| i | Se han realizado pruebas de funcionamiento y optimización de los juegos desarrollados. | | | | | ✅ |
| j | Se han documentado las fases de diseño y desarrollo de los juegos creados. | | | | | ✅ |

## Temporalización orientativa (6 semanas · 24 h)

| Semana | Contenidos | Trabajo |
|:---:|:---|:---|
| 1 | [1. Arquitectura y motores](../contenidos/01-arquitectura-motores) · [2. Unity, escenas y GameObjects](../contenidos/02-unity-escena) | Instalación de Unity. Inicio de la Práctica 1. |
| 2 | [3. Programación en C#](../contenidos/03-scripting-csharp) | Entrega P1. Práctica 2. |
| 3 | [4. Juegos 2D](../contenidos/04-juegos-2d) | Entrega P2. Práctica 3. |
| 4 | [5. Juegos 3D](../contenidos/05-juegos-3d) | Entrega P3. Práctica 4. Elección del proyecto final. |
| 5 | [6. Fases, optimización y Android](../contenidos/06-fases-optimizacion-android) | Entrega P4. GDD y desarrollo del proyecto final. |
| 6 | — | Pruebas, optimización, APK y **defensa** del proyecto final. |

## Normas de entrega comunes

Salvo que se indique otra cosa en la práctica:

1. Proyecto creado con **Unity 6.3 LTS** y llamado **NombrePracticaTuNombre**.
2. Antes de comprimir, **cierra Unity** y comprime **solo** las carpetas `Assets`, `Packages` y `ProjectSettings` en un `.zip` (no incluyas `Library`, `Temp`, `Logs` ni las compilaciones).
3. **Vídeo** de un máximo de 5 minutos y 100 MB en el que se vea el juego funcionando y expliques las partes pedidas en cada práctica.
4. Archivo **CREDITOS.md** dentro de `Assets` con la autoría y la licencia de cada asset de terceros.
5. Entrega en el aula virtual.
