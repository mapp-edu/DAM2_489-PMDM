---
layout: doc
title: UP06 — Corrutinas, side effects y MVVM
sidebar: true
outline: [2, 3]
aside: true
---

# ⚙️ UP06 — Corrutinas, side effects y MVVM

Las aplicaciones reales hacen tareas que no pueden bloquear la interfaz: acceder a la red, a una base de datos, esperar un tiempo… En esta unidad aprenderás a ejecutarlas con **corrutinas**, a controlar los **efectos secundarios** de la recomposición y a organizar el código con la arquitectura **MVVM** usando `ViewModel` y `StateFlow`.

## Contenidos

| Apartado | Qué vas a aprender |
|:---|:---|
| [1. Corrutinas y Side Effects](./contenidos/01-corrutinas-side-effects) | Hilo principal, corrutinas, funciones `suspend`, *side effects*, `LaunchedEffect`, `rememberCoroutineScope`, *dispatchers* y `derivedStateOf` |
| [2. MVVM y StateFlow](./contenidos/02-mvvm-stateflow) | Arquitecturas MVC, MVP y MVVM, `StateFlow`, `ViewModel` y el caso práctico de Registro |
| [3. Caso práctico: libros](./contenidos/03-caso-practico-libros) | Aplicación completa con navegación y ViewModel compartido, búsqueda, favoritos y cancelación de corrutinas |

## Actividades

| Actividad | Descripción |
|:---|:---|
| [Boletín de actividades UP06](./ejercicios/) | Lista de elementos, Añadiendo favoritos y Entrenador personal (con rúbrica) |

## Resultados de aprendizaje trabajados

::: info-box RA1 · Aplica tecnologías de desarrollo para dispositivos móviles evaluando sus características y capacidades
- **a)** Se han analizado las limitaciones que plantea la ejecución de aplicaciones en los dispositivos móviles.
- **f)** Se ha analizado la estructura de aplicaciones existentes para dispositivos móviles identificando las clases utilizadas.
- **g)** Se han realizado modificaciones sobre aplicaciones existentes.
:::

::: info-box RA2 · Desarrolla aplicaciones para dispositivos móviles analizando y empleando las tecnologías y librerías específicas
- **a)** Se ha generado la estructura de clases necesaria para la aplicación.
- **b)** Se han analizado y utilizado las clases que modelan ventanas, menús, alertas y controles para el desarrollo de aplicaciones gráficas sencillas.
- **g)** Se han realizado pruebas de interacción usuario-aplicación para optimizar las aplicaciones desarrolladas a partir de emuladores.
- **i)** Se han documentado los procesos necesarios para el desarrollo de las aplicaciones.
:::

::: info-box RA3 · Desarrolla programas que integran contenidos multimedia analizando y empleando las tecnologías y librerías específicas
- **g)** Se han utilizado clases para reproducir contenidos multimedia.
:::

::: note-box Autoría
Material elaborado, con permiso del autor, a partir de las presentaciones de **Alejandro Torres Zahinos** (licencia original [CC BY-NC-ND 4.0](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.es)).
:::

[Empezar →](./contenidos/01-corrutinas-side-effects)
