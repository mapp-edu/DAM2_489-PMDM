---
layout: doc
title: UP10 — Videojuegos 2D y 3D con Unity
sidebar: true
outline: [2, 3]
aside: true
---

# 🎮 UP10 — Videojuegos 2D y 3D con Unity

En esta unidad das el salto de las aplicaciones a los **videojuegos**. Primero analizarás cómo está construido un juego por dentro y qué ofrece cada **motor de juegos**; después desarrollarás juegos **2D y 3D** con **Unity 6** y C#, trabajando objetos, escenas, materiales, física, sonido, cámaras e iluminación, y terminarás publicando tu propio juego en un **móvil Android**.

**Duración**: 6 semanas (24 horas) · **Motor**: Unity 6.3 LTS · **Lenguaje**: C#

## Contenidos

| Apartado | Qué vas a aprender |
|:---|:---|
| [1. Arquitectura de un juego y motores](./contenidos/01-arquitectura-motores) | Bucle de juego, bloques funcionales, 2D frente a 3D, animación, componentes y tipos de motores, comparativa, áreas de especialización, estudio de juegos existentes |
| [2. Unity 6, escenas y GameObjects](./contenidos/02-unity-escena) | Instalación, editor, GameObjects y componentes, jerarquía (representación lógica), coordenadas y cámaras (representación espacial), prefabs, assets |
| [3. Programación en C# con Unity](./contenidos/03-scripting-csharp) | `MonoBehaviour` y su ciclo de vida, Input System, movimiento y física, colisiones, corrutinas, GameManager, escenas y depuración |
| [4. Desarrollo de juegos 2D](./contenidos/04-juegos-2d) | Sprites, Tilemap, física 2D, Animator, Cinemachine, UI para móvil y sonido |
| [5. Desarrollo de juegos 3D](./contenidos/05-juegos-3d) | Escenas 3D, materiales y texturas PBR, propiedades físicas, cámaras, iluminación, fuentes de audio, animación e IA básica |
| [6. Fases, optimización y Android](./contenidos/06-fases-optimizacion-android) | Fases de desarrollo, GDD, pruebas, Profiler, optimización y despliegue en Android |
| [7. Fuentes de consulta](./contenidos/07-fuentes) | Documentación oficial, guías, cursos, bibliografía y recursos con licencia libre |

## Actividades

| Actividad | Descripción |
|:---|:---|
| [Plan de evaluación](./ejercicios/) | Instrumentos, pesos, relación con los criterios y temporalización |
| [P1 · Análisis de motores y de un juego](./ejercicios/01-analisis-motores) | Informe sobre arquitectura, motores y un proyecto de Unity |
| [P2 · Modificar un juego existente](./ejercicios/02-modificar-juego) | Cambios de jugabilidad, física y un objeto nuevo en un juego de ejemplo |
| [P3 · Juego de plataformas 2D](./ejercicios/03-juego-2d) | Dos niveles con tilemaps, animación, física y sonido |
| [P4 · Escena 3D](./ejercicios/04-escena-3d) | Minijuego de bola con materiales, física, luces, cámaras y audio 3D |
| [Proyecto final · Juego para Android](./ejercicios/05-proyecto-final) | Juego original con GDD, pruebas, optimización, APK y defensa |

## Resultados de aprendizaje trabajados

::: info-box RA4 · Selecciona y prueba motores de juegos analizando la arquitectura de juegos 2D y 3D
- **a)** Se han identificado los elementos que componen la arquitectura de un juego 2D y 3D.
- **b)** Se han analizado los componentes de un motor de juegos.
- **c)** Se han analizado entornos de desarrollo de juegos.
- **d)** Se han analizado diferentes motores de juegos, sus características y funcionalidades.
- **e)** Se han identificado los bloques funcionales de un juego existente.
- **f)** Se ha reconocido la representación lógica y espacial de una escena gráfica sobre un juego existente.
:::

::: info-box RA5 · Desarrolla juegos 2D y 3D sencillos utilizando motores de juegos
- **a)** Se ha establecido la lógica de un nuevo juego.
- **b)** Se han creado los objetos necesarios para el juego y definido sus características.
- **c)** Se han creado las escenas del juego y distribuido los objetos en las mismas.
- **d)** Se han creado materiales para determinar las propiedades finales de la superficie de un objeto.
- **e)** Se han establecido las propiedades físicas de los objetos.
- **f)** Se ha incorporado sonido a los diferentes eventos del juego.
- **g)** Se han utilizado cámaras y configurado la iluminación.
- **h)** Se han desarrollado e implantado juegos para dispositivos móviles.
- **i)** Se han realizado pruebas de funcionamiento y optimización de los juegos desarrollados.
- **j)** Se han documentado las fases de diseño y desarrollo de los juegos creados.
:::

## Contenidos básicos del currículo

| Bloque | Contenido | Apartado |
|:---|:---|:---:|
| Análisis de motores de juegos | Animación 2D y 3D | 1, 4, 5 |
| | Arquitectura del juego. Componentes | 1 |
| | Motores de juegos: tipos y utilización | 1 |
| | Áreas de especialización, librerías utilizadas y lenguajes de programación | 1 |
| | Componentes de un motor de juegos | 1 |
| | Librerías que proporcionan las funciones básicas de un motor 2D/3D. Clases | 1, 3 |
| | Estudio de juegos existentes | 1, 2 · P1 |
| | Aplicación de modificaciones sobre juegos existentes | 2 · P2 |
| Desarrollo de juegos 2D y 3D | Técnicas de programación 2D/3D | 3, 4, 5 |
| | Fases de desarrollo | 6 |
| | Componentes de los objetos: materiales y texturas. Propiedades físicas | 4, 5 |
| | Fuentes de audio. Propiedades | 4, 5 |
| | Cámaras e iluminación | 4, 5 |
| | Creación de escenas. Jerarquía de objetos | 2, 4, 5 |
| | Análisis de ejecución. Optimización del código | 6 |

## Requisitos previos

- Unity Hub y **Unity 6.3 LTS** con **Android Build Support** (ver [apartado 2.1](./contenidos/02-unity-escena#_2-1-instalacion)).
- Un editor de código: Visual Studio, Rider o VS Code.
- Un **móvil Android** con la depuración USB activada para el proyecto final.

::: note-box Autoría
Material de elaboración propia. Las fuentes consultadas se recogen en el apartado [7. Fuentes de consulta](./contenidos/07-fuentes).
:::

[Empezar →](./contenidos/01-arquitectura-motores)
